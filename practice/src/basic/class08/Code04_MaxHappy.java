package basic.class08;

import java.util.ArrayList;
import java.util.List;

public class Code04_MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }

    public static class HappyInfo {
        public int come;
        public int notCome;

        public HappyInfo(int come, int notCome) {
            this.come = come;
            this.notCome = notCome;
        }
    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        HappyInfo info = processHappy(boss);
        return Math.max(info.come, info.notCome);
    }

    private static HappyInfo processHappy(Employee boss) {
        if (boss == null) {
            return new HappyInfo(0, 0);
        }
        List<Employee> nexts = boss.nexts;
        int come = boss.happy, notCome = 0;
        for (int i = 0; i < nexts.size(); i++) {
            HappyInfo happyInfo = processHappy(nexts.get(i));
            come = come + happyInfo.notCome;
            notCome = notCome + Math.max(happyInfo.come, happyInfo.notCome);
        }

        return new HappyInfo(come, notCome);
    }

    public static int maxHappy2(Employee head) {
        Info allInfo = process(head);
        return Math.max(allInfo.no, allInfo.yes);
    }

    public static class Info {
        public int no;
        public int yes;

        public Info(int n, int y) {
            no = n;
            yes = y;
        }
    }

    public static Info process(Employee x) {
        if (x == null) {
            return new Info(0, 0);
        }
        int no = 0;
        int yes = x.happy;
        for (Employee next : x.nexts) {
            Info nextInfo = process(next);
            no += Math.max(nextInfo.no, nextInfo.yes);
            yes += nextInfo.no;

        }
        return new Info(no, yes);
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
