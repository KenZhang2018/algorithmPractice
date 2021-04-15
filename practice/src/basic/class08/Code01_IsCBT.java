package basic.class08;

public class Code01_IsCBT {

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
        public boolean isFull;
        public boolean isCbt;

        public NodeInfo(int height, boolean isFull, boolean isCbt) {
            this.height = height;
            this.isFull = isFull;
            this.isCbt = isCbt;
        }
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        NodeInfo info = checkCbt(head);
        return info.isCbt;
    }

    private static NodeInfo checkCbt(Node head) {
        if (head == null) {
            return new NodeInfo(0, true, true);
        }
        NodeInfo left = checkCbt(head.left);
        NodeInfo right = checkCbt(head.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isFull = left.isFull && right.isFull;
        if (isFull && left.height != right.height) {
            isFull = false;
        }
        boolean isCbt = false;
        if (isFull) {
            isCbt = true;
        } else {
            if (left.isCbt && right.isCbt) {
                // 左满,右不满
                if (left.isFull && !right.isFull) {
                    if (left.height == right.height) {
                        isCbt = true;
                    }
                }
                // 左满,右满
                if (left.isFull && right.isFull && (left.height - right.height == 1)) {
                    isCbt = true;
                }
                // 左不满,右满
                if (!left.isFull && right.isFull) {
                    if (left.height - right.height == 1) {
                        isCbt = true;
                    }
                }
            }
        }

        return new NodeInfo(height, isFull, isCbt);
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    // 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);



        int height = Math.max(leftInfo.height, rightInfo.height) + 1;


        boolean isFull = leftInfo.isFull
                &&
                rightInfo.isFull
                && leftInfo.height == rightInfo.height;


        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else { // 以x为头整棵树，不满
            if (leftInfo.isCBT && rightInfo.isCBT) {


                if (leftInfo.isCBT
                        && rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        &&
                        rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }


            }
        }
        return new Info(isFull, isCBT, height);
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
        Node root = new Node(61);
        root.left = new Node(44);
        root.left.left = new Node(21);
        root.right = new Node(94);
        root.right.left = new Node(64);
        boolean cbt1 = isCBT1(root);
        System.out.println(cbt1);

        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
