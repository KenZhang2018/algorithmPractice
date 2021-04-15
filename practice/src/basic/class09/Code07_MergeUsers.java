package basic.class09;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code07_MergeUsers {
    
    public static class User {
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    
    public static int mergeUsers(List<User> users) {
        UnionSet<User> unionSet = new UnionSet<>(users);
        Map<String, User> aMap = new HashMap<>();
        Map<String, User> bMap = new HashMap<>();
        Map<String, User> cMap = new HashMap<>();
        for (User user: users) {
            if (aMap.containsKey(user.a)) {
                unionSet.union(user, aMap.get(user.a));
            } else {
                aMap.put(user.a, user);
            }
            if (bMap.containsKey(user.b)) {
                unionSet.union(user, bMap.get(user.b));
            } else {
                bMap.put(user.b, user);
            }
            if (cMap.containsKey(user.c)) {
                unionSet.union(user, cMap.get(user.c));
            } else {
                cMap.put(user.c, user);
            }
        }
        return unionSet.getSetNumber();
    }


    public static class Node<V> {
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        public Map<V, Node> valueMap = new HashMap<>();
        public Map<Node, Node> parentMap = new HashMap<>();
        public Map<Node, Integer> sizeMap = new HashMap<>();

        public UnionSet(List<V> list) {
            for (int i = 0; i < list.size(); i++) {
                Node<V> node = new Node<>(list.get(i));
                valueMap.put(list.get(i), node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(V a, V b) {
            if (!valueMap.containsKey(a) || !valueMap.containsKey(b)) {
                return false;
            }
            Node aFather = findFatherNode(valueMap.get(a));
            Node bFather = findFatherNode(valueMap.get(b));
            return aFather == bFather;
        }

        private Node findFatherNode(Node node) {
            Node parent = parentMap.get(node);
            Node cur = node;
            while (parent != cur) {
                cur = parent;
                parent = parentMap.get(cur);
            }
            while (parentMap.get(node) != parent) {
                Node next = parentMap.get(node);
                parentMap.put(node, parent);
                node = next;
            }
            return parent;
        }

        public void union(V a, V b) {
            if (!valueMap.containsKey(a) || !valueMap.containsKey(b)) {
                return;
            }
            Node aFather = findFatherNode(valueMap.get(a));
            Node bFather = findFatherNode(valueMap.get(b));
            if (aFather != bFather) {
                if (sizeMap.get(aFather) >= sizeMap.get(bFather)) {
                    parentMap.put(bFather, aFather);
                    sizeMap.put(aFather, sizeMap.get(aFather) + sizeMap.get(bFather));
                    sizeMap.remove(bFather);
                } else {
                    parentMap.put(aFather, bFather);
                    sizeMap.put(bFather, sizeMap.get(aFather) + sizeMap.get(bFather));
                    sizeMap.remove(aFather);
                }
            }
        }

        public int getSetNumber() {
            return sizeMap.size();
        }


    }
    
}
