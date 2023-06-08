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
	public static String MorseEncrypt(String str) {
		return MorseEncryptRecursive(str.trim());
	}
	
	// Method for actual encryption using recursion. Input trimming was separated in order to prevent weird things from happening with spaces.
	private static String MorseEncryptRecursive(String englishText) {
		
		// Set text to uppercase
		englishText = englishText.toUpperCase();
		
		// Base case, if input string is empty return empty string
		if (englishText.length() < 1) {
			return "";
		}
		
		// If input is a space, return eight spaces, because eight spaces in morse code represents one space in plain text. Pass the rest of the string in recursively
		if (englishText.charAt(0) == ' ') {
			return "        " + MorseEncryptRecursive(englishText.substring(1));
		}
		
		// Get index of the morse code letter to use, based on the current character minus 65 (which is the ASCII code for "A")
		int index = (int) englishText.charAt(0) - 65;
		
		// Checks to see if current character is in the alphabet. If true
		if (index >= 0 && index <= 25) {
			return morseLetters[index] + "    " + MorseEncryptRecursive(englishText.substring(1));
		}
		
		// If current character is not alphabetical, return the same character
		return englishText.charAt(0) + "    " +  MorseEncryptRecursive(englishText.substring(1));
	}
	
	// Method to decrypt morse code, to be called from other classes. Trims input string.
	public static String MorseDecrypt(String str) {
		return MorseDecryptRecursive(str.trim());
	}
	
	private static String MorseDecryptRecursive(String morseText) {
		
		if (morseText.length() < 1) {
			return "";
		}
		
		int indexOfNextGap = morseText.indexOf("    ");
		int indexOfNextSpace = morseText.indexOf("        ");
		
		if (indexOfNextSpace == 0) {
			return " " + MorseDecryptRecursive(morseText.substring(8));
		}
		
		if (indexOfNextGap == 0) {
			return "" + MorseDecryptRecursive(morseText.substring(4));
		}
		
		
		if (indexOfNextGap == -1) {
			int currentChar = getIndexFromItem(morseText, morseLetters);
			
			if (currentChar == -1) {
				return String.valueOf(morseText.charAt(0));
			}
			
			return String.valueOf((char) (currentChar + 65));
		}

		int currentChar = getIndexFromItem(morseText.substring(0, indexOfNextGap), morseLetters);
		
		if (currentChar == -1) {
			return morseText.charAt(0) + MorseDecryptRecursive(morseText.substring(1));
		}
		
		return String.valueOf((char) (currentChar + 65)) + MorseDecryptRecursive(morseText.substring(indexOfNextGap));
	}

	// Gets index of an item from a given array. If the item cannot be found, return -1
	private static int getIndexFromItem(String item, String[] array) {
		
		for (int i = 0; i < array.length; i++) {
			if (item.equals(array[i])) {
				return i;
			}
		}
		
		return -1;
	}
}
