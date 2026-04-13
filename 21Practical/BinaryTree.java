class BinaryTree<E>
{
    private int size; //attributes have been hidden for encapsulation
    private BinaryTreeNode<E> root;

    BinaryTree() // default constructor
    {
        size = 0;
        root = null;
    }

    BinaryTree(int size, BinaryTreeNode<E> root) //loaded constructor
    {
        this.size = size;
        this.root = root;
    }

    int getSize() //accessor method for size
    {
        return size;
    }

    BinaryTreeNode<E> getRoot() //accessor method for root
    {
        return root;
    }

    void setSize(int size) //setter method for size
    {
        this.size = size;
    }

    void setRoot(BinaryTreeNode<E> root) //setter method for root
    {
        this.root = root;
    }
}