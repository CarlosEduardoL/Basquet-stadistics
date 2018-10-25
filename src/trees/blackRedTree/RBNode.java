package trees.blackRedTree;

import trees.binarySearchTree.BSNode;
import trees.binarySearchTree.*;
public class RBNode<Key extends Comparable<Key>, Value> extends BSNode<Key, Value>{
	
    public static final boolean RED   = true;
    public static final boolean BLACK = false;
    
    private Key key;           // key
    private Value val;         // associated data
    private RBNode left, right;  // links to left and right subtrees
    private boolean color;     // color of parent link
    private int size;          // subtree count

    public RBNode(Key key, Value val, boolean color, int size) {
    	super(key,val);
    	this.key = key;
    	this.val = val;
        this.color = color;
        this.size = size;
    }
    
    public Value getValue() {
    	return this.val;
    }
    
    public Key getKey() {
    	return this.key;
    }
    
    public RBNode<Key, Value> getRigth() {
    	return this.right;
    }
    
    public RBNode<Key, Value> getLeft() {
    	return this.left;
    }
    
    public boolean isRed() {
    	return color == RED;
    }
    
    public String getColorString() {
    	if(color == RED) return "R";
    	return "B";
    }
    
    public boolean getColor() {
    	return color;
    }
    
    public void setColor(boolean nColor) {
    	color = nColor;
    }
    
    public int getSize() {
    	return size;
    }

	public void setLeft(RBNode<Key, Value> newLeft) {
		// TODO Auto-generated method stub
		left = newLeft;
	}

	public void setRigth(RBNode<Key, Value> nRigth) {
		// TODO Auto-generated method stub
		right = nRigth;
		
	}

	public void setValue(Value val2) {
		// TODO Auto-generated method stub
		val = val2;
	}

	public void setSize(int i) {
		// TODO Auto-generated method stub
		size = i;
	}

	public void setKey(Comparable key2) {
		// TODO Auto-generated method stub
		key = (Key) key2;
	}
}
