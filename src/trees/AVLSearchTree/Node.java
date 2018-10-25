package trees.AVLSearchTree;

import trees.binarySearchTree.BSNode;

public class Node<Key extends Comparable<Key>, Value> extends BSNode<Key, Value>{

    private final Key key;   // the key
    private Value val;       // the associated value
    private int height;      // height of the subtree
    private int size;        // number of nodes in subtree
    private Node<Key, Value> left;       // left subtree
    private Node<Key, Value> right;      // right subtree

	public Node(Key key, Value value, int nSize, int height) {
		super(key,value);
        this.key = key;
        this.val = value;
        this.size = nSize;
        this.height = height;
		// TODO Auto-generated constructor stub
	}

	public int getSize() {
		return size;
	}

	public void setSize(int newSize) {
		this.size = newSize;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Value getValue() {
		// TODO Auto-generated method stub
		return val;
	}

	public Node<Key, Value> getRigth() {
		// TODO Auto-generated method stub
		return this.right;
	}

	public Node<Key, Value> getLeft() {
		// TODO Auto-generated method stub
		return this.left;
	}

	public Key getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	public void setLeft(Node<Key, Value> newLeft) {
		// TODO Auto-generated method stub
		this.left = newLeft;
	}

	public void setRigth(Node<Key, Value> newRigth) {
		// TODO Auto-generated method stub
		this.right = newRigth;
	}

	public void setValue(Value value) {
		// TODO Auto-generated method stub
		this.val = value;
	}

}
