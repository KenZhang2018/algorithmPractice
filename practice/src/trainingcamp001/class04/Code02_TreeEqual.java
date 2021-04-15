package trainingcamp001.class04;

import java.util.ArrayList;
import java.util.List;

public class Code02_TreeEqual {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // big做头节点的树，其中是否有某棵子树的结构，是和small为头的树，完全一样的
    public static boolean containsTree1(Node big, Node small) {
        if (big == null || small == null) {
            return false;
        }
        if (isContains(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    private static boolean isContains(Node big, Node small) {
        if (big == null && small == null) {
            return true;
        }
        if (big != null && small == null) {
            return false;
        }
        if (big == null && small != null) {
            return false;
        }
        if (big.value != small.value) {
            return false;
        }
        return isContains(big.left, small.left) && isContains(big.right, small.right);
    }

    public static boolean containsTree2(Node big, Node small) {
        if (big == null || small == null) {
            return false;
        }
        List<String> bigList = new ArrayList<>();
        preSerial(big, bigList);
        List<String> smallList = new ArrayList<>();
        preSerial(small, smallList);
        int index = getIndexByKMP(bigList, smallList);
        return index != -1 ? true : false;
    }

    private static int getIndexByKMP(List<String> bigList, List<String> smallList) {
        int[] next = getNextArray(smallList);
        int bIndex = 0, sIndex = 0;
        while (bIndex < bigList.size() && sIndex < smallList.size()) {
            if (bigList.get(bIndex).equals(smallList.get(sIndex))) {
                bIndex++;
                sIndex++;
            } else if (sIndex == 0) {
                bIndex++;
            } else if (bigList.get(bIndex).equals(smallList.get(next[sIndex]))) {
                bIndex++;
                sIndex = next[sIndex] + 1;
            } else if (!bigList.get(bIndex).equals(smallList.get(next[sIndex]))){
                sIndex = next[sIndex];
            }
        }
        int index = sIndex == smallList.size() ? bIndex - smallList.size() : - 1;
        return index;
    }

    private static int[] getNextArray(List<String> smallList) {
        int[] next = new int[smallList.size()];
        next[0] = -1;
        if (smallList.size() == 1) {
            return next;
        }
        next[1] = 0;
        for (int i = 2; i < smallList.size(); i++) {
            if (smallList.get(i - 1).equals(smallList.get(next[i - 1]))) {
                next[i] = next[i - 1] + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }

    private static void preSerial(Node node, List<String> list) {
        if (node == null) {
            list.add("n");
            return;
        }
        list.add(String.valueOf(node.value));
        preSerial(node.left, list);
        preSerial(node.right, list);
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
        Node bigNode = new Node(1);
        bigNode.left = new Node(1);
        bigNode.left.right = new Node(1);
        Node smallNode = new Node(1);
        smallNode.left = new Node(1);
        boolean b = containsTree2(bigNode, smallNode);


        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

}
