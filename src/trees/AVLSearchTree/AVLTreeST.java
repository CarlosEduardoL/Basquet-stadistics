/**
 * Class based on RedBlackBST princeton university implementation
 */

package trees.AVLSearchTree;

import java.io.Serializable;
import java.util.NoSuchElementException;

import sun.misc.Queue;
import trees.binarySearchTree.BST;

public class AVLTreeST<Key extends Comparable<Key>, Value> extends BST<Key, Value> {

    /**
     * The root node.
     */
    private Node<Key,Value> root;
    
    private int size;

    /**
     * Initializes an empty symbol table.
     */
    public AVLTreeST() {
    	size = 0;
    }

    /**
     * Checks if the symbol table is empty.
     * 
     * @return {@code true} if the symbol table is empty.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the number key-value pairs in the symbol table.
     * 
     * @return the number key-value pairs in the symbol table
     */
    public int size() {
        return size;
    }


    /**
     * Returns the height of the internal AVL tree. It is assumed that the
     * height of an empty tree is -1 and the height of a tree with just one node
     * is 0.
     * 
     * @return the height of the internal AVL tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Returns the height of the subtree.
     * 
     * @param x the subtree
     * 
     * @return the height of the subtree.
     */
    private int height(Node<Key, Value> x) {
        if (x == null) return -1;
        return x.getHeight();
    }

    /**
     * Returns the value associated with the given key.
     * 
     * @param key the key
     * @return the value associated with the given key if the key is in the
     *         symbol table and {@code null} if the key is not in the
     *         symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        Node<Key, Value> x = get(root, key);
        if (x == null) return null;
        return (Value) x.getValue();
    }

    /**
     * Returns value associated with the given key in the subtree or
     * {@code null} if no such key.
     * 
     * @param x the subtree
     * @param key the key
     * @return value associated with the given key in the subtree or
     *         {@code null} if no such key
     */
    private Node<Key, Value> get(Node<Key, Value> x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo((Key) x.getKey());
        if (cmp < 0) return get((Node<Key, Value>)x.getLeft(), key);
        else if (cmp > 0) return get((Node<Key, Value>)x.getRigth(), key);
        else return x;
    }

    /**
     * Checks if the symbol table contains the given key.
     * 
     * @param key the key
     * @return {@code true} if the symbol table contains {@code key}
     *         and {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting
     * the old value with the new value if the symbol table already contains the
     * specified key. Deletes the specified key (and its associated value) from
     * this symbol table if the specified value is {@code null}.
     * 
     * @param key the key
     * @param value the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }
        root = put(root, key, value);
    }

    /**
     * Inserts the key-value pair in the subtree. It overrides the old value
     * with the new value if the symbol table already contains the specified key
     * and deletes the specified key (and its associated value) from this symbol
     * table if the specified value is {@code null}.
     * 
     * @param x the subtree
     * @param key the key
     * @param value the value
     * @return the subtree
     */
    private Node<Key, Value> put(Node<Key, Value> x, Key key, Value value) {
        if (x == null) {
        	size++;
        	return new Node<Key, Value>(key, value, 0, 1);
        } 
        int cmp = key.compareTo((Key) x.getKey());
        if (cmp < 0) {
            x.setLeft(put((Node<Key, Value>) x.getLeft(), key, value));
        }
        else if (cmp > 0) {
            x.setRigth(put((Node<Key, Value>) x.getRigth(), key, value));
        }
        else {
            x.setValue(value);
            size++;
            return x;
        }
        x.setHeight(1 + Math.max(height((Node<Key, Value>) x.getLeft()), height((Node<Key, Value>) x.getRigth())));
        return balance(x);
    }

