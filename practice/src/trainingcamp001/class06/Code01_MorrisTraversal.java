package trainingcamp001.class06;

public class Code01_MorrisTraversal {

    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 第一次来到
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次来到
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                // 唯一一次来到
                cur = cur.right;
            }
        }
    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 第一次来到
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次来到
                    System.out.print(cur.value + " ");
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                // 唯一一次来到
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println("=========================");
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 第一次来到
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次来到
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                // 唯一一次来到
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println("=========================");
    }

    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 第一次来到
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次来到
                    mostRight.right = null;
                    Node tail = reverse(cur.left);
                    printRightNode(tail);
                    Node left = reverse(tail);

                    cur = cur.right;
                }
            } else {
                // 唯一一次来到
                cur = cur.right;
            }
        }
        Node tail = reverse(head);
        printRightNode(tail);
        Node left = reverse(tail);
        System.out.println("=========================");
    }

    private static Node reverse(Node head) {
        Node pre = null, cur = head;
        while (cur != null) {
            Node next = cur.right;

            cur.right = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    private static void printRightNode(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        printRightNode(head.right);
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);

//        printTree(head);
        morrisIn(head);
        morrisPre(head);
        morrisPos(head);
//        printTree(head);

    }

}
