package basic.class11;

public class Code08_CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    private static int f(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        return Math.max(arr[left] + s(arr, left + 1, right), arr[right] + s(arr, left, right - 1));
    }

    private static int s(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        return Math.min(f(arr, left + 1, right), f(arr, left, right - 1));
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            f[i][i] = arr[i];
        }
        int[][] s = new int[arr.length][arr.length];
        for (int left = arr.length - 2; left >= 0; left--) {
            for (int right = left + 1; right < arr.length; right++) {
                f[left][right] = Math.max(arr[left] + s[left + 1][right], arr[right] + s[left][right - 1]);
                s[left][right] = Math.min(f[left + 1][right], f[left][right - 1]);
            }
        }
        for (int i = 1; i < arr.length; i++) { // 对角线
            int left = 0;
            int right = i;
            while (right < arr.length && left < arr.length) {
                System.out.println(left + " " + right);
                left++;
                right++;
            }

        }

        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 4,7,9,5,19,29,80,4 };
        // A 4 9
        // B 7 5
        System.out.println(win1(arr));
        System.out.println(win2(arr));

    }

}
