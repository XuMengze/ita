package com.ita.alg.divide_and_conquer;

public class ConvexHull {
    public Coordinate[] getConvexHull(Coordinate[] input, boolean left) {
        return null;
    }

    // L start from the rightest point anti-clockwise
    // R start from the leftest point clockwise
    private Coordinate[] merge(Coordinate[] L, Coordinate[] R) {
        double middle = (double) (L[0].X + R[0].X) / 2;
        double baseCase = new Tangent(L[0], R[0]).getX(middle);
        int left = 0;
        int right = 0;
        Tangent rightMove = new Tangent(L[left], R[right + 1]);
        Tangent leftMove = new Tangent(L[left + 1], R[right]);
        while (rightMove.getX(middle) > baseCase || leftMove.getX(middle) > baseCase) {
            if (rightMove.getX(middle) > baseCase) {
                right = (right + 1) % R.length;
                baseCase = rightMove.getX(middle);
                rightMove = new Tangent(L[left], R[right]);
            } else {
                left = (left + 1) % L.length;
                baseCase = leftMove.getX(middle);
                leftMove = new Tangent(L[left], R[right]);
            }
        }

        return null;
    }

    static class Coordinate {
        int X;
        int Y;
    }

    static class Tangent {
        double k;
        double b;

        public Tangent(Coordinate point1, Coordinate point2) {
            if (point1.X == point2.X && point1.Y == point2.Y) {
                throw new RuntimeException("not a line");
            }
            if (point1.X == point2.X) {
                k = Double.MAX_VALUE;
                return;
            }
            k = (double) (point2.Y - point1.Y) / (double) (point2.X) - point1.X;
            b = point1.Y - k * point1.X;
        }

        public double getX(double x) {
            if (k == Double.MAX_VALUE) {
                return Double.MAX_VALUE;
            }
            return k * x + b;
        }
    }
}
