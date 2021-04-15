package basic.class01;

import java.util.Comparator;

public class Order {
    int price;
    int createTime;
    int id;
    Order(int price, int createTime, int id) {
        this.price = price;
        this.createTime = createTime;
        this.id = id;
    }
}
 class OrderByPrice implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.price - o2.price;
    }
}

 class OrderByCreateTime implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.createTime - o2.createTime;
    }
}
