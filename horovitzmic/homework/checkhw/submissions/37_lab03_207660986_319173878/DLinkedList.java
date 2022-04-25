
public class DLinkedList<T> implements List<T>{
	
	private DNode head=null;
	private DNode tail=null;
	private DNode cursor;
	@Override
	public void insert(T newElement) {
			 if(this.head == null && newElement != null) {
				this.head = new DNode(newElement);
				this.head.setNext(null);
				this.head.setPrev(null);
				this.cursor = this.head;
				this.tail = head;
			}
			else if(cursor.next == null && newElement != null){
				cursor.next = new DNode(newElement);
				cursor.next.element = newElement;
				cursor.next.setPrev(cursor);
				this.tail = cursor.next;
				cursor = cursor.next;
			}
			else if(newElement != null){
				cursor.next.prev = new DNode(newElement);
				cursor.next.prev.setNext(cursor.next);
				cursor.next.prev.setPrev(cursor);
				cursor.next = cursor.next.prev;
				cursor = cursor.next;
			}
		
	}

	@Override
	public T remove() {
		if(cursor == null)
			return null;
		else if (cursor.next == null && cursor.prev != null) {
			T ele = cursor.element;
			this.tail = cursor.prev;
			cursor.prev.setNext(null);
			goToBeginning();
			return ele;
		}
		else if (cursor.prev != null && cursor.next != null) {
			T ele = cursor.element;
			cursor.prev.setNext(cursor.next);
			cursor.next.setPrev(cursor.prev);
			cursor = cursor.next;
			return ele;
			
		}
		else if (cursor.prev == null && cursor.next == null) {
			T ele = cursor.element;
			this.clear();
			return ele;
		}
		else if (cursor.prev == null && cursor.next != null) {
			T ele = cursor.element;
			
			this.cursor = cursor.next;
			this.cursor.prev = null;
			this.head = cursor;
			return ele;
		}
		return null;
	}

	@Override
	public T remove(T element) {
		if(cursor == null)
			return null;
		goToBeginning();
		while(cursor.next != null) {
			if(cursor.prev == null && cursor.element == element) {
				cursor = cursor.next;
				cursor.prev = null;
				this.head = cursor;
				return element;
			}
			if (cursor.element == element && cursor.prev != null) {
				cursor.prev.next = cursor.next;
				cursor.next.prev = cursor.prev;
				cursor = cursor.next;
				return element;
			}
			cursor=cursor.next;
		}
		if(cursor.getElement() == element && cursor.next == null && cursor.prev==null) {
			this.clear();
			return element;
		}
		else if(cursor.element == element && cursor.prev != null) {
			this.tail = cursor.prev;
			cursor.prev.next = null;
			goToBeginning();
			return element;
		}
		return null;
	}

	@Override
	public void clear() {
		this.cursor = null;
		this.head = null;
		this.tail = null;
		
	}

	@Override
	public void replace(T newElement) {
		if(cursor != null && this.head != null && newElement != null)
			this.cursor.element = newElement;
		
	}

	@Override
	public boolean isEmpty() {
		if(cursor == null)
			return true;
		return false;
	}

	@Override
	public boolean goToBeginning() {
		if(isEmpty())
			return false;
		this.cursor = head;
		return true;
	}

	@Override
	public boolean goToEnd() {
		if(isEmpty())
			return false;
		this.cursor = tail;
		return true;
	}

	@Override
	public T getNext() {
		if(hasNext() && cursor != null) {
			cursor = cursor.next;
			return cursor.getElement();
		}
		return null;
	}

	@Override
	public T getPrev() {
		if(hasPrev() & cursor != null) {
			cursor = cursor.prev;
			return cursor.getElement();
		}
		return null;
	}

	@Override
	public T getCursor() {
		if(cursor != null && cursor.getElement() !=null)
			return cursor.getElement();
		
		return null;
	}

	@Override
	public boolean hasNext() {
		if(cursor != null && cursor.next != null)
			return true;
		else
		 return false;
	}

	@Override
	public boolean hasPrev() {
		if(cursor != null && cursor.prev != null)
			return true;
		return false;
	}
	private class DNode{
		
		private T element;
		private DNode next;
		private DNode prev;
		
		public DNode(T element) {
			this.element = element;
		}
		public T getElement() {
			return this.element;
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
}
