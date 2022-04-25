public class ListTestInteger extends ListTest<Integer>{
	private int x;
	@Override
	public Integer getParameterInstance() {
		return new Integer(++x);
	}

}
