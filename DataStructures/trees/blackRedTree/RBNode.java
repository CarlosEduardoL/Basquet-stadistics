package trees.blackRedTree;

import trees.binarySearchTree.BSNode;

public class RBNode<K extends Comparable<K>,V> extends BSNode<K, V>{
	
	public static final int RED = 0;
	public static final int BLACK = 1;
	
	private int color;

	public RBNode(K key, V value, int color) {
		super(key, value);
		this.color = color;
	}
	
	public boolean isRed() {
		return color == RED;
	}
	
	public boolean isBlack() {
		return color == BLACK;
	}
	
	@Override
	public RBNode<K, V> getParent() {
		return (RBNode<K, V>)super.getParent();
	}
	
	@Override
	public RBNode<K, V> getRigth() {
		return (RBNode<K, V>)super.getRigth();
	}

	@Override
	public RBNode<K, V> getLeft() {
		return (RBNode<K, V>) super.getLeft();
	}
}
