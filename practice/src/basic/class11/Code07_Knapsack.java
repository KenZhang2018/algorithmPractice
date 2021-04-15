package basic.class11;

public class Code07_Knapsack {

    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process(w, v, bag, 0, 0);
    }

    private static int process(int[] w, int[] v, int rest, int index, int totalV) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return totalV;
        }
        int p1 = process(w, v, rest, index + 1, totalV);
        int p2 = process(w, v, rest - w[index], index + 1, totalV + v[index]);
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
//        System.out.println(dpWay(weights, values, bag));
    }

}
