package trees.blackRedTree;

import trees.binarySearchTree.BST;

public class RedBlackTree<K extends Comparable<K>, V> extends BST<K, V>{
	
	private RBNode<K, V> nil;
	private RBNode<K,V> root;
	
	public RedBlackTree() {
		
		nil = new RBNode<K,V>(null, null, 1);
		root = nil;
		
		root.setLeft(nil);
		root.setRigth(nil);
		root.setParent(nil);
	}
	
	public <T> RBNode<K, V> search(K key){
		RBNode<K, V> current = root;
		
		while(!isNil(current)) {
			
			if(current.getKey().compareTo(key) == 0) {
				return current;
			}else if(current.getKey().compareTo( key) < 0 ) {
				current = current.getRigth();
			}else {
				current = current.getLeft();
			}
				
		}
		
		return null;
	}
	
	private boolean isNil(RBNode node){
		// return appropriate value
		return node == nil;
	}
	
	private void leftRotate(RBNode<K,V> x) {
		RBNode<K, V> y = x.getRigth();
		x.setRigth(y.getLeft());
		y.getLeft().setParent(x);
		y.setParent(x.getParent());
		
		if(x.getParent().getKey().compareTo(nil.getKey()) == 0) {
			root = y;
		}else {
			
			//Probably will fail because of the equals. If fails try with compare to
			if(x.getKey().compareTo(x.getParent().getLeft().getKey()) == 0) {
				x.getParent().setLeft(y);
			}else {
				x.getParent().setRigth(y);
			}
		}
		
		y.setLeft(x);
		x.setParent(y);
	}
	
	private void rigthRotate(RBNode x) {
		RBNode<K, V> y = x.getLeft();
		x.setLeft(y.getRigth());
		y.getRigth().setParent(x);
		y.setParent(x.getParent());
		
		if(x.getParent().getKey().compareTo(nil.getKey()) == 0) {
			root = y;
		}else {
			
			//Probably will fail because of the equals. If fails try with compare to
			if(x.getKey().compareTo(x.getParent().getRigth().getKey()) == 0) {
				x.getParent().setRigth(y);
			}else {
				x.getParent().setLeft(y);
			}
		}
		
		y.setRigth(x);
		x.setParent(y);
	}
	
	//Change to compare to if it doesn't works
	public void insertFixUp(RBNode<K, V> z) {
		
		RBNode<K, V> zGrandParent = z.getParent().getParent();
		RBNode<K, V> y = z.getRigth();
		
		if(zGrandParent != null && y!=null) {
			
			while(z.getParent().getColor() == RBNode.RED) {
				if(z.getParent().getKey().compareTo(zGrandParent.getLeft().getKey()) == 0) {
					y = zGrandParent.getRigth();
					if(y.getColor()== RBNode.RED) {
						z.getParent().setColor(RBNode.BLACK);
						y.setColor(RBNode.BLACK);
						zGrandParent.setColor(RBNode.RED);
						z = z.getParent();
					}else if(z.getKey().compareTo(z.getParent().getRigth().getKey()) == 0) {
						z = z.getParent();
						leftRotate(z);
						z.getParent().setColor(RBNode.BLACK);
						zGrandParent.setColor(RBNode.RED);
						rigthRotate(zGrandParent);
						
					}
				}else {
					y = zGrandParent.getLeft();
					if(y.getColor()== RBNode.RED) {
						z.getParent().setColor(RBNode.BLACK);
						y.setColor(RBNode.BLACK);
						zGrandParent.setColor(RBNode.RED);
						z = z.getParent();
					}else {
						
						if(z.getKey().compareTo(z.getParent().getLeft().getKey()) == 0) {
							z = z.getParent();
							rigthRotate(z);
						}	
						z.getParent().setColor(RBNode.BLACK);
						zGrandParent.setColor(RBNode.RED);
						leftRotate(zGrandParent);
						
					}
				}
			}
			
		}
		root.setColor(RBNode.BLACK);
	}
	
	public void insert(RBNode<K, V> z) {
		RBNode<K, V> y = nil;
		RBNode<K, V> x = root;
		while(x!=nil) {
			y = x;
			if(z.getKey().compareTo(x.getKey())==0) {
				x= x.getLeft();
			}else {
				x = x.getRigth();
			}
		}
		z.setParent(y);
		
		if(y.equals(nil)) {
			root = z;
		}else {
			if(z.getKey().compareTo(y.getKey()) <0) {
				y.setLeft(z);
			}else {
				y.setRigth(z);
			}
		}
		
		z.setLeft(nil);
		z.setRigth(nil);
		z.setColor(RBNode.RED);
		insertFixUp(z);
		
	}
	
	public RBNode<K,V> getRoot(){
		return root;
	}
	
	
}
