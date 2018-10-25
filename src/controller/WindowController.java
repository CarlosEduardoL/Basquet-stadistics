package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Archive;

public class WindowController implements Initializable {
	
	public static final String HEIGHT = "Height";
	public static final String SHOT = "Shot";
	public static final String DEFENSE = "Defense";
	public static final String OFFENSE = "Offense";
	public static final String WEIGHT = "Weight";
	
	
	@FXML
	private TextField team,name,age,shot,playerID,defense,offense,shotVal,contrib,height,weight,input;

	@FXML
	private ComboBox<String> combo;

	private Archive model;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> options = 
				FXCollections.observableArrayList(
						HEIGHT,SHOT,DEFENSE,OFFENSE,WEIGHT
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

	public void aLotOfPlayers(ActionEvent e) {
		FileChooser filech = new FileChooser();
		filech.getExtensionFilters().add(new ExtensionFilter("CSV File", "*.csv"));
		File file = filech.showOpenDialog(null);
		if (file != null) {
			generatedNewPlayers(file);
		}
	}
	
	public void addNew(ActionEvent e) {
		try {
			model.addNewPlayer(team.getText() + "\n" + name.getText() + "\n" + age.getText() + "\n" + shot.getText() + "\n" + playerID.getText()
			+ "\n" + defense.getText() + "\n" + offense.getText() + "\n" + shotVal.getText() + "\n" + contrib.getText()
			+ "\n" + height.getText() + "\n" + weight.getText());
			
			loadP();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void generatedNewPlayers(File file) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String temp;
			String alv = "";
			while((temp = reader.readLine())!=null) {
				alv += temp + "separatorPro2017Lol";
			}
			String players[] = alv.split("separatorPro2017Lol");
			
			for (int i = model.getNumOfPlayersAdded()+1; i < players.length + model.getNumOfPlayersAdded(); i++) {
				
				File location = new File("playersData/player_"+i);
				BufferedWriter writer = new BufferedWriter(new FileWriter(location));
				String[] rubros = players[i].split(",");
				String player = "";
				for (int j = 0; j < 12; j++) {
					try {
						player += rubros[j] + "\n";
					}catch (Exception e) {
						player += 0 + "\n";
					}
				}
				writer.write(player);
				writer.close();
			}
			
			reader.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

	}
	
	public void search(ActionEvent e) {
		model.search(combo.getValue(),input.getText());
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
