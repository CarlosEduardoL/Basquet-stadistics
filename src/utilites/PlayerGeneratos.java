/**
 * 
 */
package utilites;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ZeroN
 *
 */
public class PlayerGeneratos {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File location = new File("playersData/");
		location.mkdirs();
		System.out.println(location.getAbsolutePath());
		BufferedReader reader = new BufferedReader(new FileReader(new File("rubros.csv"))); 
		
		String temp;
		String alv = "";
		while((temp = reader.readLine())!=null) {
			alv += temp + "separatorPro2017Lol";
		}
		String players[] = alv.split("separatorPro2017Lol");
		
		for (int i = 0; i < players.length; i++) {
			
			location = new File("playersData/player_"+i);
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
		
	}

}
