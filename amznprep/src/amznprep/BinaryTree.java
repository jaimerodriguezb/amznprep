package amznprep;

public class BinaryTree {
	private Node root = null;
	
	public BinaryTree(Node root) {
		this.root = root;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public boolean isBST(){
		return isNodeABST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isNodeABST(Node node, int min, int max){
		if (node == null) {
			return true;
		}

		if (node.getData() < min || node.getData() > max) {
			return false;
		}
		
		return isNodeABST(node.getLeft(), min, node.getData()-1) && isNodeABST(node.getRight(), node.getData()+1, max); 
	}
	
	public static void main(String [] args) {
//		BinaryTree bt = new BinaryTree(new Node(4));
//		bt.getRoot().setLeft(new Node(2));
//		bt.getRoot().getLeft().setLeft(new Node(1));
//		bt.getRoot().getLeft().setRight(new Node(3));
//		bt.getRoot().setRight(new Node(5));
		
		BinaryTree bt = new BinaryTree(new Node(4));
		bt.getRoot().setLeft(new Node(2));
		bt.getRoot().getLeft().setLeft(new Node(1));
		bt.getRoot().getLeft().setRight(new Node(3));
		bt.getRoot().setRight(new Node(7));
		bt.getRoot().getRight().setLeft(new Node(5));
		bt.getRoot().getRight().setRight(new Node(9));
		bt.getRoot().getRight().getRight().setLeft(new Node(8));
		bt.getRoot().getRight().getRight().setRight(new Node(10));
		
		System.out.println("IS BST: " + bt.isBST());
		
		//bt.getRoot().print();
		
		
	}
}


class Node {
	
	private int data;
	private Node left;
	private Node right;
	
	public Node(int data) {
		this.data = data;
		this.left = null;
		this.left = null;
	}
	
	public int getData() {
		return data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public void print() {
		System.out.println("Data: " + data);
		if (left != null) {
			System.out.println("L: ");
			left.print();
		}
		
		if (right != null) {
			System.out.println("R: ");
			right.print();
		}
	}
	
}