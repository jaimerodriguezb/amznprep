package amznprep;

import java.util.function.DoubleToLongFunction;

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

	
	void inOrderTraverse (Node current, Node parent, DoubleLinkedList list) {
		if (current.getLeft() != null) {
			inOrderTraverse(current.getLeft(), current, list);
		} else {
			System.out.println(current.getData());
			list.addNode(current.getData());
			if (!parent.getRight().equals(current)) {
				System.out.println(parent.getData());
				list.addNode(parent.getData());
			}
			if (parent.getRight() != null && !parent.getRight().equals(current)) {
				inOrderTraverse(parent.getRight(), parent, list);
			} else {
				return;
			}
			
		}
		
	}

	public DoubleLinkedList getAsDLL() {
		DoubleLinkedList list = new DoubleLinkedList();
		inOrderTraverse(root, null, list);
		System.out.println(root.getData());
		list.addNode(root.getData());
		inOrderTraverse(root.getRight(), null, list);
		
		return list;
	}

	Node inOrder (Node current, Node parent, Node grandpa) {
		if (current.getLeft() != null) {
			inOrder(current.getLeft(), current, (grandpa!=null?grandpa:null) );
		} else {
//			System.out.println(current.getData());
			if (!parent.getRight().equals(current)) {
				current.setRight(parent);
				parent.setLeft(current);
			}
			if (!parent.getRight().equals(current)) {
//				System.out.println(parent.getData());
				if (grandpa != null && grandpa.getRight() != null ) {
					grandpa.setRight(current);
					current.setLeft(grandpa);
				}
				grandpa = parent;
			}
			if (parent.getRight() != null && !parent.getRight().equals(current)) {
				inOrder(parent.getRight(), parent, grandpa);
			} 
			else {
				current.setLeft(parent);
			}
		}
		return current;
		
	}
	
	
	void linkLeftDLL(Node left) {
		Node n;
		n = left;
		while (n.getRight() != null) {
			n = n.getRight();
		}
		
		root.setLeft(n);
		n.setRight(root);
	}

	void linkRightDLL(Node right) {
		Node n = right;
		while (n.getLeft() != null) {
			n = n.getLeft();
		}
		
		root.setRight(n);
		n.setLeft(root);
	}
	
	
	public Node converToDLL() {
		Node left, right, head;
		left = inOrder(root, null, null).getLeft();
		right = inOrder(root.getRight(), null, null);
		
		linkLeftDLL(left);
		linkRightDLL(right);
		
		head = root;
		while (head.getLeft() != null) {
			head = head.getLeft();
		}
		
		return head;
	}
	
	public static void main(String [] args) {
		BinaryTree bt = new BinaryTree(new Node(7));
		bt.getRoot().setLeft(new Node(1));
		bt.getRoot().getLeft().setLeft(new Node(0));
		bt.getRoot().getLeft().setRight(new Node(3));
		bt.getRoot().getLeft().getRight().setLeft(new Node(2));
		bt.getRoot().getLeft().getRight().setRight(new Node(5));
		bt.getRoot().getLeft().getRight().getRight().setLeft(new Node(4));
		bt.getRoot().getLeft().getRight().getRight().setRight(new Node(6));
		bt.getRoot().setRight(new Node(9));
		bt.getRoot().getRight().setLeft(new Node(8));
		bt.getRoot().getRight().setRight(new Node(10));
		
//Test 1: Is binary search tree
//		System.out.println("IS BST: " + bt.isBST());
		
//Test 2: BST to DLL
//		DoubleLinkedList list = bt.getAsDLL();
//		DLLNode head = list.getHead();
//		DLLNode n = head;;
//		while (n.getNext() != null) {
//			n = n.getNext();
//			System.out.println(n.getData());
//		}
		
//Test 3: Convert BST to DLL
		Node head = bt.converToDLL();
		do {
			System.out.println(head.getData() + " ");
			head = head.getRight();
		} while (head != null);
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