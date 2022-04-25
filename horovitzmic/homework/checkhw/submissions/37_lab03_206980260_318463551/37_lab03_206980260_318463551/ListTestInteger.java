public class ListTestInteger extends ListTest<Integer>{
	public static int number = 0;
	@Override
	public Integer getParameterInstance() {
		number++;
        return Integer.valueOf(number);
	}

}
