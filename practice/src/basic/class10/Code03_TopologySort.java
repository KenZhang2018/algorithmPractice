package basic.class10;

import java.util.*;

public class Code03_TopologySort {

    // directed graph and no loop
    public static List<Node> sortedTopology(Graph graph) {
        List<Node> list = new ArrayList<>();
        Queue<Node> zeroInNodeQueue = new LinkedList<>();
        HashMap<Node, Integer> inMap = new HashMap<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInNodeQueue.add(node);
            }
        }
        while (!zeroInNodeQueue.isEmpty()) {
            Node cur = zeroInNodeQueue.poll();
            list.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInNodeQueue.add(next);
                }
            }
        }
//        while (graph.nodes.size() != 0) {
//            Set<Integer> keySet = graph.nodes.keySet();
//            for (Integer key : keySet) {
//                Node node = graph.nodes.get(key);
//                list.add(node);
//                if (node.in == 0) {
//                    for (int i = 0; i < node.nexts.size(); i++) {
//                        node.out--;
//                        Node next = node.nexts.get(i);
//                        next.in--;
//                    }
//                    graph.nodes.remove(key);
//                }
//            }
//        }
        return list;
    }


}
