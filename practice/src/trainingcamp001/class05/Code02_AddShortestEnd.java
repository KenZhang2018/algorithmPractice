package trainingcamp001.class05;

public class Code02_AddShortestEnd {

    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] manacherChars = getManacherString(s);
        int[] radiusArray = new int[manacherChars.length];
        int far = -1, farIndex = -1;
        int needIndex = -1;
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
            if (index + radius - 1 > far) {
                far = index + radius - 1;
                farIndex = index;
            }
            if (far == manacherChars.length - 1) {
                needIndex = farIndex - radiusArray[index];
                break;
            }
        }

        int i = needIndex < 0 ? -1 :  needIndex % 2 == 0 ? needIndex / 2 - 1 : needIndex / 2;
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = i; j >= 0 ; j--) {
            stringBuilder.append(s.charAt(j));
        }
        return stringBuilder.toString();
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

    public static void main(String[] args) {
        String str1 = "abcd12321";
        System.out.println(shortestEnd(str1));
        str1 = "abcd";
        System.out.println(shortestEnd(str1));
        str1 = "abcddcba";
        System.out.println(shortestEnd(str1));
    }

}
