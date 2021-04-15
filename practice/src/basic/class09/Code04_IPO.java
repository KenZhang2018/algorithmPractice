package basic.class09;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_IPO {

    public static class Program {
        public int cost;
        public int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    static class CostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    static class ProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }


    // 最多K个项目
    // W是初始资金
    // Profits[] Capital[] 一定等长
    // 返回最终最大的资金
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        if (Capital == null || Capital.length == 0) return W;
        PriorityQueue<Program> cost = new PriorityQueue<>(new CostComparator());
        PriorityQueue<Program> profit = new PriorityQueue<>(new ProfitComparator());
        for (int i = 0; i < Capital.length; i++) {
            cost.offer(new Program(Capital[i], Profits[i]));
        }
        while (cost.peek().cost <= W) {
            profit.offer(cost.poll());
        }
        while (K > 0 && !profit.isEmpty()) {
            K--;
            Program poll = profit.poll();
            W += poll.profit;
            while (!cost.isEmpty() && cost.peek().cost <= W) {
                profit.offer(cost.poll());
            }
        }
        return W;
    }

}
