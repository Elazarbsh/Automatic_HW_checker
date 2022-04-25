
public class DLinkedList<T> implements List<T> 
{
	private DNode<T> Head; 
	private DNode<T> previous; 
	private DNode<T> cur;

	
	
	
	 public DLinkedList()
	 {
		this.Head=null;
		this.previous=null;
		this.cur=null;

	 }
	 
	 
	@Override
	public void insert(T newElement) 
	{
	
		if(newElement==null)
			throw new IllegalArgumentException();
		
		else if(!isEmpty())
		{
			DNode<T>tmp= this.cur.getNext();
			cur.setNext(new DNode<T>(newElement,tmp));
			cur.getNext().setPrev(cur);
			
			cur=cur.getNext();
			if(tmp!=null)
			tmp.setPrev(cur);
		}
		else 
		{
			Head=new DNode<T>(newElement,null);
			cur=Head;	
		}
		
		
	}
	

	@Override
	public T remove() 
	{
	   if(this.isEmpty())
			return null;
		
		T element;
		DNode<T> tmp;
		if( cur!=null)
		{
			if(cur == Head)
			{
				tmp=Head;
				Head = Head.getNext();
				if(Head!=null)
				{
					Head.setPrev(null);
					element = tmp.getElement(); 
					tmp=null;
					goToBeginning();
					return element;
}
				cur=null;
				return tmp.getElement();
				
   }
			tmp =cur;
			cur=cur.getPrev();
			cur.setNext(tmp.getNext());
			if(tmp.getPrev()!=null)
			{
				tmp.getNext().setPrev(cur);
				cur = cur.getNext();
				return tmp.getElement();
				
			}
			element = tmp.getElement(); 
			tmp=null;
			goToBeginning();
			return element;
		}
		return null;
	}

	@Override
	public T remove(T element) 
	{
		DNode<T> tmp=cur;
		if(this.isEmpty())
			return null;
		goToBeginning();
		
		while(cur!=null )
		{
			if(cur.getElement()==element)
				return remove();
			tmp =cur;
			cur=cur.getNext();
		}
		cur= tmp;
		return null;
	}

	@Override
	public void clear() 
	{
		
		goToBeginning();
	
		while(!this.isEmpty())
	{remove();}
		
}

	@Override
	public void replace(Object newElement) 
	{
		DNode<T> tmp = new DNode(newElement),tmp2;
		if(!isEmpty()&& newElement!=null)
		{
			tmp2=cur.getNext();
			if(cur==Head)
			{
				tmp.setNext(tmp2);
				if(tmp2!=null)
					tmp2.setPrev(tmp);
			        Head=tmp;
			        cur = Head;
			
			
			   return;
			}
			
			
			cur = cur.getPrev();
			cur.setNext(tmp);
			tmp.setPrev(cur);
			if(tmp2!=null)
			tmp2.setPrev(tmp);
			tmp.setNext(tmp2);
			cur=tmp;
		}
		else{throw new IllegalArgumentException();} 
			
		
	}

	@Override
	public boolean isEmpty() 
	{
		if(Head!=null) {
			return false ;
			}
		else{
			return true;
		}
	}

	@Override
	public boolean goToBeginning() 
	{
		if(isEmpty())
		{
			return false;
		}
		cur=Head;

		return true;
	}

	@Override
	public boolean goToEnd() 
	{
		if(isEmpty())
		{
			return false;

		}
		
		while(cur.getNext()!=null) 
			cur = cur.getNext();   
		    return true;
	}

	@Override
	public  T getNext() 
	{
		if(isEmpty())
		{
			return null;
		}
		if(cur.getNext()!=null)
		{
			cur=cur.getNext();
			return cur.getElement();
		}
		
	    return null;
	}

	@Override
	public T getPrev() 
	{
		if(cur!=Head)
		{
			   DNode<T> nd=cur;
		    goToBeginning();
			while(cur != null)
			{
				if(cur.getNext()==nd)
				{
					return  cur.getElement();
				}
				cur=cur.getNext();
			}
		}
		
		return null;
	}

	@Override
	public T getCursor() 
	{
		if(!isEmpty())
			return cur.getElement();
		return null;
	}

	@Override
	public boolean hasNext() 
	{
		if(isEmpty()|| cur.getNext()==null)
		{
			return false;	
		}
		
		return true;
	

	}

	@Override
	public boolean hasPrev() 
	{
		if(cur==Head ||isEmpty() )
		{
			return false;
		}
			
		return true;
	}
	
	public String toString()
	{
		StringBuilder string = new StringBuilder(); 
		DNode<T> nd=Head;
		while(nd.getNext()!=null)
		{
			string.append(nd.getElement());
		}
		return string.toString();
		
	}

}
