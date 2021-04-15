package basic.class03.mergeSort;

public class Code01_MergeSort {

    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        // 1.clarification clear:corner cases
        // 2.possible solutions:optimal : time & space
        // 3.code
        // 4.test cases
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        // 1.recursion terminal
        // 2.process current level logic
        // 3.drill down
        // 4.reverse current states if needed
        if (left >= right) {
            return;
        }
        int middle = left + ((right - left) >> 1);
        process(arr, left, middle);
        process(arr, middle + 1, right);
        mergeBySelf(arr, left, right, middle);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    //  非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < N) { // log N
            // 当前左组的，第一个位置
            int L = 0;
            while (L < N) {
                if (mergeSize >= N - L) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    //  非递归方法实现
    public static void mergeSort3(int[] arr) {
        int n = arr.length, step = 1;
        while (step <= n) {
            for (int left = 0; left < n; left += 2 * step) {
                int middle = left + step -1;
                int right = Math.min(n - 1, left + 2 * step - 1);
                if (middle < right) {
                    mergeBySelf(arr, left, right, middle);
                }
            }
            if (step > n / 2) {
                break;
            }
            step = step << 1;
        }
    }

    private static void mergeBySelf(int[] arr, int left, int right, int middle) {
        int[] help = new int[right - left + 1];
        int leftIndex = left, rightIndex = middle + 1, helpIndex = 0;
        while (leftIndex <= middle && rightIndex <= right) {
            help[helpIndex++] = arr[leftIndex] <= arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        while (leftIndex <= middle) {
            help[helpIndex++] = arr[leftIndex++];
        }
        while (rightIndex <= right) {
            help[helpIndex++] = arr[rightIndex++];
        }
        leftIndex = left;
        for (int i = 0; i < help.length; i++) {
            arr[leftIndex++] = help[i];
        }
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
        int testTime = 5000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
//            printArray(arr1);
            mergeSort3(arr3);
            mergeSort2(arr2);
            if (!isEqual(arr3, arr2)) {
                System.out.println("出错了！");
                printArray(arr3);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }






}
