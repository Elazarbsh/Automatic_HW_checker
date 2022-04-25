public class DiagonalMatrix implements Matrix {
	private int size; 
    private double diagonals[];
	
	public DiagonalMatrix(int size) {
        if (size <= 0) throw new IllegalArgumentException();
        this.size = size;
        diagonals = new double[2*size-1];
	}

	public DiagonalMatrix( ) {
        this(MAX_SIZE);		
	}
	
	private int diagonal(int i, int j) {
		if (i < 1 || j < 1 || i > size || j > size) throw new IllegalArgumentException();
		return i-j + size - 1;
	}
	
	@Override
	public double get(int i, int j) {
		return diagonals[diagonal(i,j)];
	}

	@Override
	public void set(int i, int j, double x) {
		diagonals[diagonal(i,j)]=x;;
	}
	
	@Override
	public void transpose() {
		for (int i=0;i<size-1; i++) {
			int j = 2 * size - 2 - i;
			double temp = diagonals[i];
			diagonals[i] = diagonals[j];
			diagonals[j] = temp;
		}
	}

	@Override
	public Matrix getTranspose() {
		DiagonalMatrix result = new DiagonalMatrix(size);
		// This method is twice as slower as then directly creating the reversed array.
		// However, this method is more standard and avoids code duplication.
		for (int i = 0; i < 2 * size - 1; i++) {
			result.diagonals[i] = diagonals[i];
		}
		result.transpose();
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder(10*size*size);
		for (int i=1; i<=size; i++) {
			for (int j=1; j<=size; j++) {
				if (j!=1) b.append('\t');
				b.append(get(i,j));
			}
			b.append('\n');
		}
		return b.toString();    		
	}
}
