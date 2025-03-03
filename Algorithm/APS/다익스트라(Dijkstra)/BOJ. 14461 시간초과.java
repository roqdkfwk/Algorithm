import java.util.*;
import java.io.*;
public class BOJ_14461_시간초과 {

    static int N, T;
    static int[][] farm;
    static long[][][] dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static long answer = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        solution();

        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        farm = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                farm[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[3][N][N];
        for (int r = 0; r < N; r++) {
            Arrays.fill(dp[0][r], -1);
            Arrays.fill(dp[1][r], -1);
            Arrays.fill(dp[2][r], -1);
        }
        dp[0][0][0] = 0;
    }

    private static void solution() {
        bfs(0, 0, 0, 0);
    }

    private static void bfs(int r, int c, int move, long time) {
        if (move == 0) {
            goNextFarm(r, c, move, time);
            return;
        }

        // 세 번 건넌 경우
        if (move % 3 == 0) {
            // 이전에 방문하지 않았던 위치인 경우
            if (dp[0][r][c] == -1) {
                goNextFarm(r, c, move, time + farm[r][c]);
                return;
            }
            // 이전에 방문했던 위치인 경우
            if (dp[0][r][c] > time + farm[r][c]) {
                goNextFarm(r, c, move, time + farm[r][c]);
            }
        }
        // 세 번 건너지 않은 경우
        else {
            if (dp[move % 3][r][c] == -1) {
                goNextFarm(r, c, move, time);
                return;
            }
            if (dp[move % 3][r][c] > time) {
                goNextFarm(r, c, move, time);
            }
        }
    }

    private static void goNextFarm(int r, int c, int move, long time) {
        dp[move % 3][r][c] = time;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (!isInside(nr, nc)) continue;

            bfs(nr, nc, move + 1, time + T);
        }
    }

    private static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void printResult() {
        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, dp[i][N - 1][N - 1]);
        }

        System.out.println(answer);
    }
}