package trainningcamp002.class01;

public class Code03_MSumToN {

    public static boolean isMSum1(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 0; i < num; i++) {
            int sum = i;
            for (int j = i + 1; j < num; j++) {
                sum += j;
                if (num == sum) {
                    return true;
                } else if (sum > num) {
                    break;
                }
            }
        }
        return false;
    }

    public static boolean isMSum2(int num) {
        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    public static boolean isMSum3(int num) {
        if ((num & (num - 1)) == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        for (int num = 1; num < 200; num++) {
            System.out.println(num + " : " + isMSum1(num));
        }
        System.out.println("test begin");
        for (int num = 1; num < 5000; num++) {
            if (isMSum1(num) != isMSum3(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");

    }

}
