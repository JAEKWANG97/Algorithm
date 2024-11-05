import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Location implements Comparable<Location> {
        int x;
        int y;
        int count;

        public Location(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Location o) {
            return this.count - o.count;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                int value = input.charAt(j - 1) - '0';
                map[i][j] = value;
            }
        }
    }

    private static void solve() {
        PriorityQueue<Location> queue = new PriorityQueue<>();
        queue.add(new Location(1, 1, 0));
        int[][] visited = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        visited[1][1] = 0;

        while (!queue.isEmpty()) {
            Location cur = queue.poll();

            if (cur.x == N && cur.y == M) {
                System.out.println(cur.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx > N || ny > M) {
                    continue;
                }

                if (visited[nx][ny] <= cur.count + map[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = cur.count + map[nx][ny];
                queue.add(new Location(nx, ny, cur.count + map[nx][ny]));
            }
        }
    }
}