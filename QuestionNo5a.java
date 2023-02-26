// Importing the required Java utility classes
import java.util.*;

/*The provided Java code implements an algorithm to find the skyline of a set of buildings given their height, width, and position.
The algorithm first transforms each building into two points (start and end) with their corresponding heights and stores them in a list.
Then, it sorts the list based on the x-coordinate of the points and their heights. It uses a priority queue to keep track of the current maximum height,
 and for each point, it updates the queue and checks if the current maximum height has changed. If it has, the algorithm adds the point to the result list.
  Overall, the time complexity of this algorithm is O(n log n), where n is the number of buildings.*/

// Defining a class named "QuestionNo5a"
class QuestionNo5a {

    // Defining a public method that takes a 2D array of integers as input and returns a List of Lists of integers
    public List<List<Integer>> getSkyline(int[][] buildings) {

        // Creating an empty ArrayList to store the result
        List<List<Integer>> res = new ArrayList<>();

        // Creating another ArrayList to store the heights of each building's starting and ending points
        List<int[]> heights = new ArrayList<>();

        // Transforming the input array of buildings into an ArrayList of building heights
        transformBuilding(buildings, heights);

        // Sorting the ArrayList of heights based on the starting and ending points of each building, with tiebreakers based on the heights of the buildings
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]); // Time complexity of this step is O(nlog n)

        // Creating a max priority queue to keep track of the maximum height of the buildings seen so far
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> (b - a));

        // Adding a 0 to the priority queue to seed it
        pq.add(0);
        int prevMax = 0;

        // Looping through each height in the ArrayList of heights
        for (int[] height : heights) { // Time complexity of this loop is O(n)

            // If the height represents the starting point of a building, add its height to the priority queue
            if (height[1] < 0) {
                pq.add(-height[1]);
            }
            // If the height represents the ending point of a building, remove its height from the priority queue
            else {
                pq.remove(height[1]); // Time complexity of this step is O(log n)
            }

            // Get the current maximum height from the priority queue
            int CurrentMax = pq.peek();

            // If the current maximum height is different from the previous maximum height, add a new sub-result to the final result
            if (CurrentMax != prevMax) {
                List<Integer> subResult = new ArrayList<>();
                subResult.add(height[0]);
                subResult.add(CurrentMax);

                res.add(subResult);
                prevMax = CurrentMax;
            }
        }

        // Return the final result
        return res;
    }

    // This method takes a 2D array of buildings and an empty ArrayList of heights, and transforms the buildings into an ArrayList of heights
    private void transformBuilding(int[][] buildings, List<int[]> heights) {
        for (int[] building : buildings) {
            heights.add(new int[]{building[0], -building[2]});
            heights.add(new int[]{building[1], building[2]});
        }
    }

    // The main method of the program
    public static void main(String[] args) {
        // Creating an example input array of buildings
        int[][] height = {{1, 4, 10}, {2, 5, 15}, {5, 8, 12}, {9, 11, 1}, {11, 13, 15}};

        // Creating an instance of the QuestionNo5a class
        QuestionNo5a solution = new QuestionNo5a();

        // Calling the getSkyline method of the class on the example input array and storing the result in a variable
        List<List<Integer>> ans = solution.getSkyline(height);

        // Printing the result to the console
        System.out.println(ans);
    }
}