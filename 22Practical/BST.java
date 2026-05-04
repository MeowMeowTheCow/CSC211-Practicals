class BinarySearchTree{
    private Node root; // private attributes
    private int size;

    public BinarySearchTree() { // default constructor
        this.root = null;
        this.size = 0;
    }

    public BinarySearchTree(Node root, int size) { // parameterised constructor
        this.root = root;
        this.size = size;
    }

    public Node getRoot() { // getter methods
        return root;
    }

    public int getSize() {
        return size;
    }

    public void setRoot(Node root) { // setter methods
        this.root = root;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private class Counter{ // counter class to keep track of count across recursive calls
      int count = 0;
    }

    public boolean search(int data){ //search method
        return searchHelper(root, data);

    }

    private boolean searchHelper(Node node, int data){
        if (node == null){
            return false;
        }
        if (data == node.getData()){
            return true;
        } 
        else if (data < node.getData()){
            return searchHelper(node.getLeft(), data);
        } else {
            return searchHelper(node.getRight(), data);
        }
    }

    public void insert(int data){ // insert method
        if (search(data)){
            System.out.println("Data already exists in the tree.");
            return;
        }
        root = insertHelper(root, data);
        size++;
    }

    private Node insertHelper(Node node, int data){
        if (node == null){
            return new Node(data, null, null);
        }
        if (data < node.getData()){
            node.setLeft(insertHelper(node.getLeft(), data));
        } else {
            node.setRight(insertHelper(node.getRight(), data));
        }
        return node;
    }

   
    public int height(){  // height method
        return heightHelper(root);
    }

    private int heightHelper(Node node){
        if (node == null){
            return 0;
        }
        int leftHeight = heightHelper(node.getLeft());
        int rightHeight = heightHelper(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void inOrder(){ // Print in order method
        inOrderHelper(root);
        System.out.println();
    }

    private void inOrderHelper(Node node){
        if (node != null){
            inOrderHelper(node.getLeft());
            System.out.print(node.getData() + " ");
            inOrderHelper(node.getRight());
        }
    }

    public int kthSmallest(int k){ // kth smallest method
        if (k > size){
            System.out.println("k is larger than the size of the tree.");
            return -1;

        }
        Counter count = new Counter();
        return kthSmallestHelper(root, k, count);
    }

    private int kthSmallestHelper(Node node, int k, Counter count){
        if (node == null){
            return -1;
        }
        
        int left = kthSmallestHelper(node.getLeft(), k, count); // traverse left subtree
        if (left != -1){
            return left;
        }

        count.count++; // visits current node
        if (count.count == k){
            return node.getData();
        }

        return kthSmallestHelper(node.getRight(), k, count); // traverse right subtree
    }

    public void delete(int data){ // delete method
        if (!search(data)){
            System.out.println("Node to be deleted does not exist in the tree.");
            return;
        }
        root = deleteHelper(root, data);
        size--;
    }

    private Node deleteHelper(Node node, int data){ 
        if (node == null){
            return null;
        }
        if (data < node.getData()){
            node.setLeft(deleteHelper(node.getLeft(), data));
        } else if (data > node.getData()){
            node.setRight(deleteHelper(node.getRight(), data));
        } else { // Node to be deleted found
            if (node.getLeft() == null && node.getRight() == null){ // Node is a leaf
                return null;
            } else if (node.getLeft() == null){ // node has one child
                return node.getRight();
            } else if (node.getRight() == null){
                return node.getLeft();
            }
            Node predecessor = findMax(node.getRight()); // node has two children
            node.setData(predecessor.getData());
            node.setRight(deleteHelper(node.getRight(), predecessor.getData()));
        }
        return node;
    }

    private Node findMax(Node node){ // helper method to find the maximum value in a subtree
        while (node.getRight() != null){
            node = node.getRight();
        }
        return node;
    }

}
    

