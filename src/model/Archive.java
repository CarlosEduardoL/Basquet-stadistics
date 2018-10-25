package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import trees.AVLSearchTree.AVLTreeST;
import trees.blackRedTree.RedBlackBST;

public class Archive {
	
	public static final String FOLDER_DIRECTION = "playersData/";
	public static final File FOLDER = new File(FOLDER_DIRECTION);
	
	AVLTreeST<Integer, String> heightSortTree;
	AVLTreeST<Integer, String> weightSortTree;
	AVLTreeST<Double, String> shootSortTree;
	RedBlackBST<Double,String> defenseSortTree;
	RedBlackBST<Double,String> offenceSortTree;
	
	public Archive() {
		try {
			cargarArboles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "resource", "unchecked" })
	private void cargarArboles() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream reader;
		ObjectOutputStream writer;
		
		//Heigth
		try {
			reader = new ObjectInputStream(new FileInputStream(new File("arbolesSerializados/Heigth.tree")));
			heightSortTree = (AVLTreeST<Integer, String>) reader.readObject();
		}catch (Exception e) {
			createHeigthTree();
			writer = new ObjectOutputStream(new FileOutputStream(new File("arbolesSerializados/Heigth.tree")));
			writer.writeObject(heightSortTree);
		}
	}

	public void createHeigthTree() throws IOException {
		heightSortTree = new AVLTreeST<>();
		BufferedReader reader;
		for (File element : FOLDER.listFiles()) {
			reader = new BufferedReader(new FileReader(element));
			String temp;
			String player = "";
			while((temp = reader.readLine())!=null) {
				player += temp + "Separator" ;
			}
			String[] data = player.split("Separator");
			
			try {
				heightSortTree.put(Integer.parseInt(data[9]), FOLDER_DIRECTION + element.getName());
			}catch (Exception e) {
				heightSortTree.put(0, FOLDER_DIRECTION + element.getName());
			}
		}
	}
	
	public void createWeightTree() throws IOException {
		weightSortTree = new AVLTreeST<>();
		BufferedReader reader;
		for (File element : FOLDER.listFiles()) {
			reader = new BufferedReader(new FileReader(element));
			String temp;
			String player = "";
			while((temp = reader.readLine())!=null) {
				player += temp + "Separator" ;
			}
			String[] data = player.split("Separator");
			weightSortTree.put(Integer.parseInt(data[10]), FOLDER_DIRECTION + element.getName());
		}
	}
	
	public void createShootTree() throws IOException {
		shootSortTree = new AVLTreeST<>();
		BufferedReader reader;
		for (File element : FOLDER.listFiles()) {
			reader = new BufferedReader(new FileReader(element));
			String temp;
			String player = "";
			while((temp = reader.readLine())!=null) {
				player += temp + "Separator" ;
			}
			String[] data = player.split("Separator");
			shootSortTree.put(Double.parseDouble(data[2]), FOLDER_DIRECTION + element.getName());
		}
	}
	
	public void createDefenseTree() throws IOException {
		defenseSortTree = new RedBlackBST<>();
		BufferedReader reader;
		for (File element : FOLDER.listFiles()) {
			reader = new BufferedReader(new FileReader(element));
			String temp;
			String player = "";
			while((temp = reader.readLine())!=null) {
				player += temp + "Separator" ;
			}
			String[] data = player.split("Separator");
			shootSortTree.put(Double.parseDouble(data[4]), FOLDER_DIRECTION + element.getName());
		}
	}
	
	public void createOffenseTree() throws IOException {
		offenceSortTree = new RedBlackBST<>();
		BufferedReader reader;
		for (File element : FOLDER.listFiles()) {
			reader = new BufferedReader(new FileReader(element));
			String temp;
			String player = "";
			while((temp = reader.readLine())!=null) {
				player += temp + "Separator" ;
			}
			String[] data = player.split("Separator");
			shootSortTree.put(Double.parseDouble(data[4]), FOLDER_DIRECTION + element.getName());
		}
	}
	
}
