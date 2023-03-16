package com.ita.alg.dp;

public class Knapsack {
    private final int[] value;
    private final int[] weight;
    private final int[] amountLimit;
    private final int packSize;
    private final int itemCount;

    public Knapsack(int[] value, int[] weight, int[] amountLimit, int packSize) {
        this.value = value;
        this.weight = weight;
        this.amountLimit = amountLimit;
        this.packSize = packSize;
        this.itemCount = weight.length;
    }

    public int maxValue() {
        return buy(0, packSize);
    }

    private int buy(int i, int leftPackSize) {
        if (i >= itemCount) {
            return 0;
        }
        if (leftPackSize < weight[i]) {
            return buy(i + 1, leftPackSize);
        }
        int maxValue = buy(i + 1, leftPackSize);
        for (int j = 1; j < Math.min(leftPackSize / weight[i], amountLimit[i]); j++) {
            int afterBuyValue = j * value[i] + buy(i + 1, leftPackSize - weight[i] * j);
            if (afterBuyValue > maxValue) {
                maxValue = afterBuyValue;
            }
        }
        return maxValue;
    }

    private int buyDP() {
        int[][] dp = new int[packSize][itemCount];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[itemCount];
        }

        for (int i = 0; i < packSize; i++) {
            dp[i][itemCount - 1] = Math.min(amountLimit[itemCount - 1], packSize / weight[itemCount]) * value[itemCount - 1];
        }

        for (int i = itemCount - 2; i >= 0; i++) { // goods
            for (int j = 0; j < packSize; j++) { // pack size
                int max = dp[i + 1][j];
                for (int k = 1; k < Math.min(amountLimit[i], j / weight[i]); k++) { // how many to buy
                    max = Math.max(max, value[i] * k + dp[i + 1][j - k * weight[i]]);
                }
                dp[i][j] = max;
            }
        }
        return dp[0][packSize - 1];
    }
}
