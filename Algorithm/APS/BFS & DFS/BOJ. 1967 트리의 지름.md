# BOJ. 1967 트리의 지름

<aside>
🚨 **문제 출처**

**[BOJ. 1967 트리의 지름](https://www.acmicpc.net/problem/1967)**

</aside>

<aside>
📖 알고리즘 분류

- 그래프 이론
- 그래프 탐색
- 트리
- 깊이 우선 탐색
</aside>

<aside>
📖 **문제 아이디어**

- 간선에 가중치가 부여되어 있는 문제로 BFS보다는 DFS로 접근하는 것이 좋아보인다.
- 지름이 최대가 되는 경우는 무조건 리프 노드에서 시작하기 때문에 리프 노드만을 체크할 배열 `hasChi`을 만들었다.
- 지름이 최대가 되는 경우는 무조건 리프 노드에서 끝날 필요는 없다.
</aside>

<aside>
🚨 **실수했던 점**

- 시작은 무조건 리프 노드에서 시작하는 것이 맞지만 끝도 리프 노드일 필요는 없다.
- 시작과 끝을 모두 리프 노드라고 생각해서 틀렸었다.
</aside>

<aside>
⌨️ **코드**

</aside>

- `DFS(int num, int distance)`
    - 시작하는 리프 노드의 번호와 노드를 건널 때마다 더해지는 가중치를 두 개의 인자로 받는 `DFS()` 메소드
    
    ```java
    	private static void DFS(int num, int distance) {
    
    		// 루트 노드에 도착한 경우 || 리프 노드에 도착한 경우
    //		if (!hasChi[num] && visit[list[num].get(0).link]) {
    		boolean flag = true;
    		for (int i = 0; i < list[num].size(); i++) {
    			
    			if (!visit[list[num].get(i).link]) {
    				flag = false;
    			}
    		}
    		
    		if (flag) {
    			ans = Math.max(ans, distance);
    			return;
    		}
    		
    		visit[num] = true;
    		for (int i = 0; i < list[num].size(); i++) {
    			if (!visit[list[num].get(i).link]) {
    				
    				visit[list[num].get(i).link] = true;
    				DFS(list[num].get(i).link, distance + list[num].get(i).weight);
    			}
    		}
    	}	// DFS
    ```
    

- 틀린 코드
    
    ```jsx
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Stack;
    import java.util.StringTokenizer;
    
    public class Main {
    
    	static int N;
    	static List<Node>[] list;
    	static boolean[] visit;
    	static boolean[] hasChi;
    	static int ans = 0;
    	
    	static class Node {
    		
    		int link;
    		int weight;
    		Node (int link, int weight){
    			this.link = link;
    			this.weight = weight;
    		}
    	}	// Node class
    	
    	public static void main(String[] args) throws NumberFormatException, IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		N = Integer.parseInt(br.readLine());
    		list = new ArrayList[N + 1];
    		for (int i = 1; i < N + 1; i++)
    			list[i] = new ArrayList<>();
    		
    		hasChi = new boolean[N + 1];
    		for (int i = 1; i < N; i++) {
    			
    			st = new StringTokenizer(br.readLine());
    			int A = Integer.parseInt(st.nextToken());
    			int B = Integer.parseInt(st.nextToken());
    			int D = Integer.parseInt(st.nextToken());
    
    			hasChi[A] = true;
    			list[A].add(new Node(B, D));
    			list[B].add(new Node(A, D));
    		}	// 트리 정보 완성
    		
    		for (int i = 1; i < N + 1; i++) {
    			
    			visit = new boolean[N + 1];
    			if (!hasChi[i])	// 자식이 없는 리프 노드에서 DFS시작
    				DFS(i, 0);
    		}
    		
    		System.out.println(ans);
    	}	// main
    
    	private static void DFS(int num, int distance) {
    
    		// 루트 노드에 도착한 경우 || 리프 노드에 도착한 경우 >> 틀렸던 부분
    		// 반례
    		// 4
    		// 1 2 1
    		// 2 3 3
    		// 4 2 2
    		// 위와 같이 생긴 트리는 리프 노드가 한 개인 트리로 끝나는 부분이 리프 노드일 수 없다.
    		if (num == 1 || (!hasChi[num] && visit[list[num].get(0).link])) {
    			
    			ans = Math.max(ans, distance);
    			return;
    		}
    		
    		visit[num] = true;
    		for (int i = 0; i < list[num].size(); i++) {
    			if (!visit[list[num].get(i).link]) {
    				
    				visit[list[num].get(i).link] = true;
    				DFS(list[num].get(i).link, distance + list[num].get(i).weight);
    			}
    		}
    	}	// DFS
    }
    ```
    
- 전체 코드
    
    ```java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Stack;
    import java.util.StringTokenizer;
    
    public class BOJ_1967_트리의_지름 {
    
    	static int N;
    	static List<Node>[] list;
    	static boolean[] visit;
    	static boolean[] hasChi;
    	static int ans = 0;
    	
    	static class Node {
    		
    		int link;
    		int weight;
    		Node (int link, int weight){
    			this.link = link;
    			this.weight = weight;
    		}
    	}	// Node class
    	
    	public static void main(String[] args) throws NumberFormatException, IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		N = Integer.parseInt(br.readLine());
    		list = new ArrayList[N + 1];
    		for (int i = 1; i < N + 1; i++)
    			list[i] = new ArrayList<>();
    		
    		hasChi = new boolean[N + 1];
    		for (int i = 1; i < N; i++) {
    			
    			st = new StringTokenizer(br.readLine());
    			int A = Integer.parseInt(st.nextToken());
    			int B = Integer.parseInt(st.nextToken());
    			int D = Integer.parseInt(st.nextToken());
    
    			hasChi[A] = true;
    			list[A].add(new Node(B, D));
    			list[B].add(new Node(A, D));
    		}	// 트리 정보 완성
    		
    		for (int i = 1; i < N + 1; i++) {
    			
    			visit = new boolean[N + 1];
    			if (!hasChi[i])	// 자식이 없는 리프 노드에서 DFS시작
    				DFS(i, 0);
    		}
    		
    		System.out.println(ans);
    	}	// main
    
    	private static void DFS(int num, int distance) {
    
    		// 루트 노드에 도착한 경우 || 리프 노드에 도착한 경우
    //		if (!hasChi[num] && visit[list[num].get(0).link]) {
    		boolean flag = true;
    		for (int i = 0; i < list[num].size(); i++) {
    			
    			if (!visit[list[num].get(i).link]) {
    				flag = false;
    			}
    		}
    		
    		if (flag) {
    			ans = Math.max(ans, distance);
    			return;
    		}
    		
    		visit[num] = true;
    		for (int i = 0; i < list[num].size(); i++) {
    			if (!visit[list[num].get(i).link]) {
    				
    				visit[list[num].get(i).link] = true;
    				DFS(list[num].get(i).link, distance + list[num].get(i).weight);
    			}
    		}
    	}	// DFS
    }
    ```