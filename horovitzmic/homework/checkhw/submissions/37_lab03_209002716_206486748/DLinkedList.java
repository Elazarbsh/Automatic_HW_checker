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
		
		public DNode getNext(){
			return next;
		}
		
		public void setPrev(DNode prev) {
			this.prev = prev;
		}
		
		public DNode getPrev() {
			return prev;
		}
		}

	private DNode cursor;
	public DLinkedList() {
		cursor = null;
	}

	@Override
	
	public void insert(T newElement) {
		if(newElement == null) {
			throw new RuntimeException();
		}
		DNode newNode = new DNode(newElement);
		if(isEmpty()) {
			newNode.setPrev(null);
			newNode.setNext(null);
		}
		else {
			DNode temp = new DNode(cursor.getElement());
			temp.setNext(cursor.getNext());
			temp.setPrev(cursor.getPrev());			
			cursor.setNext(newNode);
			newNode.setPrev(cursor);
			if(temp.getNext() == null) {
				newNode.setNext(null);	
			} else {
				newNode.setNext(temp.getNext());
				temp.getNext().setPrev(newNode);
			}
			
		}
		cursor = newNode;
	}

	@Override
	public T remove() {
		if(isEmpty()) return null;
		T deleted = cursor.getElement();
		if(cursor.getNext() == null) { //end of the list case
			if(cursor.getPrev() == null) { //one element in the list case
				cursor = null;
			}
			else { //last element
				cursor.getPrev().setNext(null);
				goToBeginning(); 
			}
		}
		else {
//			DNode temp = new DNode(cursor.getElement());
//			temp.setNext(cursor.getNext());
//			temp.setPrev(cursor.getPrev());			
			if(cursor.getPrev() == null) { //first element of the list case
				cursor.getNext().setPrev(null);
				
			} else { //an element in the middle
				cursor.getPrev().setNext(cursor.getNext());
				cursor.getNext().setPrev(cursor.getPrev());
			}	
			
			if (!isEmpty()) 
				getNext();

		}
		
		return deleted;
	}

	@Override
	public T remove(T element) {
		goToBeginning();


		if(cursor == null) {
			return null;
		}
		
		else {
			while(cursor != null && !((cursor.getElement()).equals(element))) {
				if(cursor.getNext() == null && !((cursor.getElement()).equals(element))) {
					return null;
				}
				getNext();
				
			}
			return remove();
		}
	}

	@Override
	public void clear() {
		if(cursor != null) {
			goToBeginning();
			cursor.setNext(null);
			cursor.setPrev(null);
			cursor = null;
		}	
	}

	@Override
	public void replace(T newElement) {
		if(isEmpty() || newElement == null) 
			throw new RuntimeException();
		cursor.element = newElement;
	}

	@Override
	public String toString() {
		String list = "";
		DNode temp = cursor;
		list += "[";
		goToBeginning();
		while(true) {
			list += cursor.getElement().toString();
			if (cursor.getNext() != null) {
				list += ",";	
			}
			if(cursor.getNext() == null) break;
			getNext();
		}
		list += "]";
		cursor = temp;
		return list;
	}

	@Override
	public boolean isEmpty() {
		if(cursor == null) return true;
		return false;
	}

	@Override
	public boolean goToBeginning() {
		if(!isEmpty()) {
			if(cursor.getPrev() == null) return true;
			while(cursor.getPrev() != null) {
				getPrev();
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean goToEnd() {
		if(!isEmpty()) {
			if(cursor.getNext() == null) return true;
			while(cursor.getNext() != null) {
				getNext();
			}
			return true;
		}
		return false;
	}

	@Override
	public T getNext() {
		if(cursor == null) {
			return null;
		}
		if(cursor.getNext() != null) {
			cursor = cursor.getNext();
			return cursor.getElement();
		}
		return null;
	}

	@Override
	public T getPrev() {
		if(cursor == null) {
			return null;
		}
		if(cursor.getPrev() != null) {
			cursor = cursor.getPrev();
			return cursor.getElement();
		}
		return null;
	}

	@Override
	public T getCursor() {
		if(isEmpty()) {
			return null;
		}
		return cursor.getElement();
	}

	@Override
	public boolean hasNext() {
		if(isEmpty()) {
			return false;
		}
		if(cursor.getNext() == null) return false;
		return true;
	}

	@Override
	public boolean hasPrev() {
		if(isEmpty()) return false;
		if(cursor.getPrev() == null) return false;
		return true;
	}

}