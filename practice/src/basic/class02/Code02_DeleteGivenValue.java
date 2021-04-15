package basic.class02;

public class Code02_DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 把给定值都删除
    // head = removeValue(head, 2);
    public static Node removeValue(Node head, int num) {
        while (head != null) {
            if (head.value == num) {
                head = head.next;
            } else {
                break;
            }
        }

        Node previous = head;
        Node current = head.next;
        while (current != null) {
            if (current.value == num) {
                current = current.next;
                previous = current;
            } else {
                previous = current;
                current = current.next;
            }
        }
        return head;
    }

}
