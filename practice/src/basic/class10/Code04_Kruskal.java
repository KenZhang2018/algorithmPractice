package basic.class10;

import java.util.*;

public class Code04_Kruskal {

    public static class EdgeAscComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        PriorityQueue<Edge> littleHeap = new PriorityQueue<>(new EdgeAscComparator());
        for (Edge edge : graph.edges) {
            littleHeap.offer(edge);
        }
        UnionSet unionSet = new UnionSet();
        Set<Edge> set = new HashSet<>();
        while (!littleHeap.isEmpty()) {
            Edge poll = littleHeap.poll();
            Node fromNode = graph.nodes.get(poll.from);
            Node toNode = graph.nodes.get(poll.to);
            if (!unionSet.isSameSet(fromNode, toNode)) {
                set.add(poll);
                unionSet.addNewNode(fromNode);
                unionSet.addNewNode(toNode);
                unionSet.union(fromNode, toNode);
            }
        }
        return set;
    }

//    public static class Node<V> {
//        private V value;
//
//        public Node(V value) {
//            this.value = value;
//        }
//    }

    public static class UnionSet {
        public HashMap<Node, Node> parentMap = new HashMap<>();
        public HashMap<Node, Integer> sizeMap = new HashMap<>();

        public void addNewNode(Node node) {
            if (!parentMap.containsKey(node)) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node a, Node b) {
            if (!parentMap.containsKey(a) || parentMap.containsKey(b)) {
                return false;
            }
            return parentMap.get(a) == parentMap.get(b);
        }

        public void union(Node a, Node b) {
            if (!parentMap.containsKey(a) || !parentMap.containsKey(b)) {
                return;
            }
            Node aParentNode = findParentNode(a);
            Node bParentNode = findParentNode(b);
            if (aParentNode != bParentNode) {
                Integer aSize = sizeMap.get(aParentNode);
                Integer bSize = sizeMap.get(bParentNode);
                if (aSize >= bSize) {
                    sizeMap.put(aParentNode, aSize + bSize);
                    parentMap.put(bParentNode, aParentNode);
                    sizeMap.remove(bParentNode);
                } else {
                    sizeMap.put(bParentNode, aSize + bSize);
                    parentMap.put(aParentNode, bParentNode);
                    sizeMap.remove(aParentNode);
                }
            }
        }

        private Node findParentNode(Node node) {
            Node cur = node;
            Node parent = parentMap.get(cur);
            while (parent != cur) {
                cur = parent;
                parent = parentMap.get(cur);
            }
            while (parent != node) {
                Node next = parentMap.get(node);
                parentMap.put(node, parent);
                node = next;
            }
            return parent;
        }
    }

}
