/*Algorithm Explaination
        This algorithm takes an input array and uses bit manipulation to generate all possible combinations of the array.
        It then splits each combination into two subsets of equal size, calculates the product of each subset,
        and finds the absolute difference between the two products. It keeps track of the minimum difference seen so far and returns it at the end.
        The algorithm achieves this by iterating through each possible combination of the array, splitting it into two subsets,
        calculating the product of each subset, and updating the minimum difference if necessary.
        This algorithm is efficient and has a time complexity of O(2^n), where n is the length of the input array, which is reasonable for small inputs.

        */


public class QuestionNo3a {
    public static int product(int[] subaaray) {
// This if statement checks if the length of the input array is zero.
        if (subaaray.length == 0) {
// If the length of the array is zero, the method returns zero.
            return 0;
        }
// This initializes an integer variable named "res" to 1.
        int res = 1;
// This for loop iterates through the input array and multiplies each element with the previous result.
        for (int i = 0; i < subaaray.length; i++) {
            res = res * subaaray[i];
        }
// The method returns the final product of all the elements in the array.
        return res;
    }

    // This is another public static method named "findminimumdifference" that takes an integer array as input and returns an integer.
    public static int findminimumdifference(int[] array) {
        // This initializes an integer variable named "mindiff" to the maximum possible value of an integer.
        int mindiff = Integer.MAX_VALUE;
        // This initializes an integer variable named "n" to the length of the input array.
        int n = array.length;
        // This for loop iterates through all possible combinations of the input array by generating all possible subsets using bit manipulation.
        for (int i = 0; i < (1 << n); i++) {
            // This if statement checks if the number of set bits in the current combination is equal to half the length of the input array.
            if (Integer.bitCount(i) == n / 2) {
                // This creates two new integer arrays, each with a length equal to half the length of the input array.
                int[] subarray1 = new int[n / 2];
                int[] subarray2 = new int[n / 2];
                // This initializes two integer variables named "index1" and "index2" to 0.
                int index1 = 0;
                int index2 = 0;
                // This for loop iterates through the input array and assigns each element to one of the two new arrays based on its position in the binary representation of the current combination.
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) {
                        subarray1[index1++] = array[j];
                    } else {
                        subarray2[index2++] = array[j];
                    }
                }
                // This calculates the product of each of the two new arrays using the "product" method defined earlier.
                int subarray1product = product(subarray1);
                int subarray2product = product(subarray2);
                // This initializes an integer variable named "curr_min_diff" to 0.
                int curr_min_diff = 0;
                // This if statement compares the two products and calculates the absolute difference between them.
                if (subarray2product > subarray1product) {
                    curr_min_diff = subarray2product - subarray1product;
                } else {
                    curr_min_diff = subarray1product - subarray2product;
                }
                // This if statement checks if the current minimum difference is smaller than the previous minimum difference, and updates the "mindiff" variable if it is.
                if (curr_min_diff < mindiff) {
                    mindiff = curr_min_diff;
                }

            }
        }
        // The method returns the minimum difference between the product of the two subsets that have equal number of elements.
        return mindiff;
    }

    // This is the main method that is executed when the program is run.
    public static void main(String[] args) {
        // This creates an integer array containing four elements.
        int[] array = {5, 2, 4, 11};
        // This calls the "findminimumdifference" method with the input array and assigns the result to an integer variable named "answer".
        int answer = findminimumdifference(array);
        // This prints the value of "answer" to the console.
        System.out.println(answer);
    }
}