package basic.class08;

public class Code03_IsBalanced {

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
        public boolean isBalance;

        public NodeInfo(int height, boolean isBalance) {
            this.height = height;
            this.isBalance = isBalance;
        }
    }

    public static boolean isBalanced1(Node head) {
        if (head == null) {
            return true;
        }
        NodeInfo nodeInfo = checkBalance(head);
        return nodeInfo.isBalance;
    }

    private static NodeInfo checkBalance(Node head) {
        if (head == null) {
            return new NodeInfo(0, true);
        }
        NodeInfo left = checkBalance(head.left);
        NodeInfo right = checkBalance(head.right);
        boolean isBalance = left.isBalance && right.isBalance && Math.abs(left.height - right.height) < 2;
        int height = Math.max(left.height, right.height) + 1;
        return new NodeInfo(height, isBalance);
    }

    public static boolean isBalanced2(Node head) {
        return process(head).isBalanced;
    }

    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }

    public static Info process(Node x) {
        if(x == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height)  + 1;
        boolean isBalanced = true;
        if(!leftInfo.isBalanced) {
            isBalanced = false;
        }
        if(!rightInfo.isBalanced) {
            isBalanced = false;
        }
        if(Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        return new Info(isBalanced, height);
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
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
