package application;
	
import trees.binarySearchTree.BST;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			BST<Integer,Integer> tree = new BST<Integer,Integer>();
            //Random rnd = new Random();
            //for (int i = 0; i < 50; i++)
            //{
            //    int element = rnd.Next(0, 500);
            //    tree.Add(element, element);
            //}

            tree.add(6, 6);
            tree.add(8, 8);
            tree.add(3, 3);
            tree.add(5, 5);
            tree.add(4, 4);
            tree.add(2, 2);

            tree.remove(6);

            ArrayList<Integer> list = tree.inOrderArray();
            for(int i = 0; i < list.size(); i++)
            {
                System.out.println(list.get(i));
            }
			
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
