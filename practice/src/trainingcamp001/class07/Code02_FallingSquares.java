package trainingcamp001.class07;

import java.util.*;

public class Code02_FallingSquares {

    public static void main(String[] args) {
        // [[46,18],[34,1],[77,40],[38,90],[16,5],[1,11],[70,79],[2,56],[67,14],[19,67]]
//        int[][] positions = {{46, 18}, {34, 1}, {77, 40}, {38,90}, {16, 5}, {1,11}, {70, 79}};
        int[][] positions = {{77, 40}, {38,90}, {16, 5}, {1,11}, {70, 79}};
        Code02_FallingSquares instance = new Code02_FallingSquares();
        List<Integer> list = instance.fallingSquares(positions);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    public List<Integer> fallingSquares(int[][] positions) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < positions.length; i++) {
            int L = positions[i][0];
            int R = positions[i][0] + positions[i][1] - 1;
            treeSet.add(L);
            treeSet.add(R);
        }
        // 构建离散化
        Map<Integer, Integer> map = new HashMap<>();
        int count = 1;
        while (!treeSet.isEmpty()) {
            Integer integer = treeSet.pollFirst();
            map.put(integer, count++);
        }
        // 利用线段树收集答案
        List<Integer> result = new ArrayList<>();
        SegmentTree segmentTree = new SegmentTree(map.size());
        int left = 1, right = map.size(), maxH = Integer.MIN_VALUE;
        for (int i = 0; i < positions.length; i++) {
            int L = map.get(positions[i][0]);
            int R = map.get(positions[i][0] + positions[i][1] - 1);
            int H = positions[i][1];
            int nextH = segmentTree.query(L, R, left, right, 1) + H;
            maxH = Math.max(maxH, nextH);
            segmentTree.update(L, R, nextH, left, right, 1);
            result.add(maxH);
        }
        return result;
    }

    public static class SegmentTree {
        int[] max;
        boolean[] update;
        int[] change;

        public SegmentTree(int size) {
            this.max = new int[(size + 1) << 2];
            this.update = new boolean[(size + 1) << 2];
            this.change = new int[(size + 1) << 2];
        }


        public void update(int L, int R, int H, int left, int right, int index) {
            if (L <= left && right <= R) {
                update[index] = true;
                change[index] = H;
                max[index] = H;
                return;
            }
            pushDown(left, right, index);
            int middle = left + ((right - left) >> 1);
            if (L <= middle) {
                update(L, R, H, left, middle, index << 1);
            }
            if (middle < R) {
                update(L, R, H, middle + 1, right, index << 1 | 1);
            }
            max[index] = Math.max(max[index << 1], max[index << 1 | 1]);
        }

        private void pushDown(int left, int right, int index) {
            if (update[index]) {
                update[index << 1] = true;
                change[index << 1] = change[index];
                max[index << 1] = max[index];

                update[index << 1 | 1] = true;
                change[index << 1 | 1] = change[index];
                max[index << 1 | 1] = max[index];

                update[index] = false;
                change[index] = 0;
            }

        }

        public int query(int L, int R, int left, int right, int index) {
            if (L <= left && right <= R) {
                return max[index];
            }
            pushDown(left, right, index);
            int leftHeight = Integer.MIN_VALUE;
            int middle = left + ((right - left) >> 1);
            if (L <= middle) {
                leftHeight = query(L, R, left, middle, index << 1);
            }
            int rightHeight = Integer.MIN_VALUE;
            if (middle < R) {
                rightHeight = query(L, R, middle + 1, right, index << 1 | 1);
            }
            return Math.max(leftHeight, rightHeight);
        }
    }




}
