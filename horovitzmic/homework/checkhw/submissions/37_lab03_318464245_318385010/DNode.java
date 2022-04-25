
public class DNode<T>{

	private T element;
	private DNode<T> next;
	private DNode<T> prev;
	
	
	public DNode(T element) {
		this.element=element;
	}
	
	public T getElement() {
		return element;
	}
	
	
	public void setNext(DNode<T> next) {
		this.next=next;
	}
	public DNode<T> getNext() {
		return next;
	}
	public void setPrev(DNode<T> prev) {
		this.prev=prev;
	}
	public DNode<T> getPrev() {
		return prev;
	}
}
