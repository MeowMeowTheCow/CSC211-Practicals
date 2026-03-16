//tryHeapsort.java
// CSC 211 - Practical 6 - Heapsort Timings
// Assited by Claude in adapting the old timing methods for the heapsort, debugging and minor formatting (claude.ai, free version)
import java.io.*;
import java.text.*;
import java.util.*;

public class tryHeapsort {

    static class StringHeap { // Min-heap of strings

        private String[] heap;
        private int size;

        StringHeap(int capacity) {// Initialize the heap with a given capacity
            heap = new String[capacity];
            size = 0;
        }

        private int parent(int i) { return (i - 1) / 2; } 
        private int left(int i)   { return 2 * i + 1;   }
        private int right(int i)  { return 2 * i + 2;   }

        private void swap(int i, int j) {
            String t = heap[i]; heap[i] = heap[j]; heap[j] = t;
        }

        private void shiftDown(int i, int n) {// Used in Bottom-Up build
            int smallest = i;
            int l = left(i), r = right(i);
            if (l < n && heap[l].compareTo(heap[smallest]) < 0) smallest = l;
            if (r < n && heap[r].compareTo(heap[smallest]) < 0) smallest = r;
            if (smallest != i) { swap(i, smallest); shiftDown(smallest, n); }
        }

        private void shiftUp(int i) {// Used in Top-Down build
            while (i > 0 && heap[i].compareTo(heap[parent(i)]) < 0) {
                swap(i, parent(i));
                i = parent(i);
            }
        }

        void buildBottomUp(String[] words) {//Bottom-Up build: O(n)
            size = words.length;
            System.arraycopy(words, 0, heap, 0, size);
            for (int i = (size / 2) - 1; i >= 0; i--)
                shiftDown(i, size);
        }

        void TopDown(String[] words) {// Top-Down build: O(n log n)
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

        private void shiftDownLocal(String[] a, int i, int n) { // Local version of shiftDown for heapSort, to avoid modifying the original heap array
            int smallest = i;
            int l = 2*i+1, r = 2*i+2;
            if (l < n && a[l].compareTo(a[smallest]) < 0) smallest = l;
            if (r < n && a[r].compareTo(a[smallest]) < 0) smallest = r;
            if (smallest != i) {
                String t = a[i]; a[i] = a[smallest]; a[smallest] = t;
                shiftDownLocal(a, smallest, n);
            }
        }

        void printHeap(int limit) { // Print the first 'limit' elements of the heap for demonstration
            int cap = Math.min(limit, size);
            System.out.print("  Heap [first " + cap + "]: ");
            for (int i = 0; i < cap; i++)
                System.out.print(heap[i] + (i < cap - 1 ? ", " : ""));
            System.out.println();
        }
    }

    static String[] loadWords(String filename) throws IOException {//Load unique words from the file, ignoring case and non-alphabetic characters
        Set<String> seen = new LinkedHashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null)
            for (String tok : line.split("[^a-zA-Z]+"))
                if (!tok.isEmpty()) seen.add(tok.toLowerCase());
        br.close();
        return seen.toArray(new String[0]);
    }

    static void printStats(String label, double[] times) {// Print timing statistics for a set of runs
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

    public static void main(String[] args) throws IOException {// Main method to run the heapsort demo and timing tests

        String filename = (args.length > 0) ? args[0] : "ulysses.text";
        String[] words = loadWords(filename);
        int n = words.length;
 
        String[] demo = Arrays.copyOf(words, 20);
 
        System.out.println("\n========================================================");
        System.out.println("   DEMO  (first 20 words from Ulysses)");
        System.out.println("========================================================");
        System.out.println("  Words: " + Arrays.toString(demo));
 
        StringHeap dh = new StringHeap(demo.length);
 
        System.out.println("\n(a) Bottom-Up Build:");
        dh.buildBottomUp(demo);
        dh.printHeap(20);
        String[] buDemo = dh.heapSort();
        System.out.println("  Sorted: " + Arrays.toString(buDemo));
 
        System.out.println("\n(b) Top-Down Build:");
        dh.TopDown(demo);
        dh.printHeap(20);
        String[] tdDemo = dh.heapSort();
        System.out.println("  Sorted: " + Arrays.toString(tdDemo));

        System.out.println("\n========================================================");
        System.out.println("   FULL RUN  (Ulysses)");
        System.out.println("========================================================");
        System.out.println("  Unique words loaded: " + n);
 
        StringHeap heap = new StringHeap(n);
        int REPS = 30;
 
        double[] buBuild = new double[REPS];
        double[] buSort  = new double[REPS];
        double[] tdBuild = new double[REPS];
        double[] tdSort  = new double[REPS];
        String[] result  = null;

        for (int r = 0; r < REPS; r++) {
            long t0 = System.nanoTime();
            heap.buildBottomUp(words);
            buBuild[r] = (System.nanoTime() - t0) / 1e6;
 
            t0 = System.nanoTime();
            result = heap.heapSort();
            buSort[r] = (System.nanoTime() - t0) / 1e6;
        }
 
        for (int r = 0; r < REPS; r++) {
            long t0 = System.nanoTime();
            heap.TopDown(words);
            tdBuild[r] = (System.nanoTime() - t0) / 1e6;
 
            t0 = System.nanoTime();
            result = heap.heapSort();
            tdSort[r] = (System.nanoTime() - t0) / 1e6;
        }
 
        System.out.println("\n========================================================");
        System.out.printf ("   TIMING RESULTS   n=%-6d   reps=%-3d%n", n, REPS);
        System.out.println("========================================================");
 
        printStats("(a) Bottom-Up  HEAP BUILD   O(n)",       buBuild);
        printStats("(a) Bottom-Up  HEAP SORT    O(n log n)", buSort);
        printStats("(b) Top-Down   HEAP BUILD   O(n log n)", tdBuild);
        printStats("(b) Top-Down   HEAP SORT    O(n log n)", tdSort);
 
        double buTotal = 0, tdTotal = 0;
        for (double t : buBuild) buTotal += t;
        for (double t : buSort)  buTotal += t;
        for (double t : tdBuild) tdTotal += t;
        for (double t : tdSort)  tdTotal += t;
 
        System.out.println("\n========================================================");
        System.out.println("   COMBINED  (build + sort, total over " + REPS + " reps)");
        System.out.println("========================================================");
        System.out.printf ("  Bottom-Up total : %10.4f ms%n", buTotal);
        System.out.printf ("  Top-Down  total : %10.4f ms%n", tdTotal);
        System.out.printf ("  Ratio  (TD/BU)  : %10.4fx%n",   tdTotal / buTotal);


        boolean isCorrect = true; // Verify that the result is sorted correctly
        for (int i = 1; i < result.length; i++)
            if (result[i].compareTo(result[i-1]) < 0) { isCorrect = false; break; }
 
        System.out.println("\n  Sort correct    : " + isCorrect);
        System.out.println("  First 10 words  : "
            + Arrays.toString(Arrays.copyOf(result, 10)));
        System.out.println("  Last  10 words  : "
            + Arrays.toString(Arrays.copyOfRange(result, result.length - 10, result.length)));
    }
}

   