/**
 * Class based on RedBlackBST princeton university implementation
 */

package trees.blackRedTree;

import java.util.NoSuchElementException;
import trees.binarySearchTree.*;
import sun.misc.Queue;

public class RedBlackBST<Key extends Comparable<Key>, Value> extends BST{


    private RBNode<Key, Value> root;     // root of the BST

    // BST helper RBNode data type


    /**
     * Initializes an empty symbol table.
     */
    public RedBlackBST() {
    }

   /***************************************************************************
    *  RBNode helper methods.
    ***************************************************************************/
    // is RBNode x red; false if x is null ?
    private boolean isRed(RBNode<Key, Value> x) {
        if (x == null) return false;
        return x.getColor() == RBNode.RED;
    }

    // number of RBNode in subtree rooted at x; 0 if x is null
    private int size(RBNode<Key, Value> x) {
        if (x == null) return 0;
        return x.getSize();
    } 


    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

   /**
     * Is this symbol table empty?
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }


   /***************************************************************************
    *  Standard BST search.
    ***************************************************************************/
    
    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associaew RBRBNode(5, "Carlos",ted with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(RBNode<Key, Value> x, Key key) {
        while (x != null) {
            int cmp = key.compareTo((Key) x.getKey());
            if      (cmp < 0) x = x.getLeft();
            else if (cmp > 0) x = x.getRigth();
            else              return (Value) x.getValue();
        }
        return null;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *     {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

   /***************************************************************************
    *  Red-black tree insertion.
    ***************************************************************************/

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.setColor(RBNode.BLACK);
        // assert check();
    }

    // insert the key-value pair in the subtree rooted at h
    private RBNode<Key, Value> put(RBNode<Key, Value> h, Key key, Value val) { 
        if (h == null) return new RBNode<Key, Value>(key, val, RBNode.RED, 1);
        
        int cmp = key.compareTo((Key) h.getKey());
        if      (cmp < 0) h.setLeft( put(h.getLeft(),  key, val)); 
        else if (cmp > 0) h.setRigth(put(h.getRigth(), key, val)); 
        else              h.setValue(val);

        // fix-up any right-leaning links
        if (isRed(h.getRigth()) && !isRed(h.getLeft()))      h = rotateLeft(h);
        if (isRed(h.getLeft())  &&  isRed(h.getLeft().getLeft())) h = rotateRight(h);
        if (isRed(h.getLeft())  &&  isRed(h.getRigth()))     flipColors(h);
        h.setSize(size(h.getLeft()) + size(h.getRigth()) + 1);

        return h;
    }

   /***************************************************************************
    *  Red-black tree deletion.
    ***************************************************************************/

    /**
     * Removes the smallest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.getLeft()) && !isRed(root.getRigth()))
            root.setColor(RBNode.RED);

        root = deleteMin(root);
        if (!isEmpty()) root.setColor(RBNode.BLACK);
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private RBNode<Key, Value> deleteMin(RBNode<Key, Value> h) { 
        if (h.getLeft() == null)
            return null;

        if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
            h = moveRedLeft(h);

        h.setLeft(deleteMin(h.getLeft()));
        return balance(h);
    }


    /**
     * Removes the largest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.getLeft()) && !isRed(root.getRigth()))
            root.setColor(RBNode.RED);

        root = deleteMax(root);
        if (!isEmpty()) root.setColor(RBNode.BLACK);
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private RBNode<Key, Value> deleteMax(RBNode<Key, Value> h) { 
        if (isRed(h.getLeft()))
            h = rotateRight(h);

        if (h.getRigth() == null)
            return null;

        if (!isRed(h.getRigth()) && !isRed(h.getRigth().getLeft()))
            h = moveRedRight(h);

        h.setRigth(deleteMax(h.getRigth()));

        return balance(h);
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) { 
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.getLeft()) && !isRed(root.getRigth()))
            root.setColor(RBNode.RED);

        root = delete(root, key);
        if (!isEmpty()) root.setColor(RBNode.BLACK);
        // assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private RBNode<Key, Value> delete(RBNode<Key, Value> h, Key key) { 
        // assert get(h, key) != null;

        if (key.compareTo((Key) h.getKey()) < 0)  {
            if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
                h = moveRedLeft(h);
            h.setLeft(delete(h.getLeft(), key)) ;
        }
        else {
            if (isRed(h.getLeft()))
                h = rotateRight(h);
            if (key.compareTo((Key) h.getKey()) == 0 && (h.getRigth() == null))
                return null;
            if (!isRed(h.getRigth()) && !isRed(h.getRigth().getLeft()))
                h = moveRedRight(h);
            if (key.compareTo((Key) h.getKey()) == 0) {
                RBNode<Key, Value> x = min(h.getRigth());
                h.setKey(x.getKey());
                h.setValue(x.getValue());
                // h.val = get(h.getRigth(), min(h.getRigth()).key);
                // h.getKey() = min(h.getRigth()).key;
                h.setRigth(deleteMin(h.getRigth()));
            }
            else h.setRigth(delete(h.getRigth(), key));
        }
        return balance(h);
    }

   /***************************************************************************
    *  Red-black tree helper functions.
    ***************************************************************************/

    // make a left-leaning link lean to the right
    private RBNode<Key, Value> rotateRight(RBNode<Key, Value> h) {
        // assert (h != null) && isRed(h.getLeft());
        RBNode x = h.getLeft();
        h.setLeft(x.getRigth());
        x.setRigth(h) ;
        x.setColor(x.getRigth().getColor()) ;
        x.getRigth().setColor(RBNode.RED);
        x.setSize(h.getSize()); 
        h.setSize(size(h.getLeft()) + size(h.getRigth()) + 1);
        return x;
    }

    // make a right-leaning link lean to the left
    private RBNode<Key, Value> rotateLeft(RBNode<Key, Value> h) {
        // assert (h != null) && isRed(h.getRigth());
        RBNode x = h.getRigth();
        h.setRigth(x.getLeft());
        x.setLeft(h);
        x.setColor(x.getLeft().getColor());
        x.getLeft().setColor(RBNode.RED) ;
        x.setSize(h.getSize()) ;
        h.setSize(size(h.getLeft()) + size(h.getRigth()) + 1) ;
        return x;
    }

    // flip the colors of a RBNode and its two children
    private void flipColors(RBNode<Key, Value> h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.getLeft() != null) && (h.getRigth() != null);
        // assert (!isRed(h) &&  isRed(h.getLeft()) &&  isRed(h.getRigth()))
        //    || (isRed(h)  && !isRed(h.getLeft()) && !isRed(h.getRigth()));
        h.setColor(!h.getColor());
        h.getLeft().setColor(!h.getLeft().getColor());
        h.getRigth().setColor(!h.getRigth().getColor());
    }

    // Assuming that h is red and both h.getLeft() and h.getLeft().left
    // are black, make h.getLeft() or one of its children red.
    private RBNode<Key, Value> moveRedLeft(RBNode<Key, Value> h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.getLeft()) && !isRed(h.getLeft().left);

        flipColors(h);
        if (isRed(h.getRigth().getLeft())) { 
            h.setRigth(rotateRight(h.getRigth()));
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.getRigth() and h.getRigth().left
    // are black, make h.getRigth() or one of its children red.
    private RBNode<Key, Value> moveRedRight(RBNode<Key, Value> h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.getRigth()) && !isRed(h.getRigth().left);
        flipColors(h);
        if (isRed(h.getLeft().getLeft())) { 
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private RBNode<Key, Value> balance(RBNode<Key, Value> h) {
        // assert (h != null);

        if (isRed(h.getRigth()))                      h = rotateLeft(h);
        if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) h = rotateRight(h);
        if (isRed(h.getLeft()) && isRed(h.getRigth()))     flipColors(h);

        h.setSize(size(h.getLeft()) + size(h.getRigth()) + 1);
        return h;
    }


   /***************************************************************************
    *  Utility functions.
    ***************************************************************************/

    /**
     * Returns the height of the BST (for debugging).
     * @return the height of the BST (a 1-RBNode tree has height 0)
     */
    public int height() {
        return height(root);
    }
    private int height(RBNode<Key, Value> x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.getLeft()), height(x.getRigth()));
    }

