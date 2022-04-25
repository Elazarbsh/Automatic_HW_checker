

import static org.junit.Assert.assertEquals;

import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class BFS_Test {

	Graph<Integer> g;

	@Before
	public void setUp() throws Exception {
		g = new Graph<Integer>();
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		g.addVertex(5);
		g.addVertex(6);
		g.addVertex(7);
		g.addVertex(8);

		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 5);
		g.addEdge(2, 6);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 7);
	}

	@Test
	public void testBFSTree() {
		Graph<Integer> tree1 = g.BFSTree(1);
		assertEquals("1:2,3,4\n" + "2:5,6\n" + "3\n" + "4:7\n" + "5\n" + "6\n" + "7", tree1.toString());

		Graph<Integer> tree2 = g.BFSTree(2);
		assertEquals("2:5,6\n" + "5\n" + "6", tree2.toString());

		Graph<Integer> tree8 = g.BFSTree(8);
		assertEquals("8", tree8.toString());

		SortedMap<Integer, Integer> distances = new TreeMap<Integer, Integer>();

		tree1 = g.BFSTree(1, distances);
		assertEquals("{1=0, 2=1, 3=1, 4=1, 5=2, 6=2, 7=2}", distances.toString());

		tree2 = g.BFSTree(2, distances);
		assertEquals("{2=0, 5=1, 6=1}", distances.toString());

		tree8 = g.BFSTree(8, distances);
		assertEquals("{8=0}", distances.toString());

	}

	@Test
	public void testBFSDistance() {
		SortedMap<Integer, Integer> distances = new TreeMap<Integer, Integer>();

		g.BFSTree(1, distances);
		assertEquals("{1=0, 2=1, 3=1, 4=1, 5=2, 6=2, 7=2}", distances.toString());

		g.BFSTree(2, distances);
		assertEquals("{2=0, 5=1, 6=1}", distances.toString());

		g.BFSTree(8, distances);
		assertEquals("{8=0}", distances.toString());
	}


	
}