package basic.class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code02_PrintAllSubsquences {

    public static List<String> subs(String s) {
        ArrayList<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        char[] chars = s.toCharArray();
        process1(chars, result, 0, "");
        return result;
    }

    private static void process1(char[] chars, ArrayList<String> result, int index, String path) {
        if (chars.length == index) {
            result.add(path);
            return;
        }
//        for (int i = index; i < chars.length; i++) { // 全排列的写法
//            swap(chars, index, i);
            process1(chars, result, index + 1, path);
            process1(chars, result, index + 1, path + chars[index]);
//            swap(chars, index, i);
//        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static List<String> subsNoRepeat(String s) {
        ArrayList<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        char[] chars = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        process2(chars, set, 0, "");
        result.addAll(set);
        return result;
    }

    private static void process2(char[] chars, HashSet<String> result, int index, String path) {
        if (chars.length == index) {
            result.add(path);
            return;
        }
        process2(chars, result, index + 1, path);
        process2(chars, result, index + 1, path + chars[index]);
    }

    public static void main(String[] args) {
        String test = "aacc";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }

}
