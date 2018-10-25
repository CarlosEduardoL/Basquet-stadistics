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

import controller.WindowController;
import trees.AVLSearchTree.AVLTreeST;
import trees.blackRedTree.RedBlackBST;

public class Archive {

	public static final String FOLDER_DIRECTION = "playersData/";
	public static final File FOLDER = new File(FOLDER_DIRECTION);

	private AVLTreeST<Integer, String> heightSortTree;
	private AVLTreeST<Integer, String> weightSortTree;
	private AVLTreeST<Double, String> shootSortTree;
	private RedBlackBST<Double,String> defenseSortTree;
	private RedBlackBST<Double,String> offenceSortTree;

	private int actualPlayer;
	private int numOfPlayersAdded;

	public Archive() {

		actualPlayer = 1;
		setNumOfPlayersAdded(FOLDER.listFiles().length);

		try {
			cargarArboles();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked" })
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
		
		//Weight
		try {
			reader = new ObjectInputStream(new FileInputStream(new File("arbolesSerializados/Weight.tree")));
			weightSortTree = (AVLTreeST<Integer, String>) reader.readObject();
		}catch (Exception e) {
			createWeightTree();
			writer = new ObjectOutputStream(new FileOutputStream(new File("arbolesSerializados/Weight.tree")));
			writer.writeObject(weightSortTree);
		}
		
		//Shoot
		try {
			reader = new ObjectInputStream(new FileInputStream(new File("arbolesSerializados/Shoot.tree")));
			shootSortTree = (AVLTreeST<Double, String>) reader.readObject();
		}catch (Exception e) {
			createShootTree();
			writer = new ObjectOutputStream(new FileOutputStream(new File("arbolesSerializados/Shoot.tree")));
			writer.writeObject(shootSortTree);
		}
		
		//Defense
		try {
			reader = new ObjectInputStream(new FileInputStream(new File("arbolesSerializados/Defense.tree")));
			defenseSortTree = (RedBlackBST<Double,String>) reader.readObject();
		}catch (Exception e) {
			createDefenseTree();
			writer = new ObjectOutputStream(new FileOutputStream(new File("arbolesSerializados/Defense.tree")));
			writer.writeObject(defenseSortTree);
		}
		
		//Offense
		try {
			reader = new ObjectInputStream(new FileInputStream(new File("arbolesSerializados/Offense.tree")));
			offenceSortTree = (RedBlackBST<Double,String>) reader.readObject();
		}catch (Exception e) {
			createOffenseTree();
			writer = new ObjectOutputStream(new FileOutputStream(new File("arbolesSerializados/Offense.tree")));
			writer.writeObject(offenceSortTree);
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
				heightSortTree.put(Integer.parseInt(data[9]), element.getName());
			}catch (Exception e) {
				heightSortTree.put(0, element.getName());
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
			try {
				weightSortTree.put(Integer.parseInt(data[10]), element.getName());
			}catch (Exception e) {
				weightSortTree.put(0, element.getName());
			}
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
			try {
				shootSortTree.put(Double.parseDouble(data[2]), element.getName());
			} catch (Exception e) {
				shootSortTree.put((double) 0, element.getName());
			}
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
			try {
				defenseSortTree.put(Double.parseDouble(data[4]), element.getName());
			}catch (Exception e) {
				defenseSortTree.put((double) 0, element.getName());
			}
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
			try {
				offenceSortTree.put(Double.parseDouble(data[4]), element.getName());
			}catch (Exception e) {
				offenceSortTree.put((double) 0, element.getName());
			}
		}
	}

	public String[] playerData() throws IOException {
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(new File(FOLDER_DIRECTION+"player_"+actualPlayer)));
		String temp;
		String player = "";
		while((temp = reader.readLine())!=null) {
			player += temp + "Separator" ;
		}
		String[] data = player.split("Separator");
		return data;
	}

	public int getNumOfPlayersAdded() {
		return numOfPlayersAdded;
	}

	public void setNumOfPlayersAdded(int numOfPlayersAdded) {
		this.numOfPlayersAdded = numOfPlayersAdded;
	}

	public void next() {
		if (actualPlayer < numOfPlayersAdded) {
			actualPlayer++;
		}else {
			actualPlayer = 1;
		}
	}

	public void back() {
		if (actualPlayer > 1) {
			actualPlayer--;
		}else {
			actualPlayer = numOfPlayersAdded;
		}
	}

	public void search(String tree, String key) {
		if (tree.equals(WindowController.DEFENSE)) {
			actualPlayer = Integer.parseInt(defenseSortTree.get(Double.parseDouble(key)).replace("Player_", ""));
		}else if (tree.equals(WindowController.OFFENSE)) {
			actualPlayer = Integer.parseInt(offenceSortTree.get(Double.parseDouble(key)).replace("Player_", ""));
		}
		
	}

}
