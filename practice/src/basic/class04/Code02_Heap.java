package basic.class04;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_Heap {

    public static class MyMaxHeap {
        private int[] heap;
        private final int capacity;
        private int size;

        public MyMaxHeap(int curLimit) {
            heap = new int[curLimit + 1];
            this.capacity = curLimit;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            heapInsertUP(value);
        }

        private void heapInsertUP(int value) {
            size++;
            heap[size] = value;
            int cur = size, parent = size >> 1;
            while (parent > 0) {
                if (heap[parent] < heap[cur]) {
                    swap(heap, parent, cur);
                    cur = parent;
                    parent = parent >> 1;
                } else {
                    break;
                }
            }
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }
            int maxValue = heap[1];
            swap(heap, 1, size);
            size--;
            heapifyDown(1);
            return maxValue;
        }

        private void heapifyDown(int curIndex) {
            int leftIndex = curIndex << 1;
            int rightIndex = curIndex << 1 | 1;
            while (leftIndex <= size) {
                int subIndex = -1;
                if (size >= rightIndex) {
                    subIndex = heap[leftIndex] > heap[rightIndex] ? leftIndex : rightIndex;
                } else if (size == leftIndex){
                    subIndex = leftIndex;
                }
                if (heap[curIndex] < heap[subIndex]) {
                    swap(heap, curIndex, subIndex);
                    curIndex = subIndex;
                    leftIndex = curIndex << 1;
                    rightIndex = curIndex << 1 | 1;
                } else {
                    break;
                }
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static void main(String[] args) {
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        //  5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while(!heap.isEmpty()) {
            heap.poll();
//            System.out.println(heap.poll());
        }


        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
