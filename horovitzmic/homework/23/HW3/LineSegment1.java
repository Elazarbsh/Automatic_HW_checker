public class LineSegment1 extends LineSegmentAbs {

	private Point center;
	private double length;
	private double angle;

	public LineSegment1() {
		super();
		updateDefault();
	}

	public LineSegment1(Point center, double length, double angle) {
		if ((length < 0))
			updateDefault();
		else {
			this.length = length;
			this.angle = getAngleBetween0ToPi(angle);
			this.center = center.copy();
		}
	}

	public LineSegment1(Point end1, Point end2) {
		length = getLength(end1, end2);
		angle = getAngle(end1, end2);
		center = getCenter(end1, end2);
	}

	private void updateDefault() {
		length = 1;
		angle = 0;
		center = new Point(0, 0);
	}

	@Override
	public double getLength() {
		return length;
	}

	@Override
	public void updateLength(double length) {
		if (length < 0)
			return;
		this.length = length;
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public void updateAngle(double angle) {
		this.angle = getAngleBetween0ToPi(angle);
	}

	@Override
	public Point getCenter() {
		return center.copy();
	}

	@Override
	public void updateCenter(Point center) {
		this.center = center.copy();
	}

	@Override
	public Point[] getEndPoints() {
		return getEndPointsFromCenterLengthAngle(center, length, angle);
	}

	@Override
	public void updateEndPoints(Point end1, Point end2) {
		center = getCenter(end1, end2);
		length = getLength(end1, end2);
		angle = getAngle(end1, end2);
	}

	@Override
	public void moveHorizontal(double deltaX) {
		center.moveHorizontal(deltaX);
	}

	@Override
	public void moveVertical(double deltaY) {
		center.moveVertical(deltaY);
	}

	@Override
	public LineSegment copy() {
		return new LineSegment1(center, length, angle);
	}

}