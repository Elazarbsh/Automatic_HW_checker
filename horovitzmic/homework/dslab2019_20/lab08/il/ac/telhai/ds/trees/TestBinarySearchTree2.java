package il.ac.telhai.ds.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestBinarySearchTree2 {

	private BinarySearchTree<Integer, String> searchTree;
	BinarySearchTree<Integer, String> small;
	BinarySearchTree<Integer, String> big;

	@Before
	public void setUp() throws Exception {
		small = new BinarySearchTree<Integer, String>(10, "10");
		big = new BinarySearchTree<Integer, String>(20, "20");
		searchTree = new BinarySearchTree<Integer, String>(15, "15", small, big);
	}

	@Test
	public void testCopyInConstructor() {
		small.setRight(new BinarySearchTree<Integer, String>(30, "30"));
		assertEquals(small.getRight().getValue(), "30");
		BinarySearchTree<Integer, String> left2 = searchTree.getLeft();
		assertNull(left2.getRight());

		big.setLeft(new BinarySearchTree<Integer, String>(0, "0"));
		assertEquals(big.getLeft().getValue(), "0");
		BinarySearchTree<Integer, String> right2 = searchTree.getRight();
		assertNull(right2.getLeft());
	}

	@Test
	public void testIllegalChangeThruGetterAndSetters() {
		BinarySearchTree<Integer, String> left = searchTree.getLeft();
		left.setRight(new BinarySearchTree<Integer, String>(30, "30"));
		assertEquals(left.getRight().getValue(), "30");
		BinarySearchTree<Integer, String> left2 = searchTree.getLeft();
		assertNull(left2.getRight());

		BinarySearchTree<Integer, String> right = searchTree.getRight();
		right.setLeft(new BinarySearchTree<Integer, String>(0, "0"));
		assertEquals(right.getLeft().getValue(), "0");
		BinarySearchTree<Integer, String> right2 = searchTree.getRight();
		assertNull(right2.getLeft());
	}

	@Test
	public void testIllegalSet() {
	}

	@Test
	public void testConstructor() {
		try {
			searchTree = new BinarySearchTree<Integer, String>(0, "0", small, big);
			fail("Shouldn't work");
		} catch (Exception e) {
		}
		try {
			searchTree = new BinarySearchTree<Integer, String>(30, "30", small, big);
			fail("Shouldn't work");
		} catch (Exception e) {
		}
		try {
			searchTree = new BinarySearchTree<Integer, String>(15, "15", big, small);
			fail("Shouldn't work");
		} catch (Exception e) {
		}
		searchTree = new BinarySearchTree<Integer, String>(15, "15", small, big);
	}

}
