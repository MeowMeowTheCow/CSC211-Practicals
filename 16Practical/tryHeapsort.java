import java.io.*;
import java.text.*;
import java.util.ArrayList;

class WordNode {
    String word;
    WordNode(String word) { this.word = word; }
}

class MinHeap {

    private WordNode[] heap;
    private int size;

    MinHeap(int capacity) {
        heap = new WordNode[capacity];
        size = 0;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i)   { return 2 * i + 1; }
    private int right(int i)  { return 2 * i + 2; }

    private void swap(int i, int j) {
        WordNode tmp = heap[i]; heap[i] = heap[j]; heap[j] = tmp;
    }

    private int cmp(int i, int j) {
        return heap[i].word.compareToIgnoreCase(heap[j].word);
    }

    private void shiftDown(int i, int heapSize) {
        int smallest = i;
        int l = left(i), r = right(i);

        if (l < heapSize && cmpAt(heap[l], heap[smallest]) < 0) smallest = l;
        if (r < heapSize && cmpAt(heap[r], heap[smallest]) < 0) smallest = r;

        if (smallest != i) {
            swap(i, smallest);
            siftDown(smallest, heapSize);
        }
    }

    private int cmpAt(WordNode a, WordNode b) {
        return a.word.compareToIgnoreCase(b.word);
    }

    private void shiftUp(int i) {
        while (i > 0 && cmpAt(heap[i], heap[parent(i)]) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public void BottomUp(WordNode[] data) {
        size = data.length;
        System.arraycopy(data, 0, heap, 0, size);

        for (int i = (size / 2) - 1; i >= 0; i--) {
            siftDown(i, size);
        }
    }

    public void TopDown(WordNode[] data) {
        size = 0;
        for (WordNode node : data) {
            insert(node);
        }
    }

    public void insert(WordNode node) {
        heap[size] = node;
        siftUp(size);
        size++;
    }

}
