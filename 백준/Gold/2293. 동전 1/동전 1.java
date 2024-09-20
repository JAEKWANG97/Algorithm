import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution(0, K));
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 동전 종류의 개수
        K = Integer.parseInt(st.nextToken());  // 목표 금액
        coins = new int[N];
        dp = new int[N][K + 1];  // dp[i][j]: i번째 동전까지 사용해서 j원을 만드는 경우의 수

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        // dp 배열을 -1로 초기화 (아직 계산되지 않은 값)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
    }

    // i 번째 동전부터 사용해서 남은 금액 remaining을 만드는 경우의 수
    private static int solution(int i, int remaining) {
        // 남은 금액이 0원이면 정확히 맞춘 경우이므로 경우의 수 1 반환
        if (remaining == 0) {
            return 1;
        }

        // 동전의 종류를 다 사용했거나 남은 금액이 음수이면 불가능하므로 0 반환
        if (i >= N || remaining < 0) {
            return 0;
        }

        // 이미 계산한 경우의 수가 있으면 그 값을 반환
        if (dp[i][remaining] != -1) {
            return dp[i][remaining];
        }

        // i 번째 동전을 사용하지 않는 경우와 사용하는 경우로 나누어 경우의 수 계산
        dp[i][remaining] = solution(i + 1, remaining)  // i 번째 동전을 사용하지 않는 경우
                         + solution(i, remaining - coins[i]);  // i 번째 동전을 사용하는 경우

        return dp[i][remaining]; // 계산된 값을 반환
    }
}