package basic.class06;

public class Code01_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getFirstLoopNode(head1);
        Node loop2 = getFirstLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            Node end1 = getEndNode(head1);
            Node end2 = getEndNode(head2);
//            if (end1 != end2) {
//                return null;
//            } else
                if (end1 == end2) {
                Node firstIntersectNode = getFirstIntersectNode(head1, head2, end1);
                return firstIntersectNode;
            }

//        } else if ((loop1 != null && loop2 == null) || (loop1 == null && loop2 != null)){
//            return null;
        } else if (loop1 != null && loop2 != null) {
            if (loop1 == loop2) {
                Node firstIntersectNode = getFirstIntersectNode(head1, head2, loop1);
                return firstIntersectNode;
            } else {
                boolean isIntersect = isIntersect(loop1, loop2);
                return isIntersect ? loop1 : null;
            }
        }
        return null;
    }

    private static boolean isIntersect(Node loop1, Node loop2) {
        if (loop1.next == loop2.next.next) {
            return true;
        }
        Node slow = loop1.next, fast = loop2.next.next;
        while (slow != loop1) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    private static Node getFirstIntersectNode(Node head1, Node head2, Node end) {
        if (head1 == head2) {
            return head1;
        }
        Node cur1 = head1, cur2 = head2;
        int count = 1;
        while (cur1 != end) {
            count++;
            cur1 = cur1.next;
        }
        count--;
        while (cur2 != end) {
            count--;
            cur2 = cur2.next;
        }
        Node big = count >= 0 ? head1 : head2;
        Node small = count >= 0 ? head2 : head1;
        count = Math.abs(count);
        for (int i = 0; i < count; i++) {
            big = big.next;
        }
        while (big != small) {
            big = big.next;
            small = small.next;
        }
        return big;
    }

    private static Node getEndNode(Node head) {
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    private static Node getFirstLoopNode(Node head) {
        if (head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next, fast = head.next.next;
        while (slow != fast && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        while (slow != fast && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow == fast ? slow : null;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

}
