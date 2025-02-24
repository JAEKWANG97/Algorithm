import java.io.*;
import java.util.*;

public class Main {

    static class Location {
        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static char[][] map;
    private static List<Location> teachers = new ArrayList<>();
    private static List<Location> emptySpaces = new ArrayList<>();
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        init();
        if (placeObstacles(0, 0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') {
                    teachers.add(new Location(i, j));
                } else if (map[i][j] == 'X') {
                    emptySpaces.add(new Location(i, j));
                }
            }
        }
    }

    private static boolean placeObstacles(int count, int index) {
        if (count == 3) {
            return isSafe();
        }

        for (int i = index; i < emptySpaces.size(); i++) {
            Location loc = emptySpaces.get(i);
            map[loc.x][loc.y] = 'O';
            if (placeObstacles(count + 1, i + 1)) {
                return true;
            }
            map[loc.x][loc.y] = 'X';
        }

        return false;
    }

    private static boolean isSafe() {
        for (Location teacher : teachers) {
            for (int d = 0; d < 4; d++) {
                int nx = teacher.x;
                int ny = teacher.y;

                while (true) {
                    nx += dx[d];
                    ny += dy[d];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                        break;
                    }
                    if (map[nx][ny] == 'O') {
                        break;
                    }
                    if (map[nx][ny] == 'S') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}