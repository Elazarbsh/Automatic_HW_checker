public class MatrixTestIntegerStaff extends MatrixTestStaff<Integer> {
	private static int i = 0;

	public Integer getParameterInstance() {
		return i++;
	}

}
