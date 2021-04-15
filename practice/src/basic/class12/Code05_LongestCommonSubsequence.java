package basic.class12;

// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/
public class Code05_LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        return process(chars1, chars2, 0, 0);
    }

    private static int process(char[] chars1, char[] chars2, int index1, int index2) {
        if (chars1.length == index1) {
            return 0;
        }
        if (chars2.length == index2) {
            return 0;
        }
        int equals = 0, notEquals = 0;
        if (chars1[index1] == chars2[index2]) {
            equals = process(chars1, chars2, index1 + 1, index2 + 1) + 1;
        } else {
            int p1 = process(chars1, chars2, index1 + 1, index2);
            int p2 = process(chars1, chars2, index1, index2 + 1);
            int p3 = process(chars1, chars2, index1 + 1, index2 + 1);
            notEquals = Math.max(Math.max(p1, p2), p3);
        }
        return Math.max(equals, notEquals);
    }


    public static int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int[][] dp = new int[str1.length][str2.length];
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
            }
            if (i > 0 && dp[i - 1][0] == 1) {
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < str2.length; i++) {
            if (str1[0] == str2[i]) {
                dp[0][i] = 1;
            }
            if (i > 0 && dp[0][i - 1] == 1) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                int equals = 0, notEquals = 0;
                if (str1[i] == str2[j]) {
                    equals = dp[i - 1][j - 1] + 1;
                } else {
                    notEquals = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
                dp[i][j] = Math.max(equals, notEquals);
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }

    public static void main(String[] args) {
        int longestCommonSubsequence2 = longestCommonSubsequence2("abcde", "ace");
        System.out.println(longestCommonSubsequence2);
    }

}
