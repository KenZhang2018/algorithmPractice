package basic.class10;

import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        HashSet<Node> set = new HashSet<>();
        set.add(node);
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        System.out.println(node.value);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    stack.push(cur);
                    stack.push(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

}
