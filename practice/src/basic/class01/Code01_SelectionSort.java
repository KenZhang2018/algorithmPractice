package basic.class01;

public class Code01_SelectionSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minValueIndex] < arr[j]) {
                    minValueIndex = j;
                }
            }
            swap(arr, minValueIndex , i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
