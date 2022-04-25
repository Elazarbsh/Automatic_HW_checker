import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Michal Hotovitz
 *
 */
public class TestPolygonPartA {

	private static double PRECISION = 1.0e-5;

	private Point p1;
	private Point p2;
	private Point p3;
	private Point p4;
	private List<Point> vertices;
	private String polygonStr;
	Polygon polyTest1, polyTest2, polyTest3, polyTest4;

	@Before
	public void setUp() {
		p1 = new Point(-1, -1);
		p2 = new Point(-1, 1);
		p3 = new Point(1, 1);
		p4 = new Point(1, -1);
		vertices = new ArrayList<Point>();
		vertices.add(p1);
		vertices.add(p2);
		vertices.add(p3);
		vertices.add(p4);
		polygonStr = "-1,-1,-1,1,1,1,1,-1";
		try {
			polyTest3 = new Polygon(vertices);
			polyTest4 = new Polygon(polygonStr);
		} catch (Exception e) {
			fail("An exception in Polygon constructor");
		}
	}

	@Test
	public void testGetVertices() {
		Set<Point> poly3Vertices = new HashSet<Point>(polyTest3.getVertices());
		Set<Point> poly4Vertices = new HashSet<Point>(polyTest4.getVertices());
		assertTrue(poly3Vertices.equals(poly4Vertices));
	}

	@Test
	public void testGetEdges() {
		Set<LineSegment> poly3Edges = new HashSet<LineSegment>(polyTest3.getEdges());
		Set<LineSegment> poly4Edges = new HashSet<LineSegment>(polyTest4.getEdges());
		assertEquals(poly3Edges.size(), poly4Edges.size());
		boolean isContained = false;
		for (LineSegment lineInPoly3 : poly3Edges) {
			isContained = false;
			for (LineSegment lineInPoly4 : poly4Edges) {
				if (lineInPoly4.isEqual(lineInPoly3)) {
					isContained = true;
					break;
				}
			}

			if (!isContained) {
				fail("Edges of polyTest3 should be equal to edges of polyTest4");
			}

		}
		// assertTrue(poly3Edges.equals(poly4Edges)); //this test works only if equals method is overrided in LineSegment correctly.
	}

	@Test
	public void testGetNumberOfEdges() {
		assertEquals(polyTest3.getNumberOfEdges(), 4);
		assertEquals(polyTest4.getNumberOfEdges(), 4);
	}

	@Test
	public void testGetPerimeter() {
		assertTrue(areEqual(polyTest3.getPerimeter(), 8));
	}

	@Test
	public void testToString() {
		assertEquals(polyTest3.toString(),
				"The Polygon points are ( Point [x=-1.00, y=-1.00] Point [x=-1.00, y=1.00] Point [x=1.00, y=1.00] Point [x=1.00, y=-1.00] )");
	}

	private boolean areEqual(double d1, double d2) {
		return Math.abs(d1 - d2) < PRECISION;
	}
}
