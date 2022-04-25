public class DLinkedList<T> implements List<T>{
	
	private class DNode {//a link in the list
		private T element;
		private DNode next;
		private DNode prev;
		
		public DNode(T element) {
			this.element=element;
		}
		
		public T getElement() {
			return this.element;
		}
		
		public void setNext(DNode next) {
			this.next=next;
		}
		
		public DNode getNext() {
			return this.next;
		}
		
		public void setPrev(DNode prev) {
			this.prev=prev;
		}
		
		public DNode getPrev() {
			return this.prev;
		}
	}
	
	DNode head;
	DNode crouser;
	DNode tail;
	
	public DLinkedList(){
		head= new DNode(null);
		crouser =new DNode(null);
		tail= new DNode(null);
	}


	
	public void insert(T newElement)  {
		if(newElement==null)
			throw new RuntimeException("new item cant be empty");
		DNode node= new DNode(newElement);
		if(isEmpty()) {
			head.setNext(node);
			tail.setPrev(node);;
		}
			else {
				if(crouser.getNext()!=null) {
					node.setNext(crouser.getNext());
					crouser.getNext().setPrev(node);
				}
				else {
					tail.setPrev(node);
				}
				crouser.setNext(node);
				node.setPrev(crouser);
			}
		crouser=node;
	}


	public T remove() {
		DNode retVal = null;
		if(isEmpty()) {
			return null;
		}
		if(crouser.getNext()!=null && crouser.getPrev() !=null ) {
			crouser.getPrev().setNext(crouser.getNext());
			crouser.getNext().setPrev(crouser.getPrev());
			retVal=crouser;
			crouser=crouser.getNext();
		}
		else {
		if(crouser.getNext()!=null && crouser.getPrev() ==null ) {
			crouser.getNext().setPrev(crouser.getPrev());
			retVal=crouser;
			crouser=crouser.getNext();
			head.setNext(crouser);
		}
		else {
		if(crouser.getNext()==null && crouser.getPrev() !=null ) {
			crouser.getPrev().setNext(crouser.getNext());
			retVal=crouser;
			crouser=crouser.getPrev();
			tail.setPrev(crouser.getPrev());
		}
		else {
		if(crouser.getNext()==null && crouser.getPrev() ==null ) {
			retVal=crouser;
			crouser=crouser.getNext();
			head.setNext(null);
			tail.setPrev(null);
		}
		}
		}
		}
		return retVal.getElement();
	}

	@Override
	public T remove(T element) {
		if(isEmpty()) {
			return null;
		}
		goToBeginning();
		while(hasNext()) {
			if(crouser.getElement()== element)
				return remove();
		}
		if(crouser.getElement()== element)
			return remove();
		return null;
		
		
	}

	@Override
	public void clear() {
		if(isEmpty())
			return;
		crouser.setNext(null);
		crouser.setPrev(null);
		head.setNext(null);
		tail.setPrev(null);
		
	}

	@Override
	public void replace(T newElement) {
		if(newElement==null)
			throw new RuntimeException("new item cant be empty");
		if(isEmpty())
			throw new RuntimeException("list is empty cant be empty");
		crouser.element=newElement;
		
	}

	@Override
	public boolean isEmpty() {
		if(head.getNext()==null)
			return true;
		return false;
	}

	@Override
	public boolean goToBeginning() {
		if(head.getNext() != null) {
			crouser = head.getNext();
			return true;
		}
		return false;
	}

	@Override
	public boolean goToEnd() {
		if(tail.getPrev() != null) {
			crouser = tail.getPrev();
			return true;
		}
		return false;
	}

	@Override
	public T getNext() {
		if(crouser.next != null) {
			crouser=crouser.next;
			return crouser.getElement();
		}
		return null;
	}

	@Override
	public T getPrev() {
		if(crouser.getPrev()!=null) {
			crouser=crouser.getPrev();
			return crouser.getElement();
		}
		return null;
	}

	@Override
	public T getCursor() {
		if(isEmpty())
			return null;
		return crouser.getElement();
	}

	@Override
	public boolean hasNext() {
		if(isEmpty())
			return false;
		if(crouser.next==null)	
			return false;
		return true;
	}

	@Override
	public boolean hasPrev() {
		if(isEmpty())
			return false;
		if(crouser.getPrev()==null)
			return false;
		return true;
	}
	
	public String toString() {
		String returned_str="";
		while(hasNext()) {
			returned_str+=(String)crouser.getElement()+" ";
			getNext();
		}
		return returned_str;
	}

}
