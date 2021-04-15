package traning3;

public class Code04_IsFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class NodeInfo {
        public int level;
        public int size;
        public NodeInfo(int level, int size) {
            this.level = level;
            this.size = size;
        }
    }

    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        NodeInfo info = checkFull(head);
        int fullSize = (1 << info.level) - 1;
        return info.size == fullSize;
    }

    private static NodeInfo checkFull(Node head) {
        if (head == null) {
            return new NodeInfo(0,  0);
        }
        NodeInfo left = checkFull(head.left);
        NodeInfo right = checkFull(head.right);
        int level = Math.max(left.level, right.level) + 1;
        int size = left.size + right.size + 1;
        return new NodeInfo(level, size);
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        Info all = process(head);
        return (1 << all.height) - 1 == all.nodes;
    }

    public static class Info {
        public int height;
        public int nodes;

        public Info(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
