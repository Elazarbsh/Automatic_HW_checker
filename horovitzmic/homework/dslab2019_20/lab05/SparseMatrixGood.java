
public class SparseMatrixGood<T> implements Matrix<T> {

	private int size = 0;
	private T defaultValue;
	private List<SparseMatrixEntry> matrixEntries;

	public SparseMatrixGood(T defaultValue) {
		super();
		setUp(Matrix.MAX_SIZE, defaultValue);
	}

	public SparseMatrixGood(int size, T defaultValue) {
		super();
		setUp(size, defaultValue);
	}

	private void setUp(int size, T defaultValue) {
		this.size = size;
		this.defaultValue = defaultValue;
		matrixEntries = new DLinkedList<SparseMatrixEntry>();
	}

	@Override
	public T get(int row, int col) {
		if (!areValid(row, col))
			throw new IllegalArgumentException();
		SparseMatrixEntry entry = findEntryInList(row, col);
		if (entry == null)
			return defaultValue;
		return entry.getValue();
	}

	@Override
	public void set(int row, int col, T element) {
		if (!areValid(row, col))
			throw new IllegalArgumentException();
		boolean isDefaultValue = isDefaultValue(element);
		SparseMatrixEntry entry = findEntryInList(row, col);

		// entry==null, isDefaultValue==true --> nothing todo
		// entry==null, isDefaultValue==false --> insert new entry.
		// entry!=null, isDefaultValue==true --> delete entry
		// entry!=null, isDefaultValue==false --> update entry
		if (!isDefaultValue)
			if (entry == null)
				matrixEntries.insert(new SparseMatrixEntry(row, col, element));
			else
				entry.setValue(element);
		else
			matrixEntries.remove(entry);
	}

	private boolean isDefaultValue(T element) {
		if (defaultValue == element)
			return true;
		if (defaultValue == null)
			return false;
		return defaultValue.equals(element);
	}

	private SparseMatrixEntry findEntryInList(int row, int col) {
		if (!areValid(row, col))
			throw new IllegalArgumentException();
		matrixEntries.goToBeginning();
		SparseMatrixEntry entry = matrixEntries.getCursor();
		while (entry != null) {
			if ((entry.getRow() == row) && (entry.getCol() == col)) {
				return entry;
			}
			entry = matrixEntries.getNext();
		}
		return null;
	}

	@Override
	public void transpose() {
		matrixEntries.goToBeginning();
		SparseMatrixEntry entry = matrixEntries.getCursor();
		while (entry != null) {
			int prevRow = entry.getRow();
			entry.setRow(entry.getCol());
			entry.setCol(prevRow);
			entry = matrixEntries.getNext();
		}
	}

	@Override
	public Matrix<T> getTranspose() {
		Matrix<T> tranMatrix = new SparseMatrixGood<T>(this.size, this.defaultValue);
		matrixEntries.goToBeginning();
		SparseMatrixEntry entry = matrixEntries.getCursor();
		while (entry != null) {
			tranMatrix.set(entry.getCol(), entry.getRow(), entry.getValue());
			entry = matrixEntries.getNext();
		}
		return tranMatrix;
	}

	public boolean areValid(int row, int col) {
		return (row >= 1 && col >= 1 && row <= size && col <= size);
	}

	private class SparseMatrixEntry {

		private int row;
		private int col;
		private T value;

		public SparseMatrixEntry(int row, int col, T value) {
			super();
			this.value = value;
			this.row = row;
			this.col = col;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

	}

}
