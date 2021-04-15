package basic.class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Code05_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node, Integer> map = new HashMap<>();
        map.put(head, 1);
        int max = 0, count = 0, nextLevel = 2;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            count++;
            Integer curLevel = map.get(poll);
            if (poll.left != null) {
                map.put(poll.left, curLevel + 1);
                queue.add(poll.left);
            }
            if (poll.right != null) {
                map.put(poll.right, curLevel + 1);
                queue.add(poll.right);
            }
            if (nextLevel == curLevel) {
                max = Math.max(max, count - 1);
                count = 1;
                nextLevel = curLevel + 1;
            }
        }
        max = Math.max(max, count);
        return max;
    }

    public static int maxWidthUseWithoutMap(Node head) {
        if (head == null) {
            return 0;
        }
        int max = 0, count = 0;
        Node end = head, nextEnd = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            count++;
            if (poll.left != null) {
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nextEnd = poll.right;
            }

            if (poll == end) {
                max = Math.max(max, count);
                count = 0;
                end = nextEnd;
            }

        }

        return max;
    }

    public static int maxWidthUseWithoutMap2(Node head) {
        if (head == null) {
            return 0;
        }
        int max = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            int size = queue.size();
            max = Math.max(max, size);
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return max;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // 当前层，最右节点是谁
        Node nextEnd = null; // 下一层，最右节点是谁
        int max = 0;
        int curLevelNodes = 0; // 当前层的节点数
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
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
        Node root = new Node(5);
        root.left = new Node(75);
        root.right = new Node(21);
        root.left.left = new Node(14);
        int i1 = maxWidthUseMap(root);
        System.out.println(i1);

        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthUseWithoutMap2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }


}
