package trees.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trees.blackRedTree.RBNode;
import trees.blackRedTree.RedBlackBST;

public class RBTreeTest {
	
	RedBlackBST<Integer, String> tree;
	
	@BeforeEach
	public void stage1() {
		tree = new RedBlackBST<>();
		
	}
	
	@Test
	public void insertTest() {
		tree.put(5, "Santi");
		tree.put(10, "Nel");
		tree.put(4, "Pastel");
		
		System.out.println(tree.getRoot().getValue());
		System.out.println(tree.getRoot().getRigth().getValue());
		System.out.println(tree.getRoot().getLeft().getValue());
	}
}