    /**
     * Restores the AVL tree property of the subtree.
     * 
     * @param x the subtree
     * @return the subtree with restored AVL property
     */
    private Node<Key, Value> balance(Node<Key, Value> x) {
        if (balanceFactor(x) < -1) {
            if (balanceFactor((Node<Key, Value>) x.getRigth()) > 0) {
                x.setRigth(rotaterigth((Node<Key, Value>) x.getRigth()));
            }
            x = rotateLeft(x);
        }
        else if (balanceFactor(x) > 1) {
            if (balanceFactor((Node<Key, Value>) x.getLeft()) < 0) {
                x.setLeft(rotateLeft((Node<Key, Value>) x.getLeft()));
            }
            x = rotaterigth(x);
        }
        return x;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and rigth subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     * 
     * @param x the subtree
     * @return the balance factor of the subtree
     */
    private int balanceFactor(Node<Key, Value> x) {
        return height((Node<Key, Value>) x.getLeft()) - height((Node<Key, Value>) x.getRigth());
    }

    /**
     * Rotates the given subtree to the rigth.
     * 
     * @param x the subtree
     * @return the rigth rotated subtree
     */
    private Node<Key, Value> rotaterigth(Node<Key, Value> x) {
        Node<Key, Value> y = (Node<Key, Value>) x.getLeft();
        x.setLeft(y.getRigth());
        y.setRigth(x);
        x.setHeight(1 + Math.max(height((Node<Key, Value>) x.getLeft()), height((Node<Key, Value>) x.getRigth())));
        y.setHeight(1 + Math.max(height((Node<Key, Value>) y.getLeft()), height((Node<Key, Value>) y.getRigth())));
        return y;
    }

    /**
     * Rotates the given subtree to the left.
     * 
     * @param x the subtree
     * @return the left rotated subtree
     */
    private Node<Key, Value> rotateLeft(Node<Key, Value> x) {
        Node<Key, Value> y = (Node<Key, Value>) x.getRigth();
        x.setRigth(y.getLeft());
        y.setLeft(x);
        x.setHeight(1 + Math.max(height((Node<Key, Value>) x.getLeft()), height((Node<Key, Value>) x.getRigth())));
        y.setHeight(1 + Math.max(height((Node<Key, Value>) y.getLeft()), height((Node<Key, Value>) y.getRigth())));
        return y;
    }

    /**
     * Removes the specified key and its associated value from the symbol table
     * (if the key is in the symbol table).
     * 
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
        root = delete(root, key);
        size--;
    }

    /**
     * Removes the specified key and its associated value from the given
     * subtree.
     * 
     * @param x the subtree
     * @param key the key
     * @return the updated subtree
     */
    private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
        int cmp = key.compareTo((Key) x.getKey());
        if (cmp < 0) {
            x.setLeft(delete((Node<Key, Value>) x.getLeft(), key));
        }
        else if (cmp > 0) {
            x.setRigth(delete((Node<Key, Value>) x.getRigth(), key));
        }
        else {
            if (x.getLeft() == null) {
                return (Node<Key, Value>) x.getRigth();
            }
            else if (x.getRigth() == null) {
                return (Node<Key, Value>) x.getLeft();
            }
            else {
                Node<Key, Value> y = x;
                x = min((Node<Key, Value>) y.getRigth());
                x.setRigth(deleteMin((Node<Key, Value>) y.getRigth()));
                x.setLeft(y.getLeft());
            }
        }
        x.setHeight(1 + Math.max(height((Node<Key, Value>) x.getLeft()), height((Node<Key, Value>) x.getRigth())));
        return balance(x);
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     * 
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMin() with empty symbol table");
        root = deleteMin(root);
    }

    /**
     * Removes the smallest key and associated value from the given subtree.
     * 
     * @param x the subtree
     * @return the updated subtree
     */
    private Node<Key, Value> deleteMin(Node<Key, Value> x) {
        if (x.getLeft() == null) return (Node<Key, Value>) x.getRigth();
        x.setLeft(deleteMin((Node<Key, Value>) x.getLeft()));
        x.setHeight(1 + Math.max(height((Node<Key, Value>) x.getLeft()), height((Node<Key, Value>) x.getRigth())));
        return balance(x);
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     * 
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMax() with empty symbol table");
        root = deleteMax(root);
    }

