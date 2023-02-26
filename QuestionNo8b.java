// Import the necessary packages
import java.util.ArrayList;
import java.util.List;

// Define a class called "MissingEven"
class MissingEven {
    // Define a method to find the k-th missing even number in the array
    public static int findKthMissingEvenNumber(int[] a, int k) {
// Create an empty ArrayList to store the missing even numbers
        List<Integer> missingEvens = new ArrayList<>();
// Initialize two variables "i" and "j" to keep track of the current even number and the index of the array respectively
        int j = 0;
        int i = a[0];
// Keep looping until we have found k missing even numbers
        while (missingEvens.size() < k) {
// If the current even number is in the array, move on to the next number in the array
            if (j < a.length && a[j] == i) {
                j++;
            } else {
// Otherwise, add the current even number to the list of missing even numbers
                missingEvens.add(i);
            }
// Move on to the next even number
            i += 2;
        }
// Return the k-th missing even number
        return missingEvens.get(k - 1);
    }

    // Define the main method
    public static void main(String[] args) {
        // Define the input array and k
        int[] a = {0, 2, 6, 18, 22};
        int k = 6;
        // Call the findKthMissingEvenNumber method and print the result
        System.out.println(findKthMissingEvenNumber(a, k));
    }
}