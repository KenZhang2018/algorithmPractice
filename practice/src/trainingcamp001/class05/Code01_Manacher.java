package trainingcamp001.class05;

public class Code01_Manacher {

    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] manacherChars = getManacherString(s);
        int[] radiusArray = new int[manacherChars.length];
        int far = -1, farIndex = -1;
        int max = 0;
        for (int index = 0; index < manacherChars.length; index++) {
            int radius = 1;
            if (far < index) {
                // 暴力扩
                radius = extend(manacherChars, index, radius);
            } else if (index <= far){
                int symmetryIndex = farIndex - (index - farIndex);
                int curFar = index + radiusArray[symmetryIndex] - 1;
                radius = radiusArray[symmetryIndex];
                if (curFar == far) {
                    // 暴力扩
                    radius = extend(manacherChars, index, radius);
                } else if (curFar > far) {
                    radius = far - index + 1;
                }
            }
            radiusArray[index] = radius;
            max = Math.max(max, radius);
            if (index + radius - 1 > far) {
                far = index + radius - 1;
                farIndex = index;
            }
        }
        return max - 1;
    }

    private static int extend(char[] manacherChars, int index, int radius) {
        // 暴力扩
        while (index - radius >= 0 && index + radius < manacherChars.length) {
            if (manacherChars[index - radius] == manacherChars[index + radius]) {
                radius++;
            } else {
                break;
            }
        }
        return radius;
    }

    private static char[] getManacherString(String s) {
        char[] manacherChars = new char[s.length() << 1 | 1];
        int index = 0;
        manacherChars[index++] = '#';
        for (int i = 0; i < s.length(); i++) {
            manacherChars[index++] = s.charAt(i);
            manacherChars[index++] = '#';
        }
        return manacherChars;
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
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
        String tmp = "eececbced";
        int manacher = manacher(tmp);
        int right = right(tmp);
        System.out.println(manacher + " " + right);

        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!" + str);
            }
        }
        System.out.println("test finish");
    }

}
