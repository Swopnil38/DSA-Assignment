
import java.util.ArrayList;
import java.util.List;

/*Algorithm explaination
-> First we Create a Graph of Given Edges
-> Then We call a function to retreive list of disconnected graph
        Firstly, we Remove the Failure From the Network Graph
        This now has automatically separated the Graph into 2 parts if it is not connected further
        so, we do DFS Search Later to determine if they are connected or not.
        we start from 1 first and find it subgraph. and mark all them as visited to be true. so,
        Later we create such subgraph but only add those subgrap to main subgraphs that does not have 0 i.e. the main
        entry point of network and Broke Node.
*/


class Graph {
    // A class to represent a graph.
    // Size of Matrix will be V (number of vertices in graph) * V
    int V;
    //Matrix to define Graph
    int[][] adjMatrix;
    //Broken Node
    int brokenNode;

    // constructor
    Graph(int V,int[][] edges, int brokenNode) {
        //defines the vertices
        this.V = V;
        // define the size of array as the number of vertices
        this.adjMatrix = new int[V][V];
        //looped over the elements of edges and populate it to Graph
        for (int[] edge : edges) {
            // Call AddEdge Function to Populate
            addEdge(edge[0], edge[1]);
        }
        //defines the brokenNode
        this.brokenNode = brokenNode;

    }

    // Adds an edge to an undirected graph
    void addEdge(int src, int dest) {
        // Add an edge from src to dest.
        adjMatrix[src][dest] = 1;
        // Since Network graph is undirected, add an edge from dest to src also
        adjMatrix[dest][src] = 1;
    }

    // Removes a vertex and all edges connected to it
    void removeVertex() {
        // Remove the vertex from the matrix
        for (int i = 0; i < V; i++) {
            adjMatrix[i][brokenNode] = 0;
            adjMatrix[brokenNode][i] = 0;
        }
    }

    // Returns a list of all disconnected Node
    List<Integer> getDisconnectedSubgraphs() {
        //Call removeVertex function to remove the broken Node from Graph
        removeVertex();
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];
        //Defined a List of Integer as subgraphs that  returns disconnected node
        List<Integer> subgraphs = new ArrayList<>();

        // Find all subgraphs
        for (int v = 0; v < V; ++v) {
            //Checks if Vertext is Visited or is broken or not
            //If Visited, it is not necssary to visit again and also if it is broken then it automatically separates graphs
            if (!visited[v] && v != brokenNode) {
                // Defined a subgraph for each as there might be multiple small disconnected due to one breakage point
                List<Integer> subgraph = new ArrayList<>();
                // Print all reachable vertices from v
                DFSUtil(v, visited, subgraph);
                // if the Subgraph is the breakpoint or if the subgraph has source of network then
                //it is not disconnected from network
                if (!subgraph.contains(brokenNode) && !subgraph.contains(0)) {
                    //If above condition satisfied then all all small part of subgraph to subgraphs
                    subgraphs.addAll(subgraph);
                }
            }
        }
        //Finally returned subgraphs
        return subgraphs;
    }

    void DFSUtil(int v, boolean[] visited, List<Integer> subgraph) {
        // Mark the current node as visited and add it to the subgraph
        visited[v] = true;
        subgraph.add(v);
        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < V; ++i) {
            if (adjMatrix[v][i] == 1 && !visited[i]) {
                DFSUtil(i, visited, subgraph);
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        //Edges OF the Graph
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        //broken Node
        int brokenNode = 4;
        // Create a graph
        Graph g = new Graph(8,edges,brokenNode);

        System.out.println("Interuppted Networks:");
        //Get Disconencted Networks
        List<Integer> subgraphs = g.getDisconnectedSubgraphs();
        System.out.println(subgraphs);

    }
}
