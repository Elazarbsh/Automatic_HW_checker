
public class DLinkedList<T> implements List<T> 
{
	private T elmnt;
	private DNode<T> head; 
	private DNode<T> cursor;
	
	
	 public DLinkedList()
	 {
		 elmnt=null;
		 this.cursor=null;
		 this.head=null;
	 }
	 
	@Override
	public void insert(T newelm) 
	{
		DNode<T> ptr;
		this.elmnt=newelm;
		if(newelm==null)
			throw new RuntimeException();
		
		else if(!this.isEmpty())
		{
			if(cursor.getNext()!=null) {
				ptr=new DNode<T>(newelm);
				ptr.setNext(cursor.getNext());
				ptr.setPrev(cursor);
				cursor.setNext(ptr);
				cursor=ptr;	
			}
			else {
				cursor.setNext(new DNode<T>(newelm));
				ptr=cursor;
				cursor=cursor.getNext();
				cursor.setPrev(ptr);
			}	
		}
		else {
			head=new DNode<T>(newelm);
			cursor=	head;	
		}	
	}
	
	@Override
	public T remove(T element) 
	{
		DNode<T> ptr=cursor;
		if(this.isEmpty())
			return null;
		goToBeginning();
		
		while( cursor!=null )
		{
			if(cursor.getElement()== element)
				return remove();
			ptr = cursor;
			cursor=cursor.getNext();
		}
		cursor = ptr;
		return null;
	}
	
	@Override
	public T remove() 
	{
		DNode<T> ptr;
		T elm=null;
		if(!isEmpty()) {			
			if(cursor.getNext()!=null) {
				elm=cursor.getElement();
				ptr=cursor.getPrev();
				if(cursor==head) {
					cursor=cursor.getNext();
					cursor.setPrev(ptr);
					head=cursor;	
				}else {
					if(cursor.getPrev()!=null) {cursor.getPrev().setNext(cursor.getNext());}
					cursor=cursor.getNext();
					cursor.setPrev(ptr);
				}
				elmnt=cursor.getElement();	
			}else {
				elm=cursor.getElement();
				if(cursor.getPrev()==null) {
					cursor=null;
					head=cursor;
				}else {
					cursor.getPrev().setNext(null);
					goToBeginning();
				}	
			}
			return elm;
		}
		return elm;
	}
	
	@Override
	public void clear(){
		while(!isEmpty()) {	
			remove();
		}
		this.head=null;
		cursor=head;	 				
	}

	@Override
	public void replace(T newelm) {
		DNode<T> ptr = new DNode<T>(newelm);
		if(!this.isEmpty() && newelm!=null)
		{
			ptr.setNext(cursor.getNext());
			ptr.setPrev(cursor.getPrev());
			if(cursor.getNext()!=null) {
				cursor.getNext().setPrev(ptr);
			}
			if(cursor.getPrev()!=null) {
				cursor.getPrev().setNext(ptr);
			}
			this.elmnt=newelm;
		}else{
			throw new RuntimeException();
			}
	}

	@Override
	public boolean isEmpty() {
		return (head==null);
	}

	@Override
	public boolean goToBeginning() {
		if(!this.isEmpty())
		{
			cursor=head;
			elmnt=cursor.getElement();
			return true;
		}
		elmnt=null;
		return false;
	}

	@Override
	public boolean goToEnd() {
		if(!this.isEmpty())
		{
			while(cursor.getNext()!=null) {
				cursor=cursor.getNext();
				elmnt=cursor.getElement();
			}
			return true;
		}
		return false;
	}

	@Override
	public T getNext() {
		if(hasNext()){
			cursor=cursor.getNext();
			elmnt=cursor.getElement();
			return cursor.getElement();
		}	
		return null;
	}

	@Override
	public T getPrev() {
		if(hasPrev()){
			cursor=cursor.getPrev();
			elmnt=cursor.getElement();
			return elmnt;		
		}
		return null;
	}

	@Override
	public T getCursor() {
		if(!isEmpty()) {
			return elmnt;
			}
		return null;
		
	}

	@Override
	public boolean hasNext() {
		if(this.isEmpty()|| cursor.getNext()==null)
		{
			return false;	
		}
		
		return true;
	}

	@Override
	public boolean hasPrev() {
		if(cursor!=head){
			return true;
		}
			
		return false;
	}
}
