package trainningcamp002.class01;

public class Code01_AppleMinBags {

    public static int minBags(int apple) {
        if (apple <= 0) {
            return -1;
        }
        int most8 = apple / 8;
        for (int bag8 = most8; bag8 >= 0; bag8--) {
            int rest = apple - 8 * bag8;
            if(rest % 6 == 0) {
                return bag8 + (rest / 6);
            }
        }
        return -1;
    }

    public static int minBags2(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple - 8 * bag8;
        while (bag8 >= 0 && rest < 24) {
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    // 如果剩余苹果rest可以被装6个苹果的袋子搞定，返回袋子数量
    // 不能搞定返回-1
    public static int minBagBase6(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }

    public static void main(String[] args) {
        for(int apple = 1; apple < 100;apple++) {
            System.out.println(apple + " : "+ minBags(apple) + " " + minBags2(apple));
        }

    }

}
