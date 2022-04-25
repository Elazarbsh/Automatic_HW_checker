public class LineSegment2 extends LineSegmentAbs {

	private Point end1;
	private Point end2;

	public LineSegment2() {
		updateDefault();
	}

	public LineSegment2(Point end1, Point end2) {
		this.end1 = end1.copy();
		this.end2 = end2.copy();
	}

	public LineSegment2(Point center, double length, double angle) {
		if ((length < 0))
			updateDefault();
		else {
		Point[] points = getEndPointsFromCenterLengthAngle(center, length, angle);
		this.end1 = points[0];
		this.end2 = points[1];
		}
	}
	
	private void updateDefault()
	{
		end1 = new Point(-0.5, 0);
		end2 = new Point(0.5, 0);
	}

	@Override
	public double getLength() {
		return getLength(end1, end2);
	}

	@Override
	public void updateLength(double length) {
		if (length < 0)
			return;
		double angle = getAngle();
		Point[] points = getEndPointsFromCenterLengthAngle(getCenter(), length, angle);
		end1 = points[0];
		end2 = points[1];
	}

	@Override
	public Point getCenter() {
		double x = (end1.getX() + end2.getX()) / 2;
		double y = (end1.getY() + end2.getY()) / 2;
		return new Point(x, y);
	}

	@Override
	public void updateCenter(Point center) {
		double deltaX = center.getX() - getCenter().getX();
		double deltaY = center.getY() - getCenter().getY();
		move(deltaX, deltaY);
	}

	@Override
	public Point[] getEndPoints() {
		Point[] points = new Point[2];
		points[0] = end1.copy();
		points[1] = end2.copy();
		return points;
	}

	@Override
	public void updateEndPoints(Point end1, Point end2) {
		this.end1 = end1.copy();
		this.end2 = end2.copy();
	}

	@Override
	public LineSegment getOrthogonalLine() {
		LineSegment l = new LineSegment2(end1, end2);
		l.rotate(Math.PI / 2);
		return l;
	}

	
	@Override
	public void moveHorizontal(double deltaX) {
		end1.moveHorizontal(deltaX);
		end2.moveHorizontal(deltaX);
	}

	@Override
	public void moveVertical(double deltaY) {
		end1.moveVertical(deltaY);
		end2.moveVertical(deltaY);
	}

	@Override
	public double getAngle() {
		return getAngle(end1, end2);
	}

	

	@Override
	public void updateAngle(double angle) {
		Point center = getCenter();
		double newAngle = getAngleBetween0ToPi(getAngle() + angle);
		double length = getLength();

		Point[] points = getEndPointsFromCenterLengthAngle(center, length,newAngle);
		end1 = points[0];
		end2 = points[1];
	}

	@Override
	public LineSegment copy() {
		return new LineSegment2(end1,end2);
	}

}
