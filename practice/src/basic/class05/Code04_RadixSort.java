package basic.class05;

import java.util.Arrays;

public class Code04_RadixSort {

    // only for no-negative value
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int maxBits = maxBits(arr);
        radixSort(arr, 0, arr.length - 1, maxBits);
    }

    private static void radixSort(int[] arr, int left, int right, int maxBits) {
        final int radix = 10;
        int[] help = new int[right - left + 1];
        for (int i = 0; i < maxBits; i++) {
            int[] buckets = new int[radix];
            for (int j = left; j <= right; j++) {
                int number = getDigit(arr[j], radix, i);
                buckets[number]++;
            }
            for (int j = 1; j < radix; j++) {
                buckets[j] = buckets[j - 1] + buckets[j];
            }
            for (int j = right; j >= left; j--) {
                int number = getDigit(arr[j], radix, i);
                int index = buckets[number];
                if (index > 0) {
                    help[index - 1] = arr[j];
                    buckets[number]--;
                }
            }
            for (int j = 0; j < help.length; j++) {
                arr[left + j] = help[j];
            }

        }

    }

    private static int getDigit(int number, int radix, int bit) {
        int digit = (int) ((number / Math.pow(10, bit)) % radix);
        return digit;
    }

    private static int maxBits(int[] arr) {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            maxValue = Math.max(maxValue, arr[i]);
        }
        int bitsCount = 0;
        while (maxValue > 0) {
            bitsCount++;
            maxValue = maxValue / 10;
        }
        return bitsCount;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }

}