    /**
     * Removes the largest key and associated value from the given subtree.
     * 
     * @param x the subtree
     * @return the updated subtree
     */
    private Node<Key, Value> deleteMax(Node<Key, Value> x) {
        if (x.getRigth() == null) return (Node<Key, Value>) x.getLeft();
        x.setRigth(deleteMax((Node<Key, Value>) x.getRigth()));
        x.setHeight(1 + Math.max(height((Node<Key, Value>) x.getLeft()), height((Node<Key, Value>) x.getRigth())));
        return balance(x);
    }

    /**
     * Returns the smallest key in the symbol table.
     * 
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return (Key) min(root).getKey();
    }

    /**
     * Returns the node with the smallest key in the subtree.
     * 
     * @param x the subtree
     * @return the node with the smallest key in the subtree
     */
    private Node<Key, Value> min(Node<Key, Value> x) {
        if (x.getLeft() == null) return x;
        return min((Node<Key, Value>) x.getLeft());
    }

    /**
     * Returns the largest key in the symbol table.
     * 
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return (Key) max(root).getKey();
    }

    /**
     * Returns the node with the largest key in the subtree.
     * 
     * @param x the subtree
     * @return the node with the largest key in the subtree
     */
    private Node<Key, Value> max(Node<Key, Value> x) {
        if (x.getRigth() == null) return x;
        return max((Node<Key, Value>) x.getRigth());
    }

    /**
     * Returns the largest key in the symbol table less than or equal to
     * {@code key}.
     * 
     * @param key the key
     * @return the largest key in the symbol table less than or equal to
     *         {@code key}
     * @throws NoSuchElementException if the symbol table is empty
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node<Key, Value> x = floor(root, key);
        if (x == null) return null;
        else return (Key) x.getKey();
    }

    /**
     * Returns the node in the subtree with the largest key less than or equal
     * to the given key.
     * 
     * @param x the subtree
     * @param key the key
     * @return the node in the subtree with the largest key less than or equal
     *         to the given key
     */
    private Node<Key, Value> floor(Node<Key, Value> x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo((Key) x.getKey());
        if (cmp == 0) return x;
        if (cmp < 0) return floor((Node<Key, Value>) x.getLeft(), key);
        Node<Key, Value> y = floor((Node<Key, Value>) x.getRigth(), key);
        if (y != null) return y;
        else return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to
     * {@code key}.
     * 
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to
     *         {@code key}
     * @throws NoSuchElementException if the symbol table is empty
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node<Key, Value> x = ceiling(root, key);
        if (x == null) return null;
        else return (Key) x.getKey();
    }

    /**
     * Returns the node in the subtree with the smallest key greater than or
     * equal to the given key.
     * 
     * @param x the subtree
     * @param key the key
     * @return the node in the subtree with the smallest key greater than or
     *         equal to the given key
     */
    private Node<Key, Value> ceiling(Node<Key, Value> x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo((Key) x.getKey());
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling((Node<Key, Value>) x.getRigth(), key);
        Node<Key, Value> y = ceiling((Node<Key, Value>) x.getLeft(), key);
        if (y != null) return y;
        else return x;
    }

    /**
     * Returns the kth smallest key in the symbol table.
     * 
     * @param k the order statistic
     * @return the kth smallest key in the symbol table
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *             {@code size() -1 }
     */




    /**
     * Returns all keys in the symbol table.
     * 
     * @return all keys in the symbol table
     */
    public Iterable<Key> keys() {
        return keysInOrder();
    }

    /**
     * Returns all keys in the symbol table following an in-order traversal.
     * 
     * @return all keys in the symbol table following an in-order traversal
     */
    public Iterable<Key> keysInOrder() {
        Queue<Key> queue = new Queue<Key>();
        keysInOrder(root, queue);
        return (Iterable<Key>) queue;
    }

