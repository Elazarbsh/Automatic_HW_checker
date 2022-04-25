public class DLinkedListMistakeInsert2<T> extends DLinkedListGood<T> {
	@Override
	public void insert(T newElement) {
		if (newElement == null)
			throw new IllegalArgumentException("New element is null");
		DNode newNode = new DNode(newElement);
		if (isEmpty()) {
			head = newNode;
		} else {
			newNode.setPrev(tail);
			tail.setNext(newNode);
		}
		tail = newNode;
		// current = newNode;
	}
}
