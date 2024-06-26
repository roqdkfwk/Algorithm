# BOJ. 1753 최단경로

<aside>
🚨 **문제 출처**

[**BOJ. 1753 최단경로**](https://www.acmicpc.net/problem/1753)

</aside>

<aside>
📖 알고리즘 분류

- 그래프 이론
- 다익스트라
- 최단 경로
</aside>

<aside>
📖 **문제 아이디어**

- 특별한 것 없는 무난한 다익스트라 알고리즘
</aside>

<aside>
❓ **생각해볼 것**

- PriorityQueue를 이용해 풀었으니 인접리스트를 이용해서도 풀어보자.
</aside>

<aside>
⌨️ **코드**

</aside>

- `Dijkstra(int start)`
    - 다익스트라 알고리즘에 따라 가중치가 작은 간선들을 찾는 메소드
    
    ```java
    private static void dijkstra(int start) {
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	
    	visit = new boolean[V + 1];
    	dist = new int[V + 1];
    	Arrays.fill(dist, INF);
    	dist[start] = 0;	// 시작 정점까지의 거리는 0
    	
    	pq.add(new Edge(start, start, 0));
    	
    	while (!pq.isEmpty()) {
    		Edge curr = pq.poll();
    		
    		if (visit[curr.to]) continue;	// 이미 방문했다면 비용을 알고 있다는 뜻
    		visit[curr.to] = true;	// 방문하지 않았다면 방문처리
    		
    		for (Edge edge : edges[curr.to]) {
    			if (!visit[edge.to] && dist[edge.to] > dist[edge.from] + edge.weight) {
    				dist[edge.to] = dist[edge.from] + edge.weight;
    				pq.add(new Edge(edge.from, edge.to, dist[edge.to]));
    			}
    		}
    	}	// while
    }	// dijkstra
    
    ```
    
- 전체 코드
    
    ```java
    public class BOJ_1753_최단경로 {
    	
    	static int V, E, K;	// 정점, 간선의 개수, 정점의 번호
    	static List<Edge> [] edges;	// Edge들을 저장할 List
    	static int[] dist; // 시작 노드부터의 거리
    	static boolean[] visit;	// 방문처리 할 배열
    	static final int INF = 987654321;
    	
    	static class Edge implements Comparable<Edge> {
    
    		int from, to, weight;
    		
    		public Edge(int from, int to, int weight) {
    			this.from = from;
    			this.to = to;
    			this.weight = weight;					
    		}
    		
    		@Override
    		public int compareTo(Edge o) {
    			return Integer.compare(this.weight, o.weight); 
    		}
    	}
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		V = Integer.parseInt(st.nextToken());
    		E = Integer.parseInt(st.nextToken());
    		K = Integer.parseInt(br.readLine());
    		
    		edges = new ArrayList[V + 1];
    		for (int i = 0; i < V + 1; i++)
    			edges[i] = new ArrayList<>();
    		
    		for (int i = 0; i < E; i++) {
    			
    			st = new StringTokenizer(br.readLine());
    			int A = Integer.parseInt(st.nextToken());
    			int B = Integer.parseInt(st.nextToken());
    			int W = Integer.parseInt(st.nextToken());
    			
    			// A에서 B로 가는 가중치 W를 갖는 간선의 정보를 추가
    			edges[A].add(new Edge(A, B, W));
    		}
    		
    		dijkstra(K);	// dijkstra 메소드
    		
    		for (int i = 1; i < V + 1; i++) {
    			
    			if (dist[i] == INF) System.out.println("INF");
    			else System.out.println(dist[i]);
    		}
    		
    	}	// main
    
    	private static void dijkstra(int start) {
    		PriorityQueue<Edge> pq = new PriorityQueue<>();
    		
    		visit = new boolean[V + 1];
    		dist = new int[V + 1];
    		Arrays.fill(dist, INF);
    		dist[start] = 0;	// 시작 정점까지의 거리는 0
    		
    		pq.add(new Edge(start, start, 0));
    		
    		while (!pq.isEmpty()) {
    			Edge curr = pq.poll();
    			
    			if (visit[curr.to]) continue;	// 이미 방문했다면 비용을 알고 있다는 뜻
    			visit[curr.to] = true;	// 방문하지 않았다면 방문처리
    			
    			for (Edge edge : edges[curr.to]) {
    				if (!visit[edge.to] && dist[edge.to] > dist[edge.from] + edge.weight) {
    					dist[edge.to] = dist[edge.from] + edge.weight;
    					pq.add(new Edge(edge.from, edge.to, dist[edge.to]));
    				}
    			}
    		}	// while
    	}	// dijkstra
    }
    
    ```
