package basic.class12;

public class Code01_RobotWalk {

    public static int ways1(int N, int M, int K, int P) {
        if (N < 1 || M < 1 || M > N || P > N || P < 1) {
            return 0;
        }
        return process1(N, P, M, K);
    }

    private static int process1(int n, int p, int current, int restStep) {
        if (restStep == 0) {
            return current == p ? 1 : 0;
        }
        int right = 0;
        if (1 == current) {
            right = process1(n, p, current + 1, restStep - 1);
        }
        int left = 0;
        if (current == n) {
            left = process1(n, p, current - 1, restStep - 1);
        }
        if (1 < current && current < n) {
            left = process1(n, p, current - 1, restStep - 1);
            right = process1(n, p, current + 1, restStep - 1);
        }
        return left + right;
    }

    public static int ways2(int N, int M, int K, int P) {
        if (N < 1 || M < 1 || M > N || P > N || P < 1) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                dp[j][i] = -1;
            }
        }
        process2(N, P, M, K, dp);
        return dp[M][K];
    }

    private static int process2(int n, int p, int current, int restStep, int[][] dp) {
        if (dp[current][restStep] != -1) {
            return dp[current][restStep];
        }
        if (restStep == 0) {
            dp[current][0] = current == p ? 1 : 0;
            return dp[current][0];
        }

        int right = 0;
        if (1 == current) {
            right = process1(n, p, current + 1, restStep - 1);
        }
        int left = 0;
        if (current == n) {
            left = process1(n, p, current - 1, restStep - 1);
        }
        if (1 < current && current < n) {
            left = process1(n, p, current - 1, restStep - 1);
            right = process1(n, p, current + 1, restStep - 1);
        }
        dp[current][restStep] = left + right;
        return dp[current][restStep];
    }

    public static int ways3(int N, int M, int K, int P) {
        if (N < 1 || M < 1 || M > N || P > N || P < 1) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[P][0] = 1;
        for (int column = 1; column <= K; column++) {
            for (int row = 0; row <= N; row++) {
                if (row == N) {
                    dp[row][column] = dp[row - 1][column - 1];
                }
                if (row == 1) {
                    dp[row][column] = dp[row + 1][column - 1];
                }
                if (1 < row && row < N) {
                    dp[row][column] = dp[row - 1][column - 1] + dp[row + 1][column - 1];
                }
            }
        }
        return dp[M][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5));
        System.out.println(ways2(7, 4, 9, 5));
        System.out.println(ways3(7, 4, 9, 5));
//        System.out.println(ways4(7, 4, 9, 5));
//        System.out.println(ways5(7, 4, 9, 5));
    }

}
