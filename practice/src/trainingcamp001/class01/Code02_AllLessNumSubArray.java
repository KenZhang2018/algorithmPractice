package trainingcamp001.class01;

import java.util.LinkedList;

public class Code02_AllLessNumSubArray {

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> bigQueue = new LinkedList<>();
        LinkedList<Integer> littleQueue = new LinkedList<>();
        int left = 0, result = 0;

//        int right = 0;
//        while (left < arr.length) {
//            while (right < arr.length) {
//                while (!bigQueue.isEmpty() && arr[bigQueue.peekLast()] <= arr[right]) {
//                    bigQueue.pollLast();
//                }
//                bigQueue.addLast(right);
//
//                while (!littleQueue.isEmpty() && arr[littleQueue.peekLast()] >= arr[right]) {
//                    littleQueue.pollLast();
//                }
//                littleQueue.addLast(right);
//
//                if(arr[bigQueue.peekFirst()] - arr[littleQueue.peekFirst()] > num) {
//                    break;
//                }
//                right++;
//            }
//
//            result += (right - left);
//            left++;
//            if (bigQueue.peekFirst() < left) {
//                bigQueue.pollFirst();
//            }
//            if (littleQueue.peekFirst() < left) {
//                littleQueue.pollFirst();
//            }
//        }

        for (int right = 0; right < arr.length; right++) {
            while (!bigQueue.isEmpty() && arr[bigQueue.peekLast()] <= arr[right]) {
                bigQueue.pollLast();
            }
            bigQueue.addLast(right);

            while (!littleQueue.isEmpty() && arr[littleQueue.peekLast()] >= arr[right]) {
                littleQueue.pollLast();
            }
            littleQueue.addLast(right);

            while (left <= right && arr[bigQueue.peekFirst()] - arr[littleQueue.peekFirst()] > num) {
                result += (right - left);
                left++;

                if (bigQueue.peekFirst() < left) {
                    bigQueue.pollFirst();
                }
                if (littleQueue.peekFirst() < left) {
                    littleQueue.pollFirst();
                }
            }
        }

        while (left < arr.length) {
            result += (arr.length - left);
            left++;
        }
        return result;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        int[] arr = getRandomArray(30);
//        6 3 9 1 9 8 1 9 4 6 8 4 5 3 7 1 2 3 9 6 4 1 5 7 8 9 3 8 3 3
//        81
        int[] arr = new int[] {6,3, 9, 1, 9, 8, 1, 9, 4, 6, 8, 4, 5, 3, 7, 1, 2, 3, 9, 6, 4, 1, 5, 7, 8, 9, 3, 8, 3, 3};
//        int[] arr = new int[] {6,3, 9, 1, 9, 8, 1, 9, 4, 6, 8, 4, 5, 3, 7, 1, 2, 3, 9, 6, 4, 1, 5, 7, 8, 9, 3, 8, 3, 3};
//        int[] arr = new int[] {6,3, 9, 1, 9, 8, 1, 9, 4, 6, 8, 4, 5, 3, 7};
//        int[] arr = new int[] {  5, 3, 7, 1, 2, 3, 9};
        //        int[] arr = new int[] {6, 3, 9, 1, 9, 8, 4, 6, 4, 5};
//        int[] arr = new int[] {0,1, 2, 3, 4};
        int num = 5;
        printArray(arr);
        System.out.println(getNum(arr, num));

    }

}
