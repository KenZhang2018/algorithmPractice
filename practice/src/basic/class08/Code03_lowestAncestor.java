package basic.class08;

import java.util.*;

public class Code03_lowestAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node lowestAncestor1(Node head, Node o1, Node o2) {

        Map<Node, Node> map = new HashMap<>();
        map.put(head, null);
        setParentOfNode(head, map);
        Set<Node> o1Set = new HashSet<>();
        o1Set.add(o1);
        Node o1Parent = map.get(o1);
        while (o1Parent != null) {
            o1Set.add(o1Parent);
            o1Parent = map.get(o1Parent);
        }
        Node o2Parent = o2;
        while (o2Parent != null) {
            if (o1Set.contains(o2Parent)) {
                return o2Parent;
            }
            o2Parent = map.get(o2Parent);
        }
        return null;
    }

    private static void setParentOfNode(Node head, Map<Node, Node> map) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            map.put(head.left, head);
            setParentOfNode(head.left, map);
        }
        if (head.right != null) {
            map.put(head.right, head);
            setParentOfNode(head.right, map);
        }

    }

    public static class NodeInfo {
        public Node lowestAncestor;
        public boolean hasO1;
        public boolean hasO2;

        public NodeInfo(Node lowestAncestor, boolean hasO1, boolean hasO2) {
            this.lowestAncestor = lowestAncestor;
            this.hasO1 = hasO1;
            this.hasO2 = hasO2;
        }
    }

    public static Node lowestAncestor2(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        NodeInfo info = findLowestAncestor(head, o1, o2);
        return info.lowestAncestor;
    }

    private static NodeInfo findLowestAncestor(Node head, Node o1, Node o2) {
        if (head == null) {
            return new NodeInfo(null, false, false);
        }
        NodeInfo left = findLowestAncestor(head.left, o1, o2);
        NodeInfo right = findLowestAncestor(head.right, o1, o2);
        Node lowestAncestor = null;
        boolean hasO1 = false, hasO2 = false;
        if (left.lowestAncestor != null) {
            lowestAncestor = left.lowestAncestor;
            hasO1 = true; hasO2 = true;
        }
        if (right.lowestAncestor != null) {
            lowestAncestor = right.lowestAncestor;
            hasO1 = true; hasO2 = true;
        }
        if (lowestAncestor == null) {
            if ((left.hasO1 && right.hasO2) || (left.hasO2 && right.hasO1)) {
                lowestAncestor = head;
                hasO1 = true; hasO2 = true;
            } else if ((left.hasO1 || right.hasO1) && head == o2) {
                lowestAncestor = head;
                hasO1 = true; hasO2 = true;
            } else if ((left.hasO2 || right.hasO2) && head == o1) {
                lowestAncestor = head;
                hasO1 = true; hasO2 = true;
            } else if (head == o1 && head == o2) {
                lowestAncestor = head;
                hasO1 = true; hasO2 = true;
            }
        }
        if (!hasO1 && (head == o1 || left.hasO1 || right.hasO1)) {
            hasO1 = true;
        }
        if (!hasO2 && (head == o2 || left.hasO2 || right.hasO2)) {
            hasO2 = true;
        }
        return new NodeInfo(lowestAncestor, hasO1, hasO2);
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
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
