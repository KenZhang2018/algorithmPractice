package basic.class02;

public class Code08_GetMax {

    // 求arr中的最大值
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        // 1.recursive terminal
        // 2.process current logic
        // 3.drill down
        // 4.reverse current state
        if (left == right) {
            return arr[left];
        }
        int middle = left + ((right - left) >> 1);
        int leftMax = process(arr, left, middle);
        int rightMax = process(arr, middle + 1, right);

        return Math.max(leftMax, rightMax);
    }


}
