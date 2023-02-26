// Import necessary classes
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// Declare a class named Huffman
class Huffman {
    // Declare a class named HuffmanNode which will be used to create Huffman Tree
    class HuffmanNode {
        int data; // frequency of the character
        char c; // character
        HuffmanNode left; // left child of the node
        HuffmanNode right; // right child of the node
    }

    // Method to print the Huffman code
    public static void printCode(HuffmanNode root, String s) {
        // If a leaf node is found (i.e. a character is found) then print the character and its code
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }
        // If a node is not a leaf node then recursively call printCode for its left and right child
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    // Method to encode the given characters using Huffman coding
    public HuffmanNode encode(char[] charArray, int[] charFreq) {
        int n = charArray.length; // Find the number of characters
        // Create a priority queue to store the Huffman nodes sorted by frequency of the characters
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new MyComparator());
        // Add the characters and their frequency to the priority queue as Huffman nodes
        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.data = charFreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }
        HuffmanNode root = null;
        // Create the Huffman tree by combining the two nodes with the lowest frequency at each iteration
        while (q.size() > 1) {
            HuffmanNode x = q.peek(); // Get the node with the lowest frequency
            q.poll(); // Remove the node from the priority queue
            HuffmanNode y = q.peek(); // Get the node with the second lowest frequency
            q.poll(); // Remove the node from the priority queue
            // Create a new node with the combined frequency and add it to the priority queue
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f; // Set the root of the Huffman tree to be the new node
            q.add(f); // Add the new node to the priority queue
        }
        // Print the Huffman code for each character in the Huffman tree
        printCode(root, "");
        return root; // Return the root of the Huffman tree
    }

    // Method to decode the given Huffman code using the Huffman tree
    public void decode(HuffmanNode root, String str) {
        ArrayList characters = new ArrayList(); // ArrayList to store the decoded characters
        ArrayList frequency = new ArrayList(); // ArrayList to store the frequency of the decoded characters
        int i = 0;
        // Iterate over the Huffman code string
        while (i < str.length()) {
            HuffmanNode current = root; // Set the current node to be the root of the Huffman tree
            // Traverse the Huffman tree based on the Huffman code
            while (current.c == '-') {
                if (str.charAt(i) == '0') { // If the Huffman code is 0, traverse the left child
                    current = current.left;
                    i++;
                    // Otherwise, traverse the right child
                } else {
                    current = current.right;
                    i++;
                }
            }
            char c = current.c; // Get the decoded character from the current node
            int f = current.data; // Get the frequency of the decoded character from the current node
            characters.add(current.c); // Add the decoded character to the ArrayList
            frequency.add(current.data); // Add the frequency of the decoded character to the ArrayList
        }
        printDecode(characters, frequency); // Call the printDecode method to print the decoded characters and their frequencies
    }

    // Method to print the decoded characters and their frequencies
    public static void printDecode(ArrayList characters, ArrayList frequencies) {
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(characters.get(i) + ":" + frequencies.get(i)); // Print the decoded character and its frequency
        }
    }

    // Comparator class to compare the frequency of the Huffman nodes
    class MyComparator implements Comparator<HuffmanNode> {
        public int compare(Huffman.HuffmanNode x, Huffman.HuffmanNode y) {
// Compare the frequency of the Huffman nodes
// Used to sort the characters in the sequence of their frequencies
            return x.data - y.data;
        }
    }

    // Driver code
    public static void main(String[] args) {
// Create the Huffman object
        Huffman h = new Huffman();
        // Create an array of characters and their frequencies
        char[] ch = {'A', 'B', 'C', 'D', 'E'};
        int[] fre = {4, 7, 3, 2, 7};

// Encode the characters using Huffman coding
        HuffmanNode hn = h.encode(ch, fre);

// Print the total number of bits used to encode the characters
        System.out.println(hn.data);

// Create a Huffman code string to decode
        String cha = "000100111011";

// Decode the Huffman code using the Huffman tree and print the decoded characters and their frequencies
        h.decode(hn, cha);
    }
}
