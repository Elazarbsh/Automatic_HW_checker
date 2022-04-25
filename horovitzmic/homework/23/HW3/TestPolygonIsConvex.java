import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Michal Hotovitz
 *
 */
public class TestPolygonIsConvex {

	private String polygonStr1;
	private String polygonStr2;
	private String polygonStr3;
	private Polygon polyTest1, polyTest2, polyTest3;

	@Before
	public void setUp() {
		try {
			polygonStr1 = "1,1,3,1,2,2";
			polygonStr2 = "0,0,3,1,-2,0";
			polygonStr3 = "0,0,5,1,0,-5,-1,1";
			polyTest1 = new Polygon(polygonStr1);
			polyTest2 = new Polygon(polygonStr2);
			polyTest3 = new Polygon(polygonStr3);
		} catch (Exception e) {
			fail("An exception in Polygon constructor");
		}
	}

	@Test
	public void testIsConvex() {
		assertTrue(polyTest1.isConvex());
		assertTrue(polyTest2.isConvex());
		assertFalse(polyTest3.isConvex());

	}
}
