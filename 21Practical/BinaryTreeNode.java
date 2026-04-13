class BinaryTreeNode<E>
{
    private E value; //attributes have been hidden for encapsulation
    private BinaryTreeNode<E> left;
    private BinaryTreeNode<E> right;

    BinaryTreeNode() // default constructor
    {
        value = null;
        left = null;
        right = null;
    }

    BinaryTreeNode(E value, BinaryTreeNode<E> left, BinaryTreeNode<E> right) //loaded constructor
    {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    E getValue() //accessor method for value
    {
        return value;
    }

    BinaryTreeNode<E> getLeft() //accessor method for left
    {
        return left;
    }

    BinaryTreeNode<E> getRight() //accessor method for right
    {
        return right;
    }

    void setValue(E value) //setter method for value
    {
        this.value = value;
    }

    void setLeft(BinaryTreeNode<E> left) //setter method for left
    {
        this.left = left;
    }

    void setRight(BinaryTreeNode<E> right)//setter method for right
    {
        this.right = right;
    }
}