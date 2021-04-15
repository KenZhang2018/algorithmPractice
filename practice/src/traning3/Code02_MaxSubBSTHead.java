package traning3;

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
        public int size;
        public int minValue;
        public int maxValue;

        public NodeInfo(Node bst, int size, int minValue, int maxValue) {
            this.bst = bst;
            this.size = size;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        NodeInfo info = getMaxSubBstHead(head);
        return info.bst;
    }

    private static NodeInfo getMaxSubBstHead(Node head) {
        if (head == null) {
            return null;
        }
        NodeInfo left = getMaxSubBstHead(head.left);
        NodeInfo right = getMaxSubBstHead(head.right);
        Node bst = null;
        int size = 0, minValue = head.value, maxValue = head.value;
        if (left != null) {
            bst = left.bst;
            size = left.size;
            minValue = Math.min(left.minValue, minValue);
            maxValue = Math.max(left.maxValue, maxValue);
        }
        if (right != null) {
            minValue = Math.min(right.minValue, minValue);
            maxValue = Math.max(right.maxValue, maxValue);
            if (right.size > size) {
                bst = right.bst;
                size = right.size;
            }
        }
        if (left == null && right == null) {
            bst = head;
            size = 1;
        } else {
            if (left != null && right == null && left.bst == head.left && left.maxValue < head.value) {
                bst = head;
                size += 1;
                maxValue = head.value;
            } else if (left == null && right != null && right.bst == head.right && head.value < right.minValue) {
                bst = head;
                size += 1;
                minValue = head.value;
            } else if (left != null && right != null && left.bst == head.left && right.bst == head.right && left.maxValue < head.value && head.value < right.minValue) {
                bst = head;
                size = left.size + right.size + 1;
                minValue = left.minValue;
                maxValue = right.maxValue;
            }
        }
        return new NodeInfo(bst, size, minValue, maxValue);
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
        if (
                (leftInfo == null ? true : (leftInfo.maxSubBSTHead == X.left && leftInfo.max < X.value))
                &&
                        (rightInfo == null ? true : (rightInfo.maxSubBSTHead == X.right && rightInfo.min > X.value))
        ) {
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
