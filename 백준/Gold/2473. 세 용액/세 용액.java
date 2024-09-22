import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static long[] list;

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        list = new long[N];
        for (int i = 0; i < N; i++) {
            list[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(list);
    }

    private static void solution() {
        long min = Long.MAX_VALUE;
        long[] answer = new long[3];

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = list[i] + list[left] + list[right];

                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    answer[0] = list[i];
                    answer[1] = list[left];
                    answer[2] = list[right];
                }

                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        Arrays.sort(answer);
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}