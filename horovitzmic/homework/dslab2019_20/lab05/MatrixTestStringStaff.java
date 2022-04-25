public class MatrixTestStringStaff extends MatrixTestStaff<String> {

	private static int i = 0;

	public String getParameterInstance() {
		return String.valueOf(i++);
	}

}
