package BOJ_16928;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int answer;
    static boolean[] visited;
    static HashMap<Integer, Integer> hashmap;
    public Solution(HashMap<Integer, Integer> hashmap) {
        this.hashmap = hashmap;
        this.visited = new boolean[101];
        bfs();
    }

    // bfs(시작 지점, 움직인 횟수
    public void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= 6; i++) queue.add(new int[] {1, i});
        visited[1] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            answer++;
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int position = curr[0] + curr[1];

                if (position == 100) return;
                if (position > 100) continue;
                if (visited[position]) continue;

                visited[position] = true;

                if (hashmap.containsKey(position)) {
                    for (int j = 1; j <= 6; j++) queue.add(new int[] {hashmap.get(position), j});
                } else {
                    for (int j = 1; j <= 6; j++) queue.add(new int[] {position, j});
                }
            }
        }
    }

    public int getCount() {
        return answer;
    }
}
