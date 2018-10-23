package model;

import trees.AVLSearchTree.AVLTreeST;
import trees.blackRedTree.RedBlackBST;

public class Archive {
	
	AVLTreeST<String, String> heightSortTree = new AVLTreeST<>();
	AVLTreeST<String, String> weightSortTree = new AVLTreeST<>();
	AVLTreeST<String, String> shootSortTree = new AVLTreeST<>();
	RedBlackBST<String,String> defenseSortTree = new RedBlackBST<>();
	RedBlackBST<String,String> offenceSortTree = new RedBlackBST<>();
	
}
