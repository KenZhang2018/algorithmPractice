package basic.class02;

public class Code04_RingArray {

    public static class MyQueue {
        private int[] arr;
        private int capacity; // 容量
        private int size; // 元素个数
        private int head;
        private int tail;

        public MyQueue(int capacity) {
            this.capacity = capacity;
            arr = new int[capacity];
        }

        public void push(int value) {
            if (size == capacity) {
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[tail] = value;
            tail = nextIndex(tail);
//            tail = ((tail + 1) % capacity) == 0 ? 0 : tail++;
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int result = arr[head];
            head = nextIndex(head);
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return  i < (capacity - 1) ? i + 1 : 0;
        }
    }

    public static void main(String[] args) {
        int tail = 0;
        for (int i = 0; i < 15; i++) {
            tail++;
            tail = tail % 5;
//            tail = ((tail + 1) % 5) == 0 ? 0 : tail + 1;
            System.out.println(tail);
        }

    }

}
