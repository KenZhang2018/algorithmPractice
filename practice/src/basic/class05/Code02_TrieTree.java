package basic.class05;

import java.util.HashMap;

public class Code02_TrieTree {

    public static class Node {
        int pass;
        int end;
        Node[] nexts;

        public Node() {
            nexts = new Node[26];
        }
    }

    public static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            if (word == null) return;
            char[] chars = word.toCharArray();
            Node curNode = root;
            curNode.pass++;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (curNode.nexts[index] == null) {
                    curNode.nexts[index] = new Node();
                }
                curNode.nexts[index].pass++;
                curNode = curNode.nexts[index];
            }
            curNode.end++;

        }

        public void delete(String word) {
            if (search(word) > 0) {
                char[] chars = word.toCharArray();
                Node curNode = root;
                curNode.pass--;
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    curNode.nexts[index].pass--;
                    if (curNode.nexts[index].pass == 0) {
                        curNode.nexts[index] = null;
                        return;
                    }
                    curNode = curNode.nexts[index];
                }
                curNode.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) return 0;
            char[] chars = word.toCharArray();
            Node curNode = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (curNode.nexts[index] == null) {
                    return 0;
                }
                curNode = curNode.nexts[index];
            }
            return curNode.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) return 0;
            char[] chars = pre.toCharArray();
            Node curNode = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (curNode.nexts[index] == null) {
                    return 0;
                }
                curNode = curNode.nexts[index];
            }
            return curNode.pass;
        }


    }

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = (int) chs[i];
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie trie1 = new Trie();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }


}
