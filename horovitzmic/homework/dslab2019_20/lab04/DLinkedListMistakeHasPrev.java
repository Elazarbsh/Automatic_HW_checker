public class DLinkedListMistakeHasPrev<T> extends DLinkedListGood<T>{
	@Override
	public boolean hasPrev() {
		return (current == head);
	}
}
