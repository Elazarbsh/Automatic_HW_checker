

public class DLinkedList<T> implements List<T>{

	
	private DNode head;
	private DNode corser;
	
	
	public DLinkedList(){
		head = null;
		corser = head;
	}

	

	@Override
	public void insert(T newElement) {
		 if(newElement==null) {
			throw new IllegalArgumentException();
			
		 }
		 
		 if(head!=null) {
			 DNode newNode = new DNode(newElement);
			 DNode x = corser.getNext();
			 corser.setNext(newNode);
			 newNode.setPrev(corser);
			 if(x!=null) {
				 newNode.setNext(x);
				 x.setPrev(newNode);
			 }
			 
			 corser = newNode;
			 
		 }
		 else {
			 DNode newNode = new DNode(newElement);
			 head = newNode;
			 corser = newNode;
		 }
		
		 
		 
		
	}

	@Override
	public T remove() {
		if(head==null) {
			return null;
		}
		if(corser.getNext()!=null) {
			DNode n = corser ;
			n = n.getPrev();
			
			
			T x = corser.getElement();
			corser = corser.getNext();
			corser.setPrev(n);
			n.setNext(corser);
			
			return x;
		}
		if(corser.getNext()==null) {
			T x = corser.getElement();
			if(head.getNext()==null) {
				x = corser.getElement();
				head = null;
				
			}
			x = corser.getElement();
			corser = head;
			return x;
		}
		return head.getElement();
		 
	}
			
	@Override
	public T remove(T element) {
		DNode n = head;
		while(n!=null) {
			if(n.getElement()==element) {
				    T t =this.remove();
					corser = n;
					
					return t;		
			}
			n = n.getNext();
			
		}
		return null;
	}
			
	@Override
	public void clear() {
		if(head!=null) {
			while(head!=null) {
				this.remove();
				
			}
			 
		}
		
		}

	@Override
	public void replace(T newElement) {
		if(!this.isEmpty()) {
			this.remove();
			this.getPrev();
			this.insert(newElement);
			return;
		}
		throw new IllegalArgumentException();
		
	}

	@Override
	public boolean isEmpty() {
		 
		return (corser==null);
	}

	@Override
	public boolean goToBeginning() {
		if(!this.isEmpty()) {
			corser = head;
			return true;
		}
		return false;
	}

	@Override
	public boolean goToEnd() {
		if(!this.isEmpty()) {
			DNode d = head;
			while(d.getNext()!=null) {
				d = d.getNext();
			}
			corser = d;
			return true;
		}
		return false;
	}

	@Override
	public T getNext() {
		if(this.isEmpty()) {
			return null;
		}
		if(corser.getNext()!=null) {
			corser = corser.getNext();
			return corser.getElement();
		}
		return null;
	}

	@Override
	public T getPrev() {
		if(this.isEmpty()) {
			return null;
		}
		if(corser!=head) {
			corser = corser.getPrev();
			return corser.getElement();
		}
		return null;
	}

	@Override
	public T getCursor() {
		 
		if(!this.isEmpty()) {
			return corser.getElement();
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		if(this.isEmpty()) {
			return false;
		}
		 
		return false;
	}

	@Override
	public boolean hasPrev() {
		if(this.isEmpty()) {
			return false;
		}
		if(corser.getPrev()!=null) {
			return true;
		}
		return false;
	}
	@Override
	public String toString( ) {
		String str="<";
		 
		 DNode x = head;
		 while(x!=null) {
			 
			 str+=x.getElement()+",";
			 x = x.getNext();
		 }
		
		 
		return str ; 
		
	}
	private class DNode{
		
		private T element;
		private DNode next;
		private DNode prev;
		
		public DNode(T element ) {
			this.element = element;
		}
		public T getElement() {
			return this.element;
		}
		public DNode getNext() {
			return next;
		}
		public DNode getPrev() {
			return prev;
		}
		 
		public void setNext(DNode next) {
			this.next = next;
		}
		public void setPrev(DNode prev) {
			this.prev = prev;
		}
	}

	
    
}
