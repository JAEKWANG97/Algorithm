import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {

        int v;
        Set<Integer> eSet;

        public Node(int v, Set<Integer> eSet) {
            this.v = v;
            this.eSet = eSet;
        }

        @Override
        public int compareTo(Node o) {
            return this.eSet.size() - o.eSet.size();
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static List<Node> nodeList;

    private static int K;
    private static int V, E;

    private static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            init();
            solve();
        }
        System.out.println(sb.toString());

    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        nodeList = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            nodeList.add(new Node(i, new HashSet<>()));
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            nodeList.get(v1).eSet.add(v2);
            nodeList.get(v2).eSet.add(v1);
        }
    }

    private static void solve() {
        int[] colors = new int[V + 1]; // 0 , 1 ,-1
        boolean flag = true;
        for (int i = 1; i < V + 1; i++) {
            if (colors[i] == 0) {
                flag = bfs(nodeList.get(i), colors, flag);
            }
        }
        sb.append(flag ? "YES" : "NO").append("\n");
    }

    private static boolean bfs(Node start, int[] colors, boolean flag) {

        Queue<Node> que = new ArrayDeque<>();
        que.add(start);
        colors[start.v] = 1;
        while (!que.isEmpty()) {
            Node cur = que.poll();

            for (Integer i : cur.eSet) {
                if (colors[i] == 0) {
                    colors[i] = colors[cur.v] == 1 ? -1 : 1;
                    que.add(nodeList.get(i));
                } else {
                    if (colors[i] == colors[cur.v]) {
                        return false;
                    }
                }
            }
        }
        return flag;
    }
}