import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] map;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 2][M + 2];
        answer = 0;

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }
    }

    private static void solve() {
        bfs();
    }

    private static void bfs() {
        Queue<Location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 2][M + 2];

        for (int j = 0; j < M + 2; j++) {
            queue.add(new Location(0, j));
            visited[0][j] = true;
            queue.add(new Location(N + 1, j));
            visited[N + 1][j] = true;
        }

        for (int i = 1; i <= N; i++) {
            queue.add(new Location(i, 0));
            visited[i][0] = true;
            queue.add(new Location(i, M + 1));
            visited[i][M + 1] = true;
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        while (!queue.isEmpty()) {
            Location cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= N + 2 || ny < 0 || ny >= M + 2 || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 1) {
                    answer++;
                } else {
                    queue.add(new Location(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }
}