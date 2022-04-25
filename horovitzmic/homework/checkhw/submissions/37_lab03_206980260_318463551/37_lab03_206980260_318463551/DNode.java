
public class DNode<T> {
	
	
	private T element;//element in the list
	private DNode<T> next;
	private DNode<T> prev;
	
	public DNode(T newelement) {
		// TODO Auto-generated constructor stub
		setElement( newelement );
		
	}
	
	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public DNode<T> getNext() {
		return next;
	}

	public void setNext(DNode<T> next) {
		this.next = next;
	}

	public DNode<T> getPrev() {
		return prev;
	}

	public void setPrev(DNode<T> prev) {
		this.prev = prev;
	}


	
	




	

}
