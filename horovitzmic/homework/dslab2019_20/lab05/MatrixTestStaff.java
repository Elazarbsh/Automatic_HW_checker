import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public abstract class MatrixTestStaff<T> {

	private MatrixFactory<T> matrixFactory = new MatrixFactory<T>();
	private Matrix<T> matrix3;
	private T defaultVal;
	private T newInstance2;
	private T newInstance3;

	/**
	 * @return a new instance of parameter T. Two instances which are created by
	 *         this method should be different according to "equals" method. For
	 *         example for T=Object, the implementation can be "return new
	 *         Object();".
	 */
	// An example for a default generic implementation, is as follows:
	// public T getParameterInstance() {
	// T newInstance=dlistFactory.getTInstance(this);
	// if (newInstance == null) {
	// fail("new instance is null");
	// }
	// return newInstance;
	// }
	// The above implementation return a new instance of parameter T created by
	// calling the T() constructor.
	// Precondition: class T has a construction without parameters.
	// Note, that for T=Object and T=String the precondition holds.
	// However, there's a big difference between Object and String.
	// For T=Object, two instances which were created by this method are different
	// according to "equals" method,
	// but for T=String they are equal.
	// This characteristic of T should be taken into consideration where designing
	// the tests.
	public abstract T getParameterInstance();

	@Before
	public void setUp() throws Exception {
		

		defaultVal = getParameterInstance();
		newInstance2 = getParameterInstance();
		newInstance3 = getParameterInstance();
		
		matrix3 = matrixFactory.getMatrix(3, defaultVal);

		if ((defaultVal.equals(newInstance2)) || (defaultVal.equals(newInstance3))
				|| (newInstance3.equals(newInstance2))) {
			fail("new instances should be different");
		}
	}
	
	@Test
	public void testSparseMatrixSize() {
		assertEquals(defaultVal, matrix3.get(1,2));
		assertEquals(defaultVal, matrix3.get(1,3));
		assertEquals(defaultVal, matrix3.get(3,2));
		try {
			matrix3.get(1,4);
			fail("Should not work");
		} catch (Exception e) { }
		try {
			matrix3.get(0,1);
			fail("Should not work");
		} catch (Exception e) { }
		

		assertEquals(defaultVal, matrix3.get(1,2));
		assertEquals(defaultVal, matrix3.get(1,3));
		assertEquals(defaultVal, matrix3.get(3,2));
		try {
			matrix3.get(1,4);
			fail("Should not work");
		} catch (Exception e) { }
		try {
			matrix3.get(0,1);
			fail("Should not work");
		} catch (Exception e) { }
		
		
		Matrix<T> matrixDefSize = matrixFactory.getMatrix(defaultVal);
		assertEquals(defaultVal, matrixDefSize.get(1,2));
		assertEquals(defaultVal, matrixDefSize.get(1,3));
		assertEquals(defaultVal, matrixDefSize.get(100,100));
		try {
			matrix3.get(101,1);
			fail("Should not work");
		} catch (Exception e) { }
		try {
			matrix3.get(0,1);
			fail("Should not work");
		} catch (Exception e) { }
		
		
	}


	@Test
	public void testGetSet() {
		matrix3.set(1,1,newInstance2);
		assertEquals(newInstance2, matrix3.get(1,1));
		assertNotEquals(defaultVal, matrix3.get(1,1));
		assertEquals(defaultVal, matrix3.get(2,2));
		matrix3.set(2,2,newInstance3);
		assertEquals(newInstance2, matrix3.get(1,1));
		assertNotEquals(defaultVal, matrix3.get(1,1));
		assertEquals(newInstance3, matrix3.get(2,2));
		assertNotEquals(defaultVal, matrix3.get(2,2));
		assertEquals(defaultVal, matrix3.get(3,3));
		assertEquals(defaultVal, matrix3.get(1,3));
		
		matrix3.set(1,3,defaultVal);
		assertEquals(defaultVal, matrix3.get(1,3));
		matrix3.set(1,1,defaultVal);
		assertEquals(defaultVal, matrix3.get(1,1));
		
		try {
			matrix3.set(4,1,newInstance3);
			fail("Should not work");
		} catch (Exception e) { }
		try {
			matrix3.set(1,4,newInstance3);
			fail("Should not work");
		} catch (Exception e) { }
	
	}

	@Test
	public void testTranspose() {
		matrix3.set(1,1,newInstance2);
		matrix3.set(2,3,newInstance3);
		matrix3.transpose();
		
		chackTrans(matrix3);
	}

	private void chackTrans(Matrix<T> transM) {
		assertEquals(newInstance2, transM.get(1,1));
		assertEquals(defaultVal, transM.get(2,2));
		assertEquals(defaultVal, transM.get(2,3));
		assertEquals(defaultVal, transM.get(1,2));
		assertEquals(newInstance3, transM.get(3,2));
	}

	@Test
	public void testGetTranspose() {
		matrix3.set(1,1,newInstance2);
		matrix3.set(2,3,newInstance3);
		Matrix<T> transMatrix = matrix3.getTranspose();
		assertEquals(newInstance2, matrix3.get(1,1));
		assertEquals(defaultVal, matrix3.get(2,2));
		assertEquals(newInstance3, matrix3.get(2,3));
		assertEquals(defaultVal, matrix3.get(1,2));
		assertEquals(defaultVal, matrix3.get(3,2));

		chackTrans(transMatrix);
	}

}
