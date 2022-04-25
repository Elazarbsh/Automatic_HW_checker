public class DLinkedListWrongHasNext<T> extends DLinkedListGood<T> {
	@Override
	public boolean hasNext() {
		return (current != head);
	}
}