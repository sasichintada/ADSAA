import java.util.*;

public class BiconnectedComponents {
    private int time = 0;
    private List<int[]> edgeStack = new ArrayList<>();

    public static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    private void DFS(List<List<Integer>> adj, int u, int parent, int[] disc, int[] low, boolean[] visited) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                edgeStack.add(new int[]{u, v});
                DFS(adj, v, u, disc, low, visited);

                low[u] = Math.min(low[u], low[v]);

                if ((parent == -1 && children > 1) || (parent != -1 && low[v] >= disc[u])) {
                    printBCC(u, v);
                }
            } else if (v != parent && disc[v] < disc[u]) {
                low[u] = Math.min(low[u], disc[v]);
                edgeStack.add(new int[]{u, v});
            }
        }
    }

    private void printBCC(int u, int v) {
        System.out.print("Biconnected Component: ");
        while (!edgeStack.isEmpty()) {
            int[] edge = edgeStack.remove(edgeStack.size() - 1);
            System.out.print("[" + edge[0] + "-" + edge[1] + "] ");
            if (edge[0] == u && edge[1] == v) break;
        }
        System.out.println();
    }

    public void findBCC(List<List<Integer>> adj, int V) {
        int[] disc = new int[V], low = new int[V];
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFS(adj, i, -1, disc, low, visited);
                if (!edgeStack.isEmpty()) printBCC(-1, -1);
            }
        }
    }

    public static void main(String[] args) {
        int V = 10;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 4, 5);
        addEdge(adj, 5, 6);
        addEdge(adj, 6, 7);
        addEdge(adj, 4, 2);
        addEdge(adj, 5, 8);
        addEdge(adj, 5, 9);

        System.out.println("Biconnected Components:");
        new BiconnectedComponents().findBCC(adj, V);
    }
}
