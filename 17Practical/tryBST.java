// tryBST.java
// CSC211 - Practical 17 - Binary Search Trees
// Builds a perfect binary search tree (BST) populated with integers [1..2^n - 1]
// using breadth-first mid-split insertion, verifies BST validity with isBST(),
// then deletes all even-keyed nodes. Times both operations over 30 repetitions
// and reports average time (ms) and standard deviation in a results table.
// Assisted by Claude in correcting timing methods and formatting.
import java.util.*;

class mNode { // main node class for BST
    int key;
    mNode left, right;
    public mNode(int item) {
        key = item;
        left = right = null;
    }
}

class BST { // binary search tree class
    mNode root;
    BST() {
        root = null;
    }

    mNode insertRec(mNode node, int key) { // recursive insert helper method
        if (node == null) {
            node = new mNode(key);
            return node;
        }
        if (key < node.key)
            node.left = insertRec(node.left, key);
        else if (key > node.key)
            node.right = insertRec(node.right, key);
        return node;

    }

    void insert(int key) { // public insert method that calls recursive helper
        root = insertRec(root, key);
    }

    void populate(int low, int high){ //populates BST with integers
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{low, high});
        while (!queue.isEmpty()) { // breadth-first mid-split insertion
            int[] range = queue.poll();
            int l = range[0], h = range[1];
            if (l > h) continue;
            int mid = (l + h) / 2;
            insert(mid);
            queue.add(new int[]{l, mid - 1});
            queue.add(new int[]{mid + 1, h});
        }
    }

    boolean isBST() {
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isBSTUtil(mNode node, int min, int max) { // utility method for isBST() that checks BST properties recursively
        if (node == null) return true;
        if (node.key <= min || node.key >= max) return false;
        return isBSTUtil(node.left, min, node.key) && isBSTUtil(node.right, node.key, max);
    }

    mNode findMin(mNode node) { // utility method to find the node with the minimum key in a subtree, used in deleteRecs() to find successor
        while (node.left != null) node = node.left;
        return node;
    }

    mNode deleteRecs(mNode node, int key) { // recursive delete helper method that removes a node with the specified key and maintains BST properties
        if (node == null) return null;
        if (key < node.key)
            node.left = deleteRecs(node.left, key);
        else if (key > node.key)
            node.right = deleteRecs(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
       
            mNode successor = findMin(node.right);
            node.key = successor.key;
            node.right = deleteRecs(node.right, successor.key);
        }
        return node;
    }
        void collectEvenNodes(mNode node, Queue<Integer> evenNums) { // utility method to collect keys of even nodes in a queue for deletion
        if (node == null) return;
        if (node.key % 2 == 0) evenNums.add(node.key);
        collectEvenNodes(node.left, evenNums);
        collectEvenNodes(node.right, evenNums);
    }

    void deleteEvenNodes() { // public method to delete all even-keyed nodes from the BST by first collecting their keys and then deleting them
        Queue<Integer> evenNums = new LinkedList<>();
        collectEvenNodes(root, evenNums);
        for (int key : evenNums) {
            root = deleteRecs(root, key);
        }
    }

    void clear() { // utility method to clear the tree by setting root to null, used before each timing run to ensure a fresh tree
        root = null;
    }

    public class tryBST {// main class to run correctness checks and timing experiments on the BST implementation
    static double[] runTimings(int n, int reps) {
        int low = 1;
        int high = (1 << n) - 1;

        BST tree = new BST();

        double[] populateTimes = new double[reps];
        double[] deleteTimes   = new double[reps];

        for (int i = 0; i < reps; i++) { // repeat timing runs for both populate and delete operations
            tree.clear();

            long start = System.nanoTime();
            tree.populate(low, high);
            long end = System.nanoTime();
            populateTimes[i] = (end - start) / 1_000_000.0;

            start = System.nanoTime();
            tree.deleteEvenNodes();
            end = System.nanoTime();
            deleteTimes[i] = (end - start) / 1_000_000.0;
        }

        double popAvg = average(populateTimes);
        double popStd = stdDev(populateTimes, popAvg);
        double delAvg = average(deleteTimes);
        double delStd = stdDev(deleteTimes, delAvg);

        return new double[]{popAvg, popStd, delAvg, delStd};
    }

    static double average(double[] arr) { // utility method to calculate the average of an array of doubles, used to compute average timing results
        double sum = 0;
        for (double t : arr) sum += t;
        return sum / arr.length;
    }

    static double stdDev(double[] arr, double avg) { // utility method to calculate the standard deviation of an array of doubles given the average, used to compute variability in timing results
        double sum = 0;
        for (double t : arr) sum += (t - avg) * (t - avg);
        return Math.sqrt(sum / arr.length);
    }

    public static void main(String[] args) { // main method to run correctness checks on a small BST and then perform timing experiments for larger trees, printing results in a formatted table

        System.out.println("---> Correctness check (n = 4) <---");
        BST test = new BST();
        test.populate(1, 15); 
        System.out.println("Is BST: " + test.isBST());
        test.deleteEvenNodes();
        System.out.println("Even nodes deleted. Is still BST: " + test.isBST());
        System.out.println();

        int n = 24;
        int reps = 30;

        System.out.println("---> Timing run: n = " + n + ", keys = " + ((1 << n) - 1) + ", reps = " + reps + " <---");
        System.out.println();

        double[] results = runTimings(n, reps);

        double popAvg = results[0], popStd = results[1];
        double delAvg = results[2], delStd = results[3];

        System.out.printf("%-30s %-20s %-20s %-20s%n",
                "Method", "Number of keys (n)", "Average time (ms)", "Standard Deviation");
        System.out.println("-".repeat(90));
        System.out.printf("%-30s %-20d %-20.2f %-20.2f%n",
                "Populate tree", (1 << n) - 1, popAvg, popStd);
        System.out.printf("%-30s %-20d %-20.2f %-20.2f%n",
                "Remove evens from the tree", (1 << n) - 1, delAvg, delStd);
        System.out.println();
        }
        
    }
}


