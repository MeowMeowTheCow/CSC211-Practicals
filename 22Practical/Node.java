public class Node {
    private int data; //private attributes
    private Node left;
    private Node right;

    public Node() { // default constructor
        this.data = 0;
        this.left = null;
        this.right = null;
    }

    public Node(int data, Node left, Node right) { // parameterised constructor
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() { // getter methods
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setData(int data) { // setter methods
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }  

    public void setRight(Node right) {
        this.right = right;
    }
}
