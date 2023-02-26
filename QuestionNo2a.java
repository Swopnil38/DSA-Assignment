import java.util.*;

/*Algorithm Explaination
*The ListOfAncestors class provides a solution to the nearest ancestor problem, where for each node in a tree,
*  we want to find the nearest ancestor that has a value which is coprime with the node's value.
* The algorithm uses a depth-first search approach to traverse the tree and maintain a map of the most recent depth and
*  node value for each number seen so far. For each node, the algorithm iterates over all numbers between 1 and 50 to find a relative
*  prime ancestor and computes the distance to the nearest ancestor with that value. Finally, the algorithm removes the current node's
* value from the map and recursively visits all children of the current node. The resulting nearest ancestor for each node is returned as an array.
*  Overall, the algorithm has a time complexity of O(NlogN), where N is the number of nodes in the tree.
* */

class ListOfAncestors {
    // helper method to compute the greatest common divisor (GCD) of two numbers
    public int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    // depth-first search algorithm to traverse the tree and find nearest ancestors
    public void dfs(int[] nums, LinkedList<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer, int[]> map, boolean[][] poss) {
        // if node has already been visited, return immediately
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        int ancestor = -1;
        int d = Integer.MAX_VALUE;
        // iterate over all numbers between 1 and 50 (inclusive) to find a relative prime ancestor
        for (int i = 1; i < 51; i++) {
            // if number i is a relative prime of the current node's value, and we have already seen this number before
            if (poss[nums[node]][i] && map.containsKey(i)) {
                // compute the distance to the nearest ancestor with value i
                if (depth - map.get(i)[0] <= d) {
                    d = depth - map.get(i)[0];
                    ancestor = map.get(i)[1];
                }
            }
        }
        ans[node] = ancestor;
        // add the current node's value and depth to the map
        int[] exist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
        map.put(nums[node], new int[]{depth, node});
        // recursively visit all children of the current node
        for (int child : tree[node]) {
            if (visited[child]) {
                continue;
            }
            dfs(nums, tree, depth+1, child, visited, ans, map, poss);
        }
        // remove the current node's value from the map
        if (exist[0] != -1) {
            map.put(nums[node], exist);
        } else {
            map.remove(nums[node]);
        }
        return;
    }

    // main method to compute the nearest ancestors for each node
    public int[] getCoprimes(int[] nums, int[][] edges) {
        // create a boolean matrix to store which pairs of numbers are relatively prime
        boolean[][] poss = new boolean[51][51];
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (gcd(i, j) == 1) {
                    poss[i][j] = true;
                    poss[j][i] = true;
                }
            }
        }
        // create an adjacency list representation of the tree
        int n = nums.length;
        LinkedList<Integer>[] tree = new LinkedList[n];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        // create an array to store the nearest ancestor for each node
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = -1;
        // create a map to store the most recent depth and node value for each number seen so far
        Map<Integer,int[]> map = new HashMap<>();
// create a boolean array to keep track of which nodes have been visited
        boolean[] visited = new boolean[n];
// perform a depth-first search to find the nearest ancestor for each node
        dfs(nums, tree, 0, 0, visited, ans, map, poss);
        return ans;
    }
}
