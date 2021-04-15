package basic.class10;

import java.util.*;

public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HashSet<Node> lockedNodeSet = new HashSet<>();
        Node minDistanceNode = selectMinDistanceNode(distanceMap, lockedNodeSet);
        while (minDistanceNode != null) {
            int curDistance = distanceMap.get(minDistanceNode);
            for (Edge edge : minDistanceNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, curDistance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), curDistance + edge.weight));
                }
            }
            lockedNodeSet.add(minDistanceNode);
            minDistanceNode = selectMinDistanceNode(distanceMap, lockedNodeSet);
        }

        return distanceMap;
    }

    private static Node selectMinDistanceNode(HashMap<Node, Integer> distanceMap, HashSet<Node> lockedNodeSet) {
        Node minDistanceNode = null;
        Set<Map.Entry<Node, Integer>> entries = distanceMap.entrySet();
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : entries) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!lockedNodeSet.contains(node) && distance < minDistance) {
                minDistance = distance;
                minDistanceNode = node;
            }
        }

        return minDistanceNode;
    }

    public static HashMap<Node, Integer> dijkstra2(Node from, int size) {
        HashMap<Node, Integer> result = new HashMap<>();
//        result.put(from, 0);
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(from, 0);
        while (!nodeHeap.isEmpty()) {
            NodeRecord minDistanceNode = nodeHeap.poll();
            Node node = minDistanceNode.node;
            int distance = minDistanceNode.distance;
            result.put(node, distance);
            for (Edge edge : node.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
            }
        }
        return result;
    }


    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        public Node[] nodes;
        public HashMap<Node, Integer> heapIndexMap;
        public HashMap<Node, Integer> distanceMap;
        public int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isInHeap(Node node) {
            if (isEntried(node) && heapIndexMap.get(node) != -1) {
                return true;
            }
            return false;
        }

        public boolean isEntried(Node node) {
            return heapIndexMap.containsKey(node);
        }

        public void addOrUpdateOrIgnore(Node node, Integer distance) {
            if (!isInHeap(node)) {
                if (!isEntried(node)) {
                    nodes[size] = node;
                    heapIndexMap.put(node, size);
                    distanceMap.put(node, distance);
                    insertHeapify(node, size);
                    size++;
                }
            } else {
                Integer curDistance = distanceMap.get(node);
                if (distance < curDistance) {
                    distanceMap.put(node, distance);
                    insertHeapify(node, heapIndexMap.get(node));
                }
            }
        }

        private void insertHeapify(Node curNode, int index) {
            int parentIndex = (index - 1) / 2;
            Node parentNode = nodes[parentIndex];
            while (distanceMap.get(curNode) < distanceMap.get(parentNode)) {
                swap(nodes, index, parentIndex);
                index = parentIndex;
                curNode = parentNode;
                parentIndex = (index - 1) / 2;
                parentNode = nodes[parentIndex];
            }
        }

        public NodeRecord poll() {
            NodeRecord result = null;
            if (size > 0) {
                Node minDistanceNode = nodes[0];
                int minDistance = distanceMap.get(minDistanceNode);
                result = new NodeRecord(minDistanceNode, minDistance);
                swap(nodes, 0, size - 1);
                nodes[size - 1] = null;
                heapIndexMap.put(minDistanceNode, -1);
                distanceMap.remove(minDistanceNode);
                size--;
                heapify(nodes[0], size);
            }
            return result;
        }

        private void heapify(Node node, int size) {
            Integer index = heapIndexMap.get(node);
            int leftIndex = index * 2 + 1;
            while (leftIndex <= size) {
//                Node maxNode = nodes[leftIndex];
                int maxIndex = leftIndex;
                int rightIndex = leftIndex + 1;
                if (rightIndex <= size && distanceMap.get(nodes[leftIndex]) < distanceMap.get(nodes[rightIndex])) {
//                    maxNode = nodes[rightIndex];
                    maxIndex = rightIndex;
                }
                if (distanceMap.get(nodes[index]) > distanceMap.get(nodes[maxIndex])) {
                    swap(nodes, index, maxIndex);
                    index = maxIndex;
                    leftIndex = index * 2 + 1;
                } else {
                    break;
                }
            }
        }

        public void swap(Node[] nodes, int i, int j) {
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
            heapIndexMap.put(nodes[i], i);
            heapIndexMap.put(nodes[j], j);
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }


//    public static HashMap<Node, Integer> dijkstra1ByMyself(Node from) {
//        HashMap<Node, Integer> result = new HashMap<>();
//        result.put(from, 0);
//        HashMap<Node, Integer> candidateMap = new HashMap<>();
//        HashMap<Integer, Node> indexMap = new HashMap<>();
//        for (Node node : from.nexts) {
//            indexMap.put(node.value, node);
//        }
//        for (EdgeNotEasyUse edge : from.edges) {
//            candidateMap.put(indexMap.get(edge.to), edge.weight);
//        }
//        while (!candidateMap.isEmpty()) {
//            Node minDistanceNode = getMinDistanceNode(result, candidateMap);
//            int minDistance = candidateMap.get(minDistanceNode);
//            result.put(minDistanceNode, minDistance);
//            for (Node node : minDistanceNode.nexts) {
//                indexMap.put(node.value, node);
//            }
//            for (EdgeNotEasyUse edge : minDistanceNode.edges) {
//                if (!candidateMap.containsKey(indexMap.get(edge.to))) {
//                    Integer distance = candidateMap.get(indexMap.get(edge.to));
//                    candidateMap.put(indexMap.get(edge.to), Math.min(distance, minDistance + edge.weight));
//                } else {
//                    candidateMap.put(indexMap.get(edge.to), minDistance + edge.weight);
//                }
//            }
//        }
//        return result;
//    }
//
//    private static Node getMinDistanceNode(HashMap<Node, Integer> result, HashMap<Node, Integer> candidateMap) {
//        Node minDistanceNode = null;
//        int minDistance = Integer.MAX_VALUE;
//        for (Map.Entry<Node, Integer> entry : candidateMap.entrySet()) {
//            if (entry.getValue() < minDistance && !result.containsKey(entry.getKey())) {
//                minDistance = entry.getValue();
//                minDistanceNode = entry.getKey();
//            }
//        }
//        return minDistanceNode;
//    }

}
