package trainingcamp001.class03;

public class Code01_FindMinKth {

    private static int[] copyArray(int[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    // 利用bfprt算法，时间复杂度O(N)
    public static int minKth3(int[] array, int k) {
        int[] arr = copyArray(array);
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    private static int bfprt(int[] arr, int left, int right, int targetIndex) {
        if (left == right) {
            return arr[left];
        }
        // 1.获取分组后的中位数数组
        // 2.求出上中位数
        int pivot = getMedianOfMedians(arr, left, right);
        // 3.用上中位数进行partition
        int[] indexArray = partition(arr, left, right, pivot);
        // 4.寻找targetIndex的值
        int result = 0;
        if (indexArray[0] <= targetIndex && targetIndex <= indexArray[1]) {
            result = arr[indexArray[0]];
        } else if (targetIndex < indexArray[0]) {
            result = bfprt(arr, left, indexArray[0] - 1, targetIndex);
        } else if (indexArray[1] < targetIndex) {
            result = bfprt(arr, indexArray[1] + 1, right, targetIndex);
        }
        return result;
    }

    private static int[] partition(int[] arr, int left, int right, int pivot) {
        int leftIndex = left - 1;
        int rightIndex = right + 1;
        int index = left;
        while (index < rightIndex) {
            if (arr[index] < pivot) {
                swap(arr, index, leftIndex + 1);
                leftIndex++;
                index++;
            } else if (arr[index] == pivot){
                index++;
            } else if (arr[index] > pivot){
                swap(arr, index, rightIndex - 1);
                rightIndex--;
            }
        }
        int[] indexArray = {leftIndex + 1, rightIndex - 1};
        return indexArray;
    }

    private static int getMedianOfMedians(int[] arr, int left, int right) {
        int[] marr = getMiddleArrayOfGroup(arr, left, right);
        int bfprt = bfprt(marr, 0, marr.length - 1, marr.length / 2);
        return bfprt;
    }

    private static int[] getMiddleArrayOfGroup(int[] arr, int left, int right) {
        int size = right - left + 1;
        int length = size % 5 == 0 ? size / 5 : (size / 5) + 1;
        int[] result = new int[length];
        int index = 0;
        for (int i = left; i <= right; i = i + 5) {
            int start = i;
            int end = Math.min(i + 4, right);
            insertionSort(arr, start, end);
            result[index++] = arr[start + ((end - start) / 2)];
        }
        return result;
    }

    private static void insertionSort(int[] arr, int start, int end) {
        for (int i = start + 1; i <= end ; i++) {
            int cur = i;
            while (start < cur) {
                if (arr[cur - 1] > arr[cur]) {
                    swap(arr, cur - 1, cur);
                    cur = cur - 1;
                } else {
                    break;
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] array = {3, 1, 5, 2, 4};
        insertionSort(array, 0, 4);
//        System.out.println(array);

        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
//            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
//            if (ans1 != ans2 || ans2 != ans3) {
            if (ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


    // 改写快排，时间复杂度O(N)
    public static int minKth2(int[] array, int k) {
        int[] arr = copyArray(array);
        return process2(arr, 0, arr.length - 1, k - 1);
    }


    // arr 第k小的数
    // process2(arr, 0, N-1, k-1)
    // arr[L..R]  范围上，如果排序的话(不是真的去排序)，找位于index的数
    // index [L..R]
    public static int process2(int[] arr, int L, int R, int index) {
        if (L == R) { // L = =R ==INDEX
            return arr[L];
        }
        // 不止一个数  L +  [0, R -L]
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];

        // range[0] range[1]
        //  L   ..... R     pivot
        //  0         1000     70...800
        int[] range = partition2(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process2(arr, L, range[0] - 1, index);
        } else {
            return process2(arr, range[1] + 1, R, index);
        }
    }

    public static int[] partition2(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[] { less + 1, more - 1 };
    }




}
