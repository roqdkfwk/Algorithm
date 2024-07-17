# BOJ. 7569 토마토

<aside>
🚨 **문제 출처**

[**BOJ. 7569 토마토**](https://www.acmicpc.net/problem/7569)

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
    private static void BFS() {
    
    	while (!tomato.isEmpty()) {
    			
    		int size = tomato.size();
    			
    		for (int i = 0; i < size; i++) {
    				
    			int[] now = tomato.poll();
    			int hNow = now[0];
    			int rNow = now[1];
    			int cNow = now[2];
    			
    			for (int j = 0; j < 6; j++) {
    					
    				int hNext = hNow + dh[j];
    				int rNext = rNow + dr[j];
    				int cNext = cNow + dc[j];
    					
    				if (isOk(hNext, rNext, cNext)) {
    					tomato.add(new int[] {hNext, rNext, cNext});
    					visit[hNext][rNext][cNext] = true;
    				}
    			}
    		}
    			
    		ans++;
    	}	// while
    }	// BFS
    ```
    
- `isOk(int r, int c)`
    - (r, c)위치가 방문할 수 있는 위치인지 확인하는 메소드
    
    ```java
    private static boolean isOk(int hNext, int rNext, int cNext) {
    		
    	if (hNext < 0 || hNext >= H || rNext < 0 || rNext >= R || cNext < 0 || cNext >= C
    			|| visit[hNext][rNext][cNext])
    		return false;
    		
    	return true;
    }	// isOk	
    ```
    

- 전체 코드
    
    ```java
    public class BOJ_7569_토마토 {
    
    	static int R, C, H;	// 상자의 크기, 상자의 개수
    	static int[][][] box;	// 상자
    	static int minimumDay;
    	static Queue<int[]> tomato;	// 토마토의 위치를 저장할 Queue
    	static boolean[][][] visit;	// 방문체크 배열
    	static int[] dr = {-1, 1, 0, 0, 0, 0};	// 상, 하, 좌, 우, 위, 아래  
    	static int[] dc = {0, 0, -1, 1, 0, 0};	// 상, 하, 좌, 우, 위, 아래
    	static int[] dh = {0, 0, 0, 0, -1, 1};	// 상, 하, 좌, 우, 위, 아래
    	static int ans = -1;	// 정답
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		
    		C = Integer.parseInt(st.nextToken());
    		R = Integer.parseInt(st.nextToken());
    		H = Integer.parseInt(st.nextToken());
    		
    		box = new int[H][R][C];
    		visit = new boolean[H][R][C];
    		tomato = new LinkedList<int[]>();
    		
    		for (int h = 0; h < H; h++) {
    			for (int r = 0; r < R; r++) {
    				
    				st = new StringTokenizer(br.readLine());
    				for (int c = 0; c < C; c++) {
    					
    					box[h][r][c] = Integer.parseInt(st.nextToken());
    					if (box[h][r][c] == 1) {
    						tomato.add(new int[] {h, r, c});
    						visit[h][r][c] = true;
    					}
    					else if (box[h][r][c] == -1)
    						visit[h][r][c] = true;
    				}
    			}
    		}	// i에 대한 for문
    		
    		BFS();
    		
    		for (int h = 0; h < H; h++) {
    			for (int r = 0; r < R; r++) {
    				for (int c = 0; c < C; c++) {
    					if (!visit[h][r][c])
    						ans = -1;
    				}
    			}
    		}
    		
    		System.out.println(ans);
    	}	// main
    
    	private static void BFS() {
    
    		while (!tomato.isEmpty()) {
    			
    			int size = tomato.size();
    			
    			for (int i = 0; i < size; i++) {
    				
    				int[] now = tomato.poll();
    				int hNow = now[0];
    				int rNow = now[1];
    				int cNow = now[2];
    				
    				for (int j = 0; j < 6; j++) {
    					
    					int hNext = hNow + dh[j];
    					int rNext = rNow + dr[j];
    					int cNext = cNow + dc[j];
    					
    					if (isOk(hNext, rNext, cNext)) {
    						tomato.add(new int[] {hNext, rNext, cNext});
    						visit[hNext][rNext][cNext] = true;
    					}
    				}
    			}
    			
    			ans++;
    		}	// while
    	}	// BFS
    
    	private static boolean isOk(int hNext, int rNext, int cNext) {
    		
    		if (hNext < 0 || hNext >= H || rNext < 0 || rNext >= R || cNext < 0 || cNext >= C
    				|| visit[hNext][rNext][cNext])
    			return false;
    		
    		return true;
    	}	// isOk	
    }
    ```