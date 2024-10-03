import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int N, M;
    private static int[] arr;
    private static int answer;

    public static void main(String[] args) throws IOException {
        solve();
    }

    private static void solve() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (isEndCondition()) {
            System.out.println(sb);
            return;
        }

        answer = 0;
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            int curNum = Integer.parseInt(br.readLine());
            if (binarySearch(curNum)) {
                answer += 1;
            }
        }
        sb.append(answer).append("\n");
        solve();
    }

    private static boolean binarySearch(int curNum) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (curNum == arr[mid]) {
                return true;
            }
            if (curNum > arr[mid]) {
                left = mid + 1;
            } else if (curNum < arr[mid]) {
                right = mid - 1;
            }
        }
        return false;
    }


    private static boolean isEndCondition() {
        return (N == 0 && M == 0);
    }
}