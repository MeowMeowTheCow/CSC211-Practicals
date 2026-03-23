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
}
