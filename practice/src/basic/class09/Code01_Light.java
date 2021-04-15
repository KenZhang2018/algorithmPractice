package basic.class09;

import java.util.HashSet;

public class Code01_Light {

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        char[] chars = road.toCharArray();
        HashSet<Integer> set = new HashSet<>();
        int min = f(chars, 0, set);
        return min;
    }

    private static int f(char[] chars, int index, HashSet<Integer> set) {
        int ans = Integer.MAX_VALUE;
        if (chars.length == index) {
            boolean isValid = isValid(chars, set);
            return isValid ? set.size() : ans;
        }
        if (chars[index] == 'X') {
            int f1 = f(chars, index + 1, set);
            ans = Math.min(ans, f1);
        } else if (chars[index] == '.') {
            // 不放灯
            int f2 = f(chars, index + 1, set);
            ans = Math.min(ans, f2);
            // 放灯
            set.add(index);
            int f3 = f(chars, index + 1, set);
            ans = Math.min(ans, f3);
            set.remove(index);
        }
        return ans;
    }

    private static boolean isValid(char[] chars, HashSet<Integer> set) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '.') {
                if (set.contains(i - 1) || set.contains(i) || set.contains(i + 1)) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public static int minLight2(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        char[] chars = road.toCharArray();
        int min = 0, index = 0;
        while (index < chars.length) {
            if (chars[index] == 'X') {
                index++;
            } else if (chars[index] == '.'){
                min++;
                if (index + 2 < chars.length && chars[index + 1] == '.' && chars[index + 2] == '.') {
                    index += 3;
                } else if (index + 1 < chars.length && chars[index + 1] == '.') {
                    index += 2;
                } else {
                    index++;
                }
            }
        }
        return min;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }


}
