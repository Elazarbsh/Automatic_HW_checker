import javax.management.RuntimeErrorException;

public class DLinkedList<T> implements List<T>{
	private class DNode{
		private T element;
		private DNode next;
		private DNode prev;
		
		public DNode(T element) {
			this.element = element;
		}
		public T getElement() {
			return element;
		}
		public void setNext(DNode next) {
			this.next = next;
		}
		public DNode getNext() {
			return next;
		}
		public void setPrev(DNode prev) {
			this.prev = prev;
		}
		public DNode getPrev() {
			return prev;
		}
	}
	
	private DNode head;
	private DNode cursor;
	private DNode tail;
	
	public DLinkedList() {
		this.head = null;
		this.cursor = null;
		this.tail = null;
	}

	
	@Override
	public void insert(T newElement) {
		if(newElement == null)
		{
			throw new IllegalArgumentException("The argument is null");
		}
		if(isEmpty())
		{
			this.head = new DNode(newElement);
			this.cursor = this.head;
			this.tail = this.head;
			this.head.setNext(null);
			this.head.setPrev(null);
		}
		else
		{
			DNode newNode = new DNode(newElement);
			newNode.setPrev(this.cursor);
			newNode.setNext(this.cursor.getNext());
			if(this.cursor.getNext() != null)	// if insert node between two nodes
			{
				this.cursor.getNext().setPrev(newNode);
			}
			this.cursor.setNext(newNode);
			this.cursor = newNode;
			if(newNode.getNext() == null)
			{
				this.tail = newNode;
			}
		}
		
	}

	@Override
	public T remove() {
		T tempElement = null;
		if(isEmpty())
		{
			return tempElement;
		}
		if(this.cursor.getPrev() == null) // if the delete element is the first element in the list
		{
			tempElement = this.head.getElement();
			this.head = this.head.getNext();
			if(this.head != null) 
			{
				this.head.setPrev(null);
			}
			this.cursor = this.head;
			return tempElement;
		}
		else if(this.cursor.getNext() == null) // if the delete element is the last element in the list
		{
			tempElement = this.tail.getElement();
			this.tail = this.tail.getPrev();
			if(this.tail != null)
			{
				this.tail.setNext(null);
			}
			this.cursor = this.head;
			return tempElement;
		}

		tempElement = this.cursor.getElement();
		this.cursor.getPrev().setNext(this.cursor.getNext());
		this.cursor.getNext().setPrev(this.cursor.getPrev());
		this.cursor = this.cursor.getNext();
		
		return tempElement;
	}

	@Override
	public T remove(T element) {
		DNode tempNode = this.cursor;
		this.cursor = this.head;
		while(this.cursor != null)
		{
			if(this.cursor.getElement() == element)
			{
				return remove();
			}
			this.cursor = this.cursor.getNext();
		}
		this.cursor = tempNode;
		return null;
	}

	@Override
	public void clear() {
		this.head = null;
		this.cursor = null;
		this.tail = null;
	}

	@Override
	public void replace(T newElement) {
		if((!isEmpty()) && (newElement != null))
		{
			this.cursor.element = newElement;
		}
		else
		{
			throw new RuntimeException("The list is empty or argument is null");
		}
	}

	@Override
	public boolean isEmpty() {
		return (this.head == null);
	}

	@Override
	public boolean goToBeginning() {
		if(!isEmpty())
		{
			this.cursor = this.head;
			return true;
		}
		return false;
	}

	@Override
	public boolean goToEnd() {
		if(!isEmpty())
		{
			this.cursor = this.tail;
			return true;
		}
		return false;
	}

	@Override
	public T getNext() {
		if((!isEmpty()) && (this.cursor.getNext() != null))
		{
			this.cursor = this.cursor.getNext();
			return getCursor();
		}
		return null;
	}

	@Override
	public T getPrev() {
		if((!isEmpty()) && (this.cursor.getPrev() != null))
		{
			this.cursor = this.cursor.getPrev();
			return getCursor();
		}
		return null;
	}

	@Override
	public T getCursor() {
		if(!isEmpty())
		{
			return this.cursor.getElement();
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		if((!isEmpty()) && (this.cursor.getNext() != null))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPrev() {
		if((!isEmpty()) && (this.cursor.getPrev() != null))
		{
			return true;
		}
		return false;
	}
	
	public String toString() {
		DNode tempNode = this.cursor;
		this.cursor = this.head;
		System.out.print('[');
		while(this.cursor != null)
		{
			System.out.print(this.cursor.getElement().toString());
			this.cursor = this.cursor.getNext();
			if(this.cursor != null) {System.out.print(" ,");}
		}
		System.out.print("]\n");
		this.cursor = tempNode;
		return null;
	}
}