import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Topological_Sorting_Stack {
	
	static int V, E;
	static int[][] adj;
	static int[] degree;
	static boolean[] visit;
	static Stack<Integer> stack;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt(); // 정점의 수
		E = sc.nextInt(); // 간선의 수, 방향이 있음

		adj = new int[V + 1][V + 1];	// 정점의 번호는 1부터 시작
		degree = new int[V + 1];			// 진입차수
		visit = new boolean[V + 1];	// 방문체크
		stack = new Stack<>();
		
		for (int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			adj[A][B] = 1;	// 가중치가 없으므로 1로 표기, 유향이므로 adj[B][A]는 초기화하지 않는다.
			degree[B]++;	// 진입차수 증가
		}
		
		for (int i = 1; i < V + 1; i++)
			if (degree[i] == 0)
				DFS(i);
	}

	private static void DFS(int v) {
		
		visit[v] = true;
		
		// 인접하고 방문하지 않은 점이 있다면 방문
		for (int i = 1; i < V + 1; i++)
			if (adj[v][i] != 0 && !visit[i])
				DFS(i);
		
		// for문을 빠져나온다는 것은 방문할 수 있는 정점을 모두 방문했다는 의미
		stack.add(v);
	}
}
