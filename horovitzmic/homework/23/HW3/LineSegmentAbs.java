public abstract class LineSegmentAbs implements LineSegment {

	@Override
	public boolean isEqual(LineSegment line) {
		if (line == null)
			return false;
		if (Double.doubleToLongBits(line.getLength()) != Double.doubleToLongBits(this.getLength()))
			return false;
		if (Double.doubleToLongBits(line.getAngle()) != Double.doubleToLongBits(this.getAngle()))
			return false;
		if (!(line.getCenter().equals(this.getCenter())))
			return false;
		return true;
	}

	@Override
	public void scale(double scalingParam) {
		updateLength(scalingParam * getLength());
	}

	@Override
	public void move(double deltaX, double deltaY) {
		moveHorizontal(deltaX);
		moveVertical(deltaY);
	}

	@Override
	public void rotate(double angle) {
		updateAngle(getAngle() + Math.PI + angle);
	}

	@Override
	public LineSegment getOrthogonalLine() {
		LineSegment l = this.copy();
		l.rotate(Math.PI / 2);
		return l;
	}

	protected double getAngle(Point end1, Point end2) {
		double deltaX = end1.getX() - end2.getX();
		double deltaY = end1.getY() - end2.getY();
		double slote = deltaY / deltaX;
		return getAngleBetween0ToPi(Math.atan(slote));
	}

	protected double getLength(Point end1, Point end2) {
		double deltaX = Math.pow(end1.getX() - end2.getX(), 2);
		double deltaY = Math.pow(end1.getY() - end2.getY(), 2);
		return Math.sqrt(deltaX + deltaY);
	}

	protected double getAngleBetween0ToPi(double angle) {
		if (angle == 0)
			return 0;
		double res = 0;
		res = angle - Math.PI * ((int) (angle / Math.PI));
		if (res < 0)
			res += Math.PI;
		return res;
	}

	protected Point[] getEndPointsFromCenterLengthAngle(Point center, double length, double angle) {
		Point[] points = new Point[2];

		double distance = length / 2;

		double deltaX = distance * Math.cos(angle);
		double deltaY = distance * Math.sin(angle);

		points[0] = new Point(center.getX() + deltaX, center.getY() + deltaY);
		points[1] = new Point(center.getX() - deltaX, center.getY() - deltaY);

		return points;
	}

	protected Point getCenter(Point end1, Point end2) {
		double x = (end1.getX() + end2.getX()) / 2;
		double y = (end1.getY() + end2.getY()) / 2;
		return new Point(x, y);
	}
	
	
	@Override
	public String toString()
	{
		Point[] endPoint = getEndPoints();
		return "LineSegment end points ("+endPoint[0] +", " +endPoint[1]+")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj==null)
			return false;
		if (getClass().getSuperclass() != obj.getClass().getSuperclass())
			return false;
		LineSegment other = (LineSegment) obj;
		if (Double.doubleToLongBits(getAngle()) != Double.doubleToLongBits(other.getAngle()))
			return false;
		if (getCenter() == null) {
			if (other.getCenter() != null)
				return false;
		} else if (!getCenter().equals(other.getCenter()))
			return false;
		if (Double.doubleToLongBits(getLength()) != Double.doubleToLongBits(other.getLength()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(getAngle());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((getCenter() == null) ? 0 : getCenter().hashCode());
		temp = Double.doubleToLongBits(getLength());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

}
