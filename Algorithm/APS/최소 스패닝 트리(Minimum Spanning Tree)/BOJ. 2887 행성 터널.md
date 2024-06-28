# BOJ. 2887 행성 터널

<aside>
🚨 **문제 출처**

[**BOJ. 2887 행성 터널**](https://www.acmicpc.net/problem/2887)

</aside>

<aside>
📖 **문제 아이디어**

- 모든 행성을 연결하는데 필요한 **최소 비용**을 구하는 문제이므로 **크루스칼 알고리즘**
</aside>

<aside>
❓ **생각해볼 것**

- 행성의 개수 **N이 최대 100,000**이므로 행성 간의 모든 비용을 구하기 위해서는 
**최대 (100,000 * 99,999) / 2 가지의 경우**의 수를 따져야 하므로 모든 비용을 구하면 안된다.
→ 처음에 시도했던 방법으로 **메모리 초과
→** 메모리 초과가 나지 않더라도 아마 시간초과일듯
- 따라서 Comparator를 활용
</aside>

<aside>
⌨️ **코드**

</aside>

- `union(int x, int y)`
    - 서로소 집합을 합치는 메소드
    
    ```java
    static void union(int x, int y) {
    
    	p[findset(y)] = findset(x);
    }
    ```
    

- `findset(int x)`
    - 집합의 대표자를 찾는 메소드
    
    ```java
    static int findset(int x) {
    
    	if (x != p[x])
    		p[x] = findset(p[x]);
    	return p[x];
    }
    ```
    

- 틀린 코드(메모리 초과)
    
    ```java
    package MST;
    
    public class BOJ_2887_행성_터널_메모리초과 {
    	
    	static int N;	// 행성의 개수
    	static int[][] list;
    	static int[][] edges;
    	static int[] p;
    	static int ans;	// 최소비용
    	static int count;	// 간선의 개수
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		N = Integer.parseInt(br.readLine());
    		list = new int[N][3];
    				
    		for (int i = 0; i < N; i++) {
    			
    			int[] xyz = new int[3];
    			st = new StringTokenizer(br.readLine());
    			xyz[0] = Integer.parseInt(st.nextToken());
    			xyz[1] = Integer.parseInt(st.nextToken());
    			xyz[2] = Integer.parseInt(st.nextToken());
    			
    			list[i] = xyz;
    		}
    		
    		edges = new int[(N * (N - 1)) / 2][3];
    		int idx = 0;
    		for (int i = 0; i < N - 1; i++) {
    			for (int j = i + 1; j < N; j++) {
    				
    				edges[idx][0] = i;
    				edges[idx][1] = j;
    				edges[idx++][2] = findMinDist(i, j);
    			}
    		} // 간선들의 가중치 모두 계산
    		
    		Arrays.sort(edges, new Comparator<int[]>() {
    
    			@Override
    			public int compare(int[] o1, int[] o2) {
    				return (o1[2] - o2[2]) > 0 ? 1 : -1;
    			}			
    		});	// 간선들의 가중치를 기준으로 오름차순으로 정렬
    		
    		p = new int[N];
    		for (int i = 0; i < N; i++) p[i] = i;
    		
    		ans = 0;
    		count = 0;
    		for (int i = 0; i < (N * (N - 1)) / 2; i++) {
    			
    			int px = findset(edges[i][0]);
    			int py = findset(edges[i][1]);
    			
    			// px != py이면 서로소 집합이므로 두 집합을 union
    			if (px != py) {
    				
    				union(px, py);	// 두 집합을 union하고
    				ans += edges[i][2];	// ans에 필요한 비용을 더한 후
    				count++;	// 간선의 개수를 1만큼 증가
    			}
    			
    			// 정점의 개수가 N개이므로 간선의 개수가 (N - 1)개이면 MST완성이므로 반복문 종료
    			if (count == N - 1) break;
    		}
    		
    		System.out.println(ans);
    	}	// main
    	
    	static void union(int x, int y) {
    	
    		p[findset(y)] = findset(x);
    	}
    
    	static int findset(int x) {
    	
    		if (x != p[x])
    			p[x] = findset(p[x]);
    		return p[x];
    	}
    
    	static int findMinDist(int V1, int V2) {
    		
    		int x = Math.abs(list[V1][0] - list[V2][0]);
    		int y = Math.abs(list[V1][1] - list[V2][1]);
    		int z = Math.abs(list[V1][2] - list[V2][2]);
    		
    		// 최소 비용을 구하는 과정
    		if (x >= y) 
    			return y > z ? z : y;
    		else
    			return x > z ? z : x;		
    	}	// findMinDist
    }
    ```
    

- 정답 코드

```java
package MST;

public class BOJ_2887_행성_터널 {
	
	static class Vertex {
		int num, x, y, z;
		
		Vertex(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}	// Vertex
	
	static class Edge implements Comparable<Edge> {
		
		int start, end, weight;
		
		Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}	// Edge
	
	static int N;	// 행성의 개수
	static Vertex[] vertex;	// 정점 배열
	static ArrayList<Edge> edgeList;	// 간선 정보
	static int[] p;
	static int ans;	// 최소비용
	static int count;	// 간선의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		vertex = new Vertex[N];
				
		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			vertex[i] = new Vertex(i, x, y, z);
		}
		
		edgeList = new ArrayList<>();
		
		// x에 대해 정렬
		Arrays.sort(vertex, (v1, v2) -> v1.x - v2.x);
		for (int i = 0; i < N - 1; i++) {
			
			// 비용을 계산
			int weight = Math.abs(vertex[i].x - vertex[i + 1].x);
			
			// 두 정점의 정보와 비용을 edgeList에 저장
			edgeList.add(new Edge(vertex[i].num, vertex[i + 1].num, weight));
		}
		
		// y에 대해 정렬
		Arrays.sort(vertex, (v1, v2) -> v1.y - v2.y);
		for (int i = 0; i < N - 1; i++) {
			
			int weight = Math.abs(vertex[i].y - vertex[i + 1].y);
			edgeList.add(new Edge(vertex[i].num, vertex[i + 1].num, weight));
		}
		
		// z에 대해 정렬
		Arrays.sort(vertex, (v1, v2) -> v1.z - v2.z);
		for (int i = 0; i < N - 1; i++) {
			
			int weight = Math.abs(vertex[i].z - vertex[i + 1].z);
			edgeList.add(new Edge(vertex[i].num, vertex[i + 1].num, weight));
		}
		
		// 각 정점의 부모를 자기 자신으로 설정
		p = new int[N];
		for (int i = 0; i < N; i++) p[i] = i;
		
		// edgeList를 weight 기준 오름차순으로 정렬
		Collections.sort(edgeList);
		
		ans = 0;
		count = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			
			Edge edge = edgeList.get(i);
			
			int px = findset(edge.start);
			int py = findset(edge.end);
			
			// px != py이면 서로소 집합이므로 두 집합을 union
			if (px != py) {
				
				union(px, py);	// 두 집합을 union하고
				ans += edge.weight;	// ans에 필요한 비용을 더한 후
				count++;	// 간선의 개수를 1만큼 증가
			}
			
			// 정점의 개수가 N개이므로 간선의 개수가 (N - 1)개이면 MST완성이므로 반복문 종료
			if (count == N - 1) break;
		}
		
		System.out.println(ans);
	}	// main
	
	static void union(int x, int y) {
	
		p[findset(y)] = findset(x);
	}

	static int findset(int x) {
	
		if (x != p[x])
			p[x] = findset(p[x]);
		return p[x];
	}	// findset
}
```