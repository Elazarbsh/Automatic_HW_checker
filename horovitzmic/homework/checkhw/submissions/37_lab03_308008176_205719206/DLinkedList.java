

public class DLinkedList<T> implements List<T> {
	private DNode head; 
	private DNode cursor;
	public DLinkedList() {
		head=null;
		cursor=null;
	}
	 @Override
		public void insert(T newElement) {
			 if(newElement == null) {
					throw new RuntimeException();}
			 DNode new_node=new  DNode(newElement);
			  if(!isEmpty())
			  {	  
				DNode new_node2=cursor.getNext();
				cursor.setNext(new_node);
				cursor.getNext().setPrev(cursor);
				cursor.getNext().setNext(new_node2);
				cursor=cursor.getNext();
				}
				else 
				{
					  head=new_node;
					  cursor=new_node;
				} 
		}

	@Override
	public T remove() {
		T ele;
		if(head==null)
			return null;
		ele=cursor.getElement();
		if(head==cursor) {
			ele=cursor.getElement();
			head=cursor.getNext();
			cursor=cursor.getNext();
			return ele;
			
		}
		if(hasPrev()==true)
		{
			cursor.getPrev().setNext(cursor.getNext());
			
		}
		if(hasNext()==false)
		{
			cursor=head;
			
		}
		if(hasNext()==true)
		{
			cursor.getNext().setPrev(cursor.getPrev());
			cursor=cursor.getNext();
			
		}
		
		return ele;
	}

	@Override
	public T remove(T element) {
		// TODO Auto-generated method stub
		if(isEmpty()||element==null)
			return null;
		goToBeginning();
		while(hasNext()==true||head==cursor) {
	
			if(cursor.getElement()!=element)
				cursor=cursor.getNext();
			else
			{
				return remove();
			}
		}
			return null;
	}

	@Override
	public void clear() {
		cursor=null;
		head=null;
	}

	@Override
	public void replace(T newElement) {
		// TODO Auto-generated method stub

		if(isEmpty()||newElement==null)
		{
			throw new RuntimeException();
		}
		
        DNode new_node= new DNode(newElement);
        new_node.setNext(cursor.getNext());
        new_node.setPrev(cursor.getPrev());
        cursor=new_node;
        if(cursor.getPrev()!=null){
            cursor.getPrev().setNext(cursor);
        }
        if(cursor.getNext()!=null) {
            cursor.getNext().setPrev(cursor);
        }
    }

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(cursor==null)
			return true ;
		return false;

	}

	@Override
	public boolean goToBeginning() {
		// TODO Auto-generated method stub
		if(!isEmpty())
		{
			cursor=head;
			return true;
		}
		return false;

	}

	@Override
	public boolean goToEnd() {
		// TODO Auto-generated method stub
		if(isEmpty()==false)
		{
			while(hasNext()==true) 
				cursor=cursor.getNext();  
			return true;
		}
		return false;
	}


	@Override
	public T getNext() {
		// TODO Auto-generated method stub
		if(hasNext()==true)
		{
			cursor=cursor.getNext();
			return cursor.getElement() ;
		}
		return null;
	}

	@Override
	public T getPrev() {
		// TODO Auto-generated method stub
		if(cursor!=head)
		{
			cursor=cursor.getPrev();
					return cursor.getElement() ;
				}
			return null;
	}

	@Override
	public T getCursor() {
		// TODO Auto-generated method stub
		if(cursor==null)
		{
			return null;
		}
			return cursor.getElement();

	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(cursor==null||cursor.getNext()==null )
			return false;
		return true;
	

	}

	@Override
	public boolean hasPrev() {
		// TODO Auto-generated method stub
		if(head!=cursor)
			return true;
		return false;
	}
	public String toString()
	{
		String str="";
		DNode new_node=head;
		while(new_node.getNext()!=null) {
			str+=new_node.getElement();
		}
		return str;	
	}

	private class DNode{
		private T element;
		private DNode next;
		private DNode prev;
		
		public DNode(T element) {
			this.element=element;
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

	
}
