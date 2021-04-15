package basic.class03.mergeSort;

public class Code03_ReversePair {

    // 统计降序对的对数
    public static int reversePairNumber(int[] arr) {
        // 1.clarification : corner cases
        // 2.possible solutions : optimal (time & space)
        // 3.code
        // 4.test cases
        if (arr == null || arr.length < 2) return 0;

        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        // 1.recursion terminal
        // 2.process current level logic
        // 3.drill down
        // 4.reverse current state if needed
        if (left == right) return 0;
        int middle = left + ((right - left) >> 1);

        int leftCount = process(arr, left, middle);
        int rightCount = process(arr, middle + 1, right);

        int pairCount = merge(arr, left, right, middle);
        return pairCount + leftCount + rightCount;
    }

    private static int merge(int[] arr, int left, int right, int middle) {
        int pairCount = 0, leftIndex = left, rightIndex = middle + 1, helpIndex = 0;
        int[] help = new int[right - left + 1];
        while (leftIndex <= middle && rightIndex <= right) {
            pairCount += arr[leftIndex] > arr[rightIndex] ? (middle - leftIndex + 1) : 0;
            help[helpIndex++] = arr[leftIndex] > arr[rightIndex] ? arr[rightIndex++] : arr[leftIndex++];
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
        return pairCount;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
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
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reversePairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
