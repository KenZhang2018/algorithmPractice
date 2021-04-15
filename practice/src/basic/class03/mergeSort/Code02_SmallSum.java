package basic.class03.mergeSort;

public class Code02_SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        int leftSum = process(arr, left, middle);
        int rightSum = process(arr, middle + 1, right);
        int sum = merge(arr, left, right, middle);
        return sum + leftSum + rightSum;
    }

    private static int merge(int[] arr, int left, int right, int middle) {
        int sum = 0, helpIndex = 0, leftIndex = left, rightIndex = middle + 1;
        int[] help = new int[right - left + 1];
        while (leftIndex <= middle && rightIndex <= right) {
            if (arr[leftIndex] < arr[rightIndex]) {
                sum += arr[leftIndex] * (right - rightIndex + 1);
                help[helpIndex++] = arr[leftIndex++];
            } else {
                help[helpIndex++] = arr[rightIndex++];
            }
        }
        while (leftIndex <= middle) {
            help[helpIndex++] = arr[leftIndex++];
        }
        while (rightIndex <= right) {
            help[helpIndex++] = arr[rightIndex++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
        return sum;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
