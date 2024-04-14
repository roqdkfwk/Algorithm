# BOJ. 2589 보물섬

<aside>
🚨 **문제 출처**

[**BOJ. 2589 보물섬**](https://www.acmicpc.net/problem/2589)

</aside>

<aside>
📖 **문제 아이디어**

- 보물이 묻혀 있는 두 곳 간의 **거리를 구하는 문제**이므로 DFS보다는 BFS로 접근
- 외에는 특별한 아이디어가 필요 없는 무난한 문제
</aside>

<aside>
❓ **생각해볼 것**

- `Dijkstra` 메소드의 수행 과정에서 가중치가 최소인 간선을 포함하는 정점을 찾을 때, index를 -1로 초기화한 후 찾으면 index 에러가 발생하는 경우가 있다. index를 0으로 초기화하면 문제가 없었는데 -1인 경우 왜 이런 경우가 발생하는지 아직 찾지 못했다.
- 두 개의 정점 사이에 가중치가 다른 여러 개의 노선이 존재하는 경우를 고려해서 우선순위 큐를 사용했다면 더 좋았을 것 같다. 이 문제에서는 해당 경우는 없었던 것 같다.
</aside>

<aside>
⌨️ **코드**

</aside>

- `BFS(int r, int c)`
    - 시작 보물섬을 기준으로 다른 보물섬까지의 거리를 구해야 하므로 `while`문 안에 `queue`를 모두 비우도록 `for`문을 사용한다.
    - `for`문 사용 시 `int size = queue.size()`를 통해 `size` 변수에 `queue`의 원소의 개수를 저장한다.
    - `for`문에서 `size`가 아닌 `queue.size()`를 사용하면 `queue`가 `poll`되면서 필요한 만큼 `for`문이 수행되지 않을 수 있다.
    
    ```java
    static void BFS(int r, int c) {
    		
    		visit = new boolean[R][C];
    		visit[r][c] = true;
    		
    		dist = 0;	// BFS를 할 때마다 dist는 0으로 초기화
    		
    		queue = new LinkedList<int[]>();
    		queue.add(new int[] {r, c});
    		
    		while (!queue.isEmpty()) {
    		
    			int size = queue.size();
    			
    			for (int n = 0; n < size; n++) {
    				
    				int[] now = queue.poll();
    				int rNow = now[0];
    				int cNow = now[1];
    				
    				for (int i = 0; i < 4; i++) {
    					
    					int rNext = rNow + dr[i];
    					int cNext = cNow + dc[i];
    					if (isOK(rNext, cNext)) {
    						
    						visit[rNext][cNext] = true;	// 방문처리 후
    						queue.add(new int[] {rNext, cNext});	// queue에 집어넣는다.
    					}
    				}	// i에 대한 for문
    			}	// n에 대한 for문
    
    			// i에 대한 for문이 끝난 후 queue가 비어있지 않다면 한 칸 더 갈 수 있다는 뜻이므로 dist를 1만큼 증가시킨다.
    			if (!queue.isEmpty()) 
    				dist++;
    		}	// while
    		
    		maxDist = Math.max(maxDist, dist);
    	}	// BFS
    ```
    

- `isOK(int row, int col)`
    
    ```java
    private static boolean isOK(int row, int col) {
    
    		if (row < 0 || row >= R || col < 0 || col >= C || visit[row][col] || map[row][col] == 'W')
    			return false;
    		
    		return true;
    	}	// isOK	
    ```
    

- 전체 코드
    
    ```java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.LinkedList;
    import java.util.Queue;
    import java.util.StringTokenizer;
    
    public class BOJ_2589_보물섬 {
    
    	static int R, C;
    	static char[][] map;
    	static boolean[][] visit;
    	static Queue<int[]> queue;
    	static int[] dr = {-1, 1, 0, 0};    // 상, 하, 좌, 우
        static int[] dc = {0, 0, -1, 1};    // 상, 하, 좌, 우
        static int maxDist = Integer.MIN_VALUE;
        static int dist;
        
        // 보물이 묻혀 있는 두 곳 간의 최단 거리를 구해야 하므로 DFS를 쓸 수 없고 BFS를 이용해야 한다.
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		R = Integer.parseInt(st.nextToken());
    		C = Integer.parseInt(st.nextToken());
    		
    		map = new char[R][C];
    		for (int r = 0; r < R; r++) {
    			
    			String str = br.readLine();
    			for (int c = 0; c < C; c++) 
    				map[r][c] = str.charAt(c);
    		}	// map
    		
    		for (int r = 0; r < R; r++) {
    			for (int c = 0; c < C; c++) {
    				
    				if (map[r][c] == 'L') 
    					BFS(r, c);
    			}
    		}
    		
    		System.out.println(maxDist);
    	}	// main
    	
    	static void BFS(int r, int c) {
    		
    		visit = new boolean[R][C];
    		visit[r][c] = true;
    		
    		dist = 0;	// BFS를 할 때마다 dist는 0으로 초기화
    		
    		queue = new LinkedList<int[]>();
    		queue.add(new int[] {r, c});
    		
    		while (!queue.isEmpty()) {
    		
    			int size = queue.size();
    			
    			for (int n = 0; n < size; n++) {
    				
    				int[] now = queue.poll();
    				int rNow = now[0];
    				int cNow = now[1];
    				
    				for (int i = 0; i < 4; i++) {
    					
    					int rNext = rNow + dr[i];
    					int cNext = cNow + dc[i];
    					if (isOK(rNext, cNext)) {
    						
    						visit[rNext][cNext] = true;	// 방문처리 후
    						queue.add(new int[] {rNext, cNext});	// queue에 집어넣는다.
    					}
    				}	// i에 대한 for문
    			}	// n에 대한 for문
    
    			// i에 대한 for문이 끝난 후 queue가 비어있지 않다면 한 칸 더 갈 수 있다는 뜻이므로 dist를 1만큼 증가시킨다.
    			if (!queue.isEmpty()) 
    				dist++;
    		}	// while
    		
    		maxDist = Math.max(maxDist, dist);
    	}	// BFS
    
    	private static boolean isOK(int row, int col) {
    
    		if (row < 0 || row >= R || col < 0 || col >= C || visit[row][col] || map[row][col] == 'W')
    			return false;
    		
    		return true;
    	}	// isOK	
    }
    ```