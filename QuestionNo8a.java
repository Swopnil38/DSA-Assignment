// Importing necessary libraries
import java.util.Arrays; // Library for working with arrays
import java.util.Stack; // Library for working with stack data structure

// Class definition for finding the maximum area of a square made up of 0s in a matrix
class MaxAreaOf0 {
    // Method for finding the maximum area of a square made up of 0s in a matrix
    public static int max(int[][] matrix) {

        // Getting the number of rows and columns in the matrix
        int n = matrix.length;
        int m = matrix[0].length;

        // Initializing a 2D array for dynamic programming approach to find the maximum area of a square made up of 0s
        int[][] dp = new int[n][m];

        // Initializing a variable to store the maximum area of a square made up of 0s
        int maxArea = 0;

        // Looping through each element in the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // If the current element is 0, set the corresponding element in dp to 1 and update the maximum area
                if (matrix[i][j] == 0) {
                    dp[i][j] = 1;
                    maxArea = 1;
                }
            }
        }

        // Looping through each element in the matrix except the first row and first column
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // If the current element is 0, compute the minimum value of its top, top-left, and left neighbors and add 1 to get the current element's value in dp
                // Update the maximum area if the current element's value in dp is greater than the current maximum area
                if (matrix[i][j] == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxArea = Math.max(maxArea, dp[i][j]);
                }
            }
        }

        // Return the maximum area of a square made up of 0s
        return maxArea * maxArea;
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        // Test matrix
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};
        // Call the max method to find the maximum area of a square made up of 0s in the matrix
        int maxArea = max(matrix);
        // Print the result
        System.out.println("Maximum area of square made by 0s: " + maxArea);
    }

}