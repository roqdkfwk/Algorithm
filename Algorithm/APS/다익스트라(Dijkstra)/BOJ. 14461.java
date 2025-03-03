import java.util.*;
import java.io.*;
public class BOJ_14461 {

    static class State implements Comparable<State> {
        int r, c, move;
        long time;

        public State(int r, int c, int move, long time) {
            this.r = r;
            this.c = c;
            this.move = move;
            this.time = time;
        }

        @Override
        public int compareTo(State state) {
            return Long.compare(this.time, state.time);
        }
    }

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
        for (int d = 0; d < 3; d++) {
            for (int r = 0; r < N; r++) {
                Arrays.fill(dp[d][r], Long.MAX_VALUE);
            }
        }
        dp[0][0][0] = 0;
    }

    private static void solution() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(0, 0, 0, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int r = curr.r;
            int c = curr.c;
            int move = curr.move;
            long time = curr.time;

            if (dp[move % 3][r][c] < time) continue;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (!isInside(nr, nc)) continue;

                int nextMove = move + 1;
                long nextTime = time + T;
                // 목초지에서 풀을 먹는다.
                if (nextMove % 3 == 0) {
                    nextTime += farm[nr][nc];
                }

                nextMove %= 3;
                if (dp[nextMove][nr][nc] > nextTime) {
                    dp[nextMove][nr][nc] = nextTime;
                    pq.add(new State(nr, nc, nextMove, nextTime));
                }
            }
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