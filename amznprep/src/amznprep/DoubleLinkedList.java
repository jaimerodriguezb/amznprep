package amznprep;

public class DoubleLinkedList {
	private DLLNode head;

	private DLLNode tail;
	
	public DoubleLinkedList() {
		head = null;
		tail = null;
	}
	
	public void addNode(int data) {
		if (head == null) {
			head = new DLLNode(data);
			tail = head;
		} else {
			tail.setNext(new DLLNode(data));
			tail = tail.getNext();
		}
	}
	
	public DLLNode getHead() {
		return head;
	}
	
}

class DLLNode{
	private int data;
	private DLLNode next;
	private DLLNode prev;
	
	DLLNode (int data){
		this.data = data;
		next = null;
		prev = null;
	}

	public DLLNode getNext() {
		return next;
	}

	public void setNext(DLLNode next) {
		this.next = next;
	}

	public DLLNode getPrev() {
		return prev;
	}

	public void setPrev(DLLNode prev) {
		this.prev = prev;
	}

	public int getData() {
		return data;
	}
	
	
}
