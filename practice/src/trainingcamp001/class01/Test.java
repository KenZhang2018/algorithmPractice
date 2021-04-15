package trainingcamp001.class01;

public class Test {

    public static int ways1(int x, int y, int k) {
        return f(x, y, k);
    }

    private static int f(int x, int y, int k) {
        if (k == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        if (x < 0 || x > 9) {
            return 0;
        }
        if (y < 0 || y > 8) {
            return 0;
        }
        int f1 = f(x - 2, y + 1, k - 1);
        int f2 = f(x - 1, y + 2, k - 1);
        int f3 = f(x + 1, y + 2, k - 1);
        int f4 = f(x + 2, y + 1, k - 1);
        int f5 = f(x + 2, y - 1, k - 1);
        int f6 = f(x + 1, y - 2, k - 1);
        int f7 = f(x - 1, y - 2, k - 1);
        int f8 = f(x - 2, y - 1, k - 1);

        return f1 + f2 + f3 + f4 + f5 + f6 + f7 + f8;
    }

    public static int ways2(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];// 0~k
        dp[0][0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < 10; j++) {
                for (int l = 0; l < 9; l++) {
                    int f1 = getValue(dp, j - 2, l + 1, i - 1);
                    int f2 = getValue(dp, j - 1, l + 2, i - 1);
                    int f3 = getValue(dp, j + 1, l + 2, i - 1);
                    int f4 = getValue(dp, j + 2, l + 1, i - 1);
                    int f5 = getValue(dp, j + 2, l - 1, i - 1);
                    int f6 = getValue(dp, j + 1, l - 2, i - 1);
                    int f7 = getValue(dp, j - 1, l - 2, i - 1);
                    int f8 = getValue(dp, j - 2, l - 1, i - 1);
                    dp[j][l][i] = f1 + f2 + f3 + f4 + f5 + f6 + f7 + f8;
                }
            }
        }
        return dp[x][y][k];
    }

    private static int getValue(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x > 9) {
            return 0;
        }
        if (y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][k];
    }


    public static void main(String[] args) {
        int x = 6;
        int y = 8;
        int k = 10;
        System.out.println(ways1(x, y, k));
        System.out.println(ways2(x, y, k));
//        System.out.println(ways3(x, y, k));

    }

}
