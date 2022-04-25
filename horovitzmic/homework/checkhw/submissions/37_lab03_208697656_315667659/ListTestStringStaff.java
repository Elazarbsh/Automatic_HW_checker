public class ListTestStringStaff extends ListTestStaff<String>{
	public static int i=0;
	@Override
	public String getParameterInstance() {
		i++;
		return String.valueOf(i);
	}
	

}
