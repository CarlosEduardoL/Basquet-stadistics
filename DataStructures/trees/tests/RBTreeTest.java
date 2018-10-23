package trees.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trees.blackRedTree.RBNode;
import trees.blackRedTree.RedBlackBST;
import trees.blackRedTree.RedBlackBST.Node;

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
		tree.put(20, "Pastel");
		tree.put(30, "Def");
		tree.put(50, "Men");
		tree.put(1, "Un");
		tree.put(2, "Do");
		tree.put(100, "Je");
		tree.put(200, "Ya we");
		Node root = tree.getRoot();
	    Queue<Node> nodes= new LinkedList<>(); 


	    List<List<Node>> levels = traverseLevels(root);

	    for (List<Node> level : levels) {
	        for (Node node : level) {
	            System.out.print(node.getValue()+"("+node.getColor() + ")" + "  ");
//	            if(node.getValue().equals("Def")) {
//	            	System.out.print(node.getRigth().getValue());
//	            }
	        }
	        System.out.println();
	    }
//		tree.put(15, "Leh");
//		tree.put(14, "Sí");
		
	}
	
	private List<List<Node>> traverseLevels(Node root) {
	    if (root == null) {
	        return Collections.emptyList();
	    }
	    List<List<Node>> levels = new LinkedList<>();

	    Queue<Node> nodes = new LinkedList<>();
	    nodes.add(root);

	    while (!nodes.isEmpty()) {
	        List<Node> level = new ArrayList<>(nodes.size());
	        levels.add(level);

	        for (Node node : new ArrayList<>(nodes)) {
	            level.add(node);
	            if (node.getLeft() != null) {
	                nodes.add(node.getLeft());
	            }
	            if (node.getRigth() != null) {
	                nodes.add(node.getRigth());
	            }
	            nodes.poll();
	        }
	    }
	    return levels;
	}
}
