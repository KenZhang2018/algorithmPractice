package basic.class01;

public class Code06_BSAwesome {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int leftIndex = 1, rightIndex = arr.length - 2;
        while (leftIndex < rightIndex) {
            int middle = leftIndex + ((rightIndex - leftIndex) >> 1);
            if (arr[middle] < arr[middle + 1]) {
                leftIndex = middle + 1;
                return middle;
            } else if (arr[middle - 1] < arr[middle]) {
                rightIndex = middle - 1;
            } else if (arr[middle - 1] > arr[middle] && arr[middle] < arr[middle + 1]) {
                return middle;
            }
        }
        return leftIndex;
    }

}
