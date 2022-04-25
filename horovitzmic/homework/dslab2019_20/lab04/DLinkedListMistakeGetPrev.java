public class DLinkedListMistakeGetPrev<T> extends DLinkedListGood<T>{
	@Override
	public T getPrev() {
		if ((current != null) && hasPrev()) {
			current.getPrev().getElement();
		}
		return null;
	}
}
