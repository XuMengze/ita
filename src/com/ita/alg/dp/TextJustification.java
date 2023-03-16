package com.ita.alg.dp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
}
