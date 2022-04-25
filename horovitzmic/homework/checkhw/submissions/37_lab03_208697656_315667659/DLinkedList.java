
public class DLinkedList<T> implements List<T>
{
	private class DNode
	{
		private T element;
		private DNode next;
		private DNode prev;
		
		public DNode(T element)
		{
			this.element = element;
		}
		
		public T getElement()
		{
			return element;
		}
		
		public void setNext(DNode next)
		{
			this.next = next;
		}
		
		public DNode getNext()
		{
			return next;
		}
		
		public void setPrev(DNode prev)
		{
			this.prev = prev;
		}
		
		public DNode getPrev()
		{
			return prev;
		}
	}
	
	private DNode head;
	private DNode tail;
	private DNode cursor;

	public DLinkedList()
	{
		this.head = this.tail = this.cursor = null;
	}
	
	@Override
	public void insert(T newElement)
	{
		if(!(newElement == null))
		{
			if(this.isEmpty())
			{
				this.head = new DNode(newElement);
				this.tail = this.head;
				this.cursor = this.head;
			}
			else
			{
				DNode node = new DNode(newElement);
				if(this.cursor == this.tail)
				{
					node.setPrev(this.tail);
					this.tail.setNext(node);
					this.tail = node;
					this.cursor = node;
				}
				else
				{
					node.setPrev(this.cursor);
					node.setNext(this.cursor.getNext());
					this.cursor.getNext().setPrev(node);
					this.cursor.setNext(node);
					this.cursor = node;
				}
			}
		}
		else throw new RuntimeException("cannot insert null element");
	}

	@Override
	public T remove()
	{
		if(!this.isEmpty())
		{
			T retval = this.getCursor();
			if(this.head == this.tail)
			{
				this.head = null;
				this.tail = null;
				this.cursor = null;
			}
			else if(this.cursor == this.head)
			{
				DNode temp = this.cursor;
				this.getNext();
				this.cursor.setPrev(null);
				temp.setNext(null);
				this.head = this.cursor;
				temp = null;
			}
			
			else if(this.cursor == this.tail)
			{
				DNode temp = this.cursor;
				this.getPrev();
				this.cursor.setNext(null);
				temp.setPrev(null);
				this.tail = this.cursor;
				this.goToBeginning();
			}
			
			else
			{
				DNode temp = this.cursor;
				this.getNext();
				this.cursor.setPrev(temp.prev);
				temp.getPrev().setNext(this.cursor);
				temp = null;
			}
			
			return retval;
		}
		return null;
	}

	@Override
	public T remove(T element)
	{
		if(!this.isEmpty())
		{
			DNode scan = this.head;
			while(scan != null)
			{
				if(scan.getElement() == element) break;
				scan = scan.getNext();
			}
			if(scan == null) return null;
			this.cursor = scan;
			return remove();
		}
		return null;
	}

	@Override
	public void clear()
	{
		this.head = this.cursor = this.tail = null;
	}

	@Override
	public void replace(T newElement)
	{
		if(!this.isEmpty() && newElement != null)
		{
			this.cursor.element = newElement;
		}
		else
		{
			String msg = "";
			if(this.isEmpty()) msg = "cannot replace element in empty list";
			else if(newElement == null) msg = "cannot replace with null element";
			throw new RuntimeException(msg);
		}
	}

	@Override
	public boolean isEmpty()
	{
		if(this.head == null && this.tail == null && this.cursor == null) return true;
		return false;
	}

	@Override
	public boolean goToBeginning()
	{
		if(!this.isEmpty())
		{
			this.cursor = this.head;
			return true;
		}
		return false;
	}

	@Override
	public boolean goToEnd()
	{
		if(!this.isEmpty())
		{
			this.cursor = this.tail;
			return true;
		}
		return false;
	}

	@Override
	public T getNext()
	{
		if(this.hasNext())
		{
			this.cursor = this.cursor.getNext();
			return this.getCursor();
		}
		return null;
	}

	@Override
	public T getPrev()
	{
		if(this.hasPrev())
		{
			this.cursor = this.cursor.getPrev();
			return this.getCursor();
		}
		return null;
	}

	@Override
	public T getCursor()
	{
		if(!this.isEmpty())
		{
			return this.cursor.getElement();
		}
		return null;
	}

	@Override
	public boolean hasNext()
	{
		if(!this.isEmpty() && this.cursor != this.tail)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPrev()
	{
		if((!this.isEmpty()) && (this.cursor != this.head))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		String str = "";
		if(!this.isEmpty())
		{
			DNode scan = this.head;
			while(scan != this.tail)
			{
				str += scan.getElement().toString();
				scan = scan.getNext();
			}
		}
		return str;
	}
	
}
