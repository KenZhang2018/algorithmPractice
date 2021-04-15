package traning3;

import basic.class04.Code02_Heap;

import java.util.PriorityQueue;

public class Circle {

    public static void main(String[] args) {
        PriorityQueue<Integer> integers = new PriorityQueue<>(new Code02_Heap.MyComparator());


        int a = 5;
        int twoA = a << 1;
        System.out.println(a + " " + twoA);
        a = a << 1;
        System.out.println(a + " " + twoA);

        String aaa = "[[[10]]]";
        Integer value = Integer.valueOf(aaa.replace("[", "").replace("]", ""));
        System.out.println(value);
        System.out.println(aaa);
        String replace = aaa.replace("[", "").replace("]", "");
        System.out.println(replace);
        System.out.println(replace == aaa);
        System.out.println(aaa.lastIndexOf("["));

        System.out.println("abc".substring(1));

        int newNumber = f(7, 4);
        System.out.println(newNumber);
    }

    public static int f(int size, int kill) {
        int newNumber = 1;
        for (int i = 2; i <= size; i++) {
            newNumber = (newNumber + kill - 1) % i + 1;
        }
        return newNumber;
    }
    public static int ff(int size) {
        int newNumber = 1;
        for (int i = 2; i <= size; i++)
            newNumber = (newNumber + 2) % i + 1;
        return newNumber;
    }

}
