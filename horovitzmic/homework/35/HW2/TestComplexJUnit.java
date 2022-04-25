import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestComplexJUnit {

	// Complex1 members
	private IComplex c11;
	private IComplex c12;
	private IComplex c13;

	// Complex2 members
	private IComplex c21;
	private IComplex c22;
	private IComplex c23;

	@Before
	public void before() {
		c11 = new Complex1();
		c12 = new Complex1(3, 4);

		c21 = new Complex2();
		c22 = new Complex2(c12);

		c23 = new Complex2(1, Math.PI / 2);
		c13 = new Complex1(c23);
	}

	@Test
	public void testGets() {
		testGets(c11, 0, 0, 0, 0);
		testGets(c21, 0, 0, 0, 0);
		testGets(c12, 3, 4, 5, Math.atan(4 / 3.0));
		testGets(c22, 3, 4, 5, Math.atan(4 / 3.0));

		testGets(c23, 0, 1, 1, Math.PI / 2);
		testGets(c13, 0, 1, 1, Math.PI / 2);
	}

	private void testGets(IComplex c, double real, double img, double radius, double angle) {
		assertTrue(ComplexUtils.areEqual(c.getReal(), real));
		assertTrue(ComplexUtils.areEqual(c.getImage(), img));
		assertTrue(ComplexUtils.areEqual(c.getRadius(), radius));
		assertTrue(ComplexUtils.areEqual(c.getAngle(), angle));
	}

	@Test
	public void testSets() {
		c11 = new Complex1(0, 1);
		c21 = new Complex2(c11);
		testSet(c11);
		testSet(c21);
	}

	private void testSet(IComplex c) {
		c.setAngle(2 * Math.PI);
		assertTrue(ComplexUtils.areEqual(c.getAngle(), 0));
		assertEquals("Complex: 1.00 + 0.00i", c.toString());

		c.setAngle(Math.PI * 3);
		assertTrue(ComplexUtils.areEqual(c.getAngle(), Math.PI));
		assertEquals("Complex: -1.00 + 0.00i", c.toString());

		c.setAngle(Math.PI / 2);
		assertTrue(ComplexUtils.areEqual(c.getAngle(), Math.PI / 2));
		assertEquals("Complex: 0.00 + 1.00i", c.toString());

		c.setReal(0.75);
		assertEquals("Complex: 0.75 + 1.00i", c.toString());

		c.setRadius(5);
		assertEquals("Complex: 3.00 + 4.00i", c.toString());

		c.setImage(1);
		assertEquals("Complex: 3.00 + 1.00i", c.toString());
	}

	@Test
	public void testAdd() {
		c13.setReal(3);
		c23.setReal(3);
		testAdd(c12, c23);
		testAdd(c22, c13);
	}

	private void testAdd(IComplex c1, IComplex c2) {
		IComplex c = c1.add(c2);
		assertTrue(c.equalTo(c1));
		assertFalse(c == c1);
		assertEquals(c1.toString(), "Complex: 6.00 + 5.00i");
	}

	@Test
	public void testSub() {
		c13.setReal(4);
		c23.setReal(4);
		testSub(c12, c23);
		testSub(c22, c13);
	}

	private void testSub(IComplex c1, IComplex c2) {
		IComplex c = c1.sub(c2);
		assertTrue(c.equalTo(c1));
		assertFalse(c == c1);
		assertEquals(c1.toString(), "Complex: -1.00 + 3.00i");
	}

	@Test
	public void testCon() {
		IComplex con = c11.conjugate();
		assertTrue(c11.equalTo(c21));
		assertTrue(con.equalTo(c11));
		assertFalse(con == c11);

		con = c21.conjugate();
		assertTrue(c21.equalTo(c11));
		assertTrue(con.equalTo(c21));
		assertFalse(con == c21);

		con = c12.conjugate();
		assertTrue(con.equalTo(c12));
		assertEquals(con.toString(), "Complex: 3.00 - 4.00i");
		con = c12.conjugate();
		assertTrue(con.equalTo(c12));
		assertTrue(con.equalTo(c22));
		assertEquals(con.toString(), "Complex: 3.00 + 4.00i");

		con = c22.conjugate();
		assertTrue(con.equalTo(c22));
		assertEquals(con.toString(), "Complex: 3.00 - 4.00i");
		con = c22.conjugate();
		assertTrue(con.equalTo(c22));
		assertTrue(con.equalTo(c12));
		assertEquals(con.toString(), "Complex: 3.00 + 4.00i");
	}

	@Test
	public void testMult() {
		IComplex c = c11.mul(c13);
		assertTrue(c.equalTo(c11));
		assertFalse(c == c11);
		assertEquals(c.toString(), "Complex: 0.00 + 0.00i");

		c = c13.mul(c11);
		assertTrue(c.equalTo(c13));
		assertFalse(c == c13);
		assertEquals(c.toString(), "Complex: 0.00 + 0.00i");

		c = c23.mul(c11);
		assertTrue(c.equalTo(c23));
		assertFalse(c == c23);
		assertEquals(c.toString(), "Complex: 0.00 + 0.00i");
	}

	@Test
	public void testDiv() {

		// test divide by (0+0i)
		IComplex c = c12.div(c11);
		assertEquals(c12.toString(), "Complex: 3.00 + 4.00i");
		assertTrue(c.equalTo(c12));
		assertFalse(c == c12);

		c = c12.div(c21);
		assertEquals(c12.toString(), "Complex: 3.00 + 4.00i");

		c = c22.div(c11);
		assertEquals(c22.toString(), "Complex: 3.00 + 4.00i");
		assertTrue(c.equalTo(c22));
		assertFalse(c == c22);

		c = c22.div(c21);
		assertEquals(c22.toString(), "Complex: 3.00 + 4.00i");

		// test divide by not zero
		c = c13.div(c12);
		assertEquals(c13.toString(), "Complex: 0.16 + 0.12i");
		assertTrue(c.equalTo(c13));
		assertFalse(c == c13);

		c = c23.div(c12);
		assertEquals(c13.toString(), "Complex: 0.16 + 0.12i");
		assertTrue(c.equalTo(c13));
		assertFalse(c == c13);

	}

	@Test
	public void testMultScalar() {
		IComplex c = c12.mulScalar(1);
		assertTrue(c12.equalTo(c22));
		assertFalse(c == c12);
		assertTrue(c.equalTo(c12));

		c = c22.mulScalar(1);
		assertTrue(c22.equalTo(c12));
		assertFalse(c == c22);
		assertTrue(c.equalTo(c22));

		c = c11.mulScalar(10);
		assertTrue(c11.equalTo(c21));
		assertFalse(c == c11);
		assertTrue(c.equalTo(c11));

		c = c21.mulScalar(5);
		assertTrue(c21.equalTo(c11));
		assertFalse(c == c21);
		assertTrue(c.equalTo(c21));

		c = c12.mulScalar(5);
		assertEquals("Complex: 15.00 + 20.00i", c12.toString());
		assertFalse(c == c12);
		assertTrue(c.equalTo(c12));

		c = c22.mulScalar(5);
		assertEquals("Complex: 15.00 + 20.00i", c22.toString());
		assertFalse(c == c22);
		assertTrue(c.equalTo(c22));
	}

	@Test
	public void testEqualsTo() {
		assertTrue(c11.equalTo(c11));
		assertTrue(c21.equalTo(c21));
		assertTrue(c11.equalTo(c21));
		assertTrue(c21.equalTo(c11));

		assertTrue(c22.equalTo(c12));
		assertTrue(c12.equalTo(c22));

		assertFalse(c11.equalTo(c12));
		assertFalse(c11.equalTo(c22));
		assertFalse(c21.equalTo(c22));
		assertFalse(c21.equalTo(c12));
	}

}
