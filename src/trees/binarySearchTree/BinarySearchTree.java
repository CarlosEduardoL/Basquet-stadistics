package trees.binarySearchTree;

public interface BinarySearchTree<K extends Comparable<K>, V>  {
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void add(K key,V value);
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public V getKey(K key);
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public V remove(K key);
	
	/**
	 * check if the tree is or not empty
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * check if the value whit the key key is in the Tree </br>
	 * @param key
	 * @return
	 */
	public boolean exists(K key);
	
}