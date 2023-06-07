/* Created by: Hanz Nathan Po
 * Date created: May 25, 2023
 * Last updated: June 6, 2023
 * Description: Program that converts plain text to and from Morse Code.
 */

// Defines class. Used statically in EnglishToMorse and MorseToEnglish.
public class MorseCode {
	
	// 
	private static final String[] morseLetters = new String[]{ "* _", "_ * * *", "_ * _ *", "_ * *", "*", "* * _ *", "_ _ *", "* * * *", "* *", "* _ _ _", "_ * _", "* _ * *", "_ _", "_ *", "_ _ _", "* _ _ *", "_ _ * _", "* _ *", "* * *", "_", "* * _", "* * * _", "* _ _", "_ * * _", "_ * _ _", "_ _ * *"};
	
	public static String MorseEncrypt(String str) {
		return MorseEncryptRecursive(str.trim());
	}
	
	private static String MorseEncryptRecursive(String englishText) {
		
		englishText = englishText.toUpperCase();
		
		if (englishText.length() < 1) {
			return "";
		}
		
		if (englishText.charAt(0) == ' ') {
			return "        " + MorseEncryptRecursive(englishText.substring(1));
		}
		
		int index = (int) englishText.charAt(0) - 65;
		
		if (index >= 0 && index <= 25) {
			return morseLetters[index] + "    " + MorseEncryptRecursive(englishText.substring(1));
		}
		
		return englishText.charAt(0) + "    " +  MorseEncryptRecursive(englishText.substring(1));
	}
	
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
		else {
			int currentChar = getIndexFromItem(morseText.substring(0, indexOfNextGap), morseLetters);
			
			if (currentChar == -1) {
				return morseText.charAt(0) + MorseDecryptRecursive(morseText.substring(1));
			}
			
			return String.valueOf((char) (currentChar + 65)) + MorseDecryptRecursive(morseText.substring(indexOfNextGap));
		}
	}

	private static int getIndexFromItem(String item, String[] array) {
		
		for (int i = 0; i < array.length; i++) {
			if (item.equals(array[i])) {
				return i;
			}
		}
		
		return -1;
	}
}
