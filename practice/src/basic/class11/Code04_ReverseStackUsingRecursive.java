package basic.class11;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.size() < 2) {
            return;
        }
        int temp = f(stack);
        stack.push(temp);
    }

    private static int f(Stack<Integer> stack) {
        if (stack.size() == 1) {
            return stack.pop();
        }
        Integer pop = stack.pop();
        reverse(stack);
        return pop;
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }

}
