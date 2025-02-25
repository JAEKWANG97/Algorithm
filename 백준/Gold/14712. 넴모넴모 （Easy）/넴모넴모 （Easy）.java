import java.util.Scanner;

public class Main {
    static int N, M;
    static boolean[][] board;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        board = new boolean[N][M];

        dfs(0, 0);
        System.out.println(count);
    }

    static void dfs(int r, int c) {
        if (r == N) {
            count++;
            return;
        }

        int nextR = (c == M - 1) ? r + 1 : r;
        int nextC = (c == M - 1) ? 0 : c + 1;

        dfs(nextR, nextC);

        if (isValid(r, c)) {
            board[r][c] = true;
            dfs(nextR, nextC);
            board[r][c] = false;
        }
    }
    
    static boolean isValid(int r, int c) {
        if (r >= 1 && c >= 1) {
            if (board[r - 1][c] && board[r][c - 1] && board[r - 1][c - 1]) {
                return false;
            }
        }
        return true;
    }
}