
public class DLinkedList<T> implements List<T> {

	private DNode<T> list;
	private DNode<T> cursor;
	
	public DLinkedList() {
		list=null;
		cursor=null;
	}
	
	public void insert(T newElement) {
		if(newElement==null)
			throw new RuntimeException();
		DNode<T> e=new DNode<T>(newElement);
		if(isEmpty())
		{
			list=e;
			cursor=list;
			list.setPrev(null);
			list.setNext(null);
		}
		else
		{
			e.setNext(cursor.getNext());
			cursor.setNext(e);
			e.setPrev(cursor);
			cursor=cursor.getNext();
		}
	}

	@Override
	public T remove() {
		if(isEmpty())
			return null;
		T ele;
		if(cursor.getNext()==null&&cursor.getPrev()!=null)
		{
			ele=cursor.getElement();
			 cursor.getPrev().setNext(null);
			 cursor=list;
			return ele;
		}
		else if(cursor.getNext()==null&&cursor.getPrev()==null)
		{
			ele=cursor.getElement();
			cursor=null;
			list=null;
			return ele;
		}
		else if(cursor.getNext()!=null&&cursor.getPrev()==null)
		{
			ele=cursor.getElement();
			cursor.getNext().setPrev(null);
			cursor=cursor.getNext();

			return ele;
		}
		else {
			ele=cursor.getElement();
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			cursor=cursor.getNext();

			return ele;
		}
	}

	@Override
	public T remove(T element) {
		if (isEmpty())
			return null;
		DNode<T> e=list;
		while(e.getElement()!=element&&e.getNext()!=null)
		{
				e=e.getNext();
				
		}
		if(e.getNext()==null&&e.getElement()!=element)
			return null;
		cursor=e;
		return remove();
	}

	@Override
	public void clear() {
		if(isEmpty())
			return;
		goToBeginning();
		while(cursor != null)
			remove();
	}

	@Override
	public void replace(T newElement) {
		if(isEmpty()||newElement==null)
			throw new RuntimeException();
		DNode<T> e=new DNode<T>(newElement);
		e.setNext(cursor.getNext());
		e.setPrev(cursor.getPrev());
		if(cursor.getNext()!=null)
		cursor.getNext().setPrev(e);
		if(cursor.getPrev()!=null)
		cursor.getPrev().setNext(e);
		cursor=e;
		
	}

	@Override
	public boolean isEmpty() {
		return list==null;
	
	}

	@Override
	public boolean goToBeginning() {
		if(isEmpty())
		return false;
		cursor=list;
		return true;
	}

	@Override
	public boolean goToEnd() {
		if(isEmpty())
		return false;
		while(cursor.getNext()!=null)
			cursor=cursor.getNext();
		return true;
	}

	@Override
	public T getNext() {
		if(isEmpty()||cursor.getNext()==null)
			return null;
		cursor=cursor.getNext();
		T ele=cursor.getElement();
		return ele;
		
	}

	@Override
	public T getPrev() {
		if(isEmpty()||cursor.getPrev()==null)
			return null;
		cursor=cursor.getPrev();
		T ele=cursor.getElement();
		return ele;
	}

	@Override
	public T getCursor() {
		if(isEmpty())
			return null;
		return cursor.getElement();
	}

	@Override
	public boolean hasNext() {
		if(isEmpty())
			return false;
		return cursor.getNext()!=null;
	}

	@Override
	public boolean hasPrev() {
		if(isEmpty())
			return false;
		return cursor.getPrev()!=null;
	}
	
	

}
