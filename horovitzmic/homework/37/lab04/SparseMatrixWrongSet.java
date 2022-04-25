
public class SparseMatrixWrongSet<T> extends SparseMatrix<T> {

	public SparseMatrixWrongSet(T defaultValue) {
		super(defaultValue);
	}

	public SparseMatrixWrongSet(int size, T defaultValue) {
		super(size, defaultValue);
	}

	@Override
	public void set(int row, int col, T element) {
	}

}
