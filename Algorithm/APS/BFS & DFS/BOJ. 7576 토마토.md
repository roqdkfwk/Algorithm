# BOJ. 7576 토마토

<aside>
🚨 **문제 출처**

[**BOJ. 7576 토마토**](https://www.acmicpc.net/problem/7576)

</aside>

<aside>
📖 알고리즘 분류

- 그래프 이론
- 그래프 탐색
- 너비 우선 탐색
</aside>

<aside>
📖 **문제 아이디어**

- 탐색 문제이므로 기본적으로 BFS 또는 DFS
- 상자 내의 토마토가 모두 익기 위한 **최소 시간을 구하는 문제**이므로 BFS로 접근
</aside>

<aside>
⌨️ **코드**

</aside>

- `BFS()`
    - 특정 위치에서 탐색을 시작하는 것이 아니라 토마토가 위치한 모든 곳에서 동시에 탐색을 시작해야하므로 매개변수를 받지 않는다.
    
    ```java
    	static void BFS() {
    		
    		while (!queue.isEmpty()) {
    			
    			// queue를 다 비워야 하므로 queue의 사이즈를 저장할 변수 size
    			int size = queue.size();			
    			
    			for (int i = 0; i < size; i++) {
    				
    				int[] now = queue.poll();
    				int rNow = now[0];
    				int cNow = now[1];
    				
    				for (int j = 0; j < 4; j++) {	// 4방 탐색
    					
    					int rNext = rNow + dr[j];
    					int cNext = cNow + dc[j];
    					
    					if (isOk(rNext, cNext))	{	// 범위를 벗어나지 않고, 방문하지 않았다면
    						queue.add(new int[] {rNext, cNext});
    						visit[rNext][cNext] = true;
    					}
    				}
    			}	// i에 대한 for문
    			
    			ans++;
    		}	// while		
    		
    	}	// BFS
    ```
    
- `isOk(int r, int c)`
    - (r, c)위치가 방문할 수 있는 위치인지 확인하는 메소드
    
    ```java
    	static boolean isOk(int rNext, int cNext) {
    		
    		if (rNext < 0 || rNext >= R || cNext < 0 || cNext >= C || visit[rNext][cNext])
    			return false;
    		
    		return true;
    	}	// isOk
    ```
    

- 전체 코드
    
    ```java
    public class BOJ_7576_토마토 {
    
    	static int R , C;	// 행, 열
    	static int[][] tom;	// 상자
    	static Queue<int[]> queue;	// 토마토의 위치 저장
    	static boolean[][] visit;	// 방문처리
    	static int[] dr = {-1, 1, 0, 0};    // 상, 하, 좌, 우
      static int[] dc = {0, 0, -1, 1};    // 상, 하, 좌, 우
      static int ans = -1;	// 정답
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		C = Integer.parseInt(st.nextToken());
    		R = Integer.parseInt(st.nextToken());
    		
    		tom = new int[R][C];
    		queue = new LinkedList<>();
    		visit = new boolean[R][C];
    		for (int r = 0; r < R; r++) {
    			
    			st = new StringTokenizer(br.readLine());
    			for (int c = 0; c < C; c++) {
    				
    				tom[r][c] = Integer.parseInt(st.nextToken());
    				if (tom[r][c] == 1)	{	// 토마토이면 queue에 추가 후 방문처리
    					queue.add(new int[] {r, c});
    					visit[r][c] = true;
    				}
    				else if (tom[r][c] == -1)	// 토마토가 없는 칸이면 방문처리
    					visit[r][c] = true;
    			}	// c에 대한 for문
    		}	// r에 대한 for문
    		
    		BFS();
    		
    		for (int r = 0; r < R; r++) 
    			for (int c = 0; c < C; c++)
    				if (!visit[r][c])
    					ans = -1;
    		
    		System.out.println(ans);
    	}	// main
    	
    	static void BFS() {
    		
    		while (!queue.isEmpty()) {
    			
    			// queue를 다 비워야 하므로 queue의 사이즈를 저장할 변수 size
    			int size = queue.size();			
    			
    			for (int i = 0; i < size; i++) {
    				
    				int[] now = queue.poll();
    				int rNow = now[0];
    				int cNow = now[1];
    				
    				for (int j = 0; j < 4; j++) {	// 4방 탐색
    					
    					int rNext = rNow + dr[j];
    					int cNext = cNow + dc[j];
    					
    					if (isOk(rNext, cNext))	{	// 범위를 벗어나지 않고, 방문하지 않았다면
    						queue.add(new int[] {rNext, cNext});
    						visit[rNext][cNext] = true;
    					}
    				}
    			}	// i에 대한 for문
    			
    			ans++;
    		}	// while		
    		
    	}	// BFS
    
    	static boolean isOk(int rNext, int cNext) {
    		
    		if (rNext < 0 || rNext >= R || cNext < 0 || cNext >= C || visit[rNext][cNext])
    			return false;
    		
    		return true;
    	}	// isOk
    }
    ```