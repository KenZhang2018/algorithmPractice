package basic.class11;

public class Code09_NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int num = process1(n, 0, new int[n]);
        return num;
    }

    private static int process1(int n, int level, int[] record) {
        if (level == n) {
            return 1;
        }
        int total = 0;
        for (int column = 0; column < n; column++) {
            boolean isValid = isValid(record, level, column);
            if (isValid) {
                record[level] = column;
                total += process1(n, level + 1, record);
            }
        }

        return total;
    }

    private static boolean isValid(int[] record, int row, int column) {
        for (int i = 0; i < row; i++) {
            if (record[i] == column || (Math.abs(row - i) == Math.abs(record[i] - column))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println((~0)+1);
        int n = 4;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }

    private static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        int curColumnLimit = 0;
        int leftColumnLimit = 0;
        int rightColumnLimit = 0;
        int total = process2(limit, curColumnLimit, leftColumnLimit, rightColumnLimit);

        return total;
    }

    private static int process2(int limit, int curColumnLimit, int leftColumnLimit, int rightColumnLimit) {
        if (limit == curColumnLimit) {
            return 1;
        }
        int total = 0;
        int temp = limit & (~(curColumnLimit | leftColumnLimit | rightColumnLimit));
        int mostRightOne = temp & ((~temp) + 1);
        while (mostRightOne != 0) {
            temp = temp - mostRightOne; // = temp - mostRightOne;
            int nextColumnLimit = curColumnLimit | mostRightOne;
            int nextLeftColumnLimit = ((leftColumnLimit | mostRightOne) << 1) & limit;
            int nextRightColumnLimit = ((rightColumnLimit | mostRightOne) >>> 1) & limit;
            total += process2(limit, nextColumnLimit, nextLeftColumnLimit, nextRightColumnLimit);
            mostRightOne =  temp & ((~temp) + 1);
        }
        return total;
    }

}
