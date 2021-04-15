package basic.class09;

import java.text.BreakIterator;
import java.util.*;

public class Code01_LowestLexicography {

    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        TreeSet<String> set = new TreeSet<>();
        List<String> path = new LinkedList<>();
        process(strs, 0, path, set);
        String first = set.first();
        return first;
    }

    private static void process(String[] strs, int i, List<String> path, TreeSet<String> set) {
        if (strs.length == i) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < path.size(); j++) {
                stringBuilder.append(path.get(j));
            }
            set.add(stringBuilder.toString());
            return;
        }
        for (int k = i; k < strs.length; k++) {
            swap(strs, i, k);
            path.add(strs[i]);
            process(strs, i + 1, path, set);
            path.remove(path.size() - 1);
            swap(strs, i, k);
        }
    }

    private static void swap(String[] strs, int j, int k) {
        String temp = strs[j];
        strs[j] = strs[k];
        strs[k] = temp;
    }

    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
//            System.out.println(o1 + " " + o2 + ": "+(o1 + o2).compareTo(o2 + o1));
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String ans = "";
        for (int i = 0; i < strs.length; i++) {
            ans = ans + strs[i];
        }
        return ans;
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
