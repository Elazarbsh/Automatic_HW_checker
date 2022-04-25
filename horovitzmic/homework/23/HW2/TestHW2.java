import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Michal Hotovitz
 */
public class TestHW2 {

	LineSegment lineClass1No1; // center(0,0),length=1,angle=0
	LineSegment lineClass2No1; // p1(-1,0), p2(1,0)

	LineSegment lineClass1No2; // center(1.5,2),length=5,angle=..
	LineSegment lineClass2No2; // p1(0,0), p2(3,4)

	LineSegment lineClass1No3; // center(-1.5,2),length=5,angle=..
	LineSegment lineClass2No3; // p1(0,0), p2(-3,4)

	LineSegment lineClass1No4; // center(-3,3),length=...,angle=3pi/4
	LineSegment lineClass2No4; // p1(0,0), p2(-6,6)

	LineSegment lineClass1No5; // center(3,3),length=...,angle=pi/4
	LineSegment lineClass2No5; // p1(0,0), p2(6,6)

	LineSegment lineClass1No6; // center(0,0),length=1,angle=pi/2
	LineSegment lineClass2No6; // p1(0,1), p2(0,-1)

	@Before
	public void setUp() {
		Point end1 = new Point();
		Point end2 = new Point(3, 4);
		Point center = new Point(1.5, 2);
		double length = 5;
		double angle = Math.atan((double) 4 / 3);

		lineClass1No1 = new LineSegment1();
		lineClass2No1 = new LineSegment2();

		lineClass1No2 = new LineSegment1(center, length, angle);
		lineClass2No2 = new LineSegment2(end1, end2);

		center.setX(-1.5);
		angle = Math.atan((double) -4 / 3) + Math.PI;
		end2.setX(-3);
		lineClass1No3 = new LineSegment1(center, length, angle);
		lineClass2No3 = new LineSegment2(end1, end2);

		end1 = new Point();
		end2 = new Point(6, 6);
		center = new Point(3, 3);
		length = Math.sqrt(72);
		angle = Math.PI / 4;
		lineClass1No5 = new LineSegment1(center, length, angle);
		lineClass2No5 = new LineSegment2(end1, end2);

		center.setX(-3);
		angle = 3 * Math.PI / 4;
		end2.setX(-6);
		lineClass1No4 = new LineSegment1(center, length, angle);
		lineClass2No4 = new LineSegment2(end1, end2);

		lineClass1No6 = new LineSegment1(new Point(0, 0), 1, Math.PI / 2);
		lineClass2No6 = new LineSegment2(new Point(0, 0), 1, Math.PI / 2);
	}

	@Test
	public void testConstructors() {

		LineSegment l11_2 = new LineSegment1(new Point(1, 1), -5, 5);
		LineSegment l21_2 = new LineSegment2(new Point(1, 1), -5, 5);
		assertTrue(l11_2.isEqual(l21_2));
		assertTrue(lineClass1No1.isEqual(l11_2));
		assertTrue(lineClass2No1.isEqual(l21_2));

		Point[] ends = lineClass2No2.getEndPoints();
		LineSegment lineClass2No2V2 = new LineSegment2(ends[0], ends[1]);
		assertTrue(lineClass2No2V2.isEqual(lineClass2No2));

		Point end1 = new Point();
		Point end2 = new Point(-3, -4);
		Point center = new Point(-1.5, -2);
		double length = 5;
		double angle = Math.atan((double) 4 / 3);

		LineSegment lineClass1_1 = new LineSegment1(end2, end1);
		LineSegment lineClass2_1 = new LineSegment2(center, length, angle);
		LineSegment lineClass1_2 = new LineSegment1(center, length, angle);
		LineSegment lineClass2_2 = new LineSegment2(end1, end2);

		assertTrue(lineClass1_1.isEqual(lineClass2_1));
		assertTrue(lineClass1_2.isEqual(lineClass2_2));
		assertTrue(lineClass2_1.isEqual(lineClass1_1));
		assertTrue(lineClass2_2.isEqual(lineClass1_2));
		assertTrue(lineClass2_1.isEqual(lineClass1_2));

		// test a copy in the constructor
		end1.setX(2);
		center.setY(9);
		end1.setY(10);
		assertTrue(lineClass1_1.isEqual(lineClass2_1));
		assertTrue(lineClass1_2.isEqual(lineClass2_2));

	}

