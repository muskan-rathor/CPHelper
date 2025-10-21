package cphelper;

import java.util.*;

public final class GraphUtils {
    private GraphUtils() {}

    // Build adjacency list for n nodes (0..n-1)
    public static List<Integer>[] buildUndirected(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        return g;
    }

    // BFS distances from src in unweighted graph
    public static int[] bfs(List<Integer>[] g, int src) {
        int n = g.length;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer>q = new ArrayDeque<>();
        q.add(src);
        dist[src] = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : g[u]) if (dist[v] == -1) {
                dist[v] = dist[u] + 1;
                q.add(v);
            }
        }
        return dist;
    }

    // Dijkstra for weighted graph: edges as list of (to, weight)
    public static long[] dijkstra(List<List<long[]>> g, int src) {
        int n = g.size();
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        dist[src] = 0;
        pq.add(new long[]{0, src});
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long d = top[0]; int u = (int)top[1];
            if (d != dist[u]) continue;
            for (long[] e : g.get(u)) {
                int v = (int)e[0]; long w = e[1];
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.add(new long[]{dist[v], v});
                }
            }
        }
        return dist;
    }
}
