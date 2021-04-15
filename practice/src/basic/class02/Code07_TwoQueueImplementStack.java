package basic.class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code07_TwoQueueImplementStack {

    public static class TwoQueueStack<T> {
        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueueStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public boolean push(T e) {
            return queue.offer(e);
        }

        public T poll() throws RuntimeException {
            if (queue.size() > 1) {
                while (queue.size() > 1) {
                    help.offer(queue.poll());
                }
            }
            if (queue.size() == 0) {
                while (help.size() > 1) {
                    queue.offer(help.poll());
                }
                Queue<T> temp = help;
                help = queue;
                queue = temp;
            }
            return queue.poll();

        }

        public T peek() {
            if (queue.size() > 1) {
                while (queue.size() > 1) {
                    help.offer(queue.poll());
                }
            }
            if (queue.size() == 0) {
                while (help.size() > 1) {
                    queue.offer(help.poll());
                }
                Queue<T> temp = help;
                help = queue;
                queue = temp;
            }
            return queue.peek();
        }

        public boolean isEmpty() {
            return queue.isEmpty() && help.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }

}