   /***************************************************************************
    *  Ordered symbol table methods.
    ***************************************************************************/

    /**
     * Returns the smallest key in the symbol table.
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return (Key) min(root).getKey();
    } 

    // the smallest key in subtree rooted at x; null if no such key
    private RBNode<Key, Value> min(RBNode<Key, Value> x) { 
        // assert x != null;
        if (x.getLeft() == null) return x; 
        else                return min(x.getLeft()); 
    } 

    /**
     * Returns the largest key in the symbol table.
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return (Key) max(root).getKey();
    } 

    // the largest key in the subtree rooted at x; null if no such key
    private RBNode<Key, Value> max(RBNode x) { 
        // assert x != null;
        if (x.getRigth() == null) return x; 
        else                 return max(x.getRigth()); 
    } 


    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        RBNode<Key, Value> x = floor(root, key);
        if (x == null) return null;
        else           return (Key) x.getKey();
    }    

    // the largest key in the subtree rooted at x less than or equal to the given key
    private RBNode<Key, Value> floor(RBNode<Key, Value> x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo((Key) x.getKey());
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.getLeft(), key);
        RBNode<Key, Value> t = floor(x.getRigth(), key);
        if (t != null) return t; 
        else           return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        RBNode<Key, Value> x = ceiling(root, key);
        if (x == null) return null;
        else           return (Key) x.getKey();  
    }

    // the smallest key in the subtree rooted at x greater than or equal to the given key
    private RBNode<Key, Value> ceiling(RBNode<Key, Value> x, Key key) {  
        if (x == null) return null;
        int cmp = key.compareTo((Key) x.getKey());
        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.getRigth(), key);
        RBNode<Key, Value> t = ceiling(x.getLeft(), key);
        if (t != null) return t; 
        else           return x;
    }

    /**
     * Return the key in the symbol table whose rank is {@code k}.
     * This is the (k+1)st smallest key in the symbol table. 
     *
     * @param  k the order statistic
     * @return the key in the symbol table of rank {@code k}
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>n</em>–1
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        RBNode<Key, Value> x = select(root, k);
        return (Key) x.getKey();
    }

    // the key of rank k in the subtree rooted at x
    private RBNode<Key, Value> select(RBNode<Key, Value> x, int k) {
        // assert x != null;
        // assert k >= 0 && k < size(x);
        int t = size(x.getLeft()); 
        if      (t > k) return select(x.getLeft(),  k); 
        else if (t < k) return select(x.getRigth(), k-t-1); 
        else            return x; 
    } 

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     * @param key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    } 

    // number of keys less than key in the subtree rooted at x
    private int rank(Key key, RBNode<Key, Value> x) {
        if (x == null) return 0; 
        int cmp = key.compareTo((Key) x.getKey()); 
        if      (cmp < 0) return rank(key, x.getLeft()); 
        else if (cmp > 0) return 1 + size(x.getLeft()) + rank(key, x.getRigth()); 
        else              return size(x.getLeft()); 
    } 

   /***************************************************************************
    *  Range count and range search.
    ***************************************************************************/

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return (Iterable<Key>) new Queue<Key>();
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the sybol table between {@code lo} 
     *    (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *    is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, queue, lo, hi);
        return (Iterable<Key>) queue;
    } 

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(RBNode<Key, Value> x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo((Key) x.getKey()); 
        int cmphi = hi.compareTo((Key) x.getKey()); 
        if (cmplo < 0) keys(x.getLeft(), queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue((Key) x.getKey()); 
        if (cmphi > 0) keys(x.getRigth(), queue, lo, hi); 
    } 

    /**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return the number of keys in the sybol table between {@code lo} 
     *    (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *    is {@code null}
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }



    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(RBNode<Key, Value> x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.getKey().compareTo(min) <= 0) return false;
        if (max != null && x.getKey().compareTo(max) >= 0) return false;
        return isBST(x.getLeft(), min, (Key) x.getKey()) && isBST(x.getRigth(), (Key) x.getKey(), max);
    } 

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(RBNode<Key, Value> x) {
        if (x == null) return true;
        if (x.getSize() != size(x.getLeft()) + size(x.getRigth()) + 1) return false;
        return isSizeConsistent(x.getLeft()) && isSizeConsistent(x.getRigth());
    } 

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() { return is23(root); }
    private boolean is23(RBNode<Key, Value> x) {
        if (x == null) return true;
        if (isRed(x.getRigth())) return false;
        if (x != root && isRed(x) && isRed(x.getLeft()))
            return false;
        return is23(x.getLeft()) && is23(x.getRigth());
    } 

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() { 
        int black = 0;     // number of black links on path from root to min
        RBNode<Key, Value> x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.getLeft();
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(RBNode<Key, Value> x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.getLeft(), black) && isBalanced(x.getRigth(), black);
    } 

    public RBNode getRoot() {
    	return this.root;
    }
}