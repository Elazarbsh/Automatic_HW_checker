import java.io.IOException;
import java.util.ArrayList;

public class DLinkedList<T> implements List<T> {
	
	
	private DNode cursor;
	private DNode head;
	private T next;
	private T prev;

	
	
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
			this.next=next;
		}
		public DNode getNext() {
			return next;
		}
		public void setPrev(DNode prev) {
			this.prev=prev;
		}
		public DNode getPrev() {
			return prev;
		}
	}
	DLinkedList(){
		this.cursor = head;
	}
	
	@Override
	public void insert(T newElement) throws RuntimeException{
		// TODO Auto-generated method stub
		if(newElement == null) {
			throw new RuntimeException("the new element is null");
		}
		DNode temp = new DNode(newElement);
		if(head == null) {
			head = temp;
			cursor = temp;
		}
		else {
			cursor.setNext(temp);
			temp.setPrev(cursor);
			cursor = temp;
		}
		
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		if(head == null) {
			return null;
		}
		if(cursor.next == null && cursor.prev == null) {
			T deltemp = cursor.element;
			cursor = null;
			return deltemp;
		}
		if(cursor.next != null && cursor.prev != null) {
			T deltemp =cursor.element;
			cursor.next.prev=cursor.prev;
			cursor.prev.next=cursor.next;
			cursor = cursor.next;
			return deltemp;
		}
		if(cursor.next == null && cursor.prev != null) {
			DNode d = cursor;
			T deltemp = cursor.element;
			cursor = head;
			d.prev.next = null;
			return deltemp;
		}else {
			head = cursor.next;
			T deltemp = cursor.element;
			cursor = head;
			head.prev = null;
			return deltemp;
		}
	}
	@Override
	public T remove(T element) {
		// TODO Auto-generated method stub
		if(head == null) {
			return null;
		}
		while(cursor.next!=null) {
			if(cursor.element == element) {
				T deltemp = remove();
				return deltemp;
			}
			cursor = cursor.next;

		}
		return null;

	}


	@Override
	public void clear() {
		head = null;
		cursor = null;
	}

	@Override
	public void replace(T newElement) throws RuntimeException{
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new RuntimeException("null list");
		}
		if(newElement == null) {
			throw new RuntimeException("null element");
		}
		else {
			cursor.element = newElement;
		}
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(head == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean goToBeginning() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			return false;
		}else {
			cursor=head;
			return true;
		}
	}

	@Override
	public boolean goToEnd() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			return false;
		}else {
			while(cursor.next != null)
				cursor = cursor.next;
			return true;
		}	}

	@Override
	public T getNext() {
		// TODO Auto-generated method stub 
		return next;
	}

	@Override
	public T getPrev() {
		// TODO Auto-generated method stub
		return prev;
	}

	@Override
	public T getCursor(){
		// TODO Auto-generated method stub
		if(isEmpty())
			return null;
		return cursor.element;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(isEmpty() || cursor.next == null)
			return false;
		return true;
	}

	@Override
	public boolean hasPrev() {
		// TODO Auto-generated method stub
		if(isEmpty() || cursor.prev == null)
			return false;
		return true;
	}
	

}