import java.awt.Point;

public class ArrayPointList implements PointList {
	private Point[] points;
	private int size;
	private int cursor;
	
	public ArrayPointList() {
		this(MAX_SIZE);
	}

	public ArrayPointList(int maxSize) {
		points = new Point[maxSize];
		size = 0;
		cursor = -1;
	}
	
	@Override
	public void append(Point newPoint) {
        if (isFull()) throw new IllegalStateException("List full");
        points[size++]=newPoint;
        cursor = size - 1;
	}

	@Override
	public void clear() {
        for (int i=0; i<points.length; i++) {
            points[i]=null;        	
        }
        size = 0;
        cursor = -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == points.length;
	}

	@Override
	public boolean goToBeginning() {
		if (isEmpty()) return false;
		cursor = 0;
		return true;
	}

	@Override
	public boolean goToEnd() {
		if (isEmpty()) return false;
		cursor = size - 1;
		return true;
	}

	@Override
	public boolean goToNext() {
		if (cursor == size - 1) return false;
		cursor++;
		return true;
	}

	@Override
	public boolean goToPrior() {
		if (cursor == 0) return false;
		cursor--;
		return true;
	}

	@Override
	public Point getCursor() {
		if (isEmpty()) return null;
		return new Point(points[cursor]);
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "Empty list";
		} else {
			StringBuilder pointsString = new StringBuilder();
			for (Point p : points) {
				pointsString.append(p);
			}
			return pointsString.toString();
		}
	}

}
