package trainningcamp002.class01;

public class Code02_EatGrass {

    // n份青草放在一堆
    // 先手后手都绝顶聪明
    // string "先手" "后手"
    public static String winner1(int n) {
        if (n < 0) {
            return null;
        }
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }
        int base = 1;
        while (base <= n) {
            int rest = n - base;
            String s = winner1(rest);
            if ("后手".equals(s)) {
                return "先手";
            }
            if (base > (n / 4)) {
                break;
            }
            base = base * 4;
        }
        return "后手";

    }

    // n份青草放在一堆
    // 先手后手都绝顶聪明
    // string "先手" "后手"
    public static String winner2(int n) {
        // 0  1  2  3 4
        // 后 先 后 先 先
        if (n < 5) { // base case
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        // n >= 5 时
        int base = 1; // 当前先手决定吃的草数
        // 当前是先手在选
        while (base <= n) {
            // 当前一共n份草，先手吃掉的是base份，n - base 是留给后手的草
            // 母过程 先手 在子过程里是 后手
            if (winner2(n - base).equals("后手")) {
                return "先手";
            }
            if (base > n / 4) { // 防止base*4之后溢出
                break;
            }
            base *= 4;
        }
        return "后手";
    }

    public static String winner3(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + " : " + winner1(i) + " " + winner2(i));
        }
    }

}
