import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 총 일 수
        int[] T = new int[N + 2]; // 상담 기간 (1일부터 시작하기 위해 크기를 N+2로 설정)
        int[] P = new int[N + 2]; // 상담 금액
        int[] dp = new int[N + 2]; // 최대 이익을 저장할 DP 배열

        // 상담 일정 입력
        for (int i = 1; i <= N; i++) {
            T[i] = scanner.nextInt();
            P[i] = scanner.nextInt();
        }

        // DP 계산
        for (int i = 1; i <= N + 1; i++) {
            // 이전까지의 최대 이익을 현재에도 유지
            dp[i] = Math.max(dp[i], dp[i - 1]);

            // 상담이 기간 내에 끝나는 경우
            if (i + T[i] - 1 <= N) {
                // 상담을 했을 때의 이익 갱신
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
        }

        // 최대 이익 찾기
        int maxProfit = 0;
        for (int i = 1; i <= N + 1; i++) {
            maxProfit = Math.max(maxProfit, dp[i]);
        }

        // 결과 출력
        System.out.println(maxProfit);
        scanner.close();
    }
}