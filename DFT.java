import java.util.*;

class DFT {
    static void dfs(int[][] adj, int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i = 0; i < adj.length; i++) {
            if (adj[v][i] == 1 && !visited[i]) {
                dfs(adj, i, visited);
            }
        }
    }

    static void depthFirstTraversal(int[][] adj) {
        int V = adj.length;
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(adj, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        int[][] adj = new int[V][V];

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (format: u v for each edge):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u][v] = adj[v][u] = 1; // Since it's an undirected graph
        }

        System.out.println("DFS Traversal:");
        depthFirstTraversal(adj);

        sc.close();
    }
}
