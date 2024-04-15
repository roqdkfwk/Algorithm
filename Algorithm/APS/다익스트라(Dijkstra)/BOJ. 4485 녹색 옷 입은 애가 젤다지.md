# BOJ. 4485 녹색 옷 입은 애가 젤다지?

<aside>
🚨 **문제 출처**

[**BOJ. 4485 녹색 옷 입은 애가 젤다지?**](https://www.acmicpc.net/problem/4485)

</aside>

<aside>
📖 알고리즘 분류

- 그래프 이론
- 그래프 탐색
- 다익스트라
- 최단 경로
</aside>

<aside>
📖 **문제 아이디어**

- 동굴의 모든 칸을 하나의 정점, 칸에 적힌 값을 가중치 간선으로 생각하면 그래프 문제
- **[0][0]**칸에서 **[N - 1][N - 1]**칸까지 이동하는데 잃을 수 밖에 최소 금액을 구하는 문제
- 즉, 출발 지점에서 정해진 지점까지 간선의 가중치의 합의 최솟값을 구하는 문제이므로 **다익스트라**로 
접근
</aside>

<aside>
❓ **생각해볼 것**

- 행렬을 이용한 다익스트라 문제라 처음에 조금 생소했다.
- 다른 다익스트라처럼 우선순위 큐를 이용할 수 있는지 생각해보자.
</aside>

<aside>
⌨️ **코드**

</aside>

- `Dijkstra(int row, int col)`
    - 행렬을 이용하는 문제이므로 정점을 번호가 아닌 좌표로 나타내기 때문에 두 개의 인자를 받는다.
    
    ```java
    private static void Dijkstra(int row, int col) {
    
    		int rNow = row;
    		int cNow = col;
    		
    		for (int n = 0; n < N * N - 1; n++) {
    			
    			int min = INF;
    			int Ridx = 0;
    			int Cidx = 0;
    			
    			for (int r = 0; r < N; r++) {
    				for (int c = 0; c < N; c++) {
    					if (dist[r][c] < min && !visit[r][c]) {
    						
    						min = dist[r][c];
    						Ridx = r;
    						Cidx = c;
    					}
    				}
    			}	// r에 대한 for문
    			
    			visit[Ridx][Cidx] = true;
    			
    			for (int i = 0; i < 4; i++) {
    				
    				int rNext = Ridx + dr[i];
    				int cNext = Cidx + dc[i];
    				if (isOK(rNext, cNext)) 
    					dist[rNext][cNext] = Math.min(dist[rNext][cNext], dist[Ridx][Cidx] + cave[rNext][cNext]);
    			}
    		}	// n에 대한 for문
    		
    		minDist = dist[N - 1][N - 1];
    	}	// Dijkstra
    ```
    

- 전체 코드
    
    ```java
    public class BOJ_4485_녹색_옷_입은_애가_젤다지? {
    	
    	static int N;
    	static int[][] cave;
    	static int[][] dist;
    	static boolean[][] visit;
    	static final int INF = 987654321;
    	static int[] dr = {-1, 1, 0, 0};
    	static int[] dc = {0, 0, -1, 1};
    	static int minDist;
    	
    	public static void main(String[] args) throws NumberFormatException, IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringBuilder sb = new StringBuilder();
    		StringTokenizer st;
    		
    		int stage = 1;
    		while (true) {
    			
    			N = Integer.parseInt(br.readLine());
    			if (N == 0) break;
    			
    			cave = new int[N][N];
    			for (int r = 0; r < N; r++) {
    				
    				st = new StringTokenizer(br.readLine());
    				for (int c = 0; c < N; c++) 
    					cave[r][c] = Integer.parseInt(st.nextToken());
    			}	// cave
    			
    			visit = new boolean[N][N];
    			dist = new int[N][N];
    			for (int r = 0; r < N; r++)
    				Arrays.fill(dist[r], INF);
    			dist[0][0] = cave[0][0];
    			
    			Dijkstra(0, 0);
    			
    			sb.append("Problem ").append(stage++).append(": ").append(minDist).append("\n");
    		}	// while
    		
    		sb.deleteCharAt(sb.length() - 1);
    		System.out.println(sb);
    	}	// main
    
    	private static void Dijkstra(int row, int col) {
    
    		int rNow = row;
    		int cNow = col;
    		
    		for (int n = 0; n < N * N - 1; n++) {
    			
    			int min = INF;
    			int Ridx = 0;
    			int Cidx = 0;
    			
    			for (int r = 0; r < N; r++) {
    				for (int c = 0; c < N; c++) {
    					if (dist[r][c] < min && !visit[r][c]) {
    						
    						min = dist[r][c];
    						Ridx = r;
    						Cidx = c;
    					}
    				}
    			}	// r에 대한 for문
    			
    			visit[Ridx][Cidx] = true;
    			
    			for (int i = 0; i < 4; i++) {
    				
    				int rNext = Ridx + dr[i];
    				int cNext = Cidx + dc[i];
    				if (isOK(rNext, cNext)) 
    					dist[rNext][cNext] = Math.min(dist[rNext][cNext], dist[Ridx][Cidx] + cave[rNext][cNext]);
    			}
    		}	// n에 대한 for문
    		
    		minDist = dist[N - 1][N - 1];
    	}	// Dijkstra
    
    	private static boolean isOK(int row, int col) {
    		
    		if (row < 0 || row >= N || col < 0 || col >= N || visit[row][col])
    			return false;
    		
    		return true;
    	}	// isOK
    }
    ```