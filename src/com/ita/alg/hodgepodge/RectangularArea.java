package com.ita.alg.hodgepodge;

import java.util.List;
import java.util.stream.Stream;

public class RectangularArea {
    // 左下+右上 四个点
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        return (ay2 - ay1) * (ax2 - ax1) + (by2 - by1) * (bx2 - bx1) - computeOverlap(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
    }

    public int computeOverlap(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        List<Integer> xes = Stream.of(ax1, ax2, bx1, bx2).sorted().toList();
        if (xes.get(1) == ax2 && xes.get(2) == bx1) {
            return 0;
        }
        if (xes.get(1) == bx2 && xes.get(2) == ax1) {
            return 0;
        }
        List<Integer> yes = Stream.of(ay2, ay1, by2, by1).sorted().toList();
        if (yes.get(1) == ay2 && yes.get(2) == by1) {
            return 0;
        }
        if (yes.get(1) == by2 && yes.get(2) == ay1) {
            return 0;
        }
        return (xes.get(2) - xes.get(1)) * (yes.get(2) - yes.get(1));
    }
}