	@Test
	public void testGetLength() {
		assertTrue(lineClass1No1.getLength() == 1);
		assertTrue(lineClass2No1.getLength() == 1);

		assertTrue(lineClass1No2.getLength() == 5);
		assertTrue(lineClass2No2.getLength() == 5);

		assertTrue(lineClass1No3.getLength() == 5);
		assertTrue(lineClass2No3.getLength() == 5);

		assertTrue(lineClass1No6.getLength() == 1);
		assertTrue(lineClass2No6.getLength() == 1);

		Point p = new Point();
		LineSegment l1 = new LineSegment1(p, p);
		LineSegment l2 = new LineSegment1(p, p);
		assertTrue(l1.getLength() == 0);
		assertTrue(l2.getLength() == 0);

	}

	@Test
	public void testUpdateLength() {
		lineClass1No1.updateLength(10);
		lineClass2No1.updateLength(10);
		
		assertTrue(lineClass1No1.getLength() == 10);
		assertTrue(lineClass2No1.getLength() == 10);
		
		assertEquals(lineClass1No1.getCenter(),new Point());
		assertEquals(lineClass2No1.getCenter(),new Point());
		
		assertTrue(lineClass1No1.getAngle()==0);
		assertTrue(lineClass2No1.getAngle()==0);
	
	}

	@Test
	public void testGetCenter() {

		Point center = lineClass1No1.getCenter();
		center.setX(10);
		assertTrue(lineClass2No1.isEqual(lineClass1No1));
		assertFalse(center == lineClass1No1.getCenter());

		center = lineClass2No1.getCenter();
		center.setX(10);
		assertTrue(lineClass1No1.isEqual(lineClass2No1));
		assertFalse(center == lineClass2No1.getCenter());

		assertEquals(lineClass2No1.getCenter(), lineClass1No1.getCenter());

	}

	@Test
	public void testUpdateCenter() {
		
		Point center = new Point(10,10);
		
		lineClass1No1.updateCenter(center);
		lineClass2No1.updateCenter(center);

		assertFalse(lineClass1No1.getCenter()==center);
		assertFalse(lineClass2No1.getCenter()==center);
		
		assertEquals(lineClass1No1.getCenter(),center);
		assertEquals(lineClass2No1.getCenter(),center);
		
		
		center.moveHorizontal(5);
		assertNotEquals(lineClass1No1.getCenter(),center);
		assertNotEquals(lineClass2No1.getCenter(),center);
		
	}

	@Test
	public void testGetAngle() {
		LineSegment lineClass1No1_2 = new LineSegment1(new Point(), 1, Math.PI * 2);
		assertTrue(lineClass1No1_2.getAngle() == 0);
		assertTrue(lineClass1No1_2.isEqual(lineClass1No1));

		LineSegment lineClass2No1_2 = new LineSegment1(new Point(), 1, Math.PI * 2);
		assertTrue(lineClass2No1_2.getAngle() == 0);
		assertTrue(lineClass2No1_2.isEqual(lineClass2No1));
	}

	@Test
	public void testUpdateAngle() {
		
	}

	@Test
	public void testGetEndPoints() {
	}

	@Test
	public void testUpdateEndPoints() {
		

	}

	@Test
	public void testCopy() {
		LineSegment lineClass1No1Copy = lineClass1No1.copy();
		LineSegment lineClass2No1Copy = lineClass2No1.copy();
		assertTrue(lineClass1No1Copy.isEqual(lineClass1No1));
		assertTrue(lineClass2No1Copy.isEqual(lineClass2No1));
		assertFalse(lineClass1No1Copy == lineClass1No1);
		assertFalse(lineClass2No1Copy == lineClass2No1);
	}

	@Test
	public void testRotate() {
		lineClass1No1.rotate(0);
		assertTrue(lineClass2No1.isEqual(lineClass1No1));
		lineClass2No1.rotate(0);
		assertTrue(lineClass2No1.isEqual(lineClass1No1));
		lineClass1No1.rotate(Math.PI/2);
		assertTrue(lineClass1No6.isEqual(lineClass1No1));
		lineClass2No1.rotate(Math.PI/2);
		assertTrue(lineClass2No6.isEqual(lineClass2No1));

	}

