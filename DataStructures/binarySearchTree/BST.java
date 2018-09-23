package binarySearchTree;

public class BST<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {

	private BSNode<K, V> root;

	private int size;

	public BST(K key, V value) {
		root = new BSNode<K,V>(key, value);
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
		if (root != null) {
			if (root.getKey().equals(key)) {
				return root.getValue();
			}else if(root.getKey().compareTo(key)>0) {
				return get(key, root.getRigth());
			}else {
				return get(key, root.getLeft());
			}
		}
		return null;
	}

	private V get(K key, BSNode<K, V> node) {
		if (node != null) {
			if (node.getKey().equals(key)) {
				return node.getValue();
			}else if(node.getKey().compareTo(key)>0) {
				return get(key, node.getRigth());
			}else {
				return get(key, node.getLeft());
			}
		}
		return null;
	}

	@Override
	public V remove(K key){
		BSNode<K, V> node = root;
		while(node != null && !node.getKey().equals(key))
		{
			if (node.getKey().compareTo(key) < 0)
			{
				node = node.getRigth();
			}
			else
			{
				node = node.getLeft();
			}
		}
		
		if (node.isLeaf()) {
			if (node.isRightSon()) {
				node.getParent().setRigth(null);
			}else {
				node.getParent().setLeft(null);
			}
		}

		if(node != null)
		{
			V value = node.getValue();
			BSNode<K, V> aux = GetMOL(node);
			aux.setLeft(node.getLeft());
			aux.setRigth(node.getRigth());
			aux.setParent(node.getParent());
			if (aux.isRightSon())
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
			 current.getParent().setRigth(null);
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

}
