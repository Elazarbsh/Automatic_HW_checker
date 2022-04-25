/**
 * @author Michal Hotovitz
 * 
 *         This class represents a line segment. A line segment is a part of a
 *         line that is bounded by two distinct end points and contains every
 *         point on the line between its end points
 */
public interface LineSegment {

	/**
	 * @return the length of the segment line
	 */
	public double getLength();

	/**
	 * @param length
	 *            Update the length to be the equal to the argument. The center
	 *            point and the inclination angle should not be changed
	 */
	public void updateLength(double length);

	/**
	 * @return a copy of the center point
	 */
	public Point getCenter();

	/**
	 * @param center
	 *            Update the center point to be a copy of the argument. The length
	 *            and the inclination angle should not be changed
	 */
	public void updateCenter(Point center);

	/**
	 * @return the inclination angle. The angle should be between 0 to PI (less than PI).
	 */
	public double getAngle();

	/**
	 * @param angle
	 * 
	 *            Update the inclination angle to be equal to the argument. The
	 *            center point and the length should not be changed.
	 */
	public void updateAngle(double angle);

	/**
	 * @return an array of length two contains copies of the two end points,
	 */
	public Point[] getEndPoints();

	/**
	 * @param edge1
	 * @param edge2
	 * 
	 *            Update the center point to be a copy of the argument. The length
	 *            and the inclination angle should not be changed
	 * 
	 */
	public void updateEndPoints(Point end1, Point end2);

	/**
	 * @return a copy of this line. That is return an instance of LineSegment which
	 *         is equal to the origin line according to "isEqual()" method.
	 */
	public LineSegment copy();

	/**
	 * @param angle
	 *            Rotate the line with angle to the left. The center point and the
	 *            length should not be changed.
	 */
	public void rotate(double angle);

	/**
	 * @return a new LineSegment represents an orthogonal line which have equal
	 *         center point and length
	 */
	public LineSegment getOrthogonalLine();

	/**
	 * @param deltaX
	 * @param deltaY
	 *            Move the line by deltaX horizontally, and deltaY vertically.
	 */
	public void move(double deltaX, double deltaY);

	/**
	 * @param deltaX
	 *            Move the line by deltaX horizontally.
	 */
	public void moveHorizontal(double deltaX);

	/**
	 * @param deltaY
	 *            Move the line by deltaY vertically.
	 */
	public void moveVertical(double deltaY);

	/**
	 * @param scalingParam
	 *            Stretch/shrink the length by scalingParam (multiply the length
	 *            with scalingParam). The center point and the length should not be
	 *            changed.
	 */
	public void scale(double scalingParam);

	/**
	 * @param otherLine
	 * @return return true if otherLine and this represents the same line segment.
	 *         Otherwise, return false
	 */
	public boolean isEqual(LineSegment otherLine);

}
