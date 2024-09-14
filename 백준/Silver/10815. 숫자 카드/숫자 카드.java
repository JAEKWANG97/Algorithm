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
    private static Set<Integer> nSet;

    public static void main(String[] args) throws Exception {
        solve();
        System.out.println(sb);
    }

    private static void solve() throws Exception {
        nSet = new HashSet<>();
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nSet.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer((br.readLine()));
        for (int i = 0; i < M; i++) {
            int curNum = Integer.parseInt(st.nextToken());
            if (nSet.contains(curNum)) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }
    }


}