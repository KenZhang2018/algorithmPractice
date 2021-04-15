package basic.class11;

public class Code06_ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int result = process(chars, 0);
        return result;
    }

    private static int process(char[] chars, int index) {
        if (chars.length == index) {
            return 1;
        }
        int p1 = 0, p2 = 0;
        if (chars[index] >= '1' && chars[index] <= '9') {
            p1 = process(chars, index + 1);
        }
        if ((index + 1 < chars.length)) {
            if (chars[index] == '1' && (chars[index + 1] >= '0' && chars[index + 1] <= '9')) {
                p2 = process(chars, index + 2);
            } else if (chars[index] == '2' && (chars[index + 1] >= '0' && chars[index + 1] <= '6')) {
                p2 = process(chars, index + 2);
            }
        }
        return p1 + p2;
    }

    public static int dp(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[chars.length] = 1;
        for (int index = chars.length - 1; index >= 0 ; index--) {
            int p1 = 0, p2 = 0;
            if (chars[index] >= '1' && chars[index] <= '9') {
                p1 = dp[index + 1];
            }
            if ((index + 1 < chars.length)) {
                if (chars[index] == '1' && (chars[index + 1] >= '0' && chars[index + 1] <= '9')) {
                    p2 = dp[index + 2];
                } else if (chars[index] == '2' && (chars[index + 1] >= '0' && chars[index + 1] <= '6')) {
                    p2 = dp[index + 2];
                }
            }
            dp[index] = p1 + p2;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("2132082"));
        System.out.println(dp("2132082"));
    }


}
