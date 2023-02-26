// Import the required libraries
import java.util.HashSet;
import java.util.Set;

// Define a class named "WordandTarget"
class WordandTarget {
    // Define a private static final int array named "POW_10" to store powers of 10.
    private static final int[] POW_10 = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};

    // Define a public boolean method named "isSolvable" with arguments "words" and "result".
    public boolean isSolvable(String[] words, String result) {
        // Create a new HashSet object to store unique characters in the words and result strings.
        Set<Character> charSet = new HashSet<>();
        // Create an integer array named "charCount" with 91 elements to store the count of each character in the words and result strings.
        int[] charCount = new int[91];
        // Create a boolean array named "nonLeadingZero" with 91 elements to store whether each character in the words and result strings can be a leading zero or not.
        boolean[] nonLeadingZero = new boolean[91]; // ASCII of A..Z chars are in range 65..90

        // Iterate over the "words" array.
        for (String word : words) {
            // Convert the word to a character array.
            char[] cs = word.toCharArray();
            // Iterate over each character in the word.
            for (int i = 0; i < cs.length; i++) {
                // If this is the first character in the word and the word has more than one character, set "nonLeadingZero" to true for this character.
                if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
                // Add this character to the "charSet" HashSet.
                charSet.add(cs[i]);
                // Increase the count of this character in "charCount" by a power of 10 based on its position in the word.
                charCount[cs[i]] += POW_10[cs.length - i - 1]; // charCount is calculated by units
            }
        }

        // Convert the "result" string to a character array.
        char[] cs = result.toCharArray();
        // Iterate over each character in the result string.
        for (int i = 0; i < cs.length; i++) {
            // If this is the first character in the result and the result has more than one character, set "nonLeadingZero" to true for this character.
            if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
            // Add this character to the "charSet" HashSet.
            charSet.add(cs[i]);
            // Decrease the count of this character in "charCount" by a power of 10 based on its position in the result string.
            charCount[cs[i]] -= POW_10[cs.length - i - 1]; // charCount is calculated by units
        }

        // Create a boolean array named "used" with 10 elements to store whether each digit has been used or not.
        boolean[] used = new boolean[10];
        // Convert the "charSet" HashSet to a character array named "charList".
        char[] charList = new char[charSet.size()];
        int i = 0;
        for (char c : charSet) charList[i++] = c;

        // Call a private recursive method named "backtracking" to check if a valid solution exists.
        return backtracking(used, charList, nonLeadingZero, 0, 0, charCount);
    }

    // Private recursive method to find a valid solution using backtracking.
    private boolean backtracking(boolean[] used, char[] charList, boolean[] nonLeadingZero, int step, int diff,
                                 int[] charCount) {
// Base case: if all characters have been assigned a digit, check if the sum of the words equals the result.
        if (step == charList.length) {
            return diff == 0; // Return true if the difference between the sum of the words and the result is zero.
        }
        // Recursive case: try all possible digits for the current character.
        for (int d = 0; d <= 9; d++) { // digits can be from 0 to 9.
            char c = charList[step]; // get the current character to assign a digit to.
            if (!used[d] // Check if the digit has not been used already.
                    && (d > 0 || !nonLeadingZero[c])) { // Check if the digit can be used for this character (leading zeros not allowed).
                used[d] = true; // Mark the digit as used.
                if (backtracking(used, charList, nonLeadingZero, step + 1, diff + charCount[c] * d, charCount)) {
                    return true; // Return true if a solution is found.
                }
                used[d] = false; // Unmark the digit if no solution is found.
            }
        }
        return false; // Return false if no solution is found for this character.
    }


    // Main method to test the solution with some input.
    public static void main(String[] args) {
        WordandTarget solutions = new WordandTarget(); // Create an instance of the class.
        String[] words = {"SIX", "SEVEN", "SEVEN"}; // Define an array of words.
        String result = "TWENTY"; // Define the result string.
        Boolean answer = solutions.isSolvable(words, result); // Call the "isSolvable" method with the input.
        System.out.println(answer); // Print the answer to the console.
    }

}

