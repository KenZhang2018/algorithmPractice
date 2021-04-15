package basic.class10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Code05_Prim {

    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        Graph myGraph = getMyGraph(graph);
        HashSet<Node> lockedNodeSet = new HashSet<>();
        PriorityQueue<Edge> littleHeap = new PriorityQueue(new EdgeAscComparator());
        int ans = 0;
        for (Node node : myGraph.nodes.values()) {
            if (!lockedNodeSet.contains(node)) {
                lockedNodeSet.add(node);
                for (Edge edge : node.edges) {
                    littleHeap.offer(edge);
                }
                while (!littleHeap.isEmpty()) {
                    Edge poll = littleHeap.poll();
                    Node toNode = myGraph.nodes.get(poll.to);
                    if (!lockedNodeSet.contains(toNode)) {
                        lockedNodeSet.add(toNode);
                        ans += poll.weight;
                        for (Edge edge : toNode.edges) {
                            littleHeap.offer(edge);
                        }
                    }
                }
                break;
            }
        }
//        存在问题,如果开始时默认往小根堆里添加使用了最长的边就会无缘最后的结果
//        for (Edge edge : myGraph.edges) {
//            Node fromNode = myGraph.nodes.get(edge.from);
//            if (!lockedNodeSet.contains(fromNode)) {
//                lockedNodeSet.add(fromNode);
//                littleHeap.offer(edge);
//                while (!littleHeap.isEmpty()) {
//                    Edge poll = littleHeap.poll();
//                    Node toNode = myGraph.nodes.get(poll.to);
//                    if (!lockedNodeSet.contains(toNode)) {
//                        lockedNodeSet.add(toNode);
//                        ans += poll.weight;
//                        for (Edge next : toNode.edges) {
//                            if (!lockedNodeSet.contains(myGraph.nodes.get(next.to))) {
//                                littleHeap.offer(next);
//                            }
//                        }
//                    }
//                }
//            }
//            break;
//        }
        return ans;
    }

    private static Graph getMyGraph(int[][] graph) {
        Graph myGraph = new Graph();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] != Integer.MAX_VALUE) {
                    int weight = graph[i][j];
                    int from = i; int to = j;
                    Node fromNode = new Node(from);
                    fromNode.out++;
                    Node toNode = new Node(to);
                    toNode.in++;
                    fromNode.nexts.add(toNode);
                    Edge edge = new Edge(weight, fromNode, toNode);
                    fromNode.edges.add(edge);
                    myGraph.nodes.put(from, fromNode);
                    myGraph.nodes.put(to, toNode);
                    myGraph.edges.add(edge);
                }
            }
        }
        return myGraph;
    }

    public static class EdgeAscComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

}
