package trainningcamp002.class01;

public class Code04_RotateMatrix {

    public static void rotate(int[][] matrix) {
        int aR = 0, aC = 0;
        int bR = matrix.length - 1, bC = matrix[0].length - 1;
        while (aR < bR) {
            rotateEdge(matrix, aR++, aC++, bR--, bC--);
        }
    }

    private static void rotateEdge(int[][] matrix, int aR, int aC, int bR, int bC) {
        for (int i = 0; i < (bC - aC); i++) {
            int tmp = matrix[aR][aC + i];
            matrix[aR][aC + i] = matrix[bR - i][aC];
            matrix[bR - i][aC] = matrix[bR][bC - i];
            matrix[bR][bC - i] = matrix[aR + i][bC];
            matrix[aR + i][bC] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }

}
