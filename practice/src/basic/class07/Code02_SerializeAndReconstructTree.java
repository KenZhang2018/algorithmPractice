package basic.class07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code02_SerializeAndReconstructTree {

    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *
     * */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Queue<String> preSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pre(head, queue);
        return queue;
    }

    private static void pre(Node head, Queue<String> queue) {
        if (head == null) {
            queue.offer(null);
            return;
        }
        queue.offer(String.valueOf(head.value));
        pre(head.left, queue);
        pre(head.right, queue);

    }

    public static Node buildByPreQueue(Queue<String> prelist) {
        if (prelist == null || prelist.size() == 0) {
            return null;
        }
        return pre(prelist);
    }

    private static Node pre(Queue<String> prelist) {
        String poll = prelist.poll();
        Node ans = null;
        if (poll == null) {
            return null;
        }
        ans = new Node(Integer.parseInt(poll));
        ans.left = pre(prelist);
        ans.right = pre(prelist);
        return ans;
    }

    public static Queue<String> inSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        in(head, queue);
        return queue;
    }

    private static void in(Node head, Queue<String> queue) {
        if (head == null) {
            queue.offer(null);
            return;
        }
        in(head.left, queue);
        queue.offer(String.valueOf(head.value));
        in(head.right, queue);
    }

    public static Queue<String> posSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pos(head, queue);
        return queue;
    }

    private static void pos(Node head, Queue<String> queue) {
        if (head == null) {
            queue.offer(null);
            return;
        }
        pos(head.left, queue);
        pos(head.right, queue);
        queue.offer(String.valueOf(head.value));
    }

    public static Node buildByPosQueue(Queue<String> poslist) {
        if (poslist == null || poslist.size() == 0) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!poslist.isEmpty()) {
            stack.push(poslist.poll());
        }
        return posb(stack);
    }

    private static Node posb(Stack<String> stack) {
        if (stack == null || stack.size() == 0) {
            return null;
        }
        String pop = stack.pop();
        if (pop == null) {
            return null;
        }
        Node ans = new Node(Integer.parseInt(pop));
        ans.right = posb(stack);
        ans.left = posb(stack);
        return ans;
    }


    public static Queue<String> levelSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        if (head == null) {
            return queue;
        }
        Queue<Node> help = new LinkedList<>();
        help.add(head);

        while (!help.isEmpty()) {
            int size = help.size();
            for (int i = 0; i < size; i++) {
                Node poll = help.poll();
                if (poll != null) {
                    queue.add(String.valueOf(poll.value));
                    help.add(poll.left);
                    help.add(poll.right);
                } else {
                    queue.add(null);
                }
            }
        }
        return queue;
    }

    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Queue<Node> help = new LinkedList<>();
        String top = levelList.poll();
        if (top == null) {
            return null;
        }
        Node root = generateNode(top);
        help.add(root);
        while (!help.isEmpty()) {
            int size = help.size();
            for (int i = 0; i < size; i++) {
                Node poll = help.poll();
                if (poll != null) {
                    poll.left = generateNode(levelList.poll());
                    help.add(poll.left);
                    poll.right = generateNode(levelList.poll());
                    help.add(poll.right);
                }
            }
        }
        return root;
    }


    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
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

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
//        Node root = new Node(2);
//        root.left = new Node(30);
//        root.left.left = new Node(8);
//        Queue<String> level1 = levelSerial(root);
//        Object[] objects = level1.toArray();
//        for (int i = 0; i < level1.size(); i++) {
//            System.out.println(objects[i]);
//        }
//        Node levelBuild1 = buildByLevelQueue(level1);

        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
//            if (!isSameValueStructure(posBuild, levelBuild)) {
//                System.out.println("Oops!");
//            }
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

}
