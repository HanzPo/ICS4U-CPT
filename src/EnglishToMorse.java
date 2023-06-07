import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.*;


public class EnglishToMorse {
	public static void main(String[] args) {
		
		String fileName = "src/English.txt";
		LinkedList<String> lines = new LinkedList<String>();
		
		try {
			Scanner input = new Scanner(new File(fileName));
			
			String currentLine;
			
			while (input.hasNext()) {
				currentLine = input.nextLine();
				
				lines.add(currentLine);
			}
			
			input.close();
			
			FileWriter writer = new FileWriter(fileName, false);
			
			for (int i = 0; i < lines.size(); i++) {
				lines.set(i, MorseCode.MorseEncrypt(lines.get(i)));
				writer.write(lines.get(i) + "\n");
			}
			
			writer.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "An error occured", JOptionPane.ERROR_MESSAGE);
		}
		
		System.exit(0);
	}
}
