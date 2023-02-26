
/*Algorithm Explaination

The given code defines a class named QuestionNo3b that contains a static method named matches which takes in two strings,
an input string and a pattern string, and returns a boolean indicating whether the input string matches the pattern string.
The pattern string can contain the special characters '@' and '#' which have specific meanings. '@' represents a wildcard character
 that can match any character in the input string until the next character in the pattern string is found, while '#' represents a
 character in the input string that must be matched exactly with the corresponding character in the pattern string.
 The matches method iterates through both input and pattern strings character by character, comparing them and returning true
  if they match, and false otherwise. The class also contains a main method which tests the matches method using three sets of input and pattern strings.

*/

// This is a public class named QuestionNo3b
public class QuestionNo3b {
    // This is a static method named matches that takes in two parameters, input and pattern, and returns a boolean value
    public static boolean matches(String input, String pattern) {
        // Initializing variables to keep track of the current indices and lengths of input and pattern
        int inputIndex = 0;
        int patternIndex = 0;
        int inputLength = input.length();
        int patternLength = pattern.length();

        // Using a while loop to iterate through both input and pattern until one of them is fully iterated or a mismatch is found
        while (inputIndex < inputLength && patternIndex < patternLength) {
            // Getting the current character from pattern
            char currentChar = pattern.charAt(patternIndex);

            // If the current character is '@', we need to find the next character in input and match it to the next character in pattern
            if (currentChar == '@') {
                // Incrementing the pattern index to move to the next character in pattern
                patternIndex++;

                // If the '@' character is at the end of the pattern, it matches the rest of the input, so we return true
                if (patternIndex == patternLength) {
                    return true;
                }

                // Getting the next character after '@' in pattern
                char nextChar = pattern.charAt(patternIndex);

                // Using a while loop to iterate through input until we find the next character after '@'
                while (inputIndex < inputLength && input.charAt(inputIndex) != nextChar) {
                    inputIndex++;
                }

                // If we couldn't find the next character after '@' in input, we return false
                if (inputIndex == inputLength) {
                    return false;
                }
            }

            // If the current character is '#', we can simply increment the input and pattern indices
            else if (currentChar == '#') {
                inputIndex++;
                patternIndex++;
            }

            // If the current character in input matches the corresponding character in pattern, we can increment both indices
            else if (input.charAt(inputIndex) == currentChar) {
                inputIndex++;
                patternIndex++;
            }

            // If none of the above conditions are met, it means there is a mismatch between input and pattern, so we return false
            else {
                return false;
            }
        }

        // If both input and pattern have been fully iterated, we return true, otherwise we return false
        return (inputIndex == inputLength && patternIndex == patternLength);
    }

    // This is the main method that will be executed when the class is run
    public static void main(String[] args) {
        // Defining three sets of input and pattern strings to test the matches method with

        // First test case: input = "tt", pattern = "@"
        String input = "tt";
        String pattern = "@";
        boolean isMatch = matches(input, pattern);
        System.out.println(isMatch);

        // Second test case: input = "ta", pattern = "t"
        String input2 = "ta";
        String pattern2 = "t";
        boolean isMatch2 = matches(input2, pattern2);
        System.out.println(isMatch2);

        // Third test case: input = "ta", pattern = "t#"
        String input3 = "ta";
        String pattern3 = "t#";
        boolean isMatch3 = matches(input3, pattern3);
        System.out.println(isMatch3);
    }
}