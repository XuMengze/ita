package com.ita.alg.sort;

import java.util.ArrayList;

public class MergeInterval {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if(intervals.size() == 0){
            return new ArrayList<>();
        }
        intervals.sort((s1, s2) -> {
            if (s1.start < s2.start) {
                return -1;
            } else if (s1.start > s2.start) {
                return 1;
            } else {
                return -Integer.compare(s1.end, s2.end);
            }
        });
        ArrayList<Interval> res = new ArrayList<>();
        res.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            if (res.get(res.size() - 1).end < intervals.get(i).start) {
                res.add(intervals.get(i));
            } else {
                Interval interval = res.get(res.size() - 1);
                interval.end = Math.max(intervals.get(i).end, interval.end);
                res.set(res.size() - 1, interval);
            }

        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<Interval> res = new MergeInterval().merge(new ArrayList<>() {{
            add(new Interval(10, 30));
            add(new Interval(20, 60));
            add(new Interval(80, 100));
            add(new Interval(150, 180));
        }});
        System.out.println(res);
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}