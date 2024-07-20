# BOJ. 1167 트리의 지름

<aside>
🚨 **문제 출처**

[**BOJ. 1167 트리의 지름**](https://www.acmicpc.net/problem/1167)

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

- 트리의 지름 = 임의의 두 점 사이의 거리 중 **가장 먼 거리**
    
    → DFS
    
- 트리 자료구조의 특성 상 사이클이 없다.
- 지름의 양 끝에 위치한 노드는 연결되어 있는 노드가 한 개뿐이다.
    
    → 위의 특성을 이용해 연결되어 있는 **노드가 한 개뿐인 노드들을 따로 저장 후 해당 노드들에 대해서만 DFS를 시도**했으나 **시간초과** 발생. (2 ≤ V ≤ 100,000 제한시간 2초)
    
- 임의의 어떤 노드에서 시작하든 해당 노드에서 가장 먼 노드까지 DFS를 수행하면 **가장 먼 노드는 지름을 이루는 노드 중 한 개**이다.
    
    → 위의 특성을 이용해 **1번 노드에서 DFS를 수행 후, 1번 노드와 가장 멀리 떨어져 있는 노드(지름을 이루는 노드 중 한 개)에서 다시 DFS를 수행해 지름의 반대편 노드를 구하는 로직**으로 문제 해결
    
</aside>

<aside>
🚨 **실수했던 점**

- 먼저 정점 번호가 주어지는데, 정점 번호가 순서대로 주어진다는 말이 없었다.
</aside>

<aside>
⌨️ **코드**

</aside>

- 시간초과1
    - `DFS(int num)`
        
        
        ```java
        private static void DFS(int num) {
        
        	visit[num] = true;	// 시작 정점은 방문처리
        		
        	// 시간초과가 발생하는 부분
        	for (int i = 0; i < nodes[num].size(); i++) {			
        		if (!visit[nodes[num].get(i).end]) {
        				
        			dist += nodes[num].get(i).dist;	// 연결되어 있는 정점과의 거리만큼 더해주고
        			DFS(nodes[num].get(i).end);		// DFS 시행 후
        			dist -= nodes[num].get(i).dist;	// 연결되어 있는 정점과의 거리만큼 다시 빼줌
        		}
        	}
        		
        	if (maxDist < dist)
        		maxDist = dist;
        		
        	visit[num] = false;	// 시작 정점에 대한 DFS가 끝나면 false처리
        }	// DFS
        ```
        
    
    - 전체 코드
        
        ```java
        public class BOJ_1167_트리의_지름_시간초과 {
        	
        	static class Node {
        		
        		int end, dist;
        		
        		public Node(int end, int dist) {
        			this.end = end;
        			this.dist = dist;
        		}
        	}	// Node class
        
        	static int V;	// 정점의 개수
        	static List<Node>[] nodes;	// 정점을 담을 리스트
        	static Queue<Integer> endNodes;	// 간선이 1개인 Node들을 담을 Queue
        	static boolean[] visit;	// 방문처리
        	static long dist;
        	static long maxDist = 0;
        	
        	public static void main(String[] args) throws IOException {
        		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        		StringTokenizer st;
        		
        		st = new StringTokenizer(br.readLine());
        		V = Integer.parseInt(st.nextToken());
        		
        		nodes = new ArrayList[V + 1];
        		for (int i = 1; i < V + 1; i++)
        			nodes[i] = new ArrayList<>();
        		
        		endNodes = new LinkedList<>();
        		for (int i = 1; i < V + 1; i++) {
        			
        			st = new StringTokenizer(br.readLine());
        			int start = Integer.parseInt(st.nextToken());
        			while (true) {
        				
        				int end = Integer.parseInt(st.nextToken());
        				if (end == -1) 
        					break;
        				
        				int dist = Integer.parseInt(st.nextToken());
        				
        				nodes[i].add(new Node(end, dist));	// i번째 정점에 연결된 정점과 정점까지의 거리 저장
        			}	// while
        			
        			if (nodes[i].size() == 1)	// i번째 정점에 연결된 노드가 1개라면
        				endNodes.add(i);	// 해당 노드의 번호(i)를 Queue에 저장
        		}	// for
        		
        		int size = endNodes.size();
        		visit = new boolean[V + 1];
        		for (int i = 0; i < size; i++) {
        			
        			dist = 0;
        			DFS(endNodes.poll());
        		}
        		
        		System.out.println(maxDist);
        	}	// main
        
        	private static void DFS(int num) {
        
        		visit[num] = true;	// 시작 정점은 방문처리
        		
        		// 시간 초과가 발생하는 부분
        		for (int i = 0; i < nodes[num].size(); i++) {			
        			if (!visit[nodes[num].get(i).end]) {
        				
        				dist += nodes[num].get(i).dist;	// 연결되어 있는 정점과의 거리만큼 더해주고
        				DFS(nodes[num].get(i).end);		// DFS 시행 후
        				dist -= nodes[num].get(i).dist;	// 연결되어 있는 정점과의 거리만큼 다시 빼줌
        			}
        		}
        		
        		if (maxDist < dist)
        			maxDist = dist;
        		
        		visit[num] = false;	// 시작 정점에 대한 DFS가 끝나면 false처리
        	}	// DFS
        }
        ```
        
- 시간초과2
    - `DFS(int num)`
        
        ```java
        private static void DFS(int num) {
        		
        	visit[num] = true;
        
        	// 첫 번째 코드와 마찬가지로 시간초과가 발생하는 부분
        	for (int i = 0; i < nodes[num].size(); i++) {
        		if (!visit[nodes[num].get(i).end]) {
        				
        			dist += nodes[num].get(i).dist;
        			DFS(nodes[num].get(i).end);
        			dist -= nodes[num].get(i).dist;
        		}
        	}
        		
        	if (maxDist < dist) {
        		lastNode = num;
        		maxDist = dist;
        	}
        		
        	visit[num] = false;
        }	// DFS
        ```
        
    
    - 전체 코드
        
        ```java
        public class BOJ_1167_트리의_지름_시간초과2 {
        
        	static class Node {
        
        		int end, dist;
        
        		public Node(int end, int dist) {
        			this.end = end;
        			this.dist = dist;
        		}
        	} // Node class
        
        	static int V; // 정점의 개수
        	static List<Node>[] nodes; // 정점을 담을 리스트
        	static boolean[] visit; // 방문처리
        	static int lastNode;
        	static long dist;
        	static long maxDist = 0;
        
        	public static void main(String[] args) throws IOException {
        		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        		StringTokenizer st;
        
        		V = Integer.parseInt(br.readLine());
        
        		visit = new boolean[V + 1];
        		nodes = new ArrayList[V + 1];
        		for (int i = 1; i < V + 1; i++)
        			nodes[i] = new ArrayList<>();
        		
        		for (int i = 1; i < V + 1; i++) {
        
        			st = new StringTokenizer(br.readLine());
        			int start = Integer.parseInt(st.nextToken());
        			while (true) {
        
        				int end = Integer.parseInt(st.nextToken());
        				if (end == -1)
        					break;
        
        				int dist = Integer.parseInt(st.nextToken());
        
        				nodes[i].add(new Node(end, dist)); // i번째 정점에 연결된 정점과 정점까지의 거리 저장
        			} // while
        		} // for
        		
        		dist = 0;
        		DFS(1);
        		DFS(lastNode);
        		
        		System.out.println(maxDist);
        	} // main
        
        	private static void DFS(int num) {
        		
        		visit[num] = true;
        		
        		// 첫 번째 코드와 마찬가지로 시간초과가 발생하는 부분
        		for (int i = 0; i < nodes[num].size(); i++) {
        			if (!visit[nodes[num].get(i).end]) {
        				
        				dist += nodes[num].get(i).dist;
        				DFS(nodes[num].get(i).end);
        				dist -= nodes[num].get(i).dist;
        			}
        		}
        		
        		if (maxDist < dist) {
        			lastNode = num;
        			maxDist = dist;
        		}
        		
        		visit[num] = false;
        	}	// DFS
        }
        ```
        
- 정답 코드
    - `DFS(int num, int len)`
        
        ```java
        private static void DFS(int num, int 길이) {
        		
        	if (maxDist < 길이) {	// 현재 루트까지의 거리가 maxDist보다 멀다면
        			
        		maxDist = 길이;	// maxDist 갱신
        		lastNode = num;	// 끝 노드 번호 갱신
        	}
        		
        	visit[num] = true;
        		
        	for (int i = 0; i < nodes[num].size(); i++) {
        		if (!visit[nodes[num].get(i).end]) {	// 뒤로 돌아가는 탐색을 막기 위해 방문처리 체크
        				
        			DFS(nodes[num].get(i).end, 길이 + nodes[num].get(i).dist);
        		}
        	}	// for
        }	// DFS
        ```
        
    
    - 전체 코드
        
        ```java
        public class BOJ_1167_트리의_지름 {
        	static class Node {
        
        		int end, dist;
        
        		public Node(int end, int dist) {
        			this.end = end;
        			this.dist = dist;
        		}
        	} // Node class
        
        	static int V; // 정점의 개수
        	static List<Node>[] nodes; // 정점을 담을 리스트
        	static boolean[] visit; // 방문처리
        	static int lastNode;
        	static long maxDist = 0;
        
        	public static void main(String[] args) throws IOException {
        		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        		StringTokenizer st;
        
        		V = Integer.parseInt(br.readLine());
        
        		nodes = new ArrayList[V + 1];
        		for (int i = 1; i < V + 1; i++)
        			nodes[i] = new ArrayList<>();
        		
        		for (int i = 1; i < V + 1; i++) {
        
        			st = new StringTokenizer(br.readLine());
        			int start = Integer.parseInt(st.nextToken());
        			while (true) {
        
        				int end = Integer.parseInt(st.nextToken());
        				if (end == -1)
        					break;
        
        				int dist = Integer.parseInt(st.nextToken());
        
        				nodes[start].add(new Node(end, dist)); // i번째 정점에 연결된 정점과 정점까지의 거리 저장
        			} // while
        		} // for
        
        		visit = new boolean[V + 1];
        		DFS(1, 0);
        		
        		visit = new boolean[V + 1];
        		DFS(lastNode, 0);
        		
        		System.out.println(maxDist);
        	} // main
        
        	private static void DFS(int num, int 길이) {
        		
        		if (maxDist < 길이) {	// 현재 루트까지의 거리가 maxDist보다 멀다면
        			
        			maxDist = 길이;	// maxDist 갱신
        			lastNode = num;	// 끝 노드 번호 갱신
        		}
        		
        		visit[num] = true;
        		
        		for (int i = 0; i < nodes[num].size(); i++) {
        			if (!visit[nodes[num].get(i).end]) {	// 뒤로 돌아가는 탐색을 막기 위해 방문처리 체크
        				
        				DFS(nodes[num].get(i).end, 길이 + nodes[num].get(i).dist);
        			}
        		}	// for
        	}	// DFS
        }
        ```