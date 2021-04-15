package basic.class07;

public class Code07_PaperFolding {

    public static void printAllFolds(int N) {
        printFolds(1, N, true);
    }

    private static void printFolds(int level, int n, boolean isAo) {
        if (level > n) {
            return;
        }
        printFolds(level + 1, n, true);
        String print = isAo ? level + "凹" : level + "凸";
        System.out.print(print);
        printFolds(level + 1, n, false);
    }

    public static void main(String[] args) {
        int N = 3;
        printAllFolds(N);
    }

}
