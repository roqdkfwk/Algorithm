# 등산 코스 정하기

<aside>
🚨 **문제 출처**

[**프로그래머스. 등산 코스 정하기**](https://school.programmers.co.kr/learn/courses/30/lessons/118669)

</aside>

<aside>
📖 풀이 알고리즘

- **다익스트라(Dijkstra)**
</aside>

<aside>
📖 **문제 아이디어**

- 출입구에서 산봉우리를 찍고 다시 출입구로 돌아오는 경로 중 최소 `intensity`를 구하는 문제
- 출입구에서 산봉우리를 향하는 경로가 최소 `intensity`를 포함한다면 돌아오는 경로는 똑같이 돌아오면 된다.
    
    **→ 따라서 돌아오는 경로는 고려할 필요가 없다.**
    
- **시작 지점, 도착 지점이 정해져 있고**, 사이클이 발생하지 않으므로 **다익스트라**로 접근(일반 다익스트라와는 다르긴 함)
</aside>

<aside>
❓ **생각해볼 것**

- 어렵다. 하루종일 걸렸따.
- 생각하기 싫다.
</aside>

<aside>
⌨️ **코드**

</aside>

- `dijkstra()`
    
    ```java
    private void dijkstra() {
    	pq = new PriorityQueue<>();
    
    	// 출발점을 모두 우선순위 큐에 넣고
    	// 출발점까지의 intensity 값은 0으로 초기화
    	// 0으로 초기화하는 이유는 pq 내에서 순서가 바뀌지 않게 하기 위해서
    	// intensity 배열을 출입구의 개수만큼 2차원으로 선언하지 않아도 된다
    	for (int gate : gates) {
    		pq.offer(new Node(gate, 0));
    		intensity[gate] = 0;
    	}
    
    	// while문에서 처음 나오는 Node는 출발 지점보다 이전 지점
    	while (!pq.isEmpty()) {
    		Node node = pq.poll();
    
    		// node까지 가는 intensity가 다른 방법으로 node까지 가는 intensity 값보다 크다면
    		// 더 이상 조사할 필요가 없다
    		if (node.weight > intensity[node.to])
    			continue;
    		// 산봉우리인 경우 더 이상 조사할 필요가 없다
    		if (summitSet.contains(node.to))
    			continue;
    
    		// newIntensity = Math.max((node.to)번 노드로 가기 위한 최소 intensity,
    		// 							            (node.to)번 노드에서 다음 노드(nextNode)로 가기 위한 intensity (=nextNode.weight))
    		for (Node nextNode : nodes[node.to]) {
    			int newIntensity = Math.max(intensity[node.to], nextNode.weight);
    			// newIntensity값이 더 작은 값이라면 다음 노드(nextNode.to)
    			if (newIntensity < intensity[nextNode.to]) {
    				intensity[nextNode.to] = newIntensity;
    				pq.offer(new Node(nextNode.to, newIntensity));
    			}
    		}
    	}
    }	// dijkstra
    ```
    

- 정답 코드
    
    ```java
    class Solution {
    	
    	class Node implements Comparable<Node> {
    		int to, weight;
    
    		Node(int to, int weight) {
    			this.to = to;
    			this.weight = weight;
    		}
    		
    		@Override
    		public int compareTo(Solution.Node o) {
    			return this.weight - o.weight;
    		}
    	}	// Node
    	
    	int N;	// 지점의 개수
    	int[][] paths;	// 경로
    	int[] gates;	// 출입구
    	int[] summits;	// 산봉우리
    	List<Node>[] nodes;	// 노드 리스트
    	PriorityQueue<Node> pq;	// 가중치에 따른 우선순위 큐
    	Set<Integer> summitSet;
    	int[] intensity;	// intensity[N] : 출발 지점에서 N번 지점까지 가는 데 필요한 최소 intensity
    	final int INF = 987654321;
    
    	public int[] solution(int N, int[][] paths, int[] gates, int[] summits) {
    		this.N = N;
    		this.paths = paths;
    		this.gates = gates;
    		this.summits = summits;
    		
    		nodes = new List[N + 1];
    		for (int i = 1; i <= N; i++) {
    			nodes[i] = new ArrayList<>();
    		}
    
    		for (int[] path : paths) {
    			int i = path[0], j = path[1], w = path[2];
    			nodes[i].add(new Node(j, w));
    			nodes[j].add(new Node(i, w));
    		}
    
    		summitSet = new HashSet<>();
    		for (int summit : summits) {
    			summitSet.add(summit);
    		}
    
    		intensity = new int[N + 1];
    		Arrays.fill(intensity, INF);
    
    		dijkstra();
    
    		int minIntensity = INF;	// 최소 intensity
    		int minSummit = 0;	// 번호가 가장 작은 산봉우리
    
    		Arrays.sort(summits);
    
    		for (int summit : summits) {
    			if (intensity[summit] < minIntensity) {
    				minIntensity = intensity[summit];
    				minSummit = summit;
    			}
    		}
    
    		return new int[] { minSummit, minIntensity };
    	}
    
    	private void dijkstra() {
    		pq = new PriorityQueue<>();
     
    		// 출발점을 모두 우선순위 큐에 넣고
    		// 출발점까지의 intensity 값은 0으로 초기화
    		// 0으로 초기화하는 이유는 pq 내에서 순서가 바뀌지 않게 하기 위해서
    		// intensity 배열을 출입구의 개수만큼 2차원으로 선언하지 않아도 된다
    		for (int gate : gates) {
    			pq.offer(new Node(gate, 0));
    			intensity[gate] = 0;
    		}
    
    		// while문에서 처음 나오는 Node는 출발 지점보다 이전 지점
    		while (!pq.isEmpty()) {
    			Node node = pq.poll();
    
    			// node까지 가는 intensity가 다른 방법으로 node까지 가는 intensity 값보다 크다면
    			// 더 이상 조사할 필요가 없다
    			if (node.weight > intensity[node.to])
    				continue;
    			// 산봉우리인 경우 더 이상 조사할 필요가 없다
    			if (summitSet.contains(node.to))
    				continue;
    
    			// newIntensity = Math.max((node.to)번 노드로 가기 위한 최소 intensity,
    			// 							(node.to)번 노드에서 다음 노드(nextNode)로 가기 위한 intensity (=nextNode.weight))
    			for (Node nextNode : nodes[node.to]) {
    				int newIntensity = Math.max(intensity[node.to], nextNode.weight);
    				// newIntensity값이 더 작은 값이라면 다음 노드(nextNode.to)
    				if (newIntensity < intensity[nextNode.to]) {
    					intensity[nextNode.to] = newIntensity;
    					pq.offer(new Node(nextNode.to, newIntensity));
    				}
    			}
    		}
    	}	// dijkstra
    }
    
    ```