package trainingcamp001.class07;

public class Code01_SegmentTree {

    public static class SegmentTree {

        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private boolean[] update;
        private int[] change;

        public SegmentTree(int[] origin) {
            arr = new int[origin.length + 1];
            sum = new int[(origin.length + 1) * 4];
            lazy = new int[(origin.length + 1) * 4];
            update = new boolean[(origin.length + 1) * 4];
            change = new int[(origin.length + 1) * 4];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void build(int start, int end, int index) {
            if (start == end) {
                sum[index] = arr[start];
                return;
            }
            int middle = start + ((end - start) >> 1);
            if (start <= middle) {
                build(start, middle, index << 1);
            }
            if (middle < end) {
                build(middle + 1, end, index << 1 | 1);
            }
            sum[index] = sum[index << 1] + sum[index << 1 | 1];
        }

        public void add(int L, int R, int C, int left, int right, int index) {
            if (L <= left && right <= R) {
                sum[index] += C * (right - left + 1);
                lazy[index] += C;
                return;
            }
            pushDown(index, left, right);
            int middle = left + ((right - left) >> 1);
            if (L <= middle) {
                add(L, R, C, left, middle, index << 1);
            }
            if (middle < R) {
                add(L, R, C, middle + 1, right, index << 1 | 1);
            }
            sum[index] = sum[index << 1] + sum[index << 1 | 1];
        }

        public void update(int L, int R, int C, int left, int right, int index) {
            if (L <= left && right <= R) {
                sum[index] = C * (right - left + 1);
                update[index] = true;
                change[index] = C;
                lazy[index] = 0;
                return;
            }
            pushDown(index, left, right);
            int middle = left + ((right - left) >> 1);
            if (L <= middle) {
                update(L, R, C, left, middle, index << 1);
            }
            if (middle < R) {
                update(L, R, C, middle + 1, right, index << 1 | 1);
            }
            sum[index] = sum[index << 1] + sum[index << 1 | 1];
        }

        private void pushDown(int index, int left, int right) {
            int middle = left + ((right - left) >> 1);
            if (update[index]) {
                lazy[index << 1] = 0;
                update[index << 1] = true;
                change[index << 1] = change[index];
                sum[index << 1] = change[index] * (middle - left + 1);

                lazy[index << 1 | 1] = 0;
                update[index << 1 | 1] = true;
                change[index << 1 | 1] = change[index];
                sum[index << 1 | 1] = change[index] * (right - middle);

                update[index] = false;
                change[index] = 0;
            }

            if (lazy[index] != 0) {
                sum[index << 1] += lazy[index] * (middle - left + 1) ;
                lazy[index << 1] += lazy[index];

                sum[index << 1 | 1] += lazy[index] * (right - middle);
                lazy[index << 1 | 1] += lazy[index];

                lazy[index] = 0;
            }
        }

        public long query(int L, int R, int left, int right, int index) {
            if (L <= left && right <= R) {
                return sum[index];
            }
            pushDown(index, left, right);
            int middle = left + ((right - left) >> 1);
            int ans = 0;
            if (L <= middle) {
                ans += query(L, R, left, middle, index << 1);
            }
            if (middle < R) {
                ans += query(L, R, middle + 1, right, index << 1 | 1);
            }
            return ans;
        }


    }


    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }
    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 10;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * origin.length) + 1;
                int num2 = (int) (Math.random() * origin.length) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
//                    System.out.println(j + " add L:" + L + " R:" + R + " C:" + C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
//                    System.out.println(j + " update L:" + L + " R:" + R +" C:" + C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * origin.length) + 1;
                int num2 = (int) (Math.random() * origin.length) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = { -145, -357, 255, 35, 550, -502, -911 };
//        int[] origin = { -145, -357, 255, 35, 550 };
        SegmentTree seg = new SegmentTree(origin);
        Right right = new Right(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定

        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 4, R = 6, C = -276;
        seg.build(S, N, root);
        seg.add(L, R, C, S, N, root);
        right.add(L, R, C);
//        System.out.println(seg.query(L, R, S, N, root) + "  " + right.query(L, R));

        L = 2; R = 6; C = 359;
        seg.add(L, R, C, S, N, root);
        right.add(L, R, C);
//        System.out.println(seg.query(L, R, S, N, root) + "  " + right.query(L, R));

        L = 4; R = 6; C = 630;
        seg.add(L, R, C, S, N, root);
        right.add(L, R, C);
//        System.out.println(seg.query(L, R, S, N, root) + "  " + right.query(L, R));

        L = 3; R = 3; C = -897;
        seg.update(L, R, C, S, N, root);
        right.update(L, R, C);
//        System.out.println(seg.query(L, R, S, N, root) + "  " + right.query(L, R));

        L = 2; R = 4; C= 228;
        seg.add(L, R, C, S, N, root);
        right.add(L, R, C);
//        System.out.println(seg.query(L, R, S, N, root) + "  " + right.query(L, R));

        L = 5; R = 7; C = 127;
        seg.add(L, R, C, S, N, root);
        right.add(L, R, C);
//        System.out.println(seg.query(L, R, S, N, root) + "  " + right.query(L, R));

        L = 1; R = 4; C = 186;
        seg.add(L, R, C, S, N, root);
        right.add(L, R, C);
//        System.out.println(seg.query(L, R, S, N, root) + "  " + right.query(L, R));

        L = 5; R = 6; C = -18;
        seg.update(L, R, C, S, N, root);
        right.update(L, R, C);
        System.out.println(seg.query(L, R, S, N, root) + "  " + right.query(L, R));

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }

}
