
public class DLinkedList<T> implements List<T> {
	
	DNode head;
	DNode Cursor;
	
	private class DNode{
		private T element;
		private DNode next;
		private DNode prev;
		
		public DNode(T element) {
			this.element=element;
		}
		
		public T getElement()
		{
			return element;
		}
		
		public void setNext(DNode next)
		{
			this.next=next;
		}
		
		public DNode getNext() {
			return next;
		}
		
		
		public void setPrev(DNode prev)
		{
			this.prev=prev;
		}
		
		public DNode getPrev()
		{
			return prev;
		}
	}
	public DLinkedList()
	{
		this.head=null;
		this.Cursor=null;
	}
	@Override
	public void insert(T newElement) {
		// TODO Auto-generated method stub
		if(newElement ==null ){
			throw new IllegalArgumentException();
		}
		if(this.head!= null){
			DNode d= new DNode(newElement);
			DNode C=this.Cursor.getNext();
			this.Cursor.setNext(d);
			d.setPrev(this.Cursor);
			if(C!=null){
				d.setNext(C);
				C.setPrev(d);
			}
			this.Cursor=d;
		}
		else{
			DNode d=new DNode(newElement);
			this.head=d;
			this.Cursor=d;
		}
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			if(this.hasNext() && this.hasPrev()){
				DNode d=this.Cursor;
				this.Cursor.getPrev().setNext(this.Cursor.getNext());
				this.Cursor.getNext().setPrev(this.Cursor.getPrev());
				this.Cursor.setNext(null);
				this.Cursor.setPrev(null);
				this.Cursor=d.getNext();
				return d.getElement();
			}
			if(this.Cursor==this.head){
				DNode d=this.Cursor;
				if(this.hasNext()){
				this.Cursor.getNext().setPrev(null);
				this.Cursor.setNext(null);
				this.head=d.getNext();
				this.Cursor=this.head;
				return d.getElement();
			}
			else{
					DNode d1=this.Cursor;
					this.Cursor=null;
					return d1.getElement();
				}
		}
			if(!this.hasNext() && this.hasPrev()){
				DNode d=this.Cursor;
				this.Cursor.getPrev().setNext(null);
				this.Cursor.setPrev(null);
				this.goToBeginning();
				return d.getElement();
			}
		}
		return null;
	}

	@Override
	public T remove(T element) {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			this.Cursor=this.head;
			while(this.hasNext()){
				if(this.Cursor.getElement()==element){
					return this.remove();
				}
				this.Cursor=this.Cursor.getNext();
			}
			if(this.Cursor.getElement()==element){
				return this.remove();
			}
			}
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.head=null;
		this.Cursor=head;
		}

	@Override
	public void replace(T newElement) {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			if(newElement==null){
				throw new IllegalArgumentException();
			}
			this.remove();
			this.getPrev();
			this.insert(newElement);
			return;
		}
		else
			throw new NullPointerException();
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		
		return this.head==null;
	}

	@Override
	public boolean goToBeginning() {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			this.Cursor=this.head;
			return true;
		}
		return false;
	}

	@Override
	public boolean goToEnd() {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			while(this.hasNext()){
				this.Cursor=this.Cursor.getNext();
			}
			return true;
		}
		return false;
	}

	@Override
	public T getNext() {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			if(this.hasNext()){
			this.Cursor=this.Cursor.getNext();
			return this.Cursor.getElement();
			}
		}
		return null;
	}

	@Override
	public T getPrev() {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			if(this.hasPrev()){
				this.Cursor=this.Cursor.getPrev();
				return this.Cursor.getElement();
			}
		}
		
		return null;
	}

	@Override
	public T getCursor() {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			if(this.Cursor!=null){
				return this.Cursor.getElement();
			}
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			if(this.Cursor.getNext()!=null){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasPrev() {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			if(this.Cursor!=this.head){
				return true;
			}
		}
		return false;
	}

}
