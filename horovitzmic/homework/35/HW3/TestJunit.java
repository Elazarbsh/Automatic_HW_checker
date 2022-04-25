import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestJunit {
	private static double PRECISION = 1.0e-2;
	
	private IPolynom<Complex> complexPolynom;
	private IPolynom<Complex> complexPolynom2;
	private IPolynom<Complex> complexPolynom3;
	private String complexPolyStr = "Polynom: (-1.00+2.13i) + (0.00+1.00i)x^2 + (1.00+0.00i)x^3";

	private IPolynom<Double> doublePolynom;
	private IPolynom<Double> doublePolynom2;
	private IPolynom<Double> doublePolynom3;
	private String doublePolyStr = "Polynom: 1.23 + 2.20x^2 + -3.30x^3";

	@Before
	public void before() throws HW3Exception {
		complexPolynom = new Polynom<Complex>();
		doublePolynom = new Polynom<Double>();

		complexPolynom.addMonom(0, new ComplexFieldMember(new Complex(-1, 2.129)));
		complexPolynom.addMonom(3, new ComplexFieldMember(new Complex(1, 0)));
		complexPolynom.addMonom(2, new ComplexFieldMember(new Complex(0, 1)));
		complexPolynom2 = new Polynom<Complex>(complexPolynom);
		complexPolynom3 = new Polynom<Complex>(complexPolynom.getCoefficients());

		doublePolynom.addMonom(0, new DoubleFieldMember(1.234));
		doublePolynom.addMonom(2, new DoubleFieldMember(2.2));
		doublePolynom.addMonom(3, new DoubleFieldMember(-3.3));
		doublePolynom2 = new Polynom<Double>(doublePolynom);
		doublePolynom3 = new Polynom<Double>(doublePolynom.getCoefficients());
	}

	@Test
	public void testToString() {
		assertEquals(complexPolyStr, complexPolynom.toString());
		assertEquals(complexPolyStr, complexPolynom2.toString());
		assertEquals(complexPolyStr, complexPolynom3.toString());

		assertEquals(doublePolyStr, doublePolynom.toString());
		assertEquals(doublePolyStr, doublePolynom3.toString());
		assertEquals(doublePolyStr, doublePolynom3.toString());

	}

	@Test
	public void testAddMonom() throws HW3Exception {
		complexPolynom.addMonom(4, new ComplexFieldMember(new Complex(0, 0)));
		doublePolynom.addMonom(4, new DoubleFieldMember(0.0));

		assertEquals(complexPolyStr, complexPolynom.toString());
		assertEquals(doublePolyStr, doublePolynom.toString());

		complexPolynom.addMonom(5, new ComplexFieldMember(new Complex(-1, -2)));
		doublePolynom.addMonom(5, new DoubleFieldMember(-2.0));

		assertEquals(complexPolyStr + " + (-1.00-2.00i)x^5", complexPolynom.toString());
		assertEquals(doublePolyStr + " + -2.00x^5", doublePolynom.toString());

		complexPolynom.addMonom(5, new ComplexFieldMember(new Complex(1, 2)));
		doublePolynom.addMonom(5, new DoubleFieldMember(2.0));
		assertEquals(complexPolyStr, complexPolynom.toString());
		assertEquals(doublePolyStr, doublePolynom.toString());

		try {
			complexPolynom.addMonom(-4, new ComplexFieldMember(new Complex(0, 0)));
			fail("Should not work");
		} catch (HW3Exception e) {

		} catch (Exception e) {
			fail("An InputPolyException should be thrown");
		}

		try {
			doublePolynom.addMonom(-4, new DoubleFieldMember(0.0));
			fail("Should not work");
		} catch (HW3Exception e) {

		} catch (Exception e) {
			fail("An InputPolyException should be thrown");
		}

	}

	@Test
	public void removeMonom() throws HW3Exception {
		complexPolynom.removeMonom(6);
		doublePolynom.removeMonom(6);

		assertEquals(complexPolyStr, complexPolynom.toString());
		assertEquals(doublePolyStr, doublePolynom.toString());

		complexPolynom.removeMonom(2);
		doublePolynom.removeMonom(2);

		assertEquals("Polynom: (-1.00+2.13i) + (1.00+0.00i)x^3", complexPolynom.toString());
		assertEquals("Polynom: 1.23 + -3.30x^3", doublePolynom.toString());
	}

	@Test
	public void testAdd() throws HW3Exception {
		IPolynom<Complex> compAddPoly = complexPolynom.add(complexPolynom2);
		assertEquals(complexPolyStr, complexPolynom.toString());
		assertEquals(complexPolyStr, complexPolynom2.toString());
		assertEquals("Polynom: (-2.00+4.26i) + (0.00+2.00i)x^2 + (2.00+0.00i)x^3", compAddPoly.toString());

		IPolynom<Double> doubleAddPoly = doublePolynom.add(doublePolynom2);
		assertEquals(doublePolyStr, doublePolynom.toString());
		assertEquals(doublePolyStr, doublePolynom2.toString());
		assertEquals("Polynom: 2.47 + 4.40x^2 + -6.60x^3", doubleAddPoly.toString());

		doubleAddPoly = new Polynom<Double>();
		doubleAddPoly.addMonom(0, new DoubleFieldMember(-1.234));
		doubleAddPoly = doublePolynom.add(doubleAddPoly);
		assertEquals("Polynom: 2.20x^2 + -3.30x^3", doubleAddPoly.toString());

	}

	@Test
	public void testSub() throws HW3Exception {

		IPolynom<Complex> compSubPoly = complexPolynom.sub(complexPolynom2);
		assertEquals(complexPolyStr, complexPolynom.toString());
		assertEquals(complexPolyStr, complexPolynom2.toString());
		assertEquals("", compSubPoly.toString());

		IPolynom<Double> doubleSubPoly = doublePolynom.sub(doublePolynom2);
		assertEquals(doublePolyStr, doublePolynom.toString());
		assertEquals(doublePolyStr, doublePolynom2.toString());
		assertEquals("", doubleSubPoly.toString());

		doubleSubPoly = new Polynom<Double>();
		doubleSubPoly.addMonom(0, new DoubleFieldMember(1.234));
		doubleSubPoly = doublePolynom.sub(doubleSubPoly);
		assertEquals("Polynom: 2.20x^2 + -3.30x^3", doubleSubPoly.toString());

	}

	@Test
	public void testMult() {
		IPolynom<Complex> compMultPoly = complexPolynom.mult(complexPolynom2);
		assertEquals(complexPolyStr, complexPolynom.toString());
		assertEquals(complexPolyStr, complexPolynom2.toString());
		assertEquals(
				"Polynom: (-3.53-4.26i) + (-4.26-2.00i)x^2 + (-2.00+4.26i)x^3 + (-1.00+0.00i)x^4 + (0.00+2.00i)x^5 + (1.00+0.00i)x^6",
				compMultPoly.toString());

		IPolynom<Double> doubleMultPoly = doublePolynom.mult(doublePolynom2);
		assertEquals(doublePolyStr, doublePolynom.toString());
		assertEquals(doublePolyStr, doublePolynom2.toString());
		assertEquals("Polynom: 1.52 + 5.43x^2 + -8.14x^3 + 4.84x^4 + -14.52x^5 + 10.89x^6", doubleMultPoly.toString());

		compMultPoly = complexPolynom.mult(new Polynom<Complex>());
		assertEquals("", compMultPoly.toString());
		compMultPoly = (new Polynom<Complex>()).mult(complexPolynom);
		assertEquals("", compMultPoly.toString());

		doubleMultPoly = doublePolynom.mult(new Polynom<Double>());
		assertEquals("", doubleMultPoly.toString());
		doubleMultPoly = (new Polynom<Double>()).mult(doublePolynom);
		assertEquals("", doubleMultPoly.toString());

	}

	@Test
	public void testCalculate() {
		Complex comp = complexPolynom.calculate(new ComplexFieldMember(new Complex())).getValue();
		assertEquals(comp, new Complex(-1,2.129));
		comp = complexPolynom.calculate(new ComplexFieldMember(new Complex(1, 1))).getValue();
		assertEquals(comp, new Complex(-5, 4.129));
		comp = complexPolynom.calculate(new ComplexFieldMember(new Complex(0, 1))).getValue();
		assertEquals(comp, new Complex(-1, 0.129));
		comp = complexPolynom.calculate(new ComplexFieldMember(new Complex(1, 0))).getValue();
		assertEquals(comp, new Complex(0, 3.129));
		comp = (new Polynom<Complex>()).calculate(new ComplexFieldMember(new Complex(1, 0))).getValue();
		assertEquals(comp, new Complex());
			
		double d = doublePolynom.calculate(new DoubleFieldMember(0.0)).getValue();
		assertEquals(d, 1.23, PRECISION);
		d = doublePolynom.calculate(new DoubleFieldMember(1.0)).getValue();
		assertEquals(d, 0.13, PRECISION);
		d = doublePolynom.calculate(new DoubleFieldMember(-3.0)).getValue();
		assertEquals(d, 110.13, PRECISION);
		d = doublePolynom.calculate(new DoubleFieldMember(2.5)).getValue();
		assertEquals(d, -36.5825, PRECISION);
		d = (new Polynom<Double>()).calculate(new DoubleFieldMember(1.0)).getValue();
		assertEquals(d, 0.0, PRECISION);

	}

	@Test
	public void testEquals() throws HW3Exception {
		assertEquals(complexPolynom, complexPolynom);
		assertEquals(complexPolynom, complexPolynom2);
		assertEquals(complexPolynom, complexPolynom3);

		assertEquals(doublePolynom, doublePolynom);
		assertEquals(doublePolynom, doublePolynom2);
		assertEquals(doublePolynom, doublePolynom3);

		doublePolynom.addMonom(5, new DoubleFieldMember(-1.0));
		doublePolynom2.addMonom(5, new DoubleFieldMember(-1.00));
		assertEquals(doublePolynom, doublePolynom2);
		assertNotEquals(doublePolynom, doublePolynom3);
	}

}