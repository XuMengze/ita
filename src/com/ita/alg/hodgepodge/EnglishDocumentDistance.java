package com.ita.alg.hodgepodge;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class EnglishDocumentDistance {
    public EnglishDocumentDistance() {
    }

    String document1, document2;
    Set<String> stopWords = new HashSet<>();

    String punctuationRegex = "[,.?!]";

    public double calculate() {
        // overflow ? efficiency ?
        double res = 0;
        WordCountResult wordCountResult = wordCount();
        for (String s : wordCountResult.words) {
            res += wordCountResult.document1WordCount.get(s) * wordCountResult.document2WordCount.get(s);
        }
        return res / (wordCountResult.document1Length * wordCountResult.document2Length);
    }

    public WordCountResult wordCount() {
        Map<String, Integer> wordCount1 = new HashMap<>();
        Integer doc1Len = 0;
        Map<String, Integer> wordCount2 = new HashMap<>();
        Integer doc2Len = 0;
        Set<String> words = new HashSet<>();
        for (String s : document1.split(" ")) {
            if (stopWords.contains(s)) {
                continue;
            }
            s = s.toLowerCase().replaceAll(punctuationRegex, "");
            words.add(s);
            doc1Len++;
            Integer ignore = wordCount1.containsKey(s) ? wordCount1.put(s, wordCount1.get(s) + 1) : wordCount1.put(s, 1);
            if (!wordCount2.containsKey(s)) {
                wordCount2.put(s, 0);
            }
        }
        for (String s : document2.split(" ")) {
            if (stopWords.contains(s)) {
                continue;
            }
            s = s.toLowerCase().replaceAll(punctuationRegex, "");
            words.add(s);
            doc2Len++;
            Integer ignore = wordCount2.containsKey(s) ? wordCount2.put(s, wordCount2.get(s) + 1) : wordCount2.put(s, 1);
            if (!wordCount1.containsKey(s)) {
                wordCount1.put(s, 0);
            }
        }
        return new WordCountResult(words, wordCount1, doc1Len, wordCount2, doc2Len);
    }
}

@Getter
@Setter
class WordCountResult {
    Set<String> words;
    Map<String, Integer> document1WordCount;
    Integer document1Length;
    Map<String, Integer> document2WordCount;
    Integer document2Length;

    public WordCountResult(Set<String> words, Map<String, Integer> document1WordCount, Integer document1Length, Map<String, Integer> document2WordCount, Integer document2Length) {
        this.words = words;
        this.document1WordCount = document1WordCount;
        this.document1Length = document1Length;
        this.document2WordCount = document2WordCount;
        this.document2Length = document2Length;
    }
}