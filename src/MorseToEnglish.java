/* Created by: Hanz Nathan Po
 * Date created: May 25, 2023
 * Last updated: June 8, 2023
 * Description: Program that allows users to specify a file to read Morse Code from, convert to English, then write to the same file.
 */

 // Package imports
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Scanner;
 import javax.swing.*;
 
 // Start of EnglishToMorse class, main class which converts morse code to plain english text
 public class MorseToEnglish {
	// Main method, entry point of program. All functionality is contained within here for this program
	 public static void main(String[] args) {
 
		 // Declare fileName variable. Stores the name of the file that the user would like to convert
		 String fileName;
 
		 // Assignment of file name value, with default of "src/Morse.txt"
		 fileName = JOptionPane.showInputDialog(null, "Please enter the path of the file you would like to convert:", "src/Morse.txt");
 
		 // If the user presses cancel, closes the window, or does not enter a file name, exit the program
		 if (fileName == null) {
			 System.exit(1);
		 }
 
		 // Try catch to check if the file exists and can be opened. Otherwise, exit program
		 try {
			// Declarations
			 Scanner input; // Used to read file
			 StringBuilder builder; // Used to store user input for conversion
			 String output; // Used for storing converted text
			 FileWriter writer; // Used to write to file
			 
			 input = new Scanner(new File(fileName)); // Creates new Scanner object and tells it to read from file
			 builder = new StringBuilder(); // Creates new StringBuilder object

			 // Keep reading input file until there are no more lines left
			 while (input.hasNext()) {
				// Append the current line to the stringbuilder
				 builder.append(input.nextLine() + "\n");
			 }

			 // Closes scanner when no longer in use in order to prevent issues
			 input.close();
 
			 // Displays unconverted text
			 JOptionPane.showMessageDialog(null, builder, "Showing current contents of " + fileName, JOptionPane.PLAIN_MESSAGE);
			 
			 writer = new FileWriter(fileName, false); // Creates new FileWriter object and tells it to write to file, overwriting any previous data
			 output = MorseCode.morseDecrypt(builder.toString()); // Converts contents of the String Builder and stores in output

			 writer.write(output); // Writes output to file
			 writer.close(); // Closes FileWriter when no longer in use to prevent issues
			 
			 // Shows converted text
			 JOptionPane.showMessageDialog(null, output, "Showing contents of " + fileName + " converted to English", JOptionPane.PLAIN_MESSAGE);
 
		 }
		 catch (Exception e) {
			// Show user what the error is, then exit
			 JOptionPane.showMessageDialog(null, e, e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			 System.exit(1);
		 }
		 
		 // Exit program normally
		 System.exit(0);
	 } // End of main method
 } // End of MorseToEnglish class