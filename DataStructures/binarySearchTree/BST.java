package binarySearchTree;

import cUtil.CList;

public class BST<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {

	private BSNode<K, V> root;

	private int size;

	public BST(K key, V value) {
		root = new BSNode<K,V>(key, value);
		size = 1;
	}
	
	public BST() {
		
	}

	@Override
	public void add(K key, V value) {
		if (!isEmpty()){
			if (key.compareTo(root.getKey()) > 0){
				if (root.haveRight()){
					add(key, value, root.getRigth());
				}
				else{
					root.setRigth(new BSNode<K, V>(key, value));
					root.getRigth().setParent(root);
				}
			}
			else{
				if(root.haveLeft())
				{
					add(key, value, root.getLeft());
				}
				else{
					root.setLeft(new BSNode<K, V>(key, value));
					root.getLeft().setParent(root);
				}
			}
		}
		else
		{
			root = new BSNode<K, V>(key, value);
		}
		size++;
	}

	private void add(K key, V value, BSNode<K, V> node) {

		if (key.compareTo(node.getKey()) > 0) {
			if (node.haveRight()) {				
				add(key, value, node.getRigth());
			}else {
				node.setRigth(new BSNode<K, V>(key, value));
				node.getRigth().setParent(node);
			}
		}else {
			if (node.haveLeft()) {				
				add(key, value, node.getLeft());
			}else {
				node.setLeft(new BSNode<K, V>(key, value));
				node.getLeft().setParent(node);
			}
		}
	}

	@Override
	public V get(K key) {
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
		if (node == null)
        {
            return null;
        }else if (key.equals(node.getKey()))
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
		 BSNode<K, V> node = get(key,root);

         if(node != null)
         {

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
			 remove(current.getKey());
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
	public boolean exist(K key)
    {
        if (isEmpty())
        {
            return false;
        }
        else
        {
            if (root.getKey().equals(key))
            {
                return true;
            }else if (root.getKey().compareTo(key) > 0)
            {
                return exist(key, root.getRigth());
            }
            else
            {
                return exist(key, root.getLeft());
            }
        }
    }

    private boolean exist(K key, BSNode<K, V> node)
    {
        if(node == null)
        {
            return false;
        }
        else
        {
            if (node.getKey().equals(key))
            {
                return true;
            }
            else if (node.getKey().compareTo(key) > 0)
            {
                return exist(key, node.getRigth());
            }
            else
            {
                return exist(key, node.getLeft());
            }
        }
    }
    
    public CList<V> inOrderArray()
    {
        CList<V> array = new CList<V>();
        return inOrderArray(array, root);
    }
    
    private CList<V> inOrderArray(CList<V> array, BSNode<K, V> node)
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

}
