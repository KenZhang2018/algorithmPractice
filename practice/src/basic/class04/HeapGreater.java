package basic.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HeapGreater<T>   {

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private Comparator<T> comparator;
//    private int heapSize;


    public HeapGreater(Comparator comparator) {
        heap = new ArrayList();
        indexMap = new HashMap<>();
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }

    public int size() {
        return heap.size();
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heap.size() - 1);
//        heapSize++;
        heapInsertUP(heap.size() - 1);
    }


    private int heapInsertUP(int curIndex) {
        while (curIndex > 0) {
            int parentIndex = (curIndex - 1) >> 1;
            if (comparator.compare(heap.get(curIndex), heap.get(parentIndex)) < 0) {
                swap(curIndex, parentIndex);
                curIndex = parentIndex;
            } else {
                break;
            }
        }
        return curIndex;
    }

    public T pop() {
        T result = heap.get(0);
        remove(result);
        return result;
    }

    private void heapDown(int curIndex) {
        int leftIndex = curIndex << 1 | 1;
        while (leftIndex <= heap.size() - 1) {
            int rightIndex = leftIndex + 1 < heap.size() ? leftIndex + 1 : -1;
            int biggerValueIndex = rightIndex != -1
                    && comparator.compare(heap.get(rightIndex), heap.get(leftIndex)) > 0 ? rightIndex : leftIndex;
            if (comparator.compare(heap.get(curIndex), heap.get(biggerValueIndex)) < 0) {
                swap(curIndex, biggerValueIndex);
                curIndex = biggerValueIndex;
                leftIndex = curIndex << 1 | 1;
            } else {
                break;
            }
        }
    }

    // 删除指定元素
    public void remove(T obj) {
        T lastOne = heap.get(heap.size() - 1);
        Integer removeIndex = indexMap.remove(obj);
        swap(removeIndex, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapDown(removeIndex);
        if (lastOne != obj) {
            heapInsertUP(removeIndex);
        }
    }

    public void resign(T obj) {

    }

    // 请返回堆上的所有元素
    public List<T> getAllElements() {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < heap.size(); i++) {
            result.add(heap.get(i));
        }
        return result;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        indexMap.put(heap.get(i), i);
        indexMap.put(heap.get(j), j);
    }


}
