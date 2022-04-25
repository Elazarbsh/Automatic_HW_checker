
public interface IComplex {

	/** @return the real part. */
	public double getReal();

	/** @return the real part. */
	public double getImage();

	/** @return the angle in the polar representation. 
	 * The angle will be between -pi and pi (-pi<angle<=pi)*/
	public double getAngle();

	/** @return the radius the polar representation. */
	public double getRadius();

	/** @param real - update the real part to be the new parameter. */
	public void setReal(double real);

	/** @param img - update the image part to be the new parameter. */
	public void setImage(double img);

	/**
	 * @param ang - update the angle in the polar representation to be the new
	 *            parameter.
	 */
	public void setAngle(double ang);

	/**
	 * @param r - update the radius in the polar representation to be the new
	 *          parameter.
	 */
	public void setRadius(double r);

	/** @param r - return the conjugate number. */
	public IComplex conjugate();

	/**
	 * @param c - add the complex number c to the current object. Return the result.
	 *          The current element represents the result. Note that the return
	 *          value is a copy of the current element, and not a reference to it.
	 */
	public IComplex add(IComplex c);

	/**
	 * @param c - substruct the complex number c from the current object. Return the
	 *          result. The current element represents the result. Note that the
	 *          return value is a copy of the current element, and not a reference
	 *          to it.
	 */
	public IComplex sub(IComplex c);

	/**
	 * @param c - multiply the current object by the complex number c. Return the
	 *          result. The current element represents the result. Note that the
	 *          return value is a copy of the current element, and not a reference
	 *          to it.
	 */
	public IComplex mul(IComplex c);

	/**
	 * @param c - multiply the current object by the scalar d (multiply the real
	 *          part by d, and the image part by d). Return the result. The current
	 *          element represents the result. Note that the return value is a copy
	 *          of the current element, and not a reference to it.
	 */
	public IComplex mulScalar(double d);

	/**
	 * @param c - divide the current object by the scalar complex number c.
	 * 			If c=0, then nothing is done.
	 *          The current element represents the result. Note that the return
	 *          value is a copy of the current element, and not a reference to it.
	 */
	public IComplex div(IComplex c);

	/**
	 * @param other - Returns true if other and the current element represents the
	 *              same element. Otherwise returns false.
	 */
	public boolean equalTo(IComplex other);

}