import java.io.*;
import java.util.*;

class Graph {
    private int V;
    private List<List<Integer>> adj;

    static int count = 0, time = 0;

    class Edge {
        int u;
        int v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    Graph(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    void BCCUtil(int u, int[] disc, int[] low, LinkedList<Edge> st, int[] parent) {
        disc[u] = low[u] = ++time;
        int children = 0;

        Iterator<Integer> it = adj.get(u).iterator();
        while (it.hasNext()) {
            int v = it.next();

            if (disc[v] == -1) {
                children++;
                parent[v] = u;

                st.add(new Edge(u, v));
                BCCUtil(v, disc, low, st, parent);

                if (low[u] > low[v])
                    low[u] = low[v];

                if ((disc[u] == 1 && children > 1) || (disc[u] > 1 && low[v] >= disc[u])) {
                    while (st.getLast().u != u || st.getLast().v != v) {
                        System.out.print(st.getLast().u + "--" + st.getLast().v + " ");
                        st.removeLast();
                    }
                    System.out.println(st.getLast().u + "--" + st.getLast().v + " ");
                    st.removeLast();

                    count++;
                }
            } else if (v != parent[u] && disc[v] < disc[u]) {
                if (low[u] > disc[v])
                    low[u] = disc[v];

                st.add(new Edge(u, v));
            }
        }
    }

    void BCC() {
        int[] disc = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];
        LinkedList<Edge> st = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            disc[i] = -1;
            low[i] = -1;
            parent[i] = -1;
        }

        for (int i = 0; i < V; i++) {
            if (disc[i] == -1)
                BCCUtil(i, disc, low, st, parent);

            int j = 0;
            while (st.size() > 0) {
                j = 1;
                System.out.print(st.getLast().u + "--" + st.getLast().v + " ");
                st.removeLast();
            }
            if (j == 1) {
                System.out.println();
                count++;
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(10);
        
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);

        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 3);

        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 6);

        g.addEdge(9, 9);

        g.BCC();

        System.out.println( g.count + " biconnected components in graph");
    }
}

