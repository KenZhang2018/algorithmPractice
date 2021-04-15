package trainningcamp002.class01;

public class Code05_PrintMatrixSpiralOrder {

    public static void spiralOrderPrint(int[][] matrix) {
        int ar = 0, ac = 0;
        int br = matrix.length - 1, bc = matrix[0].length - 1;
        while (ar <= br && ac <= bc) {
            printEdge(matrix, ar++, ac++, br--, bc--);
        }
    }

    private static void printEdge(int[][] matrix, int ar, int ac, int br, int bc) {
        if (ar == br) {
            for (int i = ac; i <= bc; i++) {
                System.out.print(matrix[ar][i] + " ");
            }
            System.out.println();
        } else if (ac == bc) {
            for (int i = ar; i <= br; i++) {
                System.out.print(matrix[i][ac] + " ");
            }
            System.out.println();
        } else {
            for (int i = ac; i < bc; i++) {
                System.out.print(matrix[ar][i] + " ");
            }
            System.out.println();
            for (int i = ar; i < br; i++) {
                System.out.print(matrix[i][bc] + " ");
            }
            System.out.println();
            for (int i = bc; i > ac; i--) {
                System.out.print(matrix[br][i] + " ");
            }
            System.out.println();
            for (int i = br; i > ar ; i--) {
                System.out.print(matrix[i][ac] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);

    }

}