    /**
     * Adds the keys in the subtree to queue following an in-order traversal.
     * 
     * @param x the subtree
     * @param queue the queue
     */
    private void keysInOrder(Node<Key, Value> x, Queue<Key> queue) {
        if (x == null) return;
        keysInOrder((Node<Key, Value>) x.getLeft(), queue);
        queue.enqueue((Key) x.getKey());
        keysInOrder((Node<Key, Value>) x.getRigth(), queue);
    }

    /**
     * Returns all keys in the symbol table following a level-order traversal.
     * 
     * @return all keys in the symbol table following a level-order traversal.
     * @throws InterruptedException 
     */
    public Iterable<Key> keysLevelOrder() throws InterruptedException {
        Queue<Key> queue = new Queue<Key>();
        if (!isEmpty()) {
            Queue<Node<Key,Value>> queue2 = new Queue<Node<Key,Value>>();
            queue2.enqueue(root);
            while (!queue2.isEmpty()) {
                Node<Key, Value> x = queue2.dequeue();
                queue.enqueue((Key) x.getKey());
                if (x.getLeft() != null) {
                    queue2.enqueue((Node<Key, Value>) x.getLeft());
                }
                if (x.getRigth() != null) {
                    queue2.enqueue((Node<Key, Value>) x.getRigth());
                }
            }
        }
        return (Iterable<Key>) queue;
    }

    /**
     * Returns all keys in the symbol table in the given range.
     * 
     * @param lo the lowest key
     * @param hi the highest key
     * @return all keys in the symbol table between {@code lo} (inclusive)
     *         and {@code hi} (exclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *             is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return (Iterable<Key>) queue;
    }

    /**
     * Adds the keys between {@code lo} and {@code hi} in the subtree
     * to the {@code queue}.
     * 
     * @param x the subtree
     * @param queue the queue
     * @param lo the lowest key
     * @param hi the highest key
     */
    private void keys(Node<Key, Value> x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo((Key) x.getKey());
        int cmphi = hi.compareTo((Key) x.getKey());
        if (cmplo < 0) keys((Node<Key, Value>) x.getLeft(), queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue((Key) x.getKey());
        if (cmphi > 0) keys((Node<Key, Value>) x.getRigth(), queue, lo, hi);
    }



    /**
     * Checks if AVL property is consistent.
     * 
     * @return {@code true} if AVL property is consistent.
     */
    private boolean isAVL() {
        return isAVL(root);
    }

    /**
     * Checks if AVL property is consistent in the subtree.
     * 
     * @param x the subtree
     * @return {@code true} if AVL property is consistent in the subtree
     */
    private boolean isAVL(Node<Key, Value> x) {
        if (x == null) return true;
        int bf = balanceFactor(x);
        if (bf > 1 || bf < -1) return false;
        return isAVL((Node<Key, Value>) x.getLeft()) && isAVL((Node<Key, Value>) x.getRigth());
    }

    /**
     * Checks if the symmetric order is consistent.
     * 
     * @return {@code true} if the symmetric order is consistent
     */
    private boolean isBST() {
        return isBST(root, null, null);
    }

    /**
     * Checks if the tree rooted at x is a BST with all keys strictly between
     * min and max (if min or max is null, treat as empty constraint) Credit:
     * Bob Dondero's elegant solution
     * 
     * @param x the subtree
     * @param min the minimum key in subtree
     * @param max the maximum key in subtree
     * @return {@code true} if if the symmetric order is consistent
     */
    private boolean isBST(Node<Key, Value> x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.getKey().compareTo(min) <= 0) return false;
        if (max != null && x.getKey().compareTo(max) >= 0) return false;
        return isBST((Node<Key, Value>)x.getLeft(), min,(Key) x.getKey()) && isBST((Node<Key, Value>)x.getRigth(), (Key) x.getKey(), max);
    }

    
    public Node getRoot() {
    	return this.root;
    }

}
