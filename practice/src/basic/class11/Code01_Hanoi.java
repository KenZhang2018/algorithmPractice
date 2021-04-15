package basic.class11;

public class Code01_Hanoi {

    public static void hanoi1(int n) {
        leftToRight(n);
    }

    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMiddle(n - 1);
        System.out.println("Move " + n + " from left to right");
        middleToRight(n - 1);
    }

    private static void middleToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        middleToLeft(n - 1);
        System.out.println("Move " + n +" from mid to right");
        leftToRight(n - 1);
    }

    private static void middleToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        middleToRight(n - 1);
        System.out.println("Move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMiddle(n - 1);
        System.out.println("Move " + n + " from right to left");
        middleToLeft(n - 1);
    }

    private static void rightToMiddle(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMiddle(n - 1);
    }

    private static void leftToMiddle(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMiddle(n - 1);
    }

    public static void hanoi2(int n) {
        if (n > 0) {
            func(n, "left", "right", "mid");
        }
    }

    private static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
            return;
        }
        func(n - 1, from, other, to);
        System.out.println("Move " + n + " from " + from + " to " + to);
        func(n - 1, other, to, from);
    }


    public static void main(String[] args) {
        int n = 7;
        hanoi1(n);
        System.out.println("============");
        hanoi2(n);
        System.out.println("============");
//        hanoi3(n);
    }

}
