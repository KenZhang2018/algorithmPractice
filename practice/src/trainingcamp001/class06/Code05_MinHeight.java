package trainingcamp001.class06;

public class Code05_MinHeight {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int x) {
            val = x;
        }
    }

    public static int minHeight1(Node head) {
        if (head == null) {
            return 0;
        }
        return p(head);
    }

    private static int p(Node head) {
        if (head.left == null && head.right == null) {
            return 1;
        }
        int left = head.left == null ? Integer.MAX_VALUE : p(head.left);
        int right = head.right == null ? Integer.MAX_VALUE : p(head.right);

        return Math.min(left, right) + 1;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(1);
        root.left.left = new Node(0);
        root.right = new Node(2);
        root.right.left = new Node(1);
        root.right.left.left = new Node(4);
        root.right.right = new Node(3);
        root.right.right.right = new Node(0);
        root.right.right.left = new Node(2);
        root.right.right.left.right = new Node(3);
        root.right.right.left.right.left = new Node(0);
        root.right.right.left.right.left.left = new Node(1);

        int minHeight1 = minHeight1(root);
        int minHeight2 = minHeight2(root);
        System.out.println(minHeight1 + "  " + minHeight2);


        int treeLevel = 7;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(treeLevel, nodeMaxValue);
            int ans1 = minHeight1(head);
            int ans2 = minHeight2(head);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

    public static int minHeight2(Node head) {
        if (head == null) {
            return 0;
        }
        int min = Integer.MAX_VALUE, level = 1;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int rightLevel = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                    rightLevel++;
                }
                if (mostRight.right == null) { // 第一次来到
                    mostRight.right = cur;
                    cur = cur.left;
                    level++;
                } else { // 第二次来到
                    mostRight.right = null;
                    if (mostRight.left == null) { // 结算叶子节点
                        min = Math.min(min, level - 1);
                    }
                    // 修正level
                    level = level - rightLevel - 1;

                    cur = cur.right;
                    level++;
                }
            } else { // 唯一一次来到
                cur = cur.right;
                level++;
            }
        }
        Node right = head;
        int rightLevel = 1;
        while (right.right != null) {
            right = right.right;
            rightLevel++;
        }
        if (right.left != null) {
            rightLevel = Integer.MAX_VALUE;
        }
        min = Math.min(min, rightLevel);
        return min;
    }


}
