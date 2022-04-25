public class DLinkedListMistakeClear<T> extends DLinkedListGood<T> {
	@Override
	public void clear() {
		head = null;
		tail = null;
	}
}