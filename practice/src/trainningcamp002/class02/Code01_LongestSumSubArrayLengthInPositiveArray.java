package trainningcamp002.class02;

public class Code01_LongestSumSubArrayLengthInPositiveArray {

    public static int getMaxLength(int[] arr, int K) {
        int ans = 0;
        if (arr == null || arr.length == 0 || K <= 0) {
            return ans;
        }
        int windowSum = 0, leftIndex = 0, rightIndex = 0;
        while (rightIndex < arr.length) {
            if (windowSum == K) {
                int length = rightIndex - leftIndex;
                ans = Math.max(ans, length);
                windowSum -= arr[leftIndex++];
            } else if (windowSum < K) {
                windowSum += arr[rightIndex++];
            } else if (windowSum > K) {
                windowSum -= arr[leftIndex++];
            }
        }
        while (windowSum >= K) {
            if (windowSum == K) {
                int length = rightIndex - leftIndex;
                ans = Math.max(ans, length);
                windowSum -= arr[leftIndex++];
            }  else if (windowSum > K) {
                windowSum -= arr[leftIndex++];
            }

        }
        return ans;
    }

    // for test
    public static int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generatePositiveArray(int size, int value) {
        int[] ans = new int[size];
        for (int i = 0; i != size; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {8, 2, 8, 9, 6, 7, 9, 4, 3, 5 };
        int maxLength = getMaxLength(array, 8);
        System.out.println(maxLength);

        int len = 10;
        int value = 9;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len, value);
            int K = (int) (Math.random() * value) + 1;
            int ans1 = getMaxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }


}
