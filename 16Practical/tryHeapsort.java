import java.io.*;
import java.text.*;
import java.util.*;

public class tryHeapsort {

    static class StringHeap {

        private String[] heap;
        private int size;

        StringHeap(int capacity) {
            heap = new String[capacity];
            size = 0;
        }

        private int parent(int i) { return (i - 1) / 2; }
        private int left(int i)   { return 2 * i + 1;   }
        private int right(int i)  { return 2 * i + 2;   }

        private void swap(int i, int j) {
            String t = heap[i]; heap[i] = heap[j]; heap[j] = t;
        }

        private void shiftDown(int i, int n) {
            int smallest = i;
            int l = left(i), r = right(i);
            if (l < n && heap[l].compareTo(heap[smallest]) < 0) smallest = l;
            if (r < n && heap[r].compareTo(heap[smallest]) < 0) smallest = r;
            if (smallest != i) { swap(i, smallest); shiftDown(smallest, n); }
        }

        private void shiftUp(int i) {
            while (i > 0 && heap[i].compareTo(heap[parent(i)]) < 0) {
                swap(i, parent(i));
                i = parent(i);
            }
        }

        void buildBottomUp(String[] words) {
            size = words.length;
            System.arraycopy(words, 0, heap, 0, size);
            for (int i = (size / 2) - 1; i >= 0; i--)
                shiftDown(i, size);
        }

        void TopDown(String[] words) {
            size = 0;
            for (String w : words) {
                heap[size] = w;
                shiftUp(size);
                size++;
            }
        }

        String[] heapSort() {
            String[] snap = Arrays.copyOf(heap, size);
            int n = size;
            for (int end = n - 1; end > 0; end--) {
                String t = snap[0]; snap[0] = snap[end]; snap[end] = t;
                shiftDownLocal(snap, 0, end);
            }

            for (int i = 0, j = n - 1; i < j; i++, j--) {
                String t = snap[i]; snap[i] = snap[j]; snap[j] = t;
            }
            return snap;
        }

        private void shiftDownLocal(String[] a, int i, int n) {
            int smallest = i;
            int l = 2*i+1, r = 2*i+2;
            if (l < n && a[l].compareTo(a[smallest]) < 0) smallest = l;
            if (r < n && a[r].compareTo(a[smallest]) < 0) smallest = r;
            if (smallest != i) {
                String t = a[i]; a[i] = a[smallest]; a[smallest] = t;
                siftDownLocal(a, smallest, n);
            }
        }

        void printHeap(int limit) {
            int cap = Math.min(limit, size);
            System.out.print("  Heap [first " + cap + "]: ");
            for (int i = 0; i < cap; i++)
                System.out.print(heap[i] + (i < cap - 1 ? ", " : ""));
            System.out.println();
        }
    }

    static String[] loadWords(String filename) throws IOException {
        Set<String> seen = new LinkedHashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null)
            for (String tok : line.split("[^a-zA-Z]+"))
                if (!tok.isEmpty()) seen.add(tok.toLowerCase());
        br.close();
        return seen.toArray(new String[0]);
    }

    static void printStats(String label, double[] times) {
        DecimalFormat f4 = new DecimalFormat("0.0000");
        int reps = times.length;
        double sum = 0, sum2 = 0;
        for (double t : times) { sum += t; sum2 += t * t; }
        double avg  = sum / reps;
        double sdev = Math.sqrt((sum2 - reps * avg * avg) / Math.max(reps - 1, 1));
        System.out.println("\n  " + label);
        System.out.println("  ----------------------------------------------------");
        System.out.printf ("  Total time    = %10.4f ms%n", sum);
        System.out.printf ("  Average time  = %10.4f ms  +/-  %s ms%n", avg, f4.format(sdev));
        System.out.printf ("  Std deviation = %10s ms%n", f4.format(sdev));
        System.out.printf ("  Repetitions   = %10d%n", reps);
        System.out.println("  ----------------------------------------------------");
        
    }

}


   