package basic.class09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;


public class Code03_BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 暴力！所有情况都尝试！
    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
//        int number = process(programs, 0,  0);
        int number = process1(programs, 0, 0, 0);
        return number;
    }

    private static int process1(Program[] programs, int done, int index, int end) {
//        if (programs.length == index) { // 错误
//            return done;
//        }
        boolean notEnd = false;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= end) {
                notEnd = true;
            }
        }
        if (!notEnd) {
            return done;
        }
        int max = 0;
        for (int i = index; i < programs.length; i++) {
            swap(programs, index, i);
            if (programs[index].start >= end) {
                int p2 = process1(programs, done + 1, index + 1, programs[index].end);
                max = Math.max(max, p2);
            }
            swap(programs, index, i);
        }
        return max;
    }

    private static int process(Program[] programs, int done, int end) {
        int number = done;
        if (programs.length == 0) {
            return number;
        }

        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= end) {
                Program[] next = copyButExceptI(programs, i);
                int p2 = process(next, done + 1, programs[i].end);
                number = Math.max(number, p2);
            }
        }
        return number;
    }

    private static Program[] copyButExceptI(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int j = 0; j < programs.length; j++) {
            if (j != i) {
                ans[index++] = programs[j];
            }
        }
        return ans;
    }

    private static void swap(Program[] programs, int index, int i) {
        Program temp = programs[index];
        programs[index] = programs[i];
        programs[i] = temp;
    }

    public static class MyComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange2(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        Arrays.sort(programs, new MyComparator());
        int end = 0, count = 0;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= end) {
                count++;
                end = programs[i].end;
            }
        }
        return count;
    }

    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
