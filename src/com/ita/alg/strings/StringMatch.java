package com.ita.alg.strings;

import org.apache.commons.lang.StringUtils;

public class StringMatch {

    public static int getFirst(Method m, String text, String target) {
        if (target.length() > text.length()) {
            return -1;
        }
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(target)) {
            return -1;
        }
        switch (m) {
            case TEDIOUS -> {
                return getFirstTedious(text, target);
            }
            case KARP_RABIN -> {
                return getFirstKarpRabin(text, target);
            }
        }
        return -1;
    }

    private static int getFirstTedious(String text, String target) {
        for (int i = 0; i <= text.length() - target.length(); i++) {
            if (target.equals(text.substring(i, i + target.length()))) {
                return i;
            }
        }
        return -1;
    }

    private static final int base = 256;
    private static final int prime = 101;

    private static int getHash(String s) {
        int hash = 0;
        for (Character c : s.toCharArray()) {
            hash = (hash * base % prime + c) % prime;
        }
        return hash;
    }

    private static int getHashIncr(int originalStringHash, char rm, char add, int offset) {
        return ((originalStringHash + prime - rm * offset) * base % prime + add) % prime;
    }

    private static int getFirstKarpRabin(String text, String target) {
        int targetHash = getHash(target);
        int initHash = getHash(text.substring(0, target.length()));
        if (targetHash == initHash && target.equals(text.substring(0, target.length()))) {
            return 0;
        }
        if (text.length() == target.length()) {
            return -1;
        }
        int offset = 1;
        for (int i = 0; i < target.length() - 1; i++) {
            offset = offset * base % prime;
        }
        for (int i = 1; i <= text.length() - target.length(); i++) {
            int newHash = getHashIncr(initHash, text.charAt(i - 1), text.charAt(i + target.length() - 1), offset);
            if (targetHash == newHash && target.equals(text.substring(i, i + target.length()))) {
                return i;
            }
            initHash = newHash;
        }
        return -1;
    }

}

enum Method {
    TEDIOUS, KARP_RABIN;
}
