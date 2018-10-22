package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class WindowController implements Initializable {

	@FXML
	private TextField name,age,defense,offense,production;
	
	@FXML
	private ComboBox<String> combo;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Name","Age","Defense","Offense","Production"
			    );
		combo.setItems(options);
	}

}
