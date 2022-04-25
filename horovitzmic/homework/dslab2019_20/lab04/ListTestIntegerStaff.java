public class ListTestIntegerStaff extends ListTestStaff<Integer>{
	public static int i=0;
	@Override
	public Integer getParameterInstance() {
		i++;
		return Integer.valueOf(i);
	}

}
