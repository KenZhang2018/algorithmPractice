package basic.class12;

public class Code02_StickersToSpellWord {

    public static int minStickers1(String[] stickers, String target) {
        if (target == null || target.length() == 0 || stickers == null || stickers.length == 0) {
            return 0;
        }
        // 统计词频
        int[][] map = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                map[i][stickers[i].charAt(j) - 'a']++;
            }
        }
        int[] rest = new int[26];
        for (int i = 0; i < target.length(); i++) {
            rest[target.charAt(i) - 'a']++;
        }
        // 检查词频表是否有效
        for (int i = 0; i < rest.length; i++) {
            if (rest[i] > 0) {
                boolean isHas = false;
                for (int j = 0; j < stickers.length; j++) {
                    if(map[j][i] > 0) {
                        isHas = true;
                    }
                }
                if (!isHas) {
                    return 0;
                }
            }
        }
        // 递归尝试查找最少组合张数
        return process(map, target);


    }

    private static int process(int[][] map, String restStr) {
        if (restStr.length() == 0) {
            return 0;
        }
        int[] rest = new int[26];
        for (int i = 0; i < restStr.length(); i++) {
            rest[restStr.charAt(i) - 'a']++;
        }
        int result = Integer.MAX_VALUE;
        // 尝试贴纸
        for (int i = 0; i < map.length; i++) {
            if (map[i][restStr.charAt(0) - 'a'] == 0) {
                continue;
            }
            // 使用一张贴纸
            int[] nextRest = new int[26];
            for (int j = 0; j < 26; j++) {
                nextRest[j] = rest[j] - map[i][j] <= 0 ? 0 : rest[j] - map[i][j];
            }
            StringBuilder stringBuilder = new StringBuilder("");
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < nextRest[j]; k++) {
                    stringBuilder.append((char) (j + 'a'));
                }
            }
            String nextRestStr = stringBuilder.toString();
            int process = process(map, nextRestStr);
            if (process != -1) {
                result = Math.min(result, process + 1);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        String temp = "";
        for (int i = 0; i < temp.length(); i++) {
            System.out.println(temp.charAt(i) - 'a');
        }

        String[] arr = {"aaaa","bbaa","ccddd"};
        String str = "abcccccdddddbbbaaaaa";
        System.out.println(minStickers1(arr, str));
//        System.out.println(minStickers2(arr, str));


    }

}
