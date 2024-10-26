import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from;
        int to;
        int cost;
        Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int TC = Integer.parseInt(br.readLine());
        
        for(int tc=0; tc<TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            
            List<Edge> edges = new ArrayList<>();
            
            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }
            
            for(int i=0; i<W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, -T));
            }
            
            for(int i=1; i<=N; i++) {
                edges.add(new Edge(0, i, 0));
            }
            
            long[] dist = new long[N+1];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[0] = 0;
            
            boolean updated = false;
            

            for(int i=0; i<=N; i++) { 
                updated = false;
                for(Edge edge : edges) {
                    if(dist[edge.from] != Long.MAX_VALUE && dist[edge.to] > dist[edge.from] + edge.cost) {
                        dist[edge.to] = dist[edge.from] + edge.cost;
                        updated = true;
                        if(i == N) break;
                    }
                }
                if(i == N && updated) break;
                if(!updated) break;
            }
            
            if(updated) {
                bw.write("YES\n");
            }
            else {
                bw.write("NO\n");
            }
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}