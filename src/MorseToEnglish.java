/* Created by: Hanz Nathan Po
 * Date created: May 25, 2023
 * Last updated: June 6, 2023
 * Description: Program that allows users to specify a file to read Morse Code from, convert to English, then write to the same file.
 */

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.*;


public class MorseToEnglish {
	public static void main(String[] args) {
		
		
		String fileName;
		LinkedList<String> lines = new LinkedList<>();

		fileName = JOptionPane.showInputDialog(null, "Please enter the path of the file you would like to convert:", "src/English.txt");

		if (fileName == null) {
			System.exit(1);
		}

		try {
			Scanner input = new Scanner(new File(fileName));
			StringBuilder output = new StringBuilder();
			String currentLine;
			
			while (input.hasNext()) {
				currentLine = input.nextLine();
				
				lines.add(currentLine);
			}
			
			input.close();


			
			FileWriter writer = new FileWriter(fileName, false);
			
			for (int i = 0; i < lines.size(); i++) {
				lines.set(i, MorseCode.MorseDecrypt(lines.get(i)));
				writer.write(lines.get(i) + "\n");
			}
			
			writer.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		System.exit(0);
	}
}
