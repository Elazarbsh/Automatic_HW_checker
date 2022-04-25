
public class DLinkedList<T> implements List<T> {
	
	
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

		public DNode getNext() {
			return next;
		}

		public void setNext(DNode next) {
			this.next = next;
		}

		public DNode getPrev() {
			return prev;
		}

		public void setPrev(DNode prev) {
			this.prev = prev;
		}
			
	}
	
	private DNode head;
	private DNode cursor;
	
	
	public DLinkedList(){
		head=null;
		cursor=null;
	}
	
	@Override	
	public void insert(T newElement) {
		if(isEmpty()) { //if list is empty
			head = new DNode(newElement);
			cursor = head;
			return;
		}
		//if its not empty
		DNode newNode = new DNode(newElement);
		cursor.setNext(newNode);
		cursor = newNode;
	}

	@Override
	public T remove() {
		DNode ptr = cursor;
		ptr.getPrev();
		cursor.setNext(ptr.getNext());	
		return null;
		
	}

	@Override
	public T remove(T element) {
		DNode ptr = head;
		while (ptr.getNext() !=null ) {
			if ( ptr.getNext() == element ) 
			{
				cursor=ptr;
				remove();
				break;
			}
			ptr = ptr.getNext();
		}
		return null;
	}

	@Override
	public void clear()
	{
		head = null;	
		cursor = null;
	}

	@Override
	public void replace(T newElement) {
		if(newElement==null || head==null) {
			return;
		}
		DNode newnode=new DNode(newElement);
		
		DNode ptr = head;
		while (ptr.getNext() != null ) {
			if ( ptr.getNext() == cursor ) 
			{
				cursor = ptr;
				getPrev();
				ptr.setNext(newnode);
				return;
			}
			ptr = ptr.getNext();
		}
	}

	@Override
	public boolean isEmpty()
	{
		if(head != null)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public boolean goToBeginning()
	{
		if ( isEmpty() )
		{
			return false;
		}
		cursor = head;
		return true;
	}
	@Override
	public boolean goToEnd()
	{
		if( isEmpty() )
			return false;
		DNode ptr = head;
		while ( ptr.getNext() != null ) 
			ptr = ptr.getNext();
		cursor = ptr;
		return true;
	}

	@Override
	public T getNext()
	{
		if(cursor.getNext() == null )
			return null;
		return (T)(cursor = cursor.getNext());
	}

	
	
	@Override
	public T getPrev()
	{
		if(cursor.getPrev()!=null && head!=null) {
			cursor=cursor.prev;
			return cursor.getElement();
		}
		return null;
	}
	
	@Override
	public T getCursor() 
	{
		if(isEmpty()) 
		{
			return null;
		}
		return (T) cursor.getElement();
	}

	@Override
	public boolean hasNext()
	{
		if (isEmpty())
		{
			return false;
		}
		if ( cursor.getNext() == null )
			return false;
		cursor = cursor.getNext();
		return true;
	}
 
	@Override
	public boolean hasPrev()
	{
		if (isEmpty())
		{
			return false;
		}
		DNode ptr = head;
		while ( ptr.getNext() != null ) 
		{
			if ( ptr.getNext() == cursor ) 
			{
				cursor = ptr;
				return true;
			}
			else
			{
				ptr = ptr.getNext();
			}
		}
		return false;
	}
	
	
	
	public String toString()
	{
		if (isEmpty() ) 
		{
			return  "List:empty!" ;
		}
		else
		{
			String mylinkedlist= "[" + head.getElement();
			DNode ptr = head;
			while ( ptr != null )
			{
				mylinkedlist += "," +ptr.getElement() ;
				ptr = ptr.getNext();
			}
			mylinkedlist += "]";
			return mylinkedlist;
			
		}
	}


}
