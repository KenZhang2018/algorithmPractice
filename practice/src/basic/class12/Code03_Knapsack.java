package basic.class12;

public class Code03_Knapsack {

    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag, 0);
    }

    private static int process(int[] w, int[] v, int index, int rest,  int totalV) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return totalV;
        }
        int p1 = process(w, v,  index + 1, rest, totalV);
        int p2 = process(w, v, index + 1, rest - w[index], totalV + v[index]);
        return Math.max(p1, p2);
    }

    public static int dpWay(int[] w, int[] v, int bag) {
        if (bag < 0) {
            return -1;
        }
        int[][] dp = new int[w.length + 1][bag + 1];
        for (int row = w.length - 1; row  >= 0; row--) {
            for (int column = 0; column <= bag; column++) {
                int p1 = dp[row + 1][ column];
                int p2 = 0;
                if (column - w[row] >= 0) {
                    p2 = dp[row + 1][ column - w[row]] + v[row];
                }
                dp[row][column] = Math.max(p1, p2);
            }

        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }

}
