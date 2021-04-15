package ali;

import java.util.ArrayList;

public class PrintBefore {
//    输入一个数字，打印出嵌套的正方形。设输入 n；如果 n 是 奇数，则打印 1 ，5 ，9..., n 的嵌套正方形；如果 n 是偶数，则打印出 1 , 5 ,9..., n - 1 的嵌套正方形；最大n 设置为17；
//    例子：
//    如果 输入 n = 9， 则打印出下面的图形：

    public static void main(String[] args) {
        print(-1);
    }

    private static void print(int n) {
        if (n > 17) {
            n = 17;
        }
        if (n % 2 == 0 ) {
            n = n - 1;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= n / 2; i = i + 2) {
            if (i == n / 2) {
                list.add(i);
            } else {
                list.add(i);
            }
        }
        System.out.println();

        ArrayList<Integer> starIndex = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i <= n / 2; i++) {
            if (list.contains(i)) {
                starIndex.add(i);
                if (i != n / 2) {
                    starIndex.add(n - i - 1);
                }
            }
            StringBuilder stringBuilder = new StringBuilder("");
            if (i % 2 == 0) {
                if (i <= n/2) {
                    for (int j = 0; j < i; j++) {
                        appendStarOrLine(starIndex, stringBuilder, j);
                    }
                    for (int j = i; j < n - i; j++) {
                        stringBuilder.append("*");
                    }
                    for (int j = n - i; j < n; j++) {
                        appendStarOrLine(starIndex, stringBuilder, j);
                    }
                }
            } else {
                for (int j = 0; j < n; j++) {
                    appendStarOrLine(starIndex, stringBuilder, j);
                }
            }
            stringBuilder.append("\n");
            strings.add(stringBuilder.toString());
        }
        for (int i = 0; i < strings.size(); i++) {
            System.out.print(strings.get(i));
        }
        for (int i = strings.size() - 2; i >= 0 ; i--) {
            System.out.print(strings.get(i));
        }
    }

    private static void appendStarOrLine(ArrayList<Integer> starIndex, StringBuilder stringBuilder, int j) {
        if (starIndex.contains(j)) {
            stringBuilder.append("*");
        } else {
            stringBuilder.append("_");
        }
    }


}
