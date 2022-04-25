import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GraphTest2 {

	Graph<String> g;

	@Test
	public void testAddV() {
		g = new Graph<String>();
		g.addVertex("A");
		assertEquals("A", g.toString());
		g.addVertex("B");
		assertEquals("A\nB", g.toString());
		g.addEdge("A", "B");
		assertEquals("A:B\nB", g.toString());
		g.addVertex("A");
		assertEquals("A:B\nB", g.toString());
	}

	@Test
	public void testRemoveV() {
		g = createGraph();
		assertEquals("A:B\nB:A,C\nC:D\nD", g.toString());
		g.removeVertex("A");
		assertEquals("B:C\nC:D\nD", g.toString());
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

}