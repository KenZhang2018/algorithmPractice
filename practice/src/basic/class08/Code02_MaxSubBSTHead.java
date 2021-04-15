package basic.class08;

public class Code02_MaxSubBSTHead {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class NodeInfo {
        public Node bst;
        public boolean isBst;
        public int size;
        public Integer maxValue;
        public Integer minValue;

        public NodeInfo(Node bst, boolean isBst, int size, Integer maxValue, Integer minValue) {
            this.bst = bst;
            this.isBst = isBst;
            this.size = size;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        NodeInfo info = process1(head);
        return info.bst;
    }

    private static NodeInfo process1(Node head) {
        if (head == null) {
            return new NodeInfo(null, true, 0, null, null);
        }
        NodeInfo left = process1(head.left);
        NodeInfo right = process1(head.right);
        boolean isBst = left.isBst  && right.isBst;

        if (isBst && left.maxValue != null && left.maxValue >= head.value) {
            isBst = false;
        }
        if (isBst && right.minValue != null && right.minValue <= head.value) {
            isBst = false;
        }
        Node bst = left.size >= right.size ? left.bst : right.bst;
        if (isBst) {
            bst = head;
        }
        int size = Math.max(left.size, right.size);
        size = isBst ? left.size + right.size + 1 : size;
        Integer minValue = left.minValue == null ? head.value : left.minValue;
        Integer maxValue = right.maxValue == null ? head.value : right.maxValue;

        return new NodeInfo(bst, isBst, size, maxValue, minValue);
    }

    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    // 每一棵子树
    public static class Info {
        public Node maxSubBSTHead;
        public int maxSubBSTSize;
        public int min;
        public int max;

        public Info(Node h, int size, int mi, int ma) {
            maxSubBSTHead = h;
            maxSubBSTSize = size;
            min = mi;
            max = ma;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return null;
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        int min = X.value;
        int max = X.value;
        Node maxSubBSTHead = null;
        int maxSubBSTSize = 0;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            if (rightInfo.maxSubBSTSize > maxSubBSTSize) {
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }
        if ((leftInfo == null ? true : (leftInfo.maxSubBSTHead == X.left && leftInfo.max < X.value))
                && (rightInfo == null ? true : (rightInfo.maxSubBSTHead == X.right && rightInfo.min > X.value))) {
            maxSubBSTHead = X;
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                    + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
        }
        return new Info(maxSubBSTHead, maxSubBSTSize, min, max);
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
        Node root = new Node(36);
        root.left = new Node(52);
        root.left.left = new Node(16);
        root.left.left.right = new Node(12);
        Node node = maxSubBSTHead1(root);
        System.out.println(node.value);

        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
