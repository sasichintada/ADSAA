import java.util.*;

class DFTBFT{

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
        int V = 6;
        int[][] adj = new int[V][V];

        adj[0][1] = 1; adj[1][0] = 1;
        adj[0][2] = 1; adj[2][0] = 1;
        adj[1][3] = 1; adj[3][1] = 1;
        adj[1][4] = 1; adj[4][1] = 1;
        adj[2][4] = 1; adj[4][2] = 1;
        adj[5][5] = 1;

        System.out.println("BFS Traversal:");
        breadthFirstTraversal(adj);
        System.out.println("\nDFS Traversal:");
        depthFirstTraversal(adj);
    }
}
