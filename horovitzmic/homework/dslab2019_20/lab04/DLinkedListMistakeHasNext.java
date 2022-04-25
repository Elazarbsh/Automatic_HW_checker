public class DLinkedListMistakeHasNext<T> extends DLinkedListGood<T> {
	@Override
	public boolean hasNext() {
		return (current == tail);
	}
}
