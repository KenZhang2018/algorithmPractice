package basic.class07;

public class Code04_PrintBinaryTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printTree(head, 1, 17, "H");
        System.out.println();
    }

    private static void printTree(Node head, int level, int length, String h) {
        if (head == null) {
            return;
        }
        printTree(head.right, level + 1, length, "^");
        printPadding(level - 1, 17);
        String value = String.valueOf(head.value);
        int valueLength = value.length();
        int leftLength = (length - valueLength) / 2;
        int rightLength = length - valueLength - leftLength;
        printNode(value, leftLength, rightLength, h);
        printTree(head.left, level + 1, 17, "V");
    }

    private static void printNode(String value, int leftLength, int rightLength, String h) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < leftLength; i++) {
            stringBuilder.append(h);
        }
        stringBuilder.append(value);
        for (int i = 0; i < rightLength; i++) {
            stringBuilder.append(h);
        }
        System.out.println(stringBuilder.toString());
    }

    private static void printPadding(int level, int length) {
        int total = length * level;
        for (int i = 0; i < total; i++) {
            System.out.print(" ");
        }
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);

    }

}
