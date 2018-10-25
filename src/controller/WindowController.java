package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Archive;

public class WindowController implements Initializable {

	@FXML
	private TextField team,name,age,shot,playerID,defense,offense,shotVal,contrib,height,weight;
	
	@FXML
	private ComboBox<String> combo;
	
	private Archive model;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "height","shot","Defense","Offense","weight"
			    );
		combo.setItems(options);
		model = new Archive();
		loadP();
	}
	
	public void next(ActionEvent e) {
		model.next();
		loadP();
	}
	
	public void back(ActionEvent e) {
		model.back();
		loadP();
	}
	
	public void loadP() {
		try {
			String[] data = model.playerData();
			team.setText(data[0]);
			name.setText(data[1]);
			age.setText(data[2]);
			shot.setText(data[3]);
			playerID.setText(data[4]);
			defense.setText(data[5]);
			offense.setText(data[6]);
			shotVal.setText(data[7]);
			contrib.setText(data[8]);
			height.setText(data[9]);
			weight.setText(data[10]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
