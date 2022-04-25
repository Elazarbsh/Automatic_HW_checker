public class ListTestInteger extends ListTest<Integer>{
	Integer num = 1;
	@Override
	public Integer getParameterInstance() {
		return ++num;
		//TODO add your implementation
	}

}
