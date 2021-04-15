package traning3;

import java.util.LinkedList;
import java.util.Queue;

public class Code01_IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        boolean notFull = false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            Node l = poll.left;
            Node r = poll.right;
            if (notFull && (l != null || r != null)) {
                return false;
            } else if (l == null && r != null) {
                return false;
            }
            if (l == null || r == null) {
                notFull = true;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }
        }
        return true;
    }

    public static class NodeInfo {
        public int level;
        public boolean isFull;
        public boolean isCbt;

        public NodeInfo(int level, boolean isFull, boolean isCbt) {
            this.level = level;
            this.isFull = isFull;
            this.isCbt = isCbt;
        }
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        NodeInfo info = process(head);
        return info.isCbt;
    }

    private static NodeInfo process(Node head) {
        if (head == null) {
            return new NodeInfo(0, true, true);
        }
        NodeInfo left = process(head.left);
        NodeInfo right = process(head.right);
        int level = Math.max(left.level, right.level) + 1;
        boolean isFull = left.isFull && right.isFull && left.level == right.level;
        boolean isCbt = isFull ? true : false;
        if (left.isCbt && right.isFull && (left.level - right.level == 1)) {
            isCbt = true;
        } else if (left.isFull && right.isFull && (left.level - right.level == 1)) {
            isCbt = true;
        } else if (left.isFull && right.isCbt && (left.level == right.level)) {
            isCbt = true;
        }
        return new NodeInfo(level, isFull, isCbt);
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
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
