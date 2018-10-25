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
		
		tree.put(5, "Santi");
		assertFalse(tree.isEmpty());
		
		tree.put(10, "Nel");
		tree.put(20, "Pastel");
		tree.put(30, "Def");
		tree.put(50, "Men");
		tree.put(1, "Un");
		tree.put(2, "Do");
		tree.put(100, "Je");
		
		System.out.println(tree.size());
		Node root = tree.getRoot();
	    Queue<Node> avlNodes= new LinkedList<>(); 


	    List<List<Node>> levels = traverseLevels(root);

	    for (List<Node> level : levels) {
	        for (Node Node : level) {
	            System.out.print(Node.getValue() + "  ");
	        }
	        System.out.println();
	    }
	    
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
}
