import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Info {
        int decreaseCount;
        int increaseCount;

        public Info(int decreaseCount, int increaseCount) {
            this.decreaseCount = decreaseCount;
            this.increaseCount = increaseCount;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[] arr;
    private static Info[] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new Info[N];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            dp[i] = new Info(1, 1);
        }

        dp[0] = new Info(1, 1);

        for (int i = 1; i < N; i++) {
            dp[i] = new Info(1, 1);
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i].increaseCount = Math.max(dp[i].increaseCount, dp[j].increaseCount + 1);
                } else if (arr[i] < arr[j]) {
                    dp[i].decreaseCount = Math.max(dp[i].decreaseCount,
                            Math.max(dp[j].decreaseCount, dp[j].increaseCount) + 1);
                }
            }
        }

        int maxCount = 0;
        for (int i = 0; i < N; i++) {
            maxCount = Math.max(maxCount, Math.max(dp[i].decreaseCount, dp[i].increaseCount));
        }

        System.out.println(maxCount);
    }
}