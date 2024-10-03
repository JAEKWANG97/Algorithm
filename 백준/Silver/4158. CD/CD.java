import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int N, M;
    private static Set<Integer> cdSet;

    private static int answer;

    public static void main(String[] args) throws IOException {
        solve();
    }

    private static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N == 0 && M == 0) {
            System.out.println(sb);
            return;
        }

        answer = 0;
        cdSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            cdSet.add(Integer.parseInt(br.readLine()));
        }

        for (int i = 0; i < M; i++) {
            if (cdSet.contains(Integer.parseInt(br.readLine()))) {
                answer += 1;
            }
        }

        sb.append(answer).append("\n");

        solve();
    }
}