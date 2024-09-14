import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static long[] arr;
    private static int ABLE_BUDGE;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(binarySearch());
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        st = new StringTokenizer(br.readLine());

        long totalBudget = 0; // 총 요청 예산 합계
        long maxBudget = 0;   // 요청 금액 중 최댓값

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            totalBudget += arr[i];
            if (arr[i] > maxBudget) {
                maxBudget = arr[i];
            }
        }
        Arrays.sort(arr);

        ABLE_BUDGE = Integer.parseInt(br.readLine());

        if (totalBudget <= ABLE_BUDGE) {
            System.out.println(maxBudget);
            System.exit(0); // 프로그램 종료
        }
    }

    private static long binarySearch() {
        long start = 0;
        long end = arr[N - 1];

        long result = 0;

        while (start <= end) {
            long mid = setMid(start, end);
            long sum = calSum(mid);

            if (sum <= ABLE_BUDGE) {
                result = mid; // 가능한 상한액 저장
                start = mid + 1; // 더 큰 상한액 시도
            } else {
                end = mid - 1; // 상한액 줄이기
            }
        }

        return result;
    }

    private static long calSum(long mid) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.min(arr[i], mid);
        }

        return sum;
    }

    private static long setMid(long start, long end) {
        return (start + end) / 2;
    }
}