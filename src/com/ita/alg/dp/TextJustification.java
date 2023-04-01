package com.ita.alg.dp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextJustification {
    @Setter
    @Getter
    private String text;

    private final String[] words;

    public TextJustification(String text) {
        this.text = text;
        words = text.split(" ");
    }

    public int[] getPartition(int length) {
        try {
            SplitRes res = getSplit(0, length);
            int[] result = new int[res.splitIndex.size() + 1];
            result[0] = 0;
            for (int i = 0; i < res.splitIndex.size(); i++) {
                result[i + 1] = res.splitIndex.get(i);
            }
            return result;
        } catch (NoSolutionException e) {
            return new int[0];
        }
    }

    private SplitRes getSplit(int i, int width) throws NoSolutionException {
        if (i >= words.length) {
            return SplitRes.builder().splitIndex(new ArrayList<>()).penalty(0).build();
        }
        if (words[i].length() > width) {
            throw new NoSolutionException();
        }
        int alreadyOccupied = words[i].length();
        int minIndex = -1;
        List<Integer> minSplit = new ArrayList<>();
        int minPenalty = Integer.MAX_VALUE;
        for (int j = i + 1; j <= words.length; j++) {
            if (j >= words.length) {
                return SplitRes.builder().splitIndex(new ArrayList<>()).penalty(0).build();
            }
            alreadyOccupied += (1 + words[j].length());
            if (alreadyOccupied > width) {
                break;
            }
            SplitRes tmp = getSplit(j, width);
            if (tmp.penalty + getPenalty(i, j, width) < minPenalty) {
                minPenalty = tmp.penalty;
                minIndex = j;
                minSplit = tmp.splitIndex;
            }
        }
        int finalMinIndex = minIndex;
        List<Integer> finalMinSplit = minSplit;
        return SplitRes.builder().splitIndex(new ArrayList<>() {{
            add(finalMinIndex);
            addAll(finalMinSplit);
        }}).penalty(minPenalty).build();
    }

    private int getPenalty(int i, int j, int width) {
        int len = 0;
        for (int k = i; k <= j; k++) {
            len += words[k].length();
        }
        int bar = (int) Math.pow(width - (len + j - i), 3);
        return bar > 0 ? bar : Integer.MAX_VALUE;
    }

    @Builder
    static class SplitRes {
        List<Integer> splitIndex;
        int penalty;
    }

    static class NoSolutionException extends Exception {

    }

    public List<String> fullJustify(int maxWidth) {
        List<String> res = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            List<String> middle = new ArrayList<>();
            int length = 0;
            if (words[index].length() >= maxWidth) {
                res.add(words[index++]);
                continue;
            }
            while (length + words[index].length() + (middle.size() > 1 ? middle.size() - 1 : 0) < maxWidth) {
                middle.add(words[index]);
                length += words[index++].length();
                if (index >= words.length) {
                    break;
                }
            }
            if (index >= words.length) {
                String lastLine = String.join(" ", middle);
                lastLine += String.join("", Collections.nCopies(maxWidth - lastLine.length(), " "));
                res.add(lastLine);
            } else {
                res.add(convert(middle, maxWidth));
            }
        }
        return res;
    }

    private String convert(List<String> input, int width) {
        if (input.size() == 1) {
            return input.get(0) + String.join("", Collections.nCopies(width - input.get(0).length(), " "));
        }
        int length = 0;
        for (String s : input) {
            length += s.length();
        }
        int spaceCount = width - length;
        int allocate = input.size() - 1;
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < spaceCount % allocate; i++) {
            sb.append(input.get(index++));
            sb.append(String.join("", Collections.nCopies(spaceCount / allocate + 1, " ")));
        }
        for (int i = 0; i < input.size() - 1 - spaceCount % allocate; i++) {
            sb.append(input.get(index++));
            sb.append(String.join("", Collections.nCopies(spaceCount / allocate, " ")));
        }
        sb.append(input.get(index));
        return sb.toString();
    }
}
