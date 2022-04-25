public class DLinkedListMistakeReplace3<T> extends DLinkedListGood<T> {
	@Override
	public void replace(T newElement) {
		// current.element = newElement;
		if ((newElement == null)) {
			throw new IllegalArgumentException("New element is null");
		}
		if ((isEmpty())) {
			throw new IllegalStateException("List is empty");
		}

		DNode newNode = new DNode(newElement);
		newNode.setNext(current.getNext());
		DNode prev = current.getPrev();
		if (prev != null) {
			newNode.setPrev(prev);
//			prev.setNext(newNode);
		}
		current = newNode;
	}

}
