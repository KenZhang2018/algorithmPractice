package trainingcamp001.class04;

public class Code03_IsRotation {

    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length() || a.length() == 0) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder(a);
        String doubleA = stringBuilder.append(a).toString();
        int index = getIndexOf(doubleA, b);
        return index != -1 ? true : false;
    }

    public static int getIndexOf(String s, String m) {
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int[] next = getNextArray(match);
        int sIndex = 0, mIndex = 0;
        while (sIndex < s.length() && mIndex < m.length()) {
            if (str[sIndex] == match[mIndex]) {
                sIndex++;
                mIndex++;
            } else if (mIndex == 0) {
                sIndex++;
            } else if (str[sIndex] == match[next[mIndex]]) {
                sIndex++;
                mIndex = next[mIndex] + 1;
            } else {
                mIndex = next[mIndex];
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
            if (match[i] == match[next[i - 1]]) {
                next[i] = next[i - 1] + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));

    }


}
