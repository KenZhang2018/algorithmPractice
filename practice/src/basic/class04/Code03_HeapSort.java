package basic.class04;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code03_HeapSort {

    // 堆排序额外空间复杂度O(1)
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        // 1.堆化
        // 2.堆排序
//        for (int i = 0; i < arr.length; i++) {
//            heapUpInsert(arr, i);
//        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heapDown(arr, i, arr.length);
        }

        int size = arr.length;
        while (size > 0) {
            swap(arr, 0, size - 1);
            size--;
            heapDown(arr, 0, size);
        }


    }

    private static void heapDown(int[] arr, int index, int size) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < size) {
            int rightIndex = leftIndex + 1, biggerIndex = leftIndex;
            if (rightIndex < size) {
                biggerIndex = arr[leftIndex] > arr[rightIndex] ? leftIndex : rightIndex;
            }
            if (arr[biggerIndex] > arr[index]) {
                swap(arr, biggerIndex, index);
            }
            index = biggerIndex;
            leftIndex = index * 2 + 1;
        }
    }


    private static void heapUpInsert(int[] arr, int index) {
        int parentIndex = (index - 1) / 2;
        while (arr[parentIndex] < arr[index]) {
            swap(arr, parentIndex, index);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {

        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(8);
        heap.add(0);
        heap.add(2);
        heap.add(9);
        heap.add(1);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }


}
