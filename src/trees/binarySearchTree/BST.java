package trees.binarySearchTree;

import java.io.Serializable;
import java.util.ArrayList;

public class BST<K extends Comparable<K>, V> implements BinarySearchTree<K, V>,Serializable {

	private BSNode<K, V> root;
	private int size;


	public BST(K key, V value) {
		root = new BSNode<K,V>(key, value);
	}
	
	public BST() {
		
	}

	@Override
	public void add(K key, V value) {
		if (!isEmpty())
        {
            add(key, value, root);
        }
        else
        {
            root = new BSNode<K, V>(key, value);
            size++;
        }
	}

	private void add(K key, V value, BSNode<K, V> node) {

		if (key.compareTo(node.getKey()) > 0) {
			if (node.haveRight()) {				
				add(key, value, node.getRigth());
			}else {
				size++;
				node.setRigth(new BSNode<K, V>(key, value));
				node.getRigth().setParent(node);
			}
		}else {
			if (node.haveLeft()) {				
				add(key, value, node.getLeft());
			}else {
				size++;
				node.setLeft(new BSNode<K, V>(key, value));
				node.getLeft().setParent(node);
			}
		}
	}

	@Override
	public V getKey(K key) {
		if (root != null)
        {
            BSNode<K, V> temp = get(key,root);
            if (temp != null)
            {
                return temp.getValue();
            }
            else
            {
                return null;
            }
        }
        return null;
	}

	private BSNode<K,V> get(K key, BSNode<K, V> node) {
		if (node == null || key.equals(node.getKey()))
        {
            return node;
        }
        else if(node.getKey().compareTo(key) < 0)
        {
            return get(key, node.getRigth());
        }
        else
        {
            return get(key, node.getLeft());
        }
	}

	@Override
	public V remove(K key){
		 return remove(key, root);
	}

	private V remove(K key, BSNode<K, V> startNode) {
		BSNode<K, V> node = get(key,startNode);

        if(node != null)
        {
        	size--;
            V value = node.getValue();
            if (node.isLeaf())
            {
                value = node.getValue();
                if (node.isleftSon())
                {
                    node.getParent().setLeft(null);
                }
                else
                {
                    node.getParent().setRigth(null);
                }
                return value;
            }
            BSNode<K, V> aux = GetMOL(node);
            aux.setLeft(node.getLeft());
            aux.setRigth(node.getRigth());
            aux.setParent(node.getParent());
            if (node.isTheRoot())
            {
                root = aux;
            }
            else if (node.isRightSon())
            {
                aux.getParent().setRigth(aux);
            }
            else
            {
                aux.getParent().setLeft(aux);
            }
            return value;
        }
        else
        {
            return null;
        }
	}

	/**
	 * Return the max node on the left,, if left is null return the rigth son
	 */
	 private BSNode<K,V> GetMOL(BSNode<K, V> parent)
	{
		 if(parent.getLeft() != null)
		 {
			 BSNode<K, V> current = parent.getLeft();
			 while (current.getRigth() != null)
			 {
				 current = current.getRigth();
			 }
			 BSNode<K, V> temp = current;
			 remove(current.getKey(),current);
			 return temp;
		 }
		 else
		 {
			 return parent.getRigth();
		 }
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public boolean exists(K key)
    {
        return get(key, root) != null;
    }

    public ArrayList<V> inOrderArray()
    {
        ArrayList<V> array = new ArrayList<V>();
        return inOrderArray(array, root);
    }
    
    private ArrayList<V> inOrderArray(ArrayList<V> array, BSNode<K, V> node)
    {
        if(node == null)
        {
            return array;
        }
        else
        {
            /* first recur on left child */
            if(node.getLeft() != null)
            {
                array = inOrderArray(array, node.getLeft());
            }

            /* then print the data of node */
            array.add(node.getValue());

            /* now recur on right child */
            if (node.getRigth() != null)
            {
                array = inOrderArray(array, node.getRigth());
            }
            return array;
        }
        
    }
    
    public int getSize() {
    	return size;
    }
    
    public BSNode getRoot() {
    	return root;
    }

}
