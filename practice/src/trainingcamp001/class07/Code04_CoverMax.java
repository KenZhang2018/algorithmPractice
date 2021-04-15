package trainingcamp001.class07;

import java.util.*;

public class Code04_CoverMax {

    public class Rectangle {
        public int up;
        public int down;
        public int left;
        public int right;

        public Rectangle(int up, int down, int left, int right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

    }
    
    public class DownComparator implements Comparator<Rectangle> {

        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.down - o2.down;
        }
    } 
    
    public class LeftComparator implements Comparator<Rectangle> {

        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.left - o2.left;
        }
    }
    
    public class RightComparator implements Comparator<Rectangle> {

        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.right - o2.right;
        }
    }


    // 矩形数量是N
    // O(N*LogN)
    // +
    // O(N) * [ O(N) + O(N *LogN) ]
    public int maxCover(Rectangle[] recs) {
        if (recs == null || recs.length == 0) {
            return 0;
        }
        Arrays.sort(recs, new DownComparator());
        TreeSet<Rectangle> leftTreeSet = new TreeSet<>(new LeftComparator());
        int ans = 0;
        for (int i = 0; i < recs.length;) {
            do {
                leftTreeSet.add(recs[i++]);
            } while (i < recs.length && recs[i].down == recs[i - 1].down);
            removeLowerCurDown(leftTreeSet, recs[i - 1].down);
            TreeSet<Rectangle> rightTreeSet = new TreeSet<>(new RightComparator());
            for (Rectangle leftRectangle: leftTreeSet) {
                removeLowerCurLeft(rightTreeSet, leftRectangle.left);
                rightTreeSet.add(leftRectangle);
                ans = Math.max(ans, rightTreeSet.size());
            }

        }
        return ans;
    }

    private void removeLowerCurLeft(TreeSet<Rectangle> rightTreeSet, int curLeft) {
        List<Rectangle> list = new ArrayList<>();
        for (Rectangle rectangle : rightTreeSet) {
            if (rectangle.right <= curLeft) {
                list.add(rectangle);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            rightTreeSet.remove(list.get(i));
        }
    }

    private void removeLowerCurDown(TreeSet<Rectangle> leftTreeSet, int curDown) {
        List<Rectangle> list = new ArrayList<>();
        for (Rectangle rectangle : leftTreeSet) {
            if (rectangle.up <= curDown) {
                list.add(rectangle);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            leftTreeSet.remove(list.get(i));
        }
    }


}
