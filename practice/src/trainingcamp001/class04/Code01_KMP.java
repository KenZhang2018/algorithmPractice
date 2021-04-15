package trainingcamp001.class04;

public class Code01_KMP {

    // O(N)
    public static int getIndexOf(String s, String m) {
        if (s == null || s.length() == 0 || m == null || m.length() == 0 || s.length() < m.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int[] next = getNextArray(match);
        int sIndex = 0, mIndex = 0;
        while (sIndex < s.length() && mIndex < m.length()) {
            if (str[sIndex] == match[mIndex]) {
                sIndex++;
                mIndex++;
            } else if (mIndex != 0 && str[sIndex] == match[next[mIndex]]) {
                sIndex++;
                mIndex = next[mIndex] + 1;
            } else if (mIndex != 0 && str[sIndex] != match[next[mIndex]]){
                mIndex = next[mIndex];
            } else if (mIndex == 0) {
                sIndex++;
            }
        }
        return mIndex == m.length() ? sIndex - m.length() : -1;

    }

    private static int[] getNextArray(char[] match) {
        int[] next = new int[match.length];
        next[0] = -1;
        if (match.length == 1) {
            return next;
        }
        next[1] = 0;
        for (int i = 2; i < match.length; i++) {
            boolean isEquals = match[i - 1] == match[next[i - 1]];
            next[i] = isEquals ? next[i - 1] + 1 : 0;
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
