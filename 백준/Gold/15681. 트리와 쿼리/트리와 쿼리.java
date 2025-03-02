import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static List<Integer>[] adj;
    static int[] subtreeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        subtreeSize = new int[N + 1];
        dfs(R, -1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(subtreeSize[u]).append("\n");
        }
        System.out.print(sb);
    }

    static int dfs(int node, int parent) {
        subtreeSize[node] = 1;

        for (int next : adj[node]) {
            if (next != parent) { 
                subtreeSize[node] += dfs(next, node);
            }
        }
        return subtreeSize[node];
    }
}