import java.util.*;

class BFT {
    static void bfs(int[][] adj, int s, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        visited[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.print(curr + " ");

            for (int i = 0; i < adj.length; i++) {
                if (adj[curr][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    static void breadthFirstTraversal(int[][] adj) {
        int V = adj.length;
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                bfs(adj, i, visited);
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

        System.out.println("BFS Traversal:");
        breadthFirstTraversal(adj);

        sc.close();
    }
}
