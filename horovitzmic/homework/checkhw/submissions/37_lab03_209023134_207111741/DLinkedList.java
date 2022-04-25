public class DLinkedList<T> implements List<T>{
	DNode Curser;
	DNode Head;
	DLinkedList() {
		Head=null;
		Curser=Head;
	}
	
	public void insert(T newElement) {
		// TODO Auto-generated method stub
		goToEnd();
		DNode end =new DNode(null);
		if(this.isEmpty() && newElement!=null) {
			this.Head = new DNode((T)newElement);
			this.Head.next = end;
			end.prev = Head;
		}
		else if(newElement!=null) {
			getNext();
			this.Curser.element=newElement;
			Curser.next=end;
			end.prev=Curser;
		}
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		
		if(isEmpty()) {
			return null;
		}
		if(Head==Curser) {
			Curser.element=null;
			return null;
		}
		else{
			T RetVal=Curser.element;
			if(Curser.next==null) {
				Curser.element=null;
				getPrev();
				Curser.next=null;
				goToBeginning();
			}
			Curser.prev.next=Curser.next;
			Curser.next.prev=Curser.prev;
			getNext();
			return RetVal;
		}
		
	}

	@Override
	public T remove(T element) {
		// TODO Auto-generated method stub
		goToBeginning();
		while(hasNext()) {
			if(Curser.element==element) {
				remove();
			}
			else {
				getNext();
			}
			
		}
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		goToBeginning();
		while(!isEmpty()) {
			remove();
		}
		
	}

	@Override
	public void replace(T newElement) {
		// TODO Auto-generated method stub
		Curser.element=(T) newElement;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(Head==null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean goToBeginning() {
		// TODO Auto-generated method stub
		if(!isEmpty()) {
			Curser=Head;
			return true;
		}
		return false;
		
	}

	@Override
	public boolean goToEnd() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			return false;
		}
		while(hasNext()) {
			getNext();
		}
		return true;
	}

	@Override
	public T getNext() {
		if(Curser.next==null) {
			return null;
		}
		this.Curser=Curser.getNext();
		return Curser.element;
	}

	@Override
	public T getPrev() {
		if(Curser!=Head) {
			return null;
		}
		this.Curser=Curser.getPrev();
		return Curser.element;
	}

	@Override
	public T getCursor() {
		// TODO Auto-generated method stub
		if(!isEmpty()) {
		return Curser.element;
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(Curser.next!=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPrev() {
		// TODO Auto-generated method stub
		if(Curser.prev!=null) {
			return true;
		}
		return false;
	}
	public String toString() {
		String retString="";
		DNode Temp=Head;
		if(Head==null) {
			retString+=" ";
			return "";
		}
		while(Temp!=null) {
			retString+=Temp.getElement().toString()+" ";
			Temp.getNext();
		}
		return retString;
	}
	private class DNode {
		private T element; // element in the list
		private DNode next; // reference to the next element
		private DNode prev; // reference to the previous element
		public DNode(T element) {
		this.element = element;
		}
		public T getElement() {
		return element;
		}
	
		public DNode getNext(){
		return next;
		}

		public DNode getPrev(){
		return prev;
		}
	}

}
