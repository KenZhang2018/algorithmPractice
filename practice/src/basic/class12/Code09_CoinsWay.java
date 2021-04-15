package basic.class12;

public class Code09_CoinsWay {

    // arr中都是正数且无重复值，返回组成aim的方法数
    public static int ways1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int success = 0;
        int times = rest / arr[index];
        for (int i = 0; i <= times; i++) {
            success += process(arr, index + 1, rest - arr[index] * i);
        }
        return success;
    }

    // arr中都是正数且无重复值，返回组成aim的方法数
    public static int ways2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = -1;
            }
        }
        process2(arr, 0, aim, dp);
        return dp[0][aim];
    }

    private static int process2(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (index == arr.length) {
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int success = 0;
        int times = rest / arr[index];
        for (int i = 0; i <= times; i++) {
            success += process(arr, index + 1, rest - arr[index] * i);
        }
        dp[index][rest] = success;
        return dp[index][rest];
    }

    // arr中都是正数且无重复值，返回组成aim的方法数
    public static int ways3(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int success = 0;
                int times = rest / arr[index];
                for (int i = 0; i <= times; i++) {
                    success += dp[index + 1][rest - arr[index] * i];
                }
                dp[index][rest] = success;
            }
        }
        return dp[0][aim];
    }

    // arr中都是正数且无重复值，返回组成aim的方法数
    public static int ways4(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int success = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    success += dp[index][rest - arr[index]];
                }
//                int times = rest / arr[index];
//                for (int i = 0; i <= times; i++) {
//                    success += dp[index + 1][rest - arr[index] * i];
//                }
                dp[index][rest] = success;
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = { 5, 10,50,100 };
        int sum = 1000;
        System.out.println(ways1(arr, sum));
        System.out.println(ways2(arr, sum));
        System.out.println(ways3(arr, sum));
        System.out.println(ways4(arr, sum));
    }

}
