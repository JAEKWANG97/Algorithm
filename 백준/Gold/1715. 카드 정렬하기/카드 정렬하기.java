import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        pq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        int answer = 0;

        if (n == 1) {
            System.out.println(0);
            return;
        }

        while (pq.size() > 2) {
            int cur = pq.poll();
            int next = pq.poll();
            answer += cur + next;
            pq.add(cur + next);
        }

        System.out.println(answer + pq.poll() + pq.poll());
    }
}