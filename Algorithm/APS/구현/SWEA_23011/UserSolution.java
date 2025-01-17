package SWEA_23011;

import java.util.*;

class UserSolution
{
    class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;

            Point p = (Point) o;
            return r == p.r && c == p.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    int R, C;
    int[][] map;
    Set<Point> scoreBlocks;
    Set<Point> removeBlocks;
    int[] drB = {-1, 1, 0, 0};
    int[] dcB = {0, 0, -1, 1};
    int[][] answer = new int[2][2]; // answer[플레이어가 얻은 점수][남은 블록의 개수]

    void printMap() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(map[R - 1 - r][c] + " ");
                if ((c + 1) % 5 == 0) {
                    System.out.print(" ");
                }
            }
            if ((r + 1) % 5 == 0) {
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }

    void init(int C, int R)
    {
        this.R = R;
        this.C = C;

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = 0;
            }
        }

        scoreBlocks = new HashSet<>();
        removeBlocks = new HashSet<>();

        // 점수 및 남은 블록 초기화
        answer = new int[2][2];
    }

    /**
     * mCol부터 블록을 떨어뜨린다(0-based)
     * 점수를 얻는 경우 얻은 점수를 반환한다.
     * 최대 10,000번 호출
     */
    int dropBlocks(int mPlayer, int mCol)
    {
        System.out.println("하이");
        System.out.println("DROP 시작");
        System.out.println("mPlayer : " + mPlayer + " mCol : " + mCol);
        printMap();
        int score = 0;

        // 남은 블록의 개수를 3개만큼 더한다.
        answer[mPlayer - 1][1] += 3;

        // 1. 블록을 떨어뜨린다.
        for (int i = 0; i < 3; i++) {
            int row = 0;
            while (map[row][mCol + i] != 0) row++;

            map[row][mCol + i] = mPlayer;
        }

        System.out.println("DROP 후");
        System.out.println("mPlayer : " + mPlayer + " mCol : " + mCol);
        printMap();

        do {
            // 2. 블록이 사라지면서 점수를 획득한다.
            score += removeBlock(mPlayer);
            // 3. 사라진 공간만큼 블록이 채운다.
        } while (gravity());
        System.out.println("DROP 끝");
        printMap();
        return score;
    }

    /**
     * 블록을 떨어뜨린다.
     * @return value : 떨어진 블록이 하나라도 있으면 true를 반환한다.
     */
    boolean gravity() {
        System.out.println("GRAVITY 전");
        printMap();
        boolean value = false;
        for (int c = 0; c < C; c++) {
            int floorIndex = 0;
            int row = 0;
            while (row < R && map[row][c] != 0) row++;

            floorIndex = row;

            while (row < R && map[row][c] == 0) row++;

            while (row < R && map[row][c] != 0) {
                map[floorIndex][c] = map[row][c];
                floorIndex++;
                row++;

                value = true;
            }

            for (int r = floorIndex; r < row; r++) {
                map[r][c] = 0;
            }
        }
        System.out.println("GRAVITY 후");
        printMap();
        return value;
    }

    int removeBlock(int player) {
        scoreBlocks.clear();
        removeBlocks.clear();

        System.out.println("REMOVE 전");
        printMap();
        scoreBlocks = new HashSet<>();
        removeBlocks = new HashSet<>();
        List<Point> block;
        int score;
        // 행 탐색
        for (int r = 0; r < R; r++) {
            block = new ArrayList<>();

            int c = 0;
            while (c < C) {
                int count = 0;
                // 빈 공간인 경우
                while (c < C && map[r][c] == 0) c++;

                // 해당 플레이어의 블록이 아닌 경우
                while (c < C && map[r][c] != player && map[r][c] > 0) {
                    block.add(new Point(r, c));
                    count++;
                    c++;
                }

                if (count >= 5) {
                    removeBlocks.addAll(block);
                 }

                block = new ArrayList<>();
                count = 0;

                // 해당 플레이어의 블록을 만난 경우
                while (c < C && map[r][c] == player) {
                    block.add(new Point(r, c));
                    count++;
                    c++;
                }

                // 5개 이상 연속하는 경우
                if (count >= 5) {
                    scoreBlocks.addAll(block);
                }
            }
        }

        // 열 탐색
        for (int c = 0; c < C; c++) {
            block = new ArrayList<>();

            int r = 0;
            while (r < R) {
                int count = 0;
                // 빈 공간인 경우
                while (r < R && map[r][c] == 0) {
                    r = R;
                }

                // 해당 플레이어의 블록이 아닌 경우
                while (r < R && map[r][c] != player && map[r][c] > 0) {
                    block.add(new Point(r, c));
                    count++;
                    r++;
                }

                if (count >= 5) {
                    removeBlocks.addAll(block);
                }

                block = new ArrayList<>();
                count = 0;

                // 해당 플레이어의 블록을 만난 경우
                while (r < R && map[r][c] == player) {
                    block.add(new Point(r, c));
                    count++;
                    r++;
                }

                // 5개 이상 연속하는 경우
                if (count >= 5) {
                    scoreBlocks.addAll(block);
                }
            }
        }

        // 대각 탐색
        // 1. 좌상 - 우하 탐색
        for (int c = 4; c < C; c++) {
            block = new ArrayList<>();

            int r = 0;
            int col = c;
            while (r < R && col >= 0) {
                int count = 0;
                // 빈 공간인 경우
                while (r < R && col >= 0 && map[r][col] == 0) {
                    r++;
                    col--;
                }

                // 해당 플레이어의 블록이 아닌 경우
                while (r < R && col >= 0 && map[r][col] != player && map[r][col] > 0) {
                    block.add(new Point(r, col));
                    count++;
                    r++;
                    col--;
                }

                if (count >= 5) {
                    removeBlocks.addAll(block);
                }

                block = new ArrayList<>();
                count = 0;

                while (r < R && col >= 0 && map[r][col] == player) {
                    block.add(new Point(r, col));
                    count++;
                    r++;
                    col--;
                }

                if (count >= 5) {
                    scoreBlocks.addAll(block);
                }
            }
        }
        for (int r = 1; r <= R - 5; r++) {
            block = new ArrayList<>();

            int row = r;
            int c = C - 1;
            while (row < R && c >= 0) {
                int count = 0;
                // 빈 공간인 경우
                while (row < R && c >= 0 && map[row][c] == 0) {
                    row++;
                    c--;
                }

                // 해당 플레이어의 블록이 아닌 경우
                while (row < R && c >= 0 && map[row][c] != player && map[row][c] > 0) {
                    block.add(new Point(row, c));
                    count++;
                    row++;
                    c--;
                }

                if (count >= 5) {
                    removeBlocks.addAll(block);
                }

                block = new ArrayList<>();
                count = 0;

                while (row < R && c >= 0 && map[row][c] == player) {
                    block.add(new Point(row, c));
                    count++;
                    row++;
                    c--;
                }
                if (count >= 5) {
                    scoreBlocks.addAll(block);
                }
            }
        }

        // 2. 좌하 - 우상 탐색
        for (int c = 0; c <= C - 5; c++) {
            block = new ArrayList<>();

            int r = 0;
            int col = c;
            while (r < R && col < C) {
                int count = 0;
                // 빈 공간인 경우
                while (r < R && col < C && map[r][col] == 0) {
                    r++;
                    col++;
                }

                // 해당 플레이어의 블록이 아닌 경우
                while (r < R && col < C && map[r][col] != player && map[r][col] > 0) {
                    block.add(new Point(r, col));
                    count++;
                    r++;
                    col++;
                }

                if (count >= 5) {
                    removeBlocks.addAll(block);
                }

                block = new ArrayList<>();
                count = 0;

                while (r < R && col < C && map[r][col] == player) {
                    block.add(new Point(r, col));
                    count++;
                    r++;
                    col++;
                }
                if (count >= 5) {
                    scoreBlocks.addAll(block);
                }
            }
        }
        for (int r = 1; r <= R - 5; r++) {
            block = new ArrayList<>();

            int row = r;
            int c = 0;
            while (row < R && c < C) {
                int count = 0;
                // 빈 공간인 경우
                while (row < R && c < C && map[row][c] == 0) {
                    row++;
                    c++;
                }

                // 해당 플레이어의 블록이 아닌 경우
                while (row < R && c < C && map[row][c] != player && map[row][c] > 0) {
                    block.add(new Point(row, c));
                    count++;
                    row++;
                    c++;
                }

                if (count >= 5) {
                    removeBlocks.addAll(block);
                }

                block = new ArrayList<>();
                count = 0;

                while (row < R && c < C && map[row][c] == player) {
                    block.add(new Point(row, c));
                    count++;
                    row++;
                    c++;
                }
                if (count >= 5) {
                    scoreBlocks.addAll(block);
                }
            }
        }

        score = scoreBlocks.size();
        answer[player - 1][0] += score; // 제거한 블록의 개수만큼 스코어를 더하고
        answer[player - 1][1] -= score; // 제거한 블록의 개수만큼 남은 블록의 개수를 뺀다.

        int otherP;
        if (player == 1) otherP = 2;
        else otherP = 1;
        answer[otherP - 1][1] -= removeBlocks.size();

        for (Point position : scoreBlocks) {
            map[position.r][position.c] = 0;
        }
        for (Point position : removeBlocks) {
            map[position.r][position.c] = 0;
        }
        System.out.println("REMOVE 후");
        printMap();
        System.out.println("없앤 블럭 수 score : " + score);
        return score;
    }

    /**
     * mCol과 연결되어 있는 상대 블록을 내 블록으로 변환시킨다.
     * 점수를 얻는 경우 얻은 점수를 반환한다.
     * 최대 1,000번 호출
     */
    int changeBlocks(int mPlayer, int mCol)
    {
        System.out.println("CHANGE 전");
        printMap();
        int otherP;
        if (mPlayer == 1) otherP = 2;
        else otherP = 1;

        // 시작부터 상대 블록이 아닌 경우 메서드를 종료한다.
        if (map[0][mCol] != otherP) return 0;

        // 상대 블록을 내 블록으로 바꾼다.
        map[0][mCol] = mPlayer;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, mCol});

        boolean[][] visited = new boolean[R][C];
        visited[0][mCol] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            answer[otherP - 1][1]--;
            answer[mPlayer - 1][1]++;

            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + drB[i];
                int nc = curr[1] + dcB[i];

                if (!isValid(nr, nc, otherP)) continue;
                if (visited[nr][nc]) continue;

                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
                map[nr][nc] = mPlayer;
            }
        }

        int value = 0;
        do {
            value += removeBlock(mPlayer);
        } while (gravity());
        System.out.println("CHANGE 후");
        printMap();
        return value;
    }

    boolean isValid(int r, int c, int p) {
        return r >= 0 && r < R && c >= 0 && c < C && map[r][c] == p;
    }

    /**
     * 최대 1,000번 호출
     */
    int getResult(int[] mBlockCnt)
    {
        mBlockCnt[0] = answer[0][1];
        mBlockCnt[1] = answer[1][1];

//        if (answer[0][0] > answer[1][0]) return 1;
//        else if (answer[0][0] < answer[1][0]) return 2;
//        return 0;
        if (answer[0][0] > answer[1][0]) {
            System.out.println("RESULT == 1");
            return 1;
        }
        else if (answer[0][0] < answer[1][0]) {
            System.out.println("RESULT == 2");
            return 2;
        }
        System.out.println("RESULT == 0");
        return 0;

    }
}
