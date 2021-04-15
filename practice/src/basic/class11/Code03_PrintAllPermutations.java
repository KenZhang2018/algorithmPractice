package basic.class11;

import java.util.ArrayList;
import java.util.List;

public class Code03_PrintAllPermutations {

    public static ArrayList<String> permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }
        char[] chars = str.toCharArray();
        process1(chars, result, 0, "");
        return result;
    }

    private static void process1(char[] chars, ArrayList<String> result, int index, String path) {
        if (chars.length == index) {
            result.add(path);
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
//            process1(chars, result, index + 1, path);
            process1(chars, result, index + 1, path + chars[index]);
            swap(chars, index, i);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static ArrayList<String> permutationNoRepeat(String str) {
        ArrayList<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }
        char[] chars = str.toCharArray();
        process2(chars, result, 0, "");
        return result;
    }

    private static void process2(char[] chars, ArrayList<String> result, int index, String path) {
        if (chars.length == index) {
            result.add(String.valueOf(chars));
//            result.add(path);
            return;
        }
        int[] set = new int[26];
        for (int i = index; i < chars.length; i++) {
            if (set[chars[i] - 'a'] == 0) {
                set[chars[i] - 'a']++;
                swap(chars, index, i);
//                process2(chars, result, index + 1, path);
                process2(chars, result, index + 1, path + chars[index]);
                swap(chars, index, i);
            }
        }
    }

    public static void main(String[] args) {
        String s = "aac";
        List<String> ans1 = permutation(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutationNoRepeat(s);
        for (String str : ans2) {
            System.out.println(str);
        }

    }


}
