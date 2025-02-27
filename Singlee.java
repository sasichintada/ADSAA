import java.util.*;

class Singlee {
    static final int INF = Integer.MAX_VALUE;

    static int minDistance(int[] dist, boolean[] sptSet, int n) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < n; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    static void shortest(int[][] cost, int s, int n) {
        int[] dist = new int[n];
        boolean[] sptSet = new boolean[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        for (int j = 0; j < n - 1; j++) {
            int u = minDistance(dist, sptSet, n);
            sptSet[u] = true;
            for (int v = 0; v < n; v++) {
                if (!sptSet[v] && cost[u][v] != INF && dist[u] != INF && dist[u] + cost[u][v] < dist[v]) {
                    dist[v] = dist[u] + cost[u][v];
                }
            }
        }
        System.out.println("vertex distance from source");
        for (int i = 0; i < n; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of vertices: ");
        int n = sc.nextInt();
        
        int[][] cost = new int[n][n];
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String input = sc.next();
                if (input.equals("INF")) {
                    cost[i][j] = INF;
                } else {
                    cost[i][j] = Integer.parseInt(input);
                }
            }
        }
        System.out.print("Enter the source vertex: ");
        int s = sc.nextInt();
        shortest(cost, s, n);
        
        sc.close();
    }
}

