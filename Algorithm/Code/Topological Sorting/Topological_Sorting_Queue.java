import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Topological_Sorting_Queue {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt(); // 정점의 수
		int E = sc.nextInt(); // 간선의 수, 방향이 있음

		int[][] adj = new int[V + 1][V + 1];	// 정점의 번호는 1부터 시작
		int[] degree = new int[V + 1];			// 진입차수

		for (int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();

			adj[A][B] = 1;	// 가중치가 없으므로 1로 표기, 유향이므로 adj[B][A]는 초기화하지 않는다.
			degree[B]++;	// 진입차수 증가
		}

		Queue<Integer> queue = new LinkedList<>();

		// queue로 위상정렬 구현 1단계
		// 진입차수가 0인 정점들을 queue에 추가
		for (int i = 1; i < V + 1; i++) // 정점들을 순회하면서
			if (degree[i] == 0) 		// 진입차수가 0이라면
				queue.offer(i);			// queue에 추가

		// queue로 위상정렬 구현 2단계
		// queue가 공백 상태가 될 때까지 돌린다.
		while (!queue.isEmpty()) {

			// 2-1 진입차수가 0인 정점을 하나 꺼냄
			int curr = queue.poll();

			// 2-2 꺼낸 정점과 연결되어 있는 간선을 제거
			for (int i = 1; i < V + 1; i++) {
				if (adj[curr][i] == 1)	// curr에서 i로 진입하는 간선이 존재한다면
					degree[i]--;		// 간선을 제거하므로 진입 차수를 1만큼 감소

				// 2-3 간선을 제거 후 진입 차수가 0이 되었다면 다음 순서로 queue에 추가
				if (degree[i] == 0)
					queue.offer(i);
			}
		}
	}
}
