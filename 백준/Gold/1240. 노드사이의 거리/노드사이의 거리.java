import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<int[]>[] adj;
    static int[][] parent;
    static int[] depth;
    static int[] dist;
    static int LOG;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        LOG = (int) (Math.log(N) / Math.log(2)) + 1;

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }

        parent = new int[N + 1][LOG];
        depth = new int[N + 1];
        dist = new int[N + 1];

        dfs(1, 0, 0, 0);
        preprocess();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(getDistance(u, v)).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int node, int par, int d, int cost) {
        depth[node] = d;
        parent[node][0] = par;
        dist[node] = cost;

        for (int[] edge : adj[node]) {
            int next = edge[0], weight = edge[1];
            if (next != par) {
                dfs(next, node, d + 1, cost + weight);
            }
        }
    }

    static void preprocess() {
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= N; i++) {
                if (parent[i][j - 1] != 0) {
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }
        }
    }

    static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = parent[u][i];
            }
        }

        if (u == v) {
            return u;
        }

        // LCA 찾기
        for (int i = LOG - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0];
    }

    static int getDistance(int u, int v) {
        int lca = lca(u, v);
        return dist[u] + dist[v] - 2 * dist[lca];
    }
}