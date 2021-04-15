package trainningcamp002.class01;

public class Code06_ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix) {
        int ar = 0, ac = 0;
        int br = 0, bc = 0;
        boolean fromUpToDown = false;
        while (ar < matrix.length) {
            printPoint(matrix, ar, ac, br, bc, fromUpToDown);
            fromUpToDown = !fromUpToDown;
            ar = ac == matrix[0].length - 1 ? ar + 1 : ar;
            ac = ac == matrix[0].length - 1 ? ac : ac + 1;

            bc = br == matrix.length - 1 ? bc + 1 : bc;
            br = br == matrix.length - 1 ? br : br + 1;
        }

    }

    private static void printPoint(int[][] matrix, int ar, int ac, int br, int bc, boolean fromUpToDown) {
        if (fromUpToDown) {
            while (ar <= br && ac >= bc) {
                System.out.print(matrix[ar++][ac--] + " ");
            }
            System.out.println();
        } else {
            while (br >= ar && bc <= ac) {
                System.out.print(matrix[br--][bc++] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }

}
