public class DLinkedListMistakeGetNext<T> extends DLinkedListGood<T>  {
	@Override
	public T getNext() {
		if ((current != null) && hasNext()) {
			return current.getNext().getElement();
		}
		return null;
	}
}
