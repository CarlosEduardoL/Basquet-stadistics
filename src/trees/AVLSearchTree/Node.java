package trees.AVLSearchTree;

import trees.binarySearchTree.BSNode;

public class Node<Key extends Comparable<Key>, Value> extends BSNode<Key,Value>{

	private int size;
	private int height;

	public Node(Key key, Value value, int size, int height) {
		super(key, value);
		this.setSize(size);
		this.setHeight(height);
		// TODO Auto-generated constructor stub
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
