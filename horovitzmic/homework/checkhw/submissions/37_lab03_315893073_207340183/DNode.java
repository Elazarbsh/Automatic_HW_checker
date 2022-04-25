
public class DNode <T>
{
	private T element;
	private DNode next, prev;
	
	public DNode(T element)
	{
		this.element = element;
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

	public T getData() {
		return element;
	}

	public void setData(T element) {
		this.element = element;
	}
	
	
}
