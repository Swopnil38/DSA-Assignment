import java.util.*;



/*ALgorithm Explaination
This algorithm solves the problem of determining the number of times an electric vehicle's batteries need to be replaced
while travelling from a source city to a destination city, given the service centers along the journey.
The input is an array of service centers, the target miles to be travelled, and the starting charge capacity.
The algorithm iterates through the distances of the service centers, and if the current distance is greater than the current number
of miles travelled, the battery is replaced with the previous service center's capacity and the count of battery replacements is incremented.
 Finally, if the current number of miles travelled is less than the target miles, the battery is replaced and the count is incremented.
  The algorithm then returns the final count of battery replacements.
 */

public class Question5b {
    // This method takes in an array of service centers, the target miles to be travelled, and the starting charge capacity
// and returns the number of times the car's batteries need to be replaced.
    public int numBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        // Initialize variables for the count of battery replacements and the current number of miles travelled.
        int count = 0;
        int currentMiles = startChargeCapacity;
        // Create two ArrayLists to store the distances and capacities of the service centers.
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<Integer> capacities = new ArrayList<>();

        // Extract the distances and capacities of the service centers and store them in the respective ArrayLists.
        for (int[] serviceCenter : serviceCenters) {
            distances.add(serviceCenter[0]);
            capacities.add(serviceCenter[1]);
        }

        // Iterate through the distances of the service centers.
        for (int i = 0; i < distances.size(); i++) {
            // If the current distance is greater than the current number of miles travelled, replace the battery with the
            // previous service center's capacity and increment the count of battery replacements.
            if (distances.get(i) > currentMiles) {
                currentMiles = capacities.get(i - 1);
                count++;
            }
        }
        // If the current number of miles travelled is less than the target miles, replace the battery and increment the count.
        if (currentMiles < targetMiles) {
            count++;
        }
        // Return the final count of battery replacements.
        return count;
    }

    // This is the main method that creates an instance of the Question5b class, calls the numBatteryReplacements method
// with the given input values, and prints the final count of battery replacements.
    public static void main(String[] args) {
        // Create the service center array with given input values.
        int [][] serviceCenterList={{10,60},{20,30},{30,30},{60,40}};
        // Create an instance of the Question5b class.
        Question5b question1=new Question5b();
        // Call the numBatteryReplacements method with the given input values.
        int finalAnswer=question1.numBatteryReplacements(serviceCenterList,100,10);
        // Print the final count of battery replacements.
        System.out.println("the car's batteries need to be replaced: "+finalAnswer +"times.");
    }

}

