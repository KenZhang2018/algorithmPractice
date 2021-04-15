package basic.class07;

import java.util.Stack;

public class Code02_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void pre(Node head) {
        System.out.print("pre-order: ");
        Stack<Node> stack = new Stack<>();
        if (head == null) {
            return;
        }
        stack.add(head);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.value + " ");
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
        System.out.println();
    }

    public static void in(Node cur) {
        System.out.print("in-order: ");
        if (cur == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.left;
            } else {
                Node pop = stack.pop();
                System.out.print(pop.value + " ");
                cur = pop.right;
//                if (pop.right != null) {
//                    cur = pop.right;
//                }
            }
        }
        System.out.println();
    }

    public static void pos1(Node head) {
        System.out.print("pos-order: ");
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Stack<Node> ans = new Stack<>();
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            ans.push(pop);
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        while (!ans.isEmpty()) {
            System.out.print(ans.pop().value + " ");
        }
        System.out.println();
    }

    public static void pos2(Node h) {
        System.out.print("pos-order: ");
        if (h == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(h);
        Node cur = h;
        while (!stack.isEmpty()) {
            if (cur.left != null && cur.left != h && cur.right != h) {
                stack.push(cur.left);
                cur = cur.left;
            } else if (cur.right != null && cur.right != h) {
                stack.push(cur.right);
                cur = cur.right;
            } else {
                Node pop = stack.pop();
                h = pop;
                if (!stack.isEmpty()) {
                    cur = stack.peek();
                }
                System.out.print(pop.value + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }

}
