package ali;

public class PrintAfter {

    public static void main(String[] args) {
        for (int i = 0; i < 18; i++) {
            print(i);
            System.out.println("===============================");
        }
    }

    private static void print(int n) {
        if (n <= 0) return;
        if (n > 17) {
            n = 17;
        }
        if (n % 2 == 0) {
            n = n - 1;
        }
        char[][] dp = new char[n][n];
        // 初始占位符
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = '_';
            }
        }
        // 填充正方形的上边
        for (int i = 0; i <= n / 2; i++) {
            if (i % 2 == 0) {
                for (int j = i; j < n - i; j++) {
                    dp[i][j] = '*';
                }
            }
        }
        // 旋转边
        for (int i = 0; i < n / 2; i = i + 2) {
            for (int j = i + 1; j < n - i; j++) {
                dp[j][n - i - 1] = dp[i][j];
                dp[n - i - 1][n - j - 1] = dp[j][n - i - 1];
                dp[n - j - 1][i] = dp[n - i - 1][n - j - 1];
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(new String(dp[i]));
        }
    }

}
