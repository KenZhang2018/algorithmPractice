package basic.class06;

public class Code03_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null || head.next == null) return head;
        int count = 0;
        Node temp = head;
        while (head != null) {
            count++;
            head = head.next;
        }
        head = temp;
        int[] arr = new int[count];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = head.value;
            head = head.next;
        }
        arrPartition(arr, pivot);
        Node newHead = new Node(arr[0]);
        Node cur = newHead;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
        }
        return newHead;
    }

    private static int[] arrPartition(int[] arr, int pivot) {
        int L = -1, R = arr.length;
        int index = 0;
        while (index < R) {
            if (arr[index] > pivot) {
                swap(arr, index, --R);
            } else if (arr[index] < pivot) {
                swap(arr, index++, ++L);
            } else {
                index++;
            }
        }
        int[] ans = new int[]{L + 1, R - 1};
        return ans;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Node listPartition2(Node head, int pivot) {
        Node smallHead = null, smallTail = null;
        Node equalHead = null, equalTail = null;
        Node bigHead = null, bigTail = null;
        while (head != null) {
            Node next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head; smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else if (head.value == pivot) {
                if (equalHead == null) {
                    equalHead = head; equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            } else if (head.value > pivot){
                if (bigHead == null) {
                    bigHead = head; bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            head = next;
        }
        if (smallTail != null) {
            smallTail.next = equalHead;
        }
        if (equalTail != null) {
            equalTail.next = bigHead;
        }

        Node newHead = smallHead != null ? smallHead : equalHead != null ? equalHead : bigHead;
        return newHead;
    }





//    public static Node listPartition2(Node head, int pivot) {
//        Node sH = null; // small head
//        Node sT = null; // small tail
//        Node eH = null; // equal head
//        Node eT = null; // equal tail
//        Node mH = null; // big head
//        Node mT = null; // big tail
//        Node next = null; // save next node
//        // every node distributed to three lists
//        while (head != null) {
//            next = head.next;
//            head.next = null;
//            if (head.value < pivot) {
//                if (sH == null) {
//                    sH = head;
//                    sT = head;
//                } else {
//                    sT.next = head;
//                    sT = head;
//                }
//            } else if (head.value == pivot) {
//                if (eH == null) {
//                    eH = head;
//                    eT = head;
//                } else {
//                    eT.next = head;
//                    eT = head;
//                }
//            } else {
//                if (mH == null) {
//                    mH = head;
//                    mT = head;
//                } else {
//                    mT.next = head;
//                    mT = head;
//                }
//            }
//            head = next;
//        }
//        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
//        if (sT != null) { // 如果有小于区域
//            sT.next = eH;
//            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
//        }
//        // 下一步，一定是需要用eT 去接 大于区域的头
//        // 有等于区域，eT -> 等于区域的尾结点
//        // 无等于区域，eT -> 小于区域的尾结点
//        // eT 尽量不为空的尾巴节点
//        if (eT != null) { // 如果小于区域和等于区域，不是都没有
//            eT.next = mH;
//        }
//        return sH != null ? sH : (eH != null ? eH : mH);
//    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//         head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }



}
