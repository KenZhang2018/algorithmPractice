package basic.class08;

public class Code06_MaxDistance {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class NodeInfo {
        public int height;
        public int maxDistance;

        public NodeInfo(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public static int maxDistance1(Node head) {
        if (head == null) {
            return 0;
        }
        NodeInfo info = process1(head);
        return info.maxDistance;
    }

    private static NodeInfo process1(Node head) {
        if (head == null) {
            return new NodeInfo(0, 0);
        }
        NodeInfo left = process1(head.left);
        NodeInfo right = process1(head.right);
        int height = Math.max(left.height, right.height) + 1;
        int maxDistance = Math.max(left.maxDistance, right.maxDistance);
        maxDistance = Math.max(maxDistance, left.height + right.height + 1);
        return new NodeInfo(height, maxDistance);
    }



    public static int maxDistance2(Node head) {
        return process(head).maxDistance;
    }

    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int m, int h) {
            maxDistance = m;
            height = h;
        }

    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int maxDistance = Math.max(Math.max(p1, p2), p3);
        return new Info(maxDistance, height);
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxDistance1(head) != maxDistance2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
