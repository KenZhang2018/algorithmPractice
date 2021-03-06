package basic.class06;

import java.util.HashMap;
import java.util.Map;

public class Code04_CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node copyListWithRand1(Node head) {
        if (head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.next != null) {
                map.get(cur).next = map.get(cur.next);
            }
            if (cur.rand != null) {
                map.get(cur).rand = map.get(cur.rand);
            }
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) return null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node node = new Node(cur.value);
            node.next = next;
            cur.next = node;

            cur = next;
        }
        Node newHead = head.next;
        cur = head;
        while (cur != null) {
            Node rand = cur.rand;
            cur.next.rand = rand == null ? null : rand.next;
            cur = cur.next.next;
        }
        // split
        cur = head;
        while (cur != null) {
            Node copy = cur.next;
            Node next = copy.next;
            Node copyNext = next == null ? null :next.next;
            cur.next = next;
            copy.next = copyNext;
            cur = next;
        }
        return newHead;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        System.out.println("???????????????");
        printRandLinkedList(head);
        System.out.println("=========================");
        res1 = copyListWithRand1(head);
        System.out.println("???????????????????????????");
        printRandLinkedList(res1);
        System.out.println("=========================");
        res2 = copyListWithRand2(head);
        System.out.println("???????????????????????????");
        printRandLinkedList(res2);
        System.out.println("=========================");
        System.out.println("?????????????????????????????????????????????");
        printRandLinkedList(head);
        System.out.println("=========================");

    }

}
