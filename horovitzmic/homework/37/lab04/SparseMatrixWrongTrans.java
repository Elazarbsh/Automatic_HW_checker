
public class SparseMatrixWrongTrans<T> extends SparseMatrix<T> {

	public SparseMatrixWrongTrans(int size, T defaultValue) {
		super(size, defaultValue);
	}
	public SparseMatrixWrongTrans(T defaultValue) {
		super(defaultValue);
	}
	
	@Override
	public void transpose() {
	}

}