	@Test
	public void testGetOrthogonalLine() {
		LineSegment lineClass1No1Copy =lineClass1No1.copy();
		LineSegment lineClass1No1Or = lineClass1No1.getOrthogonalLine();
		assertTrue(Math.abs(lineClass1No1Or.getAngle()-lineClass1No1.getAngle())==Math.PI/2);
		assertTrue(lineClass1No1Copy.isEqual(lineClass1No1));
		assertTrue(lineClass1No6.isEqual(lineClass1No1Or));
		assertEquals(lineClass1No1.getCenter(),lineClass1No1Copy.getCenter());
		
		LineSegment lineClass2No1Copy =lineClass2No1.copy();
		LineSegment lineClass2No1Or = lineClass2No1.getOrthogonalLine();	
		assertTrue(Math.abs(lineClass2No1Or.getAngle()-lineClass2No1.getAngle())==Math.PI/2);
		assertTrue(lineClass2No1Copy.isEqual(lineClass2No1));
		assertTrue(lineClass2No6.isEqual(lineClass2No1Or));
		assertEquals(lineClass2No1.getCenter(),lineClass2No1Copy.getCenter());
		
	}

	@Test
	public void testMove() {
		// lineClass1No1 = center(0,0),length=1,angle=0
		// lineClass2No2 = p1(-1,0), p2(1,0)
		lineClass1No1.move(10, 5);
		lineClass2No1.move(10, 5);
		testResultsOfMove();
	}

	@Test
	public void testMoveHorizontalAndVeritical() {
		lineClass1No1.moveVertical(5);
		lineClass1No1.moveHorizontal(10);
		lineClass2No1.moveVertical(5);
		lineClass2No1.moveHorizontal(10);
		testResultsOfMove();
	}

	private void testResultsOfMove() {
		assertTrue(lineClass1No1.isEqual(lineClass2No1));
		assertEquals(lineClass1No1.getCenter(), lineClass2No1.getCenter());
		assertEquals(lineClass1No1.getCenter(), new Point(10, 5));
		assertTrue(lineClass1No1.getLength() == 1);
		assertTrue(lineClass2No1.getLength() == 1);
		assertTrue(lineClass1No1.getAngle() == 0);
		assertTrue(lineClass2No1.getAngle() == 0);
	}


	@Test
	public void testScale() {
		// lineClass1No1 = center(0,0),length=1,angle=0
		// lineClass2No2 = p1(-1,0), p2(1,0)
		lineClass1No1.scale(1);
		lineClass2No1.scale(1);
		assertTrue(lineClass1No1.getLength()==1);
		assertTrue(lineClass2No1.getLength()==1);
		assertEquals(lineClass1No1.getCenter(),new Point(0,0));
		assertEquals(lineClass2No1.getCenter(),new Point(0,0));
	
		
		lineClass1No1.scale(2);
		lineClass2No1.scale(2);
		assertTrue(lineClass1No1.getLength()==2);
		assertTrue(lineClass2No1.getLength()==2);
		
		lineClass1No1.scale(0);
		lineClass2No1.scale(0);
		assertTrue(lineClass1No1.getLength()==0);
		assertTrue(lineClass2No1.getLength()==0);
		assertEquals(lineClass2No1.getEndPoints()[0],new Point(0,0));
	}

	@Test
	public void testIsEqual() {
		assertTrue(lineClass1No1.isEqual(lineClass2No1));
		assertTrue(lineClass1No5.isEqual(lineClass2No5));
		assertTrue(lineClass1No3.isEqual(lineClass2No3));
		assertTrue(lineClass1No4.isEqual(lineClass2No4));
		assertTrue(lineClass1No2.isEqual(lineClass2No2));
		assertTrue(lineClass2No6.isEqual(lineClass1No6));

		assertFalse(lineClass1No1.isEqual(lineClass1No5));
		assertFalse(lineClass2No1.isEqual(lineClass2No5));

		assertTrue(lineClass2No1.isEqual(lineClass1No1));
		assertTrue(lineClass2No2.isEqual(lineClass1No2));

		assertFalse(lineClass1No1.isEqual(null));
		assertFalse(lineClass1No2.isEqual(null));
		
		assertFalse(lineClass1No3.isEqual(lineClass2No2));
		assertFalse(lineClass2No1.isEqual(lineClass1No3));

	}

}
