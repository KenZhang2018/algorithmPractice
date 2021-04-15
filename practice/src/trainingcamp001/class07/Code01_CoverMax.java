package trainingcamp001.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax {

    public int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int maxCover = 0;
        for (int i = min ; i <= max; i++) {
            int cover = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] <= i && i < lines[j][1]) {
                    cover++;
                }
            }
            maxCover = Math.max(maxCover, cover);
        }
        return maxCover;
    }

    public int maxCover3(int[][] lines) {
        Arrays.sort(lines, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<Integer> littleHeap = new PriorityQueue<>();
        int cover = 0;
        for (int i = 0; i < lines.length; i++) {
            if (!littleHeap.isEmpty() && lines[i][0] >= littleHeap.peek()) {
                littleHeap.poll();
            }
            littleHeap.offer(lines[i][1]);
            cover = Math.max(cover, littleHeap.size());
        }
        return cover;
    }

    public class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public class LineComparatorByStart implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public int maxCover2(int[][] lines) {
        Line[] arrayLine = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            arrayLine[i] = new Line(lines[i][0], lines[i][1]);
        }
        Arrays.sort(arrayLine, new LineComparatorByStart());
        PriorityQueue<Integer> littleHeap = new PriorityQueue<>();
        int cover = 0;
        for (int i = 0; i < arrayLine.length; i++) {
            if (!littleHeap.isEmpty() && arrayLine[i].start >= littleHeap.peek()) {
                littleHeap.poll();
            }
            littleHeap.offer(arrayLine[i].end);
            cover = Math.max(cover, littleHeap.size());
        }
        return cover;
    }



    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        Code01_CoverMax instance = new Code01_CoverMax();
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = instance.maxCover1(lines);
            int ans2 = instance.maxCover3(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

}
