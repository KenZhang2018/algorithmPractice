package basic.class01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Code02_BubbleSort {

    public static <T>  void bubbleSort(ArrayList<T> arr, Comparator<? super T> comparator) {
        if (arr == null || arr.size() < 2) return;
        for (int i = arr.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(arr.get(j), arr.get(j + 1)) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
//        for (int i = 0; i < arr.size() - 1; i++) {
//            for (int j = 0; j < arr.size() - 1 - i; j++) {
//                if (comparator.compare(arr.get(j), arr.get(j +1)) > 0) {
//                    swap(arr, j, j + 1);
//                }
//            }
//        }
    }

    public static <T> void swap(ArrayList<T> arr, int i, int j) {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }


    public static void main(String[] args) {
        Order[] orders = new Order[10];
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(new Order(3, 5,0));
        orderList.add(new Order(7, 5,1));
        orderList.add(new Order(9, 5,2));
        orderList.add(new Order(7, 4,3));
        orderList.add(new Order(7, 5,4));
        orderList.add(new Order(3, 5,5));
        orderList.add(new Order(4, 5,6));

        orders[0] = new Order(3, 5,7);
        orders[1] = new Order(7, 5,8);
        orders[2] = new Order(8, 5,9);
        orders[3] = new Order(7, 4,10);
        orders[4] = new Order(7, 5,11);
        orders[5] = new Order(3, 5,12);
        orders[6] = new Order(4, 5,13);
        orders[7] = new Order(2, 5,14);
        orders[8] = new Order(1, 5,15);
        orders[9] = new Order(6, 5,17);
        List<Order> orders1 = Arrays.asList(orders);
        orderList.addAll(orders1);
        bubbleSort(orderList, new OrderByPrice());
        bubbleSort(orderList, new OrderByCreateTime());

        for (Order order : orderList) {
            System.out.println(order.createTime + " " + order.price + " " + order.id);
        }

    }



}
