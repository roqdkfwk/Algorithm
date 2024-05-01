# 프림(Prim) 알고리즘

- **하나의 정점에서 연결된 간선들 중에 하나씩 선택하면서 MST를 만들어 가는 방식**
    - **임의의 정점**을 선택하여 시작
    - 선택한 정점과 인접하는 정점들 중 최소 비용의 간선이 존재하는 정점을 선택
    - 모든 정점이 선택될 때 까지 바로 위의 과정을 반복

- 서로소인 2개의 집합 정보를 유지
    - 트리 정점 : MST를 만들기 위해 선택된 정점들
    - 비트리 정점들 : 선택 되지 않은 정점들

- **프림 알고리즘(반복문)**
    
    ```java
    import java.util.Arrays;
    import java.util.Scanner;
    
    public class 프림_반복문 {
    
    	static final int INF = Integer.MAX_VALUE;	// 큰 값으로 초기화
    	
    	public static void main(String[] args) {
    		Scanner sc = new Scanner(input);
    	
    		int V = sc.nextInt();	// 0부터 시작
    		int E = sc.nextInt();	
    		
    		// 인접행렬
    		int[][] adjArr = new int[V][V];
    		
    		for (int i = 0; i < E; i++) {
    			int A = sc.nextInt();
    			int B = sc.nextInt();
    			int W = sc.nextInt();
    			// 무향그래프
    			adjArr[A][B] = adjArr[B][A] = W;
    		}
    		
    		// 방문처리를 위해 배열선언
    		boolean[] visit = new boolean[V];
    		int[] p = new int[V];	// 어디에서 왔는지
    		int[] dist = new int[V];	// key라고 했던 가장 작은 비용을 저장하기 위한 배열
    		
    		// dist초기화 방법 1
    		for (int i = 0; i < V; i++) {
    			dist[i] = INF;
    			p[i] = -1;
    		}
    		
    		Arrays.fill(dist, INF);	// dist배열을 INF로 초기화 방법 2
    		
    		// 임의의 한 점을 선택해서 돌림
    		dist[0] = 0;	// 0번 정점부터 시작하겠다는 의미
    		
    		int ans = 0;
    		for (int i = 0; i < V - 1; i++) {
    			
    			int min = INF;
    			int idx = -1;
    			
    			// 아직 안뽑힌 정점 중 가장 작은 값을 뽑음
    			for (int j = 0; j < V; j++) {
    				
    				if (!visit[j] && dist[j] < min) { 
    					min = dist[j];
    					idx = j;
    				}
    			}	// for문 종료 시 idx : 가장 작은 값을 가지고 있고 방문하지 않은 정점이 선택 된다.
    			
    			visit[idx] = true;
    			
    			// 뽑은 정점과 인접한 정점들 중 갱신할 수 있으면 갱신
    			for (int j = 0; j < V; j++) {
    				
    				if (!visit[j] && adjArr[idx][j] != 0 && dist[j] > adjArr[idx][j]) {
    					dist[j] = adjArr[idx][j];
    					p[j] = idx;
    				}
    			}
    		}	// 정점을 선택하는 사이클
    		
    		for (int i = 0; i < V; i++) {
    			ans += dist[i];
    		}
    		System.out.println(Arrays.toString(dist));
    		System.out.println(Arrays.toString(p));
    		System.out.println(ans);
    	}
    	
    	static String input ="7 11\r\n"
    			+ "0 1 32\r\n"
    			+ "0 2 31\r\n"
    			+ "0 5 60\r\n"
    			+ "0 6 51\r\n"
    			+ "1 2 21\r\n"
    			+ "2 4 46\r\n"
    			+ "2 6 25\r\n"
    			+ "3 4 34\r\n"
    			+ "3 5 18\r\n"
    			+ "4 5 40\r\n"
    			+ "4 6 51\r\n"
    			+ "";
    }
    ```
    
- **프림 알고리즘(우선순위 큐)**
    
    ```java
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.PriorityQueue;
    import java.util.Scanner;
    
    public class 프림_우선순위큐 {
    
    	static final int INF = Integer.MAX_VALUE;	// 큰 값으로 초기화
    	
    	static class Edge implements Comparable<Edge> {
    		int st, ed, w;
    		
    		public Edge(int st, int ed, int w) {
    			this.st = st;
    			this.ed = ed;
    			this.w = w;
    		}
    
    		@Override
    		public int compareTo(Edge o) {
    			return Integer.compare(this.w, o.w);
    		}
    	}
    	
    	public static void main(String[] args) {
    		Scanner sc = new Scanner(input);
    	
    		int V = sc.nextInt();	// 0부터 시작
    		int E = sc.nextInt();	
    		
    		// 인접리스트
    		List<Edge>[] adjList = new ArrayList[V];
    		
    		for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
    		
    		for (int i = 0; i < E; i++) {
    			int A = sc.nextInt();
    			int B = sc.nextInt();
    			int W = sc.nextInt();
    			// 무향그래프
    			adjList[A].add(new Edge(A, B, W));
    			adjList[B].add(new Edge(B, A, W));
    		}
    		
    		// 방문처리를 위한 배열
    		boolean[] visit = new boolean[V];
    		
    		PriorityQueue<Edge> pq = new PriorityQueue<>();
    		
    		visit[0] = true;	// 0번 정점은 시작정점
    		
    		// 0번 정점과 인접한 정점을 전부 넣음
    //		for (int i = 0; i < adjList[0].size(); i++) {
    //			pq.add(adjList[0].get(i));
    //		}		
    //		for (Edge e : adjList[0]) {
    //			pq.add(e);
    //		}
    		
    		pq.addAll(adjList[0]);
    		
    		int pick = 1;	// 현재 확보한 정점의 개수
    		int ans = 0;	// 비용
    		
    		while (pick != V) {
    			
    			Edge e = pq.poll();
    			// 2번 정점은 st와 ed 중 ed에 들어있다.
    			if (visit[e.ed]) continue;	// Edge e에 들어있는 도착점 ed가 이미 방문한 점인 경우
    			
    			ans += e.w;	// 방문한 점이 아니라면 해당 간선이 가지고 있는 가중치를 더한다.
    			visit[e.ed] = true;
    			pick++;
    			
    			// 반복문을 돌면서 갱신할 수 있는 것 전부 갱신
    			pq.addAll(adjList[e.ed]);
    		}
    		
    		System.out.println(ans);
    	}
    	
    	static String input ="7 11\r\n"
    			+ "0 1 32\r\n"
    			+ "0 2 31\r\n"
    			+ "0 5 60\r\n"
    			+ "0 6 51\r\n"
    			+ "1 2 21\r\n"
    			+ "2 4 46\r\n"
    			+ "2 6 25\r\n"
    			+ "3 4 34\r\n"
    			+ "3 5 18\r\n"
    			+ "4 5 40\r\n"
    			+ "4 6 51\r\n"
    			+ "";
    }
    ```