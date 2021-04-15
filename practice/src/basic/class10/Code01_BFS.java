package basic.class10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_BFS {

    // 从node出发，进行宽度优先遍历
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        HashSet<Node> set = new HashSet<>();
        set.add(start);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node node : cur.nexts) {
                if (!set.contains(node)) {
                    set.add(node);
                    queue.add(node);
                }
            }
        }
    }

}
