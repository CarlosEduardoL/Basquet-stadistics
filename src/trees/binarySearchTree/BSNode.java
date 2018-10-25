package trees.binarySearchTree;

import java.io.Serializable;

public class BSNode<K extends Comparable<K>, V> implements Serializable{
	
	private BSNode<K, V> left;
	
	private BSNode<K, V> rigth;
	
	private BSNode<K, V> parent;
	
	private V value;
	
	private K key;
	
	public BSNode(K key,V value) {
		this.key = key;
		this.setValue(value);
	}

	public BSNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(BSNode<K, V> left) {
		this.left = left;
	}

	public BSNode<K, V> getRigth() {
		return rigth;
	}

	public void setRigth(BSNode<K, V> rigth) {
		this.rigth = rigth;
	}

	public V getValue() {
		return value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public BSNode<K, V> getParent() {
		return parent;
	}

	public void setParent(BSNode<K, V> patern) {
		this.parent = patern;
	}
	
	public boolean haveLeft()
    {
        return left != null;
    }

    public boolean haveRight()
    {
        return rigth != null;
    }

    public boolean isTheRoot()
    {
        return parent == null;
    }

    public boolean isRightSon()
    {
        return parent != null && parent.getRigth() != null && parent.getRigth().equals(this);
    }

    public boolean isleftSon()
    {
        return parent != null && parent.getLeft() != null && parent.getLeft().equals(this);
    }
    
    public boolean isLeaf() {
    	return !(haveLeft() || haveRight());
    }

	public void setValue(V value) {
		this.value = value;
	}

}
