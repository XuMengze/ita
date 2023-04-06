package com.ita.alg.backtrack;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {

    /**
     * 现在有一个只包含数字的字符串，将该字符串转化成IP地址的形式，返回所有可能的情况。
     * 例如：
     * 给出的字符串为"25525522135",
     * 返回["255.255.22.135", "255.255.221.35"]. (顺序没有关系)
     */

    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> storage = new ArrayList<>();
        if (s.isEmpty()) {
            return storage;
        }
        get(s, "", 0, 0, storage);
        return storage;
    }

    private void get(String original, String already, int step, int start, List<String> storage) {
        if (start >= original.length()) {
            return;
        }
        if (step == 3) {
            if (original.charAt(start) == '0') {
                if (start == original.length() - 1) {
                    storage.add((already + ".0").substring(1));
                }
                return;
            }
            if (Integer.parseInt(original.substring(start)) <= 255) {
                storage.add((already + "." + original.substring(start)).substring(1));
                return;
            }
            return;
        }
        if (original.charAt(start) == '0') {
            get(original, already + ".0", step + 1, start + 1, storage);
            return;
        }
        get(original, already + "." + original.charAt(start), step + 1, start + 1, storage);
        if (start + 2 <= original.length()) {
            get(original, already + "." + original.substring(start, start + 2), step + 1, start + 2, storage);
        }
        if (start + 3 <= original.length() && Integer.parseInt(original.substring(start, start + 3)) <= 255) {
            get(original, already + "." + original.substring(start, start + 3), step + 1, start + 3, storage);
        }
    }

    public static void main(String[] args) {
        System.out.println(new RestoreIpAddresses().restoreIpAddresses("0000"));
    }
}
