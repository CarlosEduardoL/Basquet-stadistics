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
		BufferedReader reader = new BufferedReader(new FileReader(new File("rubros.csv"))); 
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("MySelectedData.csv")));
		String temp;
		String alv = "";
		while((temp = reader.readLine())!=null) {
			alv += temp + "separator";
		}
		reader.close();
		String players[] = alv.split("separator");
		for (int i = 0; i < players.length; i++) {
			String data[] = players[i].split(",");
			String ab = data[2] + "," + data[0] + "," + data[1] + "," + data[3] + "," + data[5] + ","
					+ data[7] + "," +  data[9] + "," +  data[42] + "," +  data[54] + "," +  data[88] +
					"," +  data[105] + "," +  data[106] + "\n";
			writer.write(ab);
		}
		writer.close();
		
	}

}
