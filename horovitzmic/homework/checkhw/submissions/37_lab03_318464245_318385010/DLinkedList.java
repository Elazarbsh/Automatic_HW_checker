
public class DLinkedList<T> implements List<T> 
{
	private DNode<T> head; 
	private DNode<T> cursor;
	private T newElement;
	
	 public DLinkedList(){
		 this.cursor=null;
		 this.head=null;
		 this.newElement=null;
	 }
	 
	 
	@Override
	public void insert(T newElement) {
		DNode<T> temp=new DNode<T>(null);
		this.newElement=newElement;
		if(newElement==null)
			throw new RuntimeException();
		
		else if(!isEmpty()){
			if(hasNext()) {
				temp=new DNode<T>(newElement);
				temp.setNext(cursor.getNext());
				temp.setPrev(cursor);
				cursor.setNext(temp);
				cursor=temp;
				
			}
			else {
				cursor.setNext(new DNode<T>(newElement));
			temp=cursor;
			cursor=cursor.getNext();
			cursor.setPrev(temp);
			}	
		}
		else {
			head=new DNode<T>(newElement);
			cursor=	head;	
		}	
	}
	

	@Override
	public T remove() {
		DNode<T> temp=new DNode<T>(null);
		T elemnt=null;
		if(!isEmpty()) {			
			if(hasNext()) {
				elemnt=cursor.getElement();
				temp=cursor.getPrev();
				if(cursor==head) {
					cursor=cursor.getNext();
					cursor.setPrev(temp);
					head=cursor;	
				}
				else {
					if(hasPrev()) {cursor.getPrev().setNext(cursor.getNext());}
					cursor=cursor.getNext();
					cursor.setPrev(temp);
				}
				newElement=cursor.getElement();	
			}
			else {
				elemnt=cursor.getElement();
				if(!hasPrev()) {
					cursor=null;
					head=cursor;
				}
				else {
					cursor.getPrev().setNext(null);
					goToBeginning();
				}				
			}
			return elemnt;
		}		
		return elemnt;
	}
	
	
	
	@Override
	public T remove(T element) {
		DNode<T> temp=new DNode<T>(null);
		if(isEmpty())
			return null;
		goToBeginning();
		
		while( cursor!=null ){
			if(getCursor()== element)
				return remove();
			temp = cursor;
			cursor=cursor.getNext();
		}
		cursor = temp;
		return null;
	}
	

	@Override
	public void clear(){
		while(!isEmpty()) {	
			remove();
		}
		head=null;
		cursor=head;	 				
	}

	@Override
	public void replace(T newElement) {
		DNode<T> temp = new DNode<T>(newElement);
		if(!isEmpty()&& newElement!=null){
			temp.setNext(cursor.getNext());
			temp.setPrev(cursor.getPrev());
			if(hasNext()) {cursor.getNext().setPrev(temp);}
			if(hasPrev()) {cursor.getPrev().setNext(temp);}
			this.newElement=newElement;
		}
		else{throw new RuntimeException();}
			
		
	}

	@Override
	public boolean isEmpty() {
		if(head==null)
			return true ;
		return false;
	}

	@Override
	public boolean goToBeginning() {
		if(!this.isEmpty()){
			cursor=head;
			newElement=cursor.getElement();
			return true;
		}
		newElement=null;
		return false;
	}

	@Override
	public boolean goToEnd() {
		if(!isEmpty()){
			while(hasNext()) {
				cursor=cursor.getNext();
				newElement=cursor.getElement();
			}
			return true;	
		}
		return false;
	}

	@Override
	public T getNext() {
		if(hasNext()){
			cursor=cursor.getNext();
			newElement=cursor.getElement();
			return cursor.getElement();
		}	
		return null;
	}

	@Override
	public T getPrev() {
		if(hasPrev()){
			cursor=cursor.getPrev();
			newElement=cursor.getElement();
			return newElement;	
		}
		return null;
	}

	@Override
	public T getCursor() {
		if(!isEmpty())
			return newElement;
		return null;
	}

	@Override
	public boolean hasNext() {
		if(this.isEmpty()|| cursor.getNext()==null){return false;	}
		return true;
	}

	@Override
	public boolean hasPrev() {
		if(cursor!=head){return true;}	
		return false;
	}


}
