package basic.class12;

// 题目
// 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
// 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
// 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
// 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
// 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
// 四个参数：arr, n, a, b
// 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
public class Code06_Coffee {

    // 方法一：暴力尝试方法
    public static int minTime1(int[] arr, int n, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, a, b, 0, arr[0]);
    }

    private static int process(int[] arr, int a, int b, int index, int washTime) {
        if (arr.length - 1 == index) {
            int maxTime = Math.max(arr[index], washTime);
            return Math.min(arr[index] + b, maxTime + a);
        }
        // 选择洗
        int wash = Math.max(arr[index], washTime) + a;
        int other = process(arr, a, b, index + 1, wash);
        int minWash = Math.max(wash, other);

        // 选择不洗
        int notWash = arr[index] + b;
        int other2 = process(arr, a, b, index + 1, washTime);
        int minNotWash = Math.max(notWash, other2);

        return Math.min(minWash, minNotWash);
    }

    private static int dp(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (b < a) {
            return arr[arr.length - 1] + b;
        }
        int washEndTime = 0;
        for (int i = 0; i < arr.length; i++) {
            washEndTime = Math.max(washEndTime, arr[i]) + a;
        }
        washEndTime = washEndTime - a;
        int[][] dp = new int[arr.length][washEndTime + 1];
        for (int i = 0; i < washEndTime; i++) {
            dp[arr.length - 1][i] = Math.min(Math.max(arr[arr.length  - 1], washEndTime) + a, arr[0] + b);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = 0; j <= washEndTime; j++) {
                int nextWashTime = Math.max(arr[i], j) + a;
                int washTime = Math.max(nextWashTime, nextWashTime <= washEndTime ? dp[i + 1][nextWashTime] : 0);
                int notWashTime = Math.max(arr[i] + b, dp[i + 1][j]);
                dp[i][j] = Math.min(washTime, notWashTime);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] test = {1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15};
        int a1 = 3;
        int b1 = 10;
        System.out.println(process(test, a1, b1, 0, 0));
        System.out.println(dp(test, a1, b1));

    }


}
