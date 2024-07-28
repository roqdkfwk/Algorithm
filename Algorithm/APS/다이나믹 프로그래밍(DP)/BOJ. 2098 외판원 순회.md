# BOJ. 2098 외판원 순회

<aside>
🚨 **문제 출처**

[BOJ. 2098 외판원 순회](https://www.acmicpc.net/problem/2098)

</aside>

<aside>
📖 알고리즘 분류

- 다이나믹 프로그래밍
- 비트마스킹
- 비트필드를 이용한 다이나믹 프로그래밍
</aside>

<aside>
✅ **비슷한 문제**

- [BOJ. 1562 계단 수](https://www.acmicpc.net/problem/1562)
</aside>

<aside>
📖 **문제 아이디어**

- 어렵다.
- **비트마스킹을 이용해 방문한 도시 체크**
- `DP[현재 위치한 도시][현재까지 방문한 도시]`순서로 배열을 선언한다. 값은 **현재 위치한 도시에서 출발 지점까지 가기 위한 최소 비용**이다.
    
    ```java
    예를 들어, 1, 2, 3, 4, 5, 6번 도시가 있을 때, 1번 도시에서 출발해 4, 5번 도시를 방문한 후 현재 3번 도시에 위치한 경우,
    그리고 3번 도시에서 1번 도시까지 가기 위한 최소 비용이 10이라면
    DP[3][011101] = 10 이다.
    ```
    
- 계속 시간초과가 났던 이유 → 처음에 DP배열을 초기화하는 과정에서 값을 INF로 저장한 것이 문제였다.
    
    
    - 틀린 코드
    
    ```java
    DP = new int[N][(1 << N)];
    for (int i = 0; i < N; i++)
    	Arrays.fill(DP[i], INF);
    	
    ==========================================================================================
    
    private static int DFS(int curr, int visit) {
    	if (visit == (1 << N) - 1) {	// 모든 도시를 방문한 경우
    		if (cost[curr][0] == 0) {	// 현재 도시에서 출발 지역으로 가는 경로가 없다면
    			return INF;
    		}
    			
    		return cost[curr][0];
    	}
    	
    	// 여기에서 DP의 값이 INF인 경우로 if문을 설정하면
    	// 위에서 현재 도시에서 출발 지역으로 가는 경로가 없는 경우를 미리 구했음에도
    	// DP[curr][visit] 값을 구하기 위해 아래의 다시 for문을 반복한다.
    	// 그 결과 테스트 케이스에 따라 시간 초과가 발생한다.
    	if (DP[curr][visit] != INF)
    		return DP[curr][visit];
    		
    	DP[curr][visit] = INF;
    	
    	for (int i = 1; i < N; i++) {
    		// 현재까지 i번째 도시에 방문한 적이 없고 && 현재 도시에서 i번째 도시로 가는 경로가 있는 경우
    		if ((visit & (1 << i)) == 0 && cost[curr][i] != 0) {
    			// 현재 도시에서 출발 도시로 가는 최소 비용은
    			// 이전에 저장된 비용과 현재 도시에서 i번째 도시로 이동 후 i번째 도시에서 출발 도시로 이동하는 비용 중 작은 값이다.
    			DP[curr][visit] = Math.min(DP[curr][visit], cost[curr][i] + DFS(i, visit | (1 << i)));
    		}
    	}
    		
    	return DP[curr][visit];
    }	// DFS
    ```
    
    - 정답 코드
    
    ```java
    DP = new int[N][(1 << N)];
    for (int i = 0; i < N; i++)
    	Arrays.fill(DP[i], -1);
    	
    ==========================================================================================
    
    private static int DFS(int curr, int visit) {
    	if (visit == (1 << N) - 1) {	// 모든 도시를 방문한 경우
    		if (cost[curr][0] == 0) {	// 현재 도시에서 출발 지역으로 가는 경로가 없다면
    			return INF;
    		}
    			
    		return cost[curr][0];
    	}
    	
    	// DP의 값이 -1인 경우는 if문을 통과해서 DP의 값을 구하도록 설정
    	// 아 그래도 헷갈린다.
    	if (DP[curr][visit] != -1)
    		return DP[curr][visit];
    		
    	DP[curr][visit] = INF;
    		
    	for (int i = 1; i < N; i++) {
    		// 현재까지 i번째 도시에 방문한 적이 없고 && 현재 도시에서 i번째 도시로 가는 경로가 있는 경우
    		if ((visit & (1 << i)) == 0 && cost[curr][i] != 0) {
    			// 현재 도시에서 출발 도시로 가는 최소 비용은
    			// 이전에 저장된 비용과 현재 도시에서 i번째 도시로 이동 후 i번째 도시에서 출발 도시로 이동하는 비용 중 작은 값이다.
    			DP[curr][visit] = Math.min(DP[curr][visit], cost[curr][i] + DFS(i, visit | (1 << i)));
    		}
    	}
    		
    	return DP[curr][visit];
    }	// DFS
    ```
    
</aside>

<aside>
⌨️ **코드**

</aside>

- 전체 코드
    
    ```java
    public class BOJ_2098_외판원_순회 {
    
    	static int N;	// 도시의 수
    	static int[][] cost;	// 비용
    	static int[][] DP;	// 순회에 필요한 최소 비용
    	static final int INF = 987654321;
    	
    	public static void main(String[] args) throws NumberFormatException, IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		N = Integer.parseInt(br.readLine());
    		cost = new int[N][N];		
    		for (int r = 0; r < N; r++) {
    			st = new StringTokenizer(br.readLine());
    			for (int c = 0; c < N; c++) {
    				cost[r][c] = Integer.parseInt(st.nextToken());
    			}
    		}	// cost
    		
    		DP = new int[N][(1 << N)];
    		for (int i = 0; i < N; i++)
    			Arrays.fill(DP[i], -1);
    		
    		// 0번 도시에서 출발, 0번 도시 방문처리
    		System.out.println(DFS(0, (1 << 0)));
    	}	// main
    
    	
    	// 현재 위치한 도시, 현재까지 방문한 도시
    	private static int DFS(int curr, int visit) {
    		if (visit == (1 << N) - 1) {	// 모든 도시를 방문한 경우
    			if (cost[curr][0] == 0) {	// 현재 도시에서 출발 지역으로 가는 경로가 없다면
    				return INF;
    			}
    			
    			return cost[curr][0];
    		}
    	
    		if (DP[curr][visit] != -1)
    			return DP[curr][visit];
    		
    		DP[curr][visit] = INF;
    		
    		for (int i = 1; i < N; i++) {
    			// 현재까지 i번째 도시에 방문한 적이 없고 && 현재 도시에서 i번째 도시로 가는 경로가 있는 경우
    			if ((visit & (1 << i)) == 0 && cost[curr][i] != 0) {
    				// 현재 도시에서 출발 도시로 가는 최소 비용은
    				// 이전에 저장된 비용과 현재 도시에서 i번째 도시로 이동 후 i번째 도시에서 출발 도시로 이동하는 비용 중 작은 값이다.
    				DP[curr][visit] = Math.min(DP[curr][visit], cost[curr][i] + DFS(i, visit | (1 << i)));
    			}
    		}
    		
    		return DP[curr][visit];
    	}	// DFS
    }
    ```