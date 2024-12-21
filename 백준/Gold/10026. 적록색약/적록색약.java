import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] deltaX = new int[] { 0, 0, 1, -1 };
	static int[] deltaY = new int[] { 1, -1, 0, 0 };

	static int N;
	static char[][] nomalMap;
	static char[][] abnomalMap;

	static char[] colors = new char[] { 'R', 'B', 'G' };
	static char[] abnomalColors = new char[] { 'R', 'B' };

	static int count = 0;

	public static void main(String[] args) throws IOException {
		initVariables();
		for (int i = 0; i < colors.length; i++) {
			getColorAreaCount(colors[i] , nomalMap);
		}
		System.out.print(count + " ");
		count = 0;
		for (int i = 0; i < abnomalColors.length; i++) {
			getColorAreaCount(abnomalColors[i] , abnomalMap);
		}
		System.out.println(count);

	}

	private static void initVariables() throws IOException {
		N = Integer.parseInt(br.readLine());
		nomalMap = new char[N][N];
		abnomalMap = new char[N][N];
		initMap();
	}

	private static void initMap() throws IOException {
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				nomalMap[i][j] = input.charAt(j);
				abnomalMap[i][j] = input.charAt(j) == 'B' ? 'B' : 'R';
			}
		}
	}

	private static void getColorAreaCount(char color , char[][] map) {
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == color && !visited[i][j]) {
					bfs(i, j, color, visited, map);
					count += 1;
				}
			}
		}
	}

	private static void bfs(int x, int y, char color, boolean[][] visited, char[][] map) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] { x, y });

		visited[x][y] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curX = cur[0];
			int curY = cur[1];

			for (int i = 0; i < 4; i++) {
				int nx = curX + deltaX[i];
				int ny = curY + deltaY[i];
				if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == color) {
					visited[nx][ny] = true;
					queue.offer(new int[] { nx, ny });
				}
			}

		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}