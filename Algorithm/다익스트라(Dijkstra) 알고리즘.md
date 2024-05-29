# 다익스트라(Dijkstra) 알고리즘

- **최단 경로정의**
    - 가중치가 있는 그래프에서 두 정점 사이의 경로들 중 간선의 가중치의 합이 최소인 경로

- **하나의 시작 정점에서 끝 정점까지의 최단 경로**
    - 다익스트라(Dijkstra) 알고리즘 (음의 가중치 허용 X)
    - ~~벨만-포드(Bellman-Ford) 알고리즘 (음의 가중치 허용O)~~

- **모든 정점들에 대한 최단 경로**
    - 플로이드-워셜(Floyd-Warshall) 알고리즘

- **다익스트라 알고리즘**
    - **시작 정점에서 거리가 최소인 정점을 선택**해 나가면서 최단 경로를 구하는 방식
    - 탐욕 알고리즘 중 하나이고, 프림 알고리즘과 유사
    - 정점 A에서 정점 B까지의 최단 경로 (A → X → B)

- **다익스트라 알고리즘 동작 과정**
    - 시작 정점 입력
    - 거리 저장 배열을 INF로 초기화
    - 시작점에서 갈 수 있는 곳의 값 갱신
    - 방문하지 않은 노드 중에서 가장 비용이 적은 노드를 선택
    - 방문하지 않은 점들이 가지고 있는 거리 값과 현재 정점에서 방문하지 않은 
    정점까지의 가중치의 합이 작다면 갱신
    - 모든 정점을 방문할 때까지 반복

- 다익스트라 알고리즘(반복문)
    
    ```java
    public class 다익스트라_반복문 {
    
    	static class Node {
    		
    		int v, w;
    		
    		public Node (int v, int w) {
    			this.v = v;
    			this.w = w;
    		}
    	}
    	
    	static final int INF = Integer.MAX_VALUE;
    	static int V, E;
    	static List<Node>[] adjList;	// 인접리스트
    	static int[] dist;	// 거리 배열
    	
    	public static void main(String[] args) {
    		Scanner sc = new Scanner(System.in);
    		
    		int V = sc.nextInt();	// 정점의 개수
    		int E = sc.nextInt();	// 간선의 개수
    		
    		adjList = new ArrayList[V];
    		for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
    		
    		dist = new int[V];
    		Arrays.fill(dist, INF);
    		
    		for (int i = 0; i < E; i++) 
    			// 시작정점 - 도착정점 - 가중치 순으로 입력
    			adjList[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt()));
    		
    		Dijkstra(0);
    		
    		System.out.println(Arrays.toString(dist));
    	}
    
    	private static void Dijkstra(int start) {
    		
    		boolean[] visit = new boolean[V];	// 방문처리
    		dist[start] = 0;	// 시작 노드까지의 거리는 0으로 초기화
    		
    		for (int i = 0; i < V - 1; i++) {
    			
    			int min = INF;
    			int idx = -1;
    			
    			for (int j = 0; j < V; j++) {
    				
    				if (!visit[j] && min > dist[j]) {
    					min = dist[j];
    					idx = j;
    				}
    			}	// idx : 방문하지 않았으면서, 시작 정점으로부터 
    			  // 해당 idx 정점까지의 거리가 최소인 정점의 번호 
    			
    			visit[idx] = true;
    			
    			for (Node node : adjList[idx]) {				
    				if (!visit[node.v] && dist[node.v] > dist[idx] + node.w) 
    					dist[node.v] = dist[idx] + node.w;
    			}
    		}		
    	}	// Dijkstra
    }
    ```
    
- 다익스트라 알고리즘(우선순위 큐)
    
    ```java
    static class Node implements Comparable<Node>{
    		
    		int v, w;
    		
    		public Node (int v, int w) {
    			this.v = v;
    			this.w = w;
    		}
    
    		@Override
    		public int compareTo(Node o) {
    			return Integer.compare(this.w, o.w);
    		}
    	}
    	
    	static final int INF = Integer.MAX_VALUE;
    	static int V, E;
    	static List<Node>[] adjList;	// 인접리스트
    	static int[] dist;	// 거리 배열
    	
    	public static void main(String[] args) {
    		Scanner sc = new Scanner(System.in);
    		
    		int V = sc.nextInt();	// 정점의 개수
    		int E = sc.nextInt();	// 간선의 개수
    		
    		adjList = new ArrayList[V];
    		for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
    		
    		dist = new int[V];
    		Arrays.fill(dist, INF);
    		
    		for (int i = 0; i < E; i++) 
    			// 시작정점 - 도착정점 - 가중치 순으로 입력
    			adjList[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt()));
    		
    		Dijkstra(0);
    		
    		System.out.println(Arrays.toString(dist));
    	}
    
    	private static void Dijkstra(int start) {
    		
    		PriorityQueue<Node> pq = new PriorityQueue<>();		
    		boolean[] visit = new boolean[V];	// 방문처리
    		dist[start] = 0;	// 시작 노드까지의 거리는 0으로 초기화
    		
    		pq.add(new Node(start, 0));
    		
    		while (!pq.isEmpty()) {
    			
    			Node curr = pq.poll();
    			
    			if (visit[curr.v]) continue;	// 이미 방문했다면 비용을 알고 있다는 뜻
    			visit[curr.v] = true;	// 아니라면 선택
    			
    			for (Node node : adjList[curr.v]) {
    				
    				if (!visit[node.v] && dist[node.v] > dist[curr.v] + node.w) {
    					dist[node.v] = dist[curr.v] + node.w;
    					pq.add(new Node(node.v, dist[node.v]));
    				}
    			} 
    		}
    	}	// Dijkstra
    ```
    
- 다익스트라 알고리즘 문제
    - [BOJ. 1916 최소 비용 구하기](https://www.notion.so/BOJ-1916-1f5aeed4fd1540daad404bd4db5a2dc4?pvs=21)
    - [BOJ. 4485 녹색 옷 입은 애가 젤다지?](https://www.notion.so/BOJ-4485-247235e0352746e28c944e694714f6d8?pvs=21)
    - [BOJ. 1504 특정한 최단 경로](https://www.notion.so/BOJ-1504-a7825661ed794614a1e917bf78ac3467?pvs=21)
    - [BOJ. 1753 최단경로](https://www.notion.so/BOJ-1753-9fafa2d63fe2436599cd0c017edbcf41?pvs=21)