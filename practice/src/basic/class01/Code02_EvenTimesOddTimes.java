package basic.class01;

public class Code02_EvenTimesOddTimes {

    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i != arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int mostRightOne = eor & (~eor + 1);
        int result0 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((mostRightOne & arr[i]) != 0) {
                result0 ^= arr[i];
            }
        }
//        result0 ^= eor;
        int result1 = eor ^ result0;
        System.out.println(result0 + " " + result1);
    }

    public static int bit1counts(int N) {
        int count = 0;
        while (N != 0) {
            count++;
            int mostRightOne = N ^ (~N + 1);
            N ^= mostRightOne;
        }
        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum1(arr1);

        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum2(arr2);

    }

}
