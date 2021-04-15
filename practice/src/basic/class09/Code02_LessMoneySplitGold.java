package basic.class09;

import java.util.PriorityQueue;

public class Code02_LessMoneySplitGold {

    // 纯暴力！
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
//        int rest = 0;
//        for (int i = 0; i < arr.length; i++) {
//            rest += arr[i];
//        }
//        return getLessMoney(arr, rest, 0, 0); // 错误
    }

    private static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int[] next = copyAndMergeTwoNumber(arr, i, j);
                int cost = process(next, pre + arr[i] + arr[j]);
                min = Math.min(min, cost);
            }
        }
        return min;
    }

    private static int[] copyAndMergeTwoNumber(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int index = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                ans[index++] = arr[k];
            }
        }
        ans[index] = arr[i] + arr[j];
        return ans;
    }

//    private static int getLessMoney(int[] arr, int rest, int index, int cost) {
//        if (arr.length - 1 == index) {
//            return cost;
//        }
//        int min = Integer.MAX_VALUE;
//        for (int i = index; i < arr.length; i++) {
//            swap(arr, index, i);
//            int lessMoney = getLessMoney(arr, rest - arr[index], index + 1, cost + rest);
//            min = Math.min(lessMoney, min);
//            swap(arr, index, i);
//        }
//        return min;
//    }
//
//    private static void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }

    public static int lessMoney2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue();
        for (int i = 0; i < arr.length; i++) {
            heap.offer(arr[i]);
        }
        int sum = 0;
        while (heap.size() > 1) {
            Integer p1 = heap.poll();
            Integer p2 = heap.poll();
            int newNumber = p1 + p2;
            sum += newNumber;
            heap.offer(newNumber);
        }
        return sum;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] temp = new int[] {368, 741, 428, 457};
        System.out.println(lessMoney1(temp) + "  3988");

        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMoney2(arr)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println();
                System.out.println(lessMoney1(arr) + "   " + lessMoney2(arr));
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
