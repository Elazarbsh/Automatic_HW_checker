public class DLinkedListMistakeGoToEnd<T> extends DLinkedListGood<T> {
	@Override
	public boolean goToEnd() {
		current = head;
		return !isEmpty();
	}
}
