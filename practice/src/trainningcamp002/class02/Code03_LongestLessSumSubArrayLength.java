package trainningcamp002.class02;

public class Code03_LongestLessSumSubArrayLength {

    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minValue = new int[arr.length];
        int[] minEndIndex = new int[arr.length];
        minValue[arr.length - 1] = arr[arr.length - 1];
        minEndIndex[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0 ; i--) {
            if (minValue[i + 1] <= 0) {
                minValue[i] = minValue[i + 1] + arr[i];
                minEndIndex[i] = minEndIndex[i + 1];
            } else {
                minValue[i] = arr[i];
                minEndIndex[i] = i;
            }
        }
        int ans = 0, sum = minValue[0], endIndex = minEndIndex[0] + 1;
        for (int i = 0; i < arr.length; i++) {
            if (i >= endIndex) {
                sum += minValue[i];
                endIndex = minEndIndex[endIndex] + 1;
            }
            while (sum <= k) {
                int length = endIndex - i;
                ans = Math.max(ans, length);
                if (endIndex == arr.length) {
                    break;
                }
                sum += minValue[endIndex];
                endIndex = minEndIndex[endIndex] + 1;
            }
            sum -= arr[i];
        }
        return ans;
    }

    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            if (sum <= k) {
                ans = Math.max(ans, 1);
            }
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                if (sum <= k) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }


    public static int maxLength2(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = {6, 1, 5, -2, -2, 5, 0, -1, -3, -1};
        int i1 = maxLengthAwesome(array, -5);
        System.out.println(i1 + " " + maxLength(array, -5));

        System.out.println("test begin");
        for (int i = 0; i < 1000_0000; i++) {
            int[] arr = generateRandomArray(10, 10);
            int k = (int) (Math.random() * 10) - 5;
            if (maxLengthAwesome(arr, k) != maxLength2(arr, k)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[j] + " ");
                }
                System.out.println("Oops!" + k);
            }
        }
        System.out.println("test finish");
    }

}
