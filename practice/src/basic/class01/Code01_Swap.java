package basic.class01;

public class Code01_Swap {

    public static void main(String[] args) {
        int[] arr = new int[2];
        arr[0] = 001;
        arr[1] = 005;
        swap(arr, 0, 1);
        System.out.println(arr[0] + " " +arr[1]);
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
