public class DLinkedListMistakeGoToBegin<T> extends DLinkedListGood<T> {

	@Override
		public boolean goToBeginning() {
			return !super.goToBeginning();
		}
	
}
