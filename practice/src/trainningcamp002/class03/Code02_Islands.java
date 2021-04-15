package trainningcamp002.class03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code02_Islands {

    public static int countIslands1(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 1) {
                    sum++;
                    inflect(m, i, j);
                }
            }
        }
        return sum;
    }

    private static void inflect(int[][] m, int row, int column) {
        if (row < 0 || row >= m.length || column < 0 || column >= m[0].length || m[row][column] != 1) {
            return;
        }
        m[row][column] = 2;
        inflect(m, row - 1, column);
        inflect(m, row, column + 1);
        inflect(m, row + 1, column);
        inflect(m, row, column - 1);
    }

    public int countIslands2(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 1) {
                    list.add(String.valueOf(i) + "_" + String.valueOf(j));
                }
            }
        }
        UnionSet<String> unionSet = new UnionSet<>(list);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 1) {
                    String cur = String.valueOf(i) + "_" + String.valueOf(j);
                    if (j + 1 < m[0].length && m[i][j + 1] == 1) {
                        String right = String.valueOf(i) + "_" + String.valueOf(j + 1);
                        unionSet.unionSet(cur, right);
                    }
                    if (i + 1 < m.length && m[i + 1][j] == 1) {
                        String down = String.valueOf(i + 1) + "_" + String.valueOf(j);
                        unionSet.unionSet(cur, down);
                    }
                }
            }
        }
        return unionSet.size;
    }

    public class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    public class UnionSet<V> {
        public Map<V, Element> elementMap = new HashMap<>();
        public Map<Element, Element> fatherMap = new HashMap<>();
        public int size;

        public UnionSet(List<V> list) {
            for (int i = 0; i < list.size(); i++) {
                V v = list.get(i);
                Element element = new Element(v);
                elementMap.put(v, element);
                fatherMap.put(element, element);
            }
            size = list.size();
        }

        public boolean isSameSet(V v1, V v2) {
            if (!elementMap.containsKey(v1) || !elementMap.containsKey(v2)) {
                return false;
            }
            Element v1Father = findFather(elementMap.get(v1));
            Element v2Father = findFather(elementMap.get(v2));
            return v1Father == v2Father ? true : false;
        }

        private Element findFather(Element element) {
            Element cur = element;
            while (fatherMap.get(cur) != cur) {
                cur = fatherMap.get(cur);
            }
            Element father = cur;
            cur = element;
            while (father != cur) {
                Element tmp = cur;
                cur = fatherMap.get(cur);
                fatherMap.put(tmp, father);
            }
            return father;
        }

        public void unionSet(V v1, V v2) {
            if (!elementMap.containsKey(v1) || !elementMap.containsKey(v2)) {
                return;
            }
            Element v1Father = findFather(elementMap.get(v1));
            Element v2Father = findFather(elementMap.get(v2));
            if (v1Father != v2Father) {
                fatherMap.put(v2Father, v1Father);
                size--;
            }
        }

    }



    public static void main(String[] args) {
        Code02_Islands instance = new Code02_Islands();
        int[][] m1 = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands1(m1));

        int[][] m1Other = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(instance.countIslands2(m1Other));

        int[][] m2 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands1(m2));

        int[][] m2Other = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

        System.out.println(instance.countIslands2(m2Other));
        long maxValue = Long.MAX_VALUE;
    }

}
