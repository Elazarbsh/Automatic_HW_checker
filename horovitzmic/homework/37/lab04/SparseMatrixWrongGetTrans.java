
public class SparseMatrixWrongGetTrans<T> extends SparseMatrix<T> {

	public SparseMatrixWrongGetTrans(int size, T defaultValue) {
		super(size, defaultValue);
	}
	public SparseMatrixWrongGetTrans(T defaultValue) {
		super(defaultValue);
	}
	
	@Override
	public Matrix<T> getTranspose() {
		this.transpose();
		return this;
	}

}
