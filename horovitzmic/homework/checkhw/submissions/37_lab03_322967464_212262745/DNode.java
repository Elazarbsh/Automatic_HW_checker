

class DNode<T> 
{
	private T element;
	private DNode next;
	private DNode prev;
	
	
	public DNode(T element)
	{
		this.element=element;
	}
	public DNode(T newElement, DNode<T> temp) 
	{
		this.next=temp;
		this.element=newElement;
		
	
	}
	public T getElement()
	{
		return element;
		
	}
	public void setNext(DNode next)
	{
		this.next=next;
		
	}
	public DNode getNext()
	{
		return next;
	}
	public void setPrev(DNode prev)
	{
		this.prev=prev;
	}
	public DNode getPrev() {
		return prev;
	}

}
