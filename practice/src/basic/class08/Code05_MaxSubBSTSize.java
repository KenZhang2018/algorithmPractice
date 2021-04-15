package basic.class08;

public class Code05_MaxSubBSTSize {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class NodeInfo {
        public boolean isBst;
        public int size;
        public Integer maxValue;
        public Integer minValue;

        public NodeInfo(boolean isBst, int size, Integer maxValue, Integer minValue) {
            this.isBst = isBst;
            this.size = size;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        NodeInfo info = process1(head);
        return info.size;
    }

    private static NodeInfo process1(Node head) {
        if (head == null) {
            return new NodeInfo(true, 0, null, null);
        }
        NodeInfo left = process1(head.left);
        NodeInfo right = process1(head.right);
        boolean isBst = left.isBst && right.isBst;
        if (isBst && left.maxValue != null && left.maxValue >= head.value) {
            isBst = false;
        }
        if (isBst && right.minValue != null && right.minValue <= head.value) {
            isBst = false;
        }
        int size = Math.max(left.size, right.size);
        size = isBst ? left.size + right.size + 1 : size;
        Integer minValue = left.minValue == null ? head.value : left.minValue;
        Integer maxValue = right.maxValue == null ? head.value : right.maxValue;

        return new NodeInfo(isBst, size, maxValue, minValue);
    }

    public static int maxSubBSTSize2(Node head) {
        if(head == null) {
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }

    public static class Info {
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;

        public Info(int m, int a, int ma, int mi) {
            maxBSTSubtreeSize = m;
            allSize = a;
            max = ma;
            min = mi;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.value;
        int min = x.value;
        int allSize = 1;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
            allSize += rightInfo.allSize;
        }
        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        int p3 = -1;
        boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
            boolean rightMinMoreX = rightInfo == null ? true : (x.value < rightInfo.min);
            if (leftMaxLessX && rightMinMoreX) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
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
//            int i1 = maxSubBSTSize1(head);
//            int i2 = maxSubBSTSize2(head);
//            System.out.println(i1 + "  " + i2);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
