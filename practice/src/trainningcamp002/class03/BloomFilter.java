package trainningcamp002.class03;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class BloomFilter {

    // 样本量100亿  容错率0.0001万分之一
    // 需要空间2000亿位,25G  散列函数14个
//    public static int[][] bloom = new int[2_5000][25_0000];

    public static void main(String[] args) throws NoSuchAlgorithmException {
        long twoT = 2000_0000_0000L;
        long bytes32 = 62_5000_0000L;
        long bytes64 = 31_2500_0000L;
        long rowMax = 32 * 25_0000;
//        int[][] bloom = new int[2_5000][25_0000];
        List<byte[]> originHashCodeArray = getOriginHashCode("abc");
        byte[] sha1s = originHashCodeArray.get(0);
        byte[] newMd5s = originHashCodeArray.get(1);

        setBloomFilter("abc");
        System.out.println();
        boolean isExist = isExist("abc");

        System.out.println();

        byte[] plus = plus(new byte[]{(byte) 0x0c, (byte) 0x00},
                           new byte[]{(byte) 0x18, (byte) 0x00});
        a:for (int ii = 0; ii < 100_0000; ii++) {
            int num = (int) (Math.random() * Integer.MAX_VALUE);
            byte[] bytes1 = new byte[4];
            for (int i = 0; i < 32; i++) {
                int column2 = i / 8;
                int bit2 = i % 8;
                if (((num >> i) & 1) == 1) {
                    bytes1[column2] = (byte) (bytes1[column2] | (1 << bit2));
                }
            }
            int times = 5;
            int twice = num * times;
            byte[] bytes2 = new byte[4];
            for (int i = 0; i < 32; i++) {
                int column2 = i / 8;
                int bit2 = i % 8;
                if (((twice >> i) & 1) == 1) {
                    bytes2[column2] = (byte) (bytes2[column2] | (1 << bit2));
                }
            }
            byte[] bytes = multiply(bytes1, times);
            for (int j = 0; j < bytes.length; j++) {
                if (bytes[j] != bytes2[j]) {
                    System.out.println(num + " "+ bytes2 + " " + bytes);
                    break a;
                }
            }
        }

        System.out.println();

    }

    private static boolean isExist(String str) throws NoSuchAlgorithmException {
        List<byte[]> originHashCode = getOriginHashCode(str);
        byte[] sha1s = originHashCode.get(0);
        byte[] newMd5s = originHashCode.get(1);

        int times = 14;
        long twoT = 2000_0000_0000L;
        long rowMax = 32 * 25_0000;
        List<byte[]> list = getHashCodeList(sha1s, newMd5s, times);
        for (int i = 0; i < times; i++) {
            byte[] plus = list.get(i);
            long oneLong = 1L;
            long ans = 0;
            for (int j = 0; j < 63; j++) {
                int column = j / 8;
                int bit = j % 8;
                if (((plus[column] >> bit) & 1) == 1) {
                    ans = ans | (oneLong << j);
                }
            }
            ans = ans % twoT;
            int oneInt = 1;
            int row = (int) (ans / rowMax);
            int mod = (int) (ans % rowMax);
            int column = mod / 32;
            int bit = mod % 32;
            System.out.println("row:" + row + " column:" + column + " bit:" + bit);
//            if (((bloom[row][column] >> bit) & 1) != 1) {
//                return false;
//            }
        }
        return true;
    }

    private static void setBloomFilter(String str) throws NoSuchAlgorithmException {
        List<byte[]> originHashCode = getOriginHashCode(str);
        byte[] sha1s = originHashCode.get(0);
        byte[] newMd5s = originHashCode.get(1);

        int times = 14;
        long twoT = 2000_0000_0000L;
        long rowMax = 32 * 25_0000;
        List<byte[]> list = getHashCodeList(sha1s, newMd5s, times);
        for (int i = 0; i < times; i++) {
            byte[] plus = list.get(i);
            long oneLong = 1L;
            long ans = 0;
            for (int j = 0; j < 63; j++) {
                int column = j / 8;
                int bit = j % 8;
                if (((plus[column] >> bit) & 1) == 1) {
                    ans = ans | (oneLong << j);
                }
            }
            ans = ans % twoT;
            int oneInt = 1;
            int row = (int) (ans / rowMax);
            int mod = (int) (ans % rowMax);
            int column = mod / 32;
            int bit = mod % 32;
            System.out.println("row:" + row + " column:" + column + " bit:" + bit);
//            bloom[row][column] = bloom[row][column] | (oneInt << bit);
        }

    }

    private static List<byte[]> getOriginHashCode(String str) throws NoSuchAlgorithmException {
        List<byte[]> result = new ArrayList<>();
        byte[] sha1s = MessageDigest.getInstance("SHA1").digest(str.getBytes());
        result.add(sha1s);
        byte[] md5s = MessageDigest.getInstance("md5").digest(str.getBytes());
        byte[] newMd5s = new byte[20];
        for (int i = 0; i < md5s.length; i++) {
            newMd5s[i] = md5s[i];
        }
        result.add(newMd5s);
        return result;
    }

    private static List<byte[]> getHashCodeList(byte[] sha1s, byte[] md5s, int times) {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            byte[] multiply = multiply(md5s, i);
            list.add(plus(sha1s, multiply));
        }
        return list;
    }

    private static byte[] multiply(byte[] bytes, int times) {
        byte[] result = new byte[bytes.length];
        if (times == 0) {
            return result;
        }
        if (times == 1) {
            return bytes;
        }
        for (int i = 0; i < 8; i++) {
            if (((times >> i) & 1) == 1) {
                byte[] movedBytes = moveBits(copy(bytes), i);
                result = plus(result, movedBytes);
            }
            if (i >= times) {
                break;
            }
        }
        return result;
    }

    private static byte[] copy(byte[] bytes) {
        byte[] copy = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            copy[i] = bytes[i];
        }
        return copy;
    }

    private static byte[] moveBits(byte[] bytes, int bits) {
        byte over = 0;
        for (int i = 0; i < bytes.length; i++) {
            byte tmp = 0;
            for (int j = 7; j >= 8 - bits; j--) {
                if (((bytes[i] >> j) & 1) == 1) {
                    tmp = (byte) (tmp | (1 << ((j + bits) % 8)));
                }
            }
            bytes[i] = (byte) (bytes[i] << bits);
            bytes[i] = (byte) (bytes[i] | over);
            over = tmp;
        }
        return bytes;
    }

    private static byte[] plus(byte[] bytes1, byte[] bytes2) {
        byte high = 0;
        byte[] result = new byte[bytes1.length];
        for (int i = 0; i < bytes1.length; i++) {
            byte byte1 = bytes1[i];
            byte byte2 = bytes2[i];
            byte one = 0x01;
            for (byte j = 0; j < 8; j++) {
                byte i1 = (byte) ((byte1 >> j) & one);
                byte i2 = (byte) ((byte2 >> j) & one);
                byte i3 = (byte) (i1 + i2 + high);
                high = 0;
                if (i3 >= 2) {
                    high = 1;
                    i3 -= 2;
                }
                if (i3 == 1) {
                    result[i] = (byte) (result[i] | (i3 << j));
                }
            }
        }
        return result;
    }

}
