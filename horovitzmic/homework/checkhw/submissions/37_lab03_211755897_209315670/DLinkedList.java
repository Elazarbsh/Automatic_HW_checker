
public class DLinkedList<T> implements List<T> 
{
	private DNode<T> Head; 
	private DNode<T> cursor;/*Position in linked list*/
	private DNode<T> prev; 

	
	
	
	 public DLinkedList()
	 {
		 this.cursor=null;
		 this.Head=null;
		 this.prev=null;
	 }
	 
	 
	@Override
	public void insert(T newElement) 
	{
	
		if(newElement==null)
			throw new RuntimeException();
		
		else if(!this.isEmpty())
		{
			DNode<T>temp= this.cursor.getNext();
			cursor.setNext(new DNode<T>(newElement,temp));
			cursor.getNext().setPrev(cursor);
			
			cursor=cursor.getNext();
			if(temp!=null)
			temp.setPrev(cursor);
		}
		else 
		{
			Head=new DNode<T>(newElement,null);
			cursor=	Head;	
		}
		
		
	}
	

	@Override
	public T remove() 
	{
		
		
		if(isEmpty())
			return null;
		
		T ele;
		DNode<T> temp;
		if( cursor!=null)
		{
			if(cursor == Head)
			{
				temp=Head;
				Head = Head.getNext();
				if(Head!=null)
				{
					Head.setPrev(null);
					ele = temp.getElement(); 
					temp=null;
					goToBeginning();
					return ele;
				}

				cursor=null;
				return temp.getElement();
				
			}
			
			temp =cursor;
			cursor=cursor.getPrev();
			cursor.setNext(temp.getNext());
			if(temp.getPrev()!=null)
			{
				temp.getNext().setPrev(cursor);
				cursor = cursor.getNext();
				return temp.getElement();
				
			}
			ele = temp.getElement(); 
			temp=null;
			goToBeginning();
			return ele;
		}
		return null;
	}

	@Override
	public T remove(T element) 
	{
		DNode<T> temp=cursor;
		if(this.isEmpty())
			return null;
		goToBeginning();
		
		while( cursor!=null )
		{
			if(cursor.getElement()== element)
				return remove();
			temp = cursor;
			cursor=cursor.getNext();
		}
		cursor = temp;
		return null;
	}

	@Override
	public void clear() 
	{
		
		goToBeginning();
	
		while(!isEmpty())
		{
			remove();
			
		}
		
	
	}

	@Override
	public void replace(Object newElement) 
	{
		DNode<T> temp = new DNode(newElement),temp2;
		if(!this.isEmpty()&& newElement!=null)
		{
			temp2=cursor.getNext();
			if(cursor==Head)
			{
				temp.setNext(temp2);
				if(temp2!=null)
					temp2.setPrev(temp);
				Head=temp;
				cursor = Head;
				return;
			}
			
			cursor = cursor.getPrev();
			cursor.setNext(temp);
			temp.setPrev(cursor);
			if(temp2!=null)
			temp2.setPrev(temp);
			temp.setNext(temp2);
			cursor=temp;
		}
		else{throw new RuntimeException();} 
			
		
	}

	@Override
	public boolean isEmpty() 
	{
		if(Head==null)
			return true ;
		return false;
	}

	@Override
	public boolean goToBeginning() 
	{
		if(!this.isEmpty())
		{
			cursor=Head;
			return true;
		}
		return false;
	}

	@Override
	public boolean goToEnd() 
	{
		if(!this.isEmpty())
		{
			while(cursor.getNext()!=null) 
				cursor = cursor.getNext();   
			return true;
		}
		
		return false;
	}

	@Override
	public T getNext() 
	{
		if(this.isEmpty())
		{
			return null;
		}
		if(cursor.getNext()!=null)
		{
			cursor=cursor.getNext();
			return cursor.getElement();
		}
		
	    return null;
	}

	@Override
	public T getPrev() 
	{
		if(cursor!=Head)
		{
			DNode<T> pr=cursor;
			goToBeginning();
			while(cursor != null)
			{
				if(cursor.getNext()==pr)
				{
					return  cursor.getElement();
				}
				cursor=cursor.getNext();
			}
		}
		
		return null;
	}

	@Override
	public T getCursor() 
	{
		if(!this.isEmpty())
			return cursor.getElement();
		//throw new RuntimeException();
		return null;
	}

	@Override
	public boolean hasNext() 
	{
		if(this.isEmpty()|| cursor.getNext()==null)
		{
			return false;	
		}
		
		return true;
	

	}

	@Override
	public boolean hasPrev() 
	{
		if(cursor==Head ||this.isEmpty() )
		{
			return false;
		}
			
		return true;
	}
	
	public String toString()
	{
		String str="";
		DNode<T> Lh=Head;
		while(Lh.getNext()!=null)
		{
			str+=Lh.getElement();
		}
		return str;
		
	}

}
