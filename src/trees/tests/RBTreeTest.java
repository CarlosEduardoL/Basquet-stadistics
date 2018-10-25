package trees.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	public void putTest() {
		
		stage1();
		
		//Checking if the tree is empty before an addition
		assertTrue(tree.isEmpty());
		
		//Adding elements to the tree
		tree.put(5, "Santi");
		//Checking if the tree is empty after an addition
		assertFalse(tree.isEmpty());
		tree.put(10, "Nel");
		tree.put(20, "Pastel");
		tree.put(30, "Def");
		tree.put(50, "Men");
		tree.put(1, "Un");
		tree.put(2, "Do");
		tree.put(100, "Je");
		tree.put(200, "Ya we");
		
		//Testing wether the size is correct after adding 9 elements
		assertTrue(tree.size() == 9);
		
		//Temporal Variables for printing the tree
		RBNode root = tree.getRoot();
	    Queue<RBNode> RBNodes= new LinkedList<>(); 
	    List<List<RBNode>> levels = traverseLevels(root);
	    
	    System.out.println("///////////////////////////////");
	    System.out.println("//////  INSERTION TEST   //////");
	    System.out.println("///////////////////////////////");
	    
	    //TREE PRINTER:
	    for (List<RBNode> level : levels) {
	        for (RBNode RBNode : level) {
	            System.out.print(RBNode.getValue()+"("+RBNode.getColorString() + ")" + "  ");
	            assertTrue(tree.getRoot().getColor() == RBNode.BLACK);
	        }
	        System.out.println();
	    }
	    
	    System.out.println("///////////////////////////////");
	    System.out.println("///////////////////////////////");
	    
	    //TESTING THE STRUCTURE OF THE TREE
	    //With the insertions that we have done before, we are supposed to have a tree like the following:
		//	         				Nel(B)  
		// 					Do(B)     			 Je(B)  
		//			Un(B)  		Santi(B) 	Def(R) 	Ya we(B)  
		// Pastel(B)  Men(B) 
	    assertTrue(tree.getRoot().getValue().equals("Nel"));
	    assertTrue(tree.getRoot().getRigth().getValue().equals("Je"));
	    assertTrue(tree.getRoot().getLeft().getValue().equals("Do"));
	    assertTrue(tree.getRoot().getRigth().getRigth().getValue().equals("Ya we"));
	    assertTrue(tree.getRoot().getRigth().getLeft().getValue().equals("Def"));
	    assertTrue(tree.getRoot().getLeft().getLeft().getValue().equals("Un"));
	    assertTrue(tree.getRoot().getLeft().getRigth().getValue().equals("Santi"));
	    
	}
	
	//AUXILIAR METHOD WICH HELPS TO PRINT ALL THE ELEMENTS OF A BST
	private List<List<RBNode>> traverseLevels(RBNode root) {
	    if (root == null) {
	        return Collections.emptyList();
	    }
	    List<List<RBNode>> levels = new LinkedList<>();

	    Queue<RBNode> RBNodes = new LinkedList<>();
	    RBNodes.add(root);
	    while (!RBNodes.isEmpty()) {
	        List<RBNode> level = new ArrayList<>(RBNodes.size());
	        levels.add(level);

	        for (RBNode<Integer, String> RBNode : new ArrayList<>(RBNodes)) {
	            level.add(RBNode);
	            if (RBNode.getLeft() != null) {
	                RBNodes.add(RBNode.getLeft());
	            }
	            if (RBNode.getRigth() != null) {
	                RBNodes.add(RBNode.getRigth());
	            }
	            RBNodes.poll();
	        }
	    }
	    return levels;
	}
	
	@Test
	public void deleteTest() {
		stage1();
		
		//Testing if it's empty after adding a element
		assertTrue(tree.isEmpty());
		
		//ADDING ELEMENTS TO THE TREE
		tree.put(5, "Santi");
		//Testing it's empty after adding a element
		assertFalse(tree.isEmpty());
		tree.put(10, "Nel");
		tree.put(20, "Pastel");
		tree.put(30, "Def");
		tree.put(50, "Men");
		tree.put(1, "Un");
		tree.put(2, "Do");
		tree.put(100, "Je");
		tree.put(200, "Ya we");
		
		//Checking wether the size is correct
		
		//Temporal variables to help printing the tree
		RBNode root = tree.getRoot();
	    Queue<RBNode> RBNodes= new LinkedList<>(); 
	    List<List<RBNode>> levels = traverseLevels(root);
	    
	    //~~~~~~~~~TREE PRINTER~~~~~~~~~~~~//
	    System.out.println("///////////////////////////////");
	    System.out.println("//////   DELETION TEST   //////");
	    System.out.println("///////////////////////////////");
	    System.out.println("//////   Tree before deletions   //////");
	    System.out.println("//////  //////////////////////  //////");
	    for (List<RBNode> level : levels) {
	        for (RBNode RBNode : level) {
	            System.out.print(RBNode.getValue()+"("+RBNode.getColorString() + ")" + "  ");
	            assertTrue(tree.getRoot().getColor() == RBNode.BLACK);
	        }
	        System.out.println();
	    }
	    
	    System.out.println("///////////////////////////////");
	    System.out.println("///////////////////////////////");
	    
	    //Temporal variable to compare the number of elements before and after a delete
	    int tempBefore = tree.size();
	    
	    //Deleting Men, Un, Do, Ya we
	    tree.delete(50);
	    tree.delete(1);
	    tree.delete(2);
	    tree.delete(200);
	    assertTrue(tree.getRoot().getColor() == RBNode.BLACK);
	    
	    int tempAfter = tree.size();
	    //Testing the number of elements to be rigth after deleting 4 nodes
	    assertTrue(tempBefore-tempAfter == 4);
	    
	    //TESTING THE STRUCTURE OF THE TREE
	    //With the insertions that we have done before, we are supposed to have a tree like the following:
		//	         				Nel(B)  
		// 					Do(B)     			 Je(B)  
		//			Un(B)  		Santi(B) 	Def(R) 	Ya we(B)  
		// Pastel(B)  Men(B) 
	    
	    
	    System.out.println("//////   Tree after deletions   //////");
	    System.out.println("//////  //////////////////////  //////");
	    root = tree.getRoot();
	    levels = traverseLevels(root);
	    
	    
	    for (List<RBNode> level : levels) {
	        for (RBNode RBNode : level) {
	            System.out.print(RBNode.getValue()+"("+RBNode.getColorString() + ")" + "  ");
	        }
	        System.out.println();
	    }
	    
	    assertNull(tree.get(50));
	    assertNull(tree.get(50));
	    assertNull(tree.get(50));
	    assertNull(tree.get(50));
	    
	}
	
	
}
