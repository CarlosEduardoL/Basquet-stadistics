package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import trees.AVLSearchTree.AVLTreeST;
import trees.blackRedTree.RedBlackBST;

public class Archive {
	
	public static final String FOLDER_DIRECTION = "playersData/";
	public static final File FOLDER = new File(FOLDER_DIRECTION);
	
	AVLTreeST<Integer, String> heightSortTree;
	AVLTreeST<Integer, String> weightSortTree;
	AVLTreeST<String, String> shootSortTree = new AVLTreeST<>();
	RedBlackBST<String,String> defenseSortTree = new RedBlackBST<>();
	RedBlackBST<String,String> offenceSortTree = new RedBlackBST<>();
	
	public Archive() {
		heightSortTree = new AVLTreeST<>();
		weightSortTree = new AVLTreeST<>();
	}
	
	public void createHeigthTree() throws IOException {
		BufferedReader reader;
		for (File element : FOLDER.listFiles()) {
			reader = new BufferedReader(new FileReader(element));
			String temp;
			String player = "";
			while((temp = reader.readLine())!=null) {
				player += temp + "Separator" ;
			}
			String[] data = player.split("Separator");
			heightSortTree.put(Integer.parseInt(data[9]), FOLDER_DIRECTION + element.getName());
		}
	}
	
}
