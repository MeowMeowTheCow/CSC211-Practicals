// geeksforgeeks.org was used as a reference for the implementation of the h

public class Practical23A {

    public static void print(int[] heap) { // prints a node and its children in a ternary heap
        if (heap == null || heap.length <= 1){
            return;
        } 

        for (int i = 1; i < heap.length; i++) { 
            StringBuilder sb = new StringBuilder("Node " + heap[i] + " -> ");
            boolean hasChildren = false;

            for (int j = 0; j < 3; j++) { // calculates the indices of the children for the current node

                int childIndex = 3 * i - 1 + j;

                if (childIndex < heap.length) { 
                    if (hasChildren){

                        sb.append(", ");
                    } 

                    sb.append(heap[childIndex]);
                    hasChildren = true;
                }
            }

            if (hasChildren) {
                System.out.println(sb.toString());
            }
        }
    }

    public static int validate(int[] heap) {
        if (heap == null || heap.length <= 1) return 1;

        boolean isMinHeap = true;
        boolean isMaxHeap = true;

        for (int i = 1; i < heap.length; i++) {

            for (int j = 0; j < 3; j++) {
                int childIndex = 3 * i - 1 + j;

                if (childIndex < heap.length) {
                  
                    if (heap[i] > heap[childIndex]){
                        isMinHeap = false;
                    }
    
                    if (heap[i] < heap[childIndex]){
                        isMaxHeap = false;
                    } isMaxHeap = false;
                }
            }
        }

        if (isMinHeap || isMaxHeap) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
   
        int[] minHeap = {0, 10, 20, 25, 30, 40}; // valid min heap
        System.out.println("Testing Min Heap:");
        print(minHeap);
        System.out.println("Validation (Expected 1): " + validate(minHeap) + "\n");

        int[] maxHeap = {0, 100, 50, 40, 30, 10, -5}; // valid max heap
        System.out.println("Testing Max Heap:");
        print(maxHeap);
        System.out.println("Validation (Expected 1): " + validate(maxHeap) + "\n");

        int[] invalidHeap = {0, 50, 100, 20, 30}; // invalid heap
        System.out.println("Testing Invalid Heap:");
        print(invalidHeap);
        System.out.println("Validation (Expected -1): " + validate(invalidHeap) + "\n");
    }
}