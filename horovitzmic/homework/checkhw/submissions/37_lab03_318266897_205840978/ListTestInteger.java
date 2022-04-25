public class ListTestInteger extends ListTest<Integer>{
	static int num=0;
	@Override
	public Integer getParameterInstance() {
		num++;
		return num;
	}

}
