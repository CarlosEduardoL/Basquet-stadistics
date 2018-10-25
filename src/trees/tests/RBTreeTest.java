package trees.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	public void insertTest() {
		
		assertTrue(tree.isEmpty());
		
		tree.put(5, "Santi");
		assertFalse(tree.isEmpty());
		
		System.out.println(tree.getRoot().getValue());
		tree.put(10, "Nel");
		tree.put(20, "Pastel");
		tree.put(30, "Def");
		tree.put(50, "Men");
		tree.put(1, "Un");
		tree.put(2, "Do");
		tree.put(100, "Je");
		tree.put(200, "Ya we");
		
		assertTrue(tree.size() == 9);
		
		RBNode root = tree.getRoot();
	    Queue<RBNode> RBNodes= new LinkedList<>(); 


	    List<List<RBNode>> levels = traverseLevels(root);

	    for (List<RBNode> level : levels) {
	        for (RBNode RBNode : level) {
	            System.out.print(RBNode.getValue()+"("+RBNode.getColorString() + ")" + "  ");
	            assertTrue(tree.getRoot().getColor() == RBNode.BLACK);
	        }
	        System.out.println();
	    }
	    
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

	        for (RBNode RBNode : new ArrayList<>(RBNodes)) {
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
}
