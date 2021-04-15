package trainingcamp001.class01;

import java.util.Stack;

public class Code04_AllTimesMinToMax {

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sumArray = new int[arr.length];
        sumArray[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArray[i] = sumArray[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer popIndex = stack.pop();
                int multi = arr[popIndex] * (sumArray[i- 1] - (stack.isEmpty() ? 0 : sumArray[stack.peek()]));
                max = Math.max(max, multi);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer popIndex = stack.pop();
            int multi = arr[popIndex] * (sumArray[arr.length - 1] - (stack.isEmpty() ? 0 : sumArray[stack.peek()]));
            max = Math.max(max, multi);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }

}
