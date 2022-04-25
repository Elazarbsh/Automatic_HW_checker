

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	Graph<String> g;

	@Before
	public void setUp() throws Exception {
		g = new Graph<String>();
	}

	@Test
	public void testGraph() {
		assertEquals("", g.toString());
	}

	@Test
	public void testAddV() {
		g.addVertex("A");
		assertEquals("A", g.toString());
		g.addVertex("B");
		assertEquals("A\nB", g.toString());
	}

	@Test
	public void testRemoveNoEdges() {
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.removeVertex("C");
		assertEquals("A\nB\nD", g.toString());
	}

	@Test
	public void testPutVVE() {
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addEdge("A", "B");
		assertEquals("A:B\nB\nC\nD", g.toString());
		g.addEdge("B", "C");
		g.addEdge("B", "D");
		g.addEdge("C", "D");
		assertEquals("A:B\nB:C,D\nC:D\nD",
				g.toString());
	}

	private Graph<String> createGraph() {
		Graph<String> p4 = new Graph<String>();
		p4.addVertex("A");
		p4.addVertex("B");
		p4.addVertex("C");
		p4.addVertex("D");
		p4.addEdge("A", "B");
		p4.addEdge("B", "A");
		p4.addEdge("B", "C");
		p4.addEdge("C", "D");
		return p4;
	}


	@Test
	public void testRemoveEdge() {
		Graph<String> graph = createGraph();
		assertEquals("A:B\nB:A,C\nC:D\nD", graph.toString());
		assertFalse(graph.removeEdge("C", "B"));
		assertTrue(graph.removeEdge("B", "C"));
		assertEquals("A:B\nB:A\nC:D\nD", graph.toString());
		assertFalse(graph.removeEdge("A", "E"));
		assertFalse(graph.removeEdge("E", "A"));
		assertFalse(graph.removeEdge("B", "C"));
	}

	@Test
	public void testContains() {
		Graph<String> p = createGraph();
		assertTrue(p.containsVertex("A"));
		assertTrue(p.containsVertex("B"));
		assertTrue(p.containsVertex("C"));
		assertTrue(p.containsVertex("D"));
		assertFalse(p.containsVertex("E"));
		assertFalse(p.containsVertex("F"));
	}
	


}