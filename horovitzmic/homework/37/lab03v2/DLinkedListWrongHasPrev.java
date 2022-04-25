public class DLinkedListWrongHasPrev<T> extends DLinkedListGood<T> {
	@Override
	public boolean hasPrev() {
		return (current != tail);
	}
}