package trees.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import trees.AVLSearchTree.AVLTreeST;
import trees.AVLSearchTree.Node;

public class AVLTest {
	
	AVLTreeST<Integer, String> tree;
	
	@BeforeEach
	public void stage1() {
		tree = new AVLTreeST<>();
	}
	
	@Test
	public void putTest() {
		
		stage1();
		
		assertTrue(tree.isEmpty());
		assertTrue(tree.size( ) == 0);	
		
		tree.put(5, "Santi");
		assertFalse(tree.isEmpty());
		
		tree.put(10, "Nel");
		tree.put(20, "Pastel");
		tree.put(30, "Def");
		tree.put(50, "Men");
		tree.put(1, "Un");
		tree.put(2, "Do");
		tree.put(100, "Je");
		
		assertTrue(tree.size() == 8);
		
		Node root = tree.getRoot();
	    Queue<Node> avlNodes= new LinkedList<>(); 


	    List<List<Node>> levels = traverseLevels(root);
	    
	    System.out.println("///////////////////////////////");
	    System.out.println("//////  INSERTION TEST   //////");
	    System.out.println("///////////////////////////////");
	    for (List<Node> level : levels) {
	        for (Node Node : level) {
	            System.out.print(Node.getValue() + "  ");
	        }
	        System.out.println();
	    }
	    System.out.println("///////////////////////////////");
	    System.out.println("///////////////////////////////");
	    System.out.println();
	    
	    //TESTING THE STRUCTURE OF THE TREE
	    //With the insertions that we have done before, we are supposed to have a tree like the following:
		//	         				Def(B)  
		// 					Nel(B)     			 Je(B)  
		//			Do(B)  		Pastel(B) 	Men(R) 	
		//	 Un(B)  	Santi(B) 
	    assertTrue(tree.getRoot().getValue().equals("Def"));
	    assertTrue(tree.getRoot().getRigth().getValue().equals("Je"));
	    assertTrue(tree.getRoot().getLeft().getValue().equals("Nel"));
	    assertTrue(tree.getRoot().getRigth().getLeft().getValue().equals("Men"));
	    assertTrue(tree.getRoot().getLeft().getLeft().getLeft().getValue().equals("Un"));
	    assertTrue(tree.getRoot().getLeft().getLeft().getRigth().getValue().equals("Santi"));
	    
	}
	
	private List<List<Node>> traverseLevels(Node root) {
	    if (root == null) {
	        return Collections.emptyList();
	    }
	    List<List<Node>> levels = new LinkedList<>();

	    Queue<Node> avlNodes = new LinkedList<>();
	    avlNodes.add(root);
	    while (!avlNodes.isEmpty()) {
	        List<Node> level = new ArrayList<>(avlNodes.size());
	        levels.add(level);

	        for (Node node : new ArrayList<>(avlNodes)) {
	            level.add(node);
	            if (node.getLeft() != null) {
	                avlNodes.add((Node) node.getLeft());
	            }
	            if (node.getRigth() != null) {
	                avlNodes.add((Node) node.getRigth());
	            }
	            avlNodes.poll();
	        }
	    }
	    return levels;
	}
	
	@Test
	public void deleteTest() {
		
		stage1();
		
		assertTrue(tree.isEmpty());
		
		tree.put(25, "Martin");
		tree.put(14, "Malin");
		tree.put(30, "Buenin");
		tree.put(50, "Armando");
		tree.put(5, "Kali");
		tree.put(1, "Lol");
		
		
		assertFalse(tree.isEmpty());
		
	    System.out.println("///////////////////////////////");
	    System.out.println("//////   DELETION TEST   //////");
	    System.out.println("///////////////////////////////");
	    
	    System.out.println("/////Tree before Inserting ////");
	    System.out.println("///////////////////////////////");
	    
	    
		Node root = tree.getRoot();
		List<List<Node>> levels = traverseLevels(root);
	    for (List<Node> level : levels) {
	        for (Node Node : level) {
	            System.out.print(Node.getValue() + "  ");
	        }
	        System.out.println();
	    }
	    
	    System.out.println();
	    System.out.println("///////////////////////////////");
	    System.out.println("Deleting Armando and Lol");
	    System.out.println();
	    
	    int tempBefore = tree.size(); 
	    
	    tree.delete(50);
	    tree.delete(1);
	    
	    int tempAfter = tree.size();
	    
	    assertTrue(tempBefore-2 == tempAfter);
	    
	    root = tree.getRoot();
	    levels = traverseLevels(root);
	    System.out.println("///////////////////////////////");
	    System.out.println("Tree after deleting");
	    System.out.println("///////////////////////////////");
	    for (List<Node> level : levels) {
	        for (Node Node : level) {
	            System.out.print(Node.getValue() + "  ");
	        }
	        System.out.println();
	    }
	    
	}
}