import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class DFS_Test {

	Graph<Integer> g;
	Set<List<Integer>> edges;

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

		edges = new HashSet<List<Integer>>();

		edges.add(Arrays.asList(1, 2));
		edges.add(Arrays.asList(1, 3));
		edges.add(Arrays.asList(1, 4));
		edges.add(Arrays.asList(2, 5));
		edges.add(Arrays.asList(2, 6));
		edges.add(Arrays.asList(3, 4));
		edges.add(Arrays.asList(3, 5));
		edges.add(Arrays.asList(4, 7));

		for (List<Integer> edge : edges) {
			g.addEdge(edge.get(0), edge.get(1));
		}
	}

	@Test
	public void testDFSTree() {
		Graph<Integer> tree1 = g.DFSTree(1);
		assertEquals("1:2,3\n" + "2:5,6\n" + "3:4\n" + "4:7\n" + "5\n" + "6\n" + "7", tree1.toString());

		Graph<Integer> tree2 = g.DFSTree(2);
		assertEquals("2:5,6\n" + "5\n" + "6", tree2.toString());

		Graph<Integer> tree8 = g.DFSTree(8);
		assertEquals("8", tree8.toString());
	}

	@Test
	public void testDFSTimes() {
		Graph<Integer>.DFS_Data DFS_data = g.new DFS_Data();
		g.DFSTree(1, DFS_data);

		assertEquals("1: 1,14\n" + "2: 2,7\n" + "3: 8,13\n" + "4: 9,12\n" + "5: 3,4\n" + "6: 5,6\n" + "7: 10,11\n",
				DFS_data.getVertexToVisitingTimeString());

	}

	@Test
	public void testIsAcyclic() {

		Graph<Integer>.DFS_Data DFS_data = g.new DFS_Data();
		g.DFSTree(1, DFS_data);
		assertTrue(DFS_data.isAcyclicGraph());

		g.addEdge(2, 1);
		g.DFSTree(1, DFS_data);
		assertFalse(DFS_data.isAcyclicGraph());

	}

	@Test
	public void testTopologicalOrder() {
		List<Integer> topoList = g.topologicalOrder();

		boolean res = "[8, 1, 3, 4, 7, 2, 6, 5]".equals(topoList.toString());
		if (res == false) {
			res = true;
		}

		for (List<Integer> edge : edges) {
			int ind1 = topoList.indexOf(edge.get(0));
			int ind2 = topoList.indexOf(edge.get(1));
			res = res && (ind1 < ind2);
			if (res == false)
				break;
		}
		assertTrue(res);

		g.addEdge(2, 1);
		topoList = g.topologicalOrder();
		assertNull(topoList);
	}
}