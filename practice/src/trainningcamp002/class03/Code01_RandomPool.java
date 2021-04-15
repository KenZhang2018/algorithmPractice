package trainningcamp002.class03;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class Code01_RandomPool {

    public class Pool<K> {
        private Map<K, Integer> keyIndexMap;
        private Map<Integer, K> indexKeyMap;
        private int size;

        public Pool() {
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            this.size = 0;
        }

        public void insert(K k) {
            if (!keyIndexMap.containsKey(k)) {
                keyIndexMap.put(k, size);
                indexKeyMap.put(size++, k);
            }
        }

        public K getRandom() {
            if (size == 0) {
                return null;
            }
            int randomIndex = (int) (Math.random() * size);
            return indexKeyMap.get(randomIndex);
        }

        public void delete(K k) {
            if (keyIndexMap.containsKey(k)) {
                int deleteIndex = keyIndexMap.get(k);
                int lastIndex = --size;
                // 交换位置
                K lastK = indexKeyMap.get(lastIndex);
                K deleteK = k;
                keyIndexMap.put(lastK, deleteIndex);
                keyIndexMap.put(k, lastIndex);
                indexKeyMap.put(deleteIndex, lastK);
                indexKeyMap.put(lastIndex, k);

                // 删除k
                keyIndexMap.remove(k);
                indexKeyMap.remove(lastIndex);

            }
        }
    }

    public void ff() {
        Pool<String> pool = new Pool<String>();
        pool.insert("zuo");
        pool.insert("cheng");
        pool.insert("yun");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        pool.delete("cheng");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        pool.insert("zhang");
        pool.insert("zhong");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        pool.delete("zuo");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Code01_RandomPool instance = new Code01_RandomPool();
        instance.ff();
        int maxValue = Integer.MAX_VALUE;
//        long[] longs = new long[Integer.MAX_VALUE];
        // md5 64位 7200亿+
        // sha1 80位
        byte[] secretBytes = MessageDigest.getInstance("md5").digest("abc".getBytes());
        System.out.println(getFormattedText(secretBytes));
        System.out.println(encode("hello world!!!"));

    }

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
