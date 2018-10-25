package trees.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import trees.binarySearchTree.BST;

public class BSTest {
	
	BST<Integer, String> tree;
	
	@BeforeEach
	private void stage1() {
		tree = new BST<Integer, String>();
	}
	
	@Test
	public void putTest() {
		
		stage1();
		
		assertTrue(tree.isEmpty());
		
		//Inserting 
		tree.add(10, "Santiago");
		tree.add(5, "Rick");
		tree.add(2, "Morty");
		tree.add(0, "Bojack");
		tree.add(20, "Calamardo");
		tree.add(11, "Bob");
		tree.add(50, "Dylan");
		assertFalse(tree.isEmpty());
		
		System.out.println(tree.getSize());
		
		
	}
	
	@Test
	public void deleteTest() {
		
	}
}