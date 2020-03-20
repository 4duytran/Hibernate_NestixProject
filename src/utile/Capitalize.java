package utile;

public class Capitalize {

	public static String upperCaseAllFirst(String value) {
		 
        char[] array = value.toCharArray();
 
        // Uppercase first letter.
        array[0] = Character.toUpperCase(array[0]);
 
        // Uppercase all letters that follow a whitespace character.
        for (int i = 1; i < array.length; i++) {
            if (Character.isWhitespace(array[i - 1])) {
                array[i] = Character.toUpperCase(array[i]);
            }
        }
 
        return new String(array);
    }
	
	
}
