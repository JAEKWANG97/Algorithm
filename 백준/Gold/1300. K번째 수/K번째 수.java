import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long left = 1;
        long right = (long) N * N;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if (count < K) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}