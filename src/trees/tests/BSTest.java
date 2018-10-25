package trees.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import trees.binarySearchTree.BST;
import trees.blackRedTree.RBNode;
import trees.AVLSearchTree.Node;
import trees.binarySearchTree.*;

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
		assertTrue(tree.getSize() == 7);
		
		
		BSNode root = tree.getRoot();
	    Queue<BSNode> RBNodes= new LinkedList<>(); 
	    List<List<BSNode>> levels = traverseLevels(root);
	    
	    System.out.println("///////////////////////////////");
	    System.out.println("//////  INSERTION TEST   //////");
	    System.out.println("///////////////////////////////");
	    
	    //TESTING THE STRUCTURE OF THE TREE
	    //With the insertions that we have done before, we are supposed to have a tree like the following:
		//	         				Santiago  
		// 					Rick     			 Calamardo
		//			Morty(B)  		Bob 	 Dylan
		// Bojack
	    
	    //TREE PRINTER:
	    for (List<BSNode> level : levels) {
	        for (BSNode RBNode : level) {
	            System.out.print(RBNode.getValue() + "  ");
	        }
	        System.out.println();
	    }
	    
	    assertTrue(tree.getRoot().getValue().equals("Santiago"));
	    assertTrue(tree.getRoot().getRigth().getValue().equals("Calamardo"));
	    assertTrue(tree.getRoot().getLeft().getValue().equals("Rick"));
	    assertTrue(tree.getRoot().getRigth().getLeft().getValue().equals("Bob"));
	    assertTrue(tree.getRoot().getRigth().getRigth().getValue().equals("Dylan"));
	    assertTrue(tree.getRoot().getLeft().getLeft().getLeft().getValue().equals("Bojack"));
		
	}
	
	@Test
	public void deleteTest() {
		
		stage1();
		
		assertTrue(tree.isEmpty());
		
		tree.add(25, "Martin");
		tree.add(14, "Malin");
		tree.add(30, "Buenin");
		tree.add(50, "Armando");
		tree.add(5, "Kali");
		tree.add(1, "Lol");
		
		
		assertFalse(tree.isEmpty());
		
	    System.out.println("///////////////////////////////");
	    System.out.println("//////   DELETION TEST   //////");
	    System.out.println("///////////////////////////////");
	    
	    System.out.println("/////Tree before Inserting ////");
	    System.out.println("///////////////////////////////");
	    
	    
		BSNode root = tree.getRoot();
		List<List<BSNode>> levels = traverseLevels(root);
	    for (List<BSNode> level : levels) {
	        for (BSNode Node : level) {
	            System.out.print(Node.getValue() + "  ");
	        }
	        System.out.println();
	    }
	    
	    System.out.println();
	    System.out.println("///////////////////////////////");
	    System.out.println("Deleting Armando and Lol");
	    System.out.println();
	    
	    int tempBefore = tree.getSize(); 
	    
	    tree.remove(50);
	    tree.remove(1);
	    
	    int tempAfter = tree.getSize();
	    
	    assertTrue(tempBefore-2 == tempAfter);
	    
	    root = tree.getRoot();
	    levels = traverseLevels(root);
	    System.out.println("///////////////////////////////");
	    System.out.println("Tree after deleting");
	    System.out.println("///////////////////////////////");
	    for (List<BSNode> level : levels) {
	        for (BSNode Node : level) {
	            System.out.print(Node.getValue() + "  ");
	        }
	        System.out.println();
	    }
	}
	
	private List<List<BSNode>> traverseLevels(BSNode root) {
	    if (root == null) {
	        return Collections.emptyList();
	    }
	    List<List<BSNode>> levels = new LinkedList<>();

	    Queue<BSNode> BSNodes = new LinkedList<>();
	    BSNodes.add(root);
	    while (!BSNodes.isEmpty()) {
	        List<BSNode> level = new ArrayList<>(BSNodes.size());
	        levels.add(level);

	        for (BSNode<Integer, String> BSNode : new ArrayList<>(BSNodes)) {
	            level.add(BSNode);
	            if (BSNode.getLeft() != null) {
	                BSNodes.add(BSNode.getLeft());
	            }
	            if (BSNode.getRigth() != null) {
	                BSNodes.add(BSNode.getRigth());
	            }
	            BSNodes.poll();
	        }
	    }
	    return levels;
	}
}