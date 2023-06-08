/* Created by: Hanz Nathan Po
 * Date created: May 25, 2023
 * Last updated: June 6, 2023
 * Description: Program that converts plain text to and from Morse Code.
 */

// Defines class. Used statically in EnglishToMorse and MorseToEnglish.
public class MorseCode {

	// Array to store morse code letters. SHOULD NOT BE CHANGED!!
	private static final String[] morseLetters = new String[]{ "* _", "_ * * *", "_ * _ *", "_ * *", "*", "* * _ *", "_ _ *", "* * * *", "* *", "* _ _ _", "_ * _", "* _ * *", "_ _", "_ *", "_ _ _", "* _ _ *", "_ _ * _", "* _ *", "* * *", "_", "* * _", "* * * _", "* _ _", "_ * * _", "_ * _ _", "_ _ * *"};

	// Method to encrypt morse code, to be called from other classes. Trims input string.
	public static String morseEncrypt(String str) {
		return morseEncryptRecursive(str.trim());
	}

	// Method for actual encryption using recursion. Input trimming was separated in order to prevent weird things from happening with spaces.
	private static String morseEncryptRecursive(String englishText) {
		
		// Set text to uppercase
		englishText = englishText.toUpperCase();
		
		// Base case, if input string is empty return empty string
		if (englishText.length() < 1) {
			return "";
		}
		
		// If input is a space, return eight spaces, because eight spaces in morse code represents one space in plain text. Pass the rest of the string in recursively
		if (englishText.charAt(0) == ' ') {
			return "        " + morseEncryptRecursive(englishText.substring(1));
		}
		
		// Get index of the morse code letter to use, based on the current character minus 65 (which is the ASCII code for "A")
		int index = (int) englishText.charAt(0) - 65;
		
		// Checks to see if current character is in the alphabet. If true
		if (index >= 0 && index <= 25) {
			return morseLetters[index] + "    " + morseEncryptRecursive(englishText.substring(1));
		}
		
		// If current character is not alphabetical, return the same character
		return englishText.charAt(0) + "    " +  morseEncryptRecursive(englishText.substring(1));
	}
	
	// Method to decrypt morse code, to be called from other classes. Trims input string.
	public static String morseDecrypt(String str) {
		return morseDecryptRecursive(str.trim());
	}
	
	// Method for actual decryption using recursion. Input trimming was separated in order to prevent weird things from happening with spaces.
	private static String morseDecryptRecursive(String morseText) {
		
		// Base case, if input string is empty return empty string
		if (morseText.length() < 1) {
			return "";
		}
		
		// Looks for the location of the next gap between morse letters (equivalent to 4 spaces)
		int indexOfNextGap = morseText.indexOf("    ");
		// Looks for the location of the next morse code space (equivalent to 8 spaces)
		int indexOfNextSpace = morseText.indexOf("        ");
		// Used to store the value of the current character being converted, from 0-25, based on its position in the alphabet. If the current character is not alphabetical, such as a punctuation mark, the value is -1.
		int currentChar;
		
		// If a space is at the front of the input string, return a plain english space, then pass the rest of the input into the recursive function
		if (indexOfNextSpace == 0) {
			return " " + morseDecryptRecursive(morseText.substring(8));
		}
		
		// If a gap between letters is at the front of the input string, just pass the rest of the input into the recursive function
		if (indexOfNextGap == 0) {
			return morseDecryptRecursive(morseText.substring(4));
		}

		// If no more gaps exist, that means there is only one letter left to convert
		if (indexOfNextGap == -1) {
			// Sets the current character to a value from -1 to 25. If the number is from 0-25, that means that the current character exists in the morse code array. If the number is -1, it is not in the alphabet. This includes characters such as punctuation marks.
			currentChar = getIndexFromItem(morseText, morseLetters);
			
			// If the current character is not alphabetical, simply return that character
			if (currentChar == -1) {
				return String.valueOf(morseText.charAt(0));
			}
			
			// If it is alphabetical, add 65 to get the ASCII code, convert to a String, then return.
			return String.valueOf((char) (currentChar + 65));
		}

		// Sets the current character to a value from -1 to 25. If the number is from 0-25, that means that the current character exists in the morse code array. If the number is -1, it is not in the alphabet. This includes characters such as punctuation marks.
		currentChar = getIndexFromItem(morseText.substring(0, indexOfNextGap), morseLetters);
		
		// If the current character is not alphabetical, simply return that character. Call the method recursively while passing in the rest of the input
		if (currentChar == -1) {
			return morseText.charAt(0) + morseDecryptRecursive(morseText.substring(1));
		}
		
		// If it is alphabetical, add 65 to get the ASCII code, convert to a String, then return. Call the method recursively while passing in the rest of the input
		return String.valueOf((char) (currentChar + 65)) + morseDecryptRecursive(morseText.substring(indexOfNextGap));
	} // End of morseDecryptRecursive

	// Gets index of an item from a given array. If the item cannot be found, return -1
	private static int getIndexFromItem(String item, String[] array) {
		
		// Iterate through given array looking for item. Return i if found.
		for (int i = 0; i < array.length; i++) {
			if (item.equals(array[i])) {
				return i;
			}
		}
		
		// Return -1 if nothing is found
		return -1;
	} // End of getIndexFromItem
} // End of MorseCode class
