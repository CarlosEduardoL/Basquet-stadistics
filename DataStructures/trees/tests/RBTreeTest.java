package trees.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trees.blackRedTree.RBNode;
import trees.blackRedTree.RedBlackTree;

public class RBTreeTest {
	
	RedBlackTree<Integer, String> tree;
	
	@BeforeEach
	public void stage1() {
		tree = new RedBlackTree<>();
		
	}
	
	@Test
	public void insertTest() {
		tree.insert(new RBNode(5, "Carlos", RBNode.RED));
		tree.insert(new RBNode(10, "Yox", RBNode.RED));
		tree.insert(new RBNode(1, "Nel", RBNode.RED));
	}
}
