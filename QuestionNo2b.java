// Importing required Java libraries
import java.util.LinkedList;
import java.util.Queue;

/*Algortithm Explaination
    The algorithm first constructs a binary tree from the given array of binary trees, where each node in the tree represents a city.
    Then, it uses a depth-first search (DFS) to traverse the tree and determines whether a node needs a service center or not.
    If a node needs a service center, the algorithm increments the count of service centers required and marks its parent node as covered.
    The DFS function returns a value of 0, 1, or 2, indicating whether the node is covered by a service center, needs a service center,
    or is already covered by its parent. Finally, the algorithm returns the count of service centers required to cover all cities in the tree.
* */


// TreeNode class represents a node in a binary tree
class TreeNode{
    TreeNode left; // Represents the left child of the node
    TreeNode right; // Represents the right child of the node
    int data; // Represents the data stored in the node
    // Constructor to initialize the TreeNode with given data
    TreeNode(int data){
        this.data=data;
        this.left=this.right=null;
    }

    // Default constructor
    TreeNode(){

    }

    // Function to add elements to the binary tree using level order traversal
    public TreeNode addToTree(Object[] input) {
        if (input == null || input.length == 0) {
            return null;
        }

        // Creating the root node of the binary tree
        TreeNode root = new TreeNode((int) input[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Adding nodes to the binary tree using level order traversal
        for (int i = 1; i < input.length; i += 2) {
            TreeNode curr = queue.poll();
            if (input[i] != null) {
                curr.left = new TreeNode((int) input[i]);
                queue.offer(curr.left);
            }
            if (i+1 < input.length && input[i+1] != null) {
                curr.right = new TreeNode((int) input[i+1]);
                queue.offer(curr.right);
            }
        }

        return root; // Return the root of the binary tree
    }
}

// ConstructionServiceCenter class provides the solution to the given problem
class ConstructionServiceCenter {
    int res = 0; // Counter to keep track of the number of service centers required

    // Function to find the minimum number of service centers required to provide service to all the cities
    public int minCameraCover(TreeNode root) {
        // If the root is not covered, increment the counter and return 1, else return 2
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    // Function to perform depth first search on the binary tree
    public int dfs(TreeNode root) {
        // If the root is null, return 2 (meaning it is already covered)
        if (root == null) return 2;

        // Find the coverage status of the left and right subtree
        int left = dfs(root.left), right = dfs(root.right);

        // If any of the subtrees is not covered, increment the counter and return 1
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }

        // If any of the subtrees is already covered, return 2, else return 0
        return left == 1 || right == 1 ? 2 : 0;
    }

    public static void main(String[] args) {
        // Creating an object array to represent the binary tree
        Object[] tree = {0, 0, null, 0, null, 0, null, null, 0};
        // Creating the binary tree using the object array
        TreeNode root = new TreeNode().addToTree(tree);
        // Finding the minimum number of service centers required to provide service to all the cities
        int ans = new ConstructionServiceCenter().minCameraCover(root);
        // Printing the answer
        System.out.println(ans);
    }
}