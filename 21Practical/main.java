public class Main {
        public static void main(String[] args) {
                
	        BinaryTreeNode<String> bt1LeftLeft = new BinaryTreeNode<>("d", null, null); 
		BinaryTreeNode<String> bt1LeftRight = new BinaryTreeNode<>("e", null, null);
		BinaryTreeNode<String> bt1Left = new BinaryTreeNode<>("b", bt1LeftLeft, bt1LeftRight);
		BinaryTreeNode<String> bt1Right = new BinaryTreeNode<>("c", null, null); //
		BinaryTreeNode<String> bt1Root = new BinaryTreeNode<>("a", bt1Left, bt1Right); //creating the root of the first binary tree
		BinaryTree<String> bt1 = new BinaryTree<>(5, bt1Root); //creating the first binary tree
		
		BinaryTreeNode<Double> bt2LeftLeft = new BinaryTreeNode<>(-9.3, null, null);
		BinaryTreeNode<Double> bt2Left = new BinaryTreeNode<>(-1.5, bt2LeftLeft, null);
		BinaryTreeNode<Double> bt2Right = new BinaryTreeNode<>(2.9, null, null);
		BinaryTreeNode<Double> bt2Root = new BinaryTreeNode<>(3.4, bt2Left, bt2Right); //creating the root of the second binary tree
		BinaryTree<Double> bt2 = new BinaryTree<>(4, bt2Root); //creating the second binary tree

		System.out.println(bt1.getRoot().getValue() + ", "
				+ bt1.getRoot().getLeft().getValue() + ", "
				+ bt1.getRoot().getRight().getValue() + ", "
				+ bt1.getRoot().getLeft().getLeft().getValue() + ", "
				+ bt1.getRoot().getLeft().getRight().getValue()); //printing the values of the nodes in the first binary tree
		System.out.println(bt2.getRoot().getValue() + ", "
				+ bt2.getRoot().getLeft().getValue() + ", "
				+ bt2.getRoot().getRight().getValue() + ", "
				+ bt2.getRoot().getLeft().getLeft().getValue()); //printing the values of the nodes in the second binary tree
        }
}