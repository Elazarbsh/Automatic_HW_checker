import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Michal Hotovitz
 *
 */
public class TestPrismPyramidPartB {

	private static double PRECISION = 1.0e-5;

	private String polygonStr1;
	private String polygonStr2;
	private String polygonStr3;
	Polygon polyTest1, polyTest2, polyTest3;
	PrismPyramid prism1, prism2, prism3;
	PrismPyramid pyramid1, pyramid2, pyramid3;

	@Before
	public void setUp() {
		try {
			polygonStr1 = "1,1,3,1,2,2";

			polygonStr2 = "0,0,3,1,-2,0";
			polygonStr3 = "0,0,5,1,0,-5,-1,1";
			polyTest1 = new Polygon(polygonStr1);
			polyTest2 = new Polygon(polygonStr2);
			polyTest3 = new Polygon(polygonStr3);

			prism1 = new Prism(polyTest1, 0, 1);
			prism2 = new Prism(polyTest2, -1.0, 1);
			prism3 = new Prism(polyTest3, 0, -1);
			pyramid1 = new Pyramid(polyTest1, 0, 1, 1, 1);
			pyramid2 = new Pyramid(polyTest2, -1.0, 1, 1, 0);
			pyramid3 = new Pyramid(polyTest3, 0, 1, 1, -1);
		} catch (Exception e) {
			fail("An exception in Polygon constructor");
		}
	}

	@Test
	public void testConstructorsAndEquals() {
		PrismPyramid prism12 = null;
		PrismPyramid prism22 = null;
		PrismPyramid prism32 = null;
		PrismPyramid pyramid12 = null;
		PrismPyramid pyramid22 = null;
		PrismPyramid pyramid32 = null;
		try {
			Polygon polygon1 = new Polygon(polygonStr1);
			prism12 = new Prism(polygon1, 0, 1);
			prism22 = new Prism(polyTest2, -1.0, 1);
			prism32 = new Prism(polyTest3, 0, -1);
			pyramid12 = new Pyramid(polyTest1, 0, 1, 1, 1);
			pyramid22 = new Pyramid(polyTest2, -1.0, 1, 1, 0);
			pyramid32 = new Pyramid(polyTest3, 0, 1, 1, -1);
		} catch (Exception e) {
			fail("An exception in constructor");
		}

		assertTrue(prism12.equals(prism1));
		assertTrue(pyramid12.equals(pyramid1));
		assertTrue(prism22.equals(prism2));
		assertTrue(pyramid22.equals(pyramid2));
		assertTrue(prism32.equals(prism3));
		assertTrue(pyramid32.equals(pyramid3));

	}

	@Test
	public void testDiffrentOrderOfVertices() {
		String polygonStr12 = "2,2,1,1,3,1";	
		PrismPyramid prism12 = null;
		PrismPyramid pyramid12 = null;
		try {
			Polygon polygon12 = new Polygon(polygonStr12);
			prism12 = new Prism(polygon12, 0, 1);
			pyramid12 = new Pyramid(polygon12, 0, 1, 1, 1);
		} catch (Exception e) {
			fail("An exception in Polygon constructor");
		}

		assertTrue(prism12.equals(prism1));
		assertTrue(pyramid12.equals(pyramid1));

	}
	
	
	@Test
	public void testGetBase() {
		assertEquals(prism1.getBaseString(),
				"The Polygon points are ( Point [x=1.00, y=1.00] Point [x=2.00, y=2.00] Point [x=3.00, y=1.00] )");
		assertEquals(prism2.getBaseString(),
				"The Polygon points are ( Point [x=-2.00, y=0.00] Point [x=0.00, y=0.00] Point [x=3.00, y=1.00] )");
		assertEquals(prism3.getBaseString(),
				"The Polygon points are ( Point [x=-1.00, y=1.00] Point [x=0.00, y=-5.00] Point [x=5.00, y=1.00] Point [x=0.00, y=0.00] )");
		assertEquals(pyramid1.getBaseString(),
				"The Polygon points are ( Point [x=1.00, y=1.00] Point [x=2.00, y=2.00] Point [x=3.00, y=1.00] )");
		assertEquals(pyramid2.getBaseString(),
				"The Polygon points are ( Point [x=-2.00, y=0.00] Point [x=0.00, y=0.00] Point [x=3.00, y=1.00] )");
		assertEquals(pyramid3.getBaseString(),
				"The Polygon points are ( Point [x=-1.00, y=1.00] Point [x=0.00, y=-5.00] Point [x=5.00, y=1.00] Point [x=0.00, y=0.00] )");
	}

	@Test
	public void testGetHeight() {
		assertTrue(areEqual(prism1.getHeight(), 1));
		assertTrue(areEqual(prism2.getHeight(), 2));
		assertTrue(areEqual(prism3.getHeight(), 1));
		assertTrue(areEqual(pyramid1.getHeight(), 1));
		assertTrue(areEqual(pyramid2.getHeight(), 1));
		assertTrue(areEqual(pyramid3.getHeight(), 1));	
	}

	@Test
	public void testToString() {
		assertEquals(prism1.toString(),
				"Prism: Base shape=The Polygon points are ( Point [x=1.00, y=1.00] Point [x=2.00, y=2.00] Point [x=3.00, y=1.00] ). z-values for bases=0.00,1.00");
		assertEquals(prism2.toString(),
				"Prism: Base shape=The Polygon points are ( Point [x=-2.00, y=0.00] Point [x=0.00, y=0.00] Point [x=3.00, y=1.00] ). z-values for bases=-1.00,1.00");
		assertEquals(prism3.toString(),
				"Prism: Base shape=The Polygon points are ( Point [x=-1.00, y=1.00] Point [x=0.00, y=-5.00] Point [x=5.00, y=1.00] Point [x=0.00, y=0.00] ). z-values for bases=-1.00,0.00");
		assertEquals(pyramid1.toString(),
				"Pyramid: Base shape=The Polygon points are ( Point [x=1.00, y=1.00] Point [x=2.00, y=2.00] Point [x=3.00, y=1.00] ). z-base shape =0.00. Apex=(1.00,1.00,1.00)");
		assertEquals(pyramid2.toString(),
				"Pyramid: Base shape=The Polygon points are ( Point [x=-2.00, y=0.00] Point [x=0.00, y=0.00] Point [x=3.00, y=1.00] ). z-base shape =-1.00. Apex=(1.00,1.00,0.00)");
		assertEquals(pyramid3.toString(),
				"Pyramid: Base shape=The Polygon points are ( Point [x=-1.00, y=1.00] Point [x=0.00, y=-5.00] Point [x=5.00, y=1.00] Point [x=0.00, y=0.00] ). z-base shape =0.00. Apex=(1.00,1.00,-1.00)");
	}

	private boolean areEqual(double d1, double d2) {
		return Math.abs(d1 - d2) < PRECISION;
	}
}
