package basic.class03.quickSort;

public class Code02_PartitionAndQuickSort {


    // arr[L..R]上，以arr[R]位置的数做划分值
    // <= X > X
    // <= X X
    public static int partition(int[] arr, int L, int R) {
        // 1.recursion terminal
        // 2.process current level logic
        // 3.drill down
        // 4.reverse current state if needed
        if (L > R) return -1;
        if (L == R) return L;

        int prior = arr[R], index = L, lessEqual = L - 1, right = R;
        while (index < R) {
            if (arr[index] < arr[R]) {
                swap(arr, index++, ++lessEqual);
            } else {
                index++;
            }
        }
        swap(arr, R, ++lessEqual);
        return lessEqual;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSort1(int[] arr) {
        // 1.clarification : corner cases
        // 2.process current level logic
        // 3.drill down
        // 4.reverse current state if needed
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int L, int R) {
        if (L >= R) return;
        int partitionIndex = partition(arr, L, R);

        process1(arr, L, partitionIndex - 1);
        process1(arr, partitionIndex + 1, R);
    }

    public static void quickSort4(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process4(arr, 0, arr.length - 1);
    }

    private static void process4(int[] arr, int L, int R) {
        if (L >= R) return;
        int[] flags = netherlandsFlag(arr, L, R);
        process4(arr, L, flags[0] - 1);
        process4(arr, flags[1] + 1, R);
    }

    public static void quickSort5(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process5(arr, 0, arr.length - 1);
    }

    private static void process5(int[] arr, int L, int R) {
        if (L >= R) return;
        int priorIndex = (int) ((R - L + 1) * Math.random() + L);
        swap(arr, priorIndex, R);
        int[] flags = netherlandsFlag(arr, L, R);
        process4(arr, L, flags[0] - 1);
        process4(arr, flags[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {

    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    // arr[L...R] 排有序，快排2.0方式
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // [ equalArea[0]  ,  equalArea[0]]
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
       int less = L - 1, more = R, index = L;
       int prior = arr[R];
       while (index < more) {
            if (arr[index] < prior) {
                swap(arr, index, less + 1);
                index++; less++;
            } else {
                swap(arr, index, more - 1);
                more--;
            }
       }
       swap(arr, more, R);
       return new int[] { less + 1, more};
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
            int[] arr3 = copyArray(arr1);
            int[] arr4 = copyArray(arr1);
            int[] arr5 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
//            quickSort3(arr3);
            quickSort4(arr4);
            quickSort5(arr5);

//            if (!isEqual(arr1, arr2)) {
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr5)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }

}
