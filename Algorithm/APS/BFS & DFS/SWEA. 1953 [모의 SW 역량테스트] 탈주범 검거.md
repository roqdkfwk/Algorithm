# 1953. [모의 SW 역량테스트] 탈주범 검거

<aside>
🚨 **문제 출처**

[1953. [모의 SW 역량테스트] 탈주범 검거](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq&)

</aside>

<aside>
📖 **문제 아이디어**

탈주범이 맨 처음 맨홀 뚜껑을 기준으로 한 시간에 거리 1만큼을 이동할 수 있다.
→ 시간 별로 방문할 수 있는 위치를 모두 체크하기 위해서 BFS를 이용

→ 0으로 채워진 부분은 방문할 수 없는 지역이므로 시작할 때 `visit`배열에서 `true`처리

</aside>

<aside>
⌨️ **코드**

</aside>

- `BFS(int row, int col, int time)`
    - 정해진 시간에 방문할 수 있는 지점이 정해져 있으므로 `while`문 안에서 이전에 담은 좌표를 모두 비우는 
    `for`문을 작성
    
    <aside>
    🚨 **실수했던 점**
    
    `for`문을 `queue`가 모두 빌 때까지 반복해야 하는데 `i = 0`부터 `i = queue.size()`까지 반복하도록
    
    설정해서 `queue`를 모두 비우기 전에 `for`문이 종료되었다.
    
    → `for`문 위에서 `int size = queue.size()`로 저장 후 `size`까지 반복하도록 설정해서 해결
    
    </aside>
    
    ```java
    private static void BFS(int row, int col, int time) {
    		
    		queue = new LinkedList<>();
    		queue.add(new int[] {row, col});
    		visit[row][col] = true;
    		
    		while (!queue.isEmpty()) {
    			
    			int size = queue.size();
    			for (int i = 0; i < size; i++) {
    				
    				count++;
    				int[] now = queue.poll();
    				int rNow = now[0];
    				int cNow = now[1];
    				
    				checkConnection(rNow, cNow);				
    			}	// for문
    			
    			time++;
    			if (time == L)	// 탈출 후 소요된 시간 
    				return;
    		}	// while문
    	}	// BFS
    ```
    

- `checkConnection(int rNow, int cNow)`
    - 인접한 위치라도 터널 구조물의 모양에 따라 탈주범이 이동하지 못하는 지역이 있을 수 있다.
    - 현재 위치를 기준으로 인접한 위치가 탈주범이 이동할 수 있는 위치인지 체크하는 메소드
    - 인접한 위치가 `map`의 범위를 벗어나지 않으며 이전에 방문했던 적이 있는지 확인하기 위해 
    `check()`메소드 사용
    
    ```java
    private static void checkConnection(int rNow, int cNow) {
    
    		if (map[rNow][cNow] == 1) {	// 상, 하, 좌, 우
    			
    			if (check(rNow - 1, cNow)) {	// 상 방향 범위를 벗어나지 않는 경우
    				if (map[rNow - 1][cNow] == 1 || map[rNow - 1][cNow] == 2
    						|| map[rNow - 1][cNow] == 5 || map[rNow - 1][cNow] == 6) {
    					visit[rNow - 1][cNow] = true;
    					queue.add(new int[] {rNow - 1, cNow});
    				}
    			} 
    			
    			if (check(rNow + 1, cNow)) {	// 하 방향
    				if (map[rNow + 1][cNow] == 1 || map[rNow + 1][cNow] == 2
    						|| map[rNow + 1][cNow] == 4 || map[rNow + 1][cNow] == 7) {
    					visit[rNow + 1][cNow] = true;
    					queue.add(new int[] {rNow + 1, cNow});
    				}
    			}
    			
    			if (check(rNow, cNow - 1)) {	// 좌 방향
    				if (map[rNow][cNow - 1] == 1 || map[rNow][cNow - 1] == 3
    						|| map[rNow][cNow - 1] == 4 || map[rNow][cNow - 1] == 5) {
    					visit[rNow][cNow - 1] = true;
    					queue.add(new int[] {rNow, cNow - 1});
    				}
    			}
    			
    			if (check(rNow, cNow + 1)) {	// 우 방향
    				if (map[rNow][cNow + 1] == 1 || map[rNow][cNow + 1] == 3
    						|| map[rNow][cNow + 1] == 6 || map[rNow][cNow + 1] == 7) {
    					visit[rNow][cNow + 1] = true;					
    					queue.add(new int[] {rNow, cNow + 1});
    				}
    			}
    		} else if (map[rNow][cNow] == 2) { 	// 상, 하
    			
    			if (check(rNow - 1, cNow)) {	// 상
    				if (map[rNow - 1][cNow] == 1 || map[rNow - 1][cNow] == 2
    						|| map[rNow - 1][cNow] == 5 || map[rNow - 1][cNow] == 6) {
    					visit[rNow - 1][cNow] = true;
    					queue.add(new int[] {rNow - 1, cNow});
    				}
    			}
    			
    			if (check(rNow + 1, cNow)) {	// 하
    				if (map[rNow + 1][cNow] == 1 || map[rNow + 1][cNow] == 2
    						|| map[rNow + 1][cNow] == 4 || map[rNow + 1][cNow] == 7) {
    					visit[rNow + 1][cNow] = true;
    					queue.add(new int[] {rNow + 1, cNow});
    				}
    			}
    			
    		} else if (map[rNow][cNow] == 3) { 	// 좌, 우
    			if (check(rNow, cNow - 1)) {	// 좌
    				if (map[rNow][cNow - 1] == 1 || map[rNow][cNow - 1] == 3
    						|| map[rNow][cNow - 1] == 4 || map[rNow][cNow - 1] == 5) {
    					visit[rNow][cNow - 1] = true;
    					queue.add(new int[] {rNow, cNow - 1});
    				}
    			}
    			
    			if (check(rNow, cNow + 1)) {	// 우
    				if (map[rNow][cNow + 1] == 1 || map[rNow][cNow + 1] == 3
    						|| map[rNow][cNow + 1] == 6 || map[rNow][cNow + 1] == 7) {
    					visit[rNow][cNow + 1] = true;
    					queue.add(new int[] {rNow, cNow + 1});
    				}
    			}
    				
    		} else if (map[rNow][cNow] == 4) {	// 상, 우
    			if (check(rNow - 1, cNow)) {	// 상
    				if (map[rNow - 1][cNow] == 1 || map[rNow - 1][cNow] == 2
    						|| map[rNow - 1][cNow] == 5 || map[rNow - 1][cNow] == 6) {
    					visit[rNow - 1][cNow] = true;
    					queue.add(new int[] {rNow - 1, cNow});
    				}
    			}
    			
    			if (check(rNow, cNow + 1)) {	// 우
    				if (map[rNow][cNow + 1] == 1 || map[rNow][cNow + 1] == 3
    						|| map[rNow][cNow + 1] == 6 || map[rNow][cNow + 1] == 7) {
    					visit[rNow][cNow + 1] = true;
    					queue.add(new int[] {rNow, cNow + 1});
    				}
    			}
    			
    		} else if (map[rNow][cNow] == 5) { 	// 하, 우
    			if (check(rNow + 1, cNow)) {	// 하
    				if (map[rNow + 1][cNow] == 1 || map[rNow + 1][cNow] == 2
    						|| map[rNow + 1][cNow] == 4 || map[rNow + 1][cNow] == 7) {
    					visit[rNow + 1][cNow] = true;					
    					queue.add(new int[] {rNow + 1, cNow});
    				}
    			}
    			
    			if (check(rNow, cNow + 1)) {	// 우
    				if (map[rNow][cNow + 1] == 1 || map[rNow][cNow + 1] == 3
    						|| map[rNow][cNow + 1] == 6 || map[rNow][cNow + 1] == 7) {
    					visit[rNow][cNow + 1] = true;
    					queue.add(new int[] {rNow, cNow + 1});
    				}
    			}
    			
    		} else if (map[rNow][cNow] == 6) { 	// 하, 좌
    			if (check(rNow + 1, cNow)) {	// 하
    				if (map[rNow + 1][cNow] == 1 || map[rNow + 1][cNow] == 2
    						|| map[rNow + 1][cNow] == 4 || map[rNow + 1][cNow] == 7) {
    					visit[rNow + 1][cNow] = true;
    					queue.add(new int[] {rNow + 1, cNow});
    				}
    			}
    			
    			if (check(rNow, cNow - 1)) {	// 좌
    				if (map[rNow][cNow - 1] == 1 || map[rNow][cNow - 1] == 3
    						|| map[rNow][cNow - 1] == 4 || map[rNow][cNow - 1] == 5) {
    					visit[rNow][cNow - 1] = true;					
    					queue.add(new int[] {rNow, cNow - 1});
    				}
    			}
    			
    		} else { 	// 상, 좌
    			if (check(rNow - 1, cNow)) {	// 상
    				if (map[rNow - 1][cNow] == 1 || map[rNow - 1][cNow] == 2
    						|| map[rNow - 1][cNow] == 5 || map[rNow - 1][cNow] == 6) {
    					visit[rNow - 1][cNow] = true;					
    					queue.add(new int[] {rNow - 1, cNow});
    				}
    			}	
    			
    			if (check(rNow, cNow - 1)) {	// 좌
    				if (map[rNow][cNow - 1] == 1 || map[rNow][cNow - 1] == 3
    						|| map[rNow][cNow - 1] == 4 || map[rNow][cNow - 1] == 5) {
    					visit[rNow][cNow - 1] = true;					
    					queue.add(new int[] {rNow, cNow - 1});
    				}
    			}
    		}			
    	}	// checkConnection
    ```
    

- `check(int rNow, int cNow)`
    - `(rNow, cNow)`위치가 방문할 수 있는 위치인지 체크하는 메소드
    - 방문할 수 있는 위치이면 `true`를 아니라면 `false`를 반환
    
    ```java
    private static boolean check(int rNow, int cNow) {
    		
    		// 범위를 벗어나거나 || 방문한 위치라면 false를 반환
    		if (rNow < 0 || rNow >= R || cNow < 0 || cNow >= C || visit[rNow][cNow])
    			return false;
    		return true;
    	}
    ```
    
- 전체 코드
    
    ```java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Arrays;
    import java.util.LinkedList;
    import java.util.Queue;
    import java.util.StringTokenizer;
    
    public class SWEA_1953_탈주범_검거 {
    
    	static int T;	// 테스트 케이스의 수
    	static int R, C;	// R : 세로, C : 가로
    	static int hR, hC;	// hR, hC : 맨홀 뚜껑의 위치
    	static int[][] map;	// 지하 터널
    	static boolean[][] visit;	// 방문 체크
    	static int L;	// 탈출 후 소요된 시간
    	static Queue<int[]> queue;
    	static int[] dr = {-1, 1, 0, 0};
    	static int[] dc = {0, 0, -1, 1};
    	static int count;	// 탈주범이 위치할 수 있는 장소의 개수
    	
    	// 1. 상하좌우, 2. 상하, 3. 좌우, 4. 상우, 5. 하우, 6. 하좌, 7. 상좌
    	public static void main(String[] args) throws NumberFormatException, IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringBuilder sb = new StringBuilder();
    		StringTokenizer st;
    		
    		T = Integer.parseInt(br.readLine());
    		for (int tc = 1; tc <= T; tc++) {
    			
    			sb.append("#" + tc + " ");
    			
    			count = 0;
    			
    			st = new StringTokenizer(br.readLine());
    			R = Integer.parseInt(st.nextToken());
    			C = Integer.parseInt(st.nextToken());
    			hR = Integer.parseInt(st.nextToken());
    			hC = Integer.parseInt(st.nextToken());
    			L = Integer.parseInt(st.nextToken());
    			
    			map = new int[R][C];
    			visit = new boolean[R][C];
    			for (int r = 0; r < R; r++) {
    				
    				st = new StringTokenizer(br.readLine());
    				for (int c = 0; c < C; c++) {
    					
    					map[r][c] = Integer.parseInt(st.nextToken());
    					if (map[r][c] == 0) visit[r][c] = true;
    				}
    			}	// map
    			
    			BFS(hR, hC, 0);
    			sb.append(count + "\n");
    		}	// tc에 대한 for문
    		
    		System.out.println(sb);
    	}	// main
    	
    	private static void BFS(int row, int col, int time) {
    		
    		queue = new LinkedList<>();
    		queue.add(new int[] {row, col});
    		visit[row][col] = true;
    		
    		while (!queue.isEmpty()) {
    			
    			int size = queue.size();
    			for (int i = 0; i < size; i++) {
    				
    				count++;
    				int[] now = queue.poll();
    				int rNow = now[0];
    				int cNow = now[1];
    				
    				checkConnection(rNow, cNow);				
    			}	// for문
    			
    			time++;
    			if (time == L)	// 탈출 후 소요된 시간 
    				return;
    		}	// while문
    	}	// BFS
    
    	private static void checkConnection(int rNow, int cNow) {
    
    		if (map[rNow][cNow] == 1) {	// 상, 하, 좌, 우
    			
    			if (check(rNow - 1, cNow)) {	// 상 방향 범위를 벗어나지 않는 경우
    				if (map[rNow - 1][cNow] == 1 || map[rNow - 1][cNow] == 2
    						|| map[rNow - 1][cNow] == 5 || map[rNow - 1][cNow] == 6) {
    					visit[rNow - 1][cNow] = true;
    					queue.add(new int[] {rNow - 1, cNow});
    				}
    			} 
    			
    			if (check(rNow + 1, cNow)) {	// 하 방향
    				if (map[rNow + 1][cNow] == 1 || map[rNow + 1][cNow] == 2
    						|| map[rNow + 1][cNow] == 4 || map[rNow + 1][cNow] == 7) {
    					visit[rNow + 1][cNow] = true;
    					queue.add(new int[] {rNow + 1, cNow});
    				}
    			}
    			
    			if (check(rNow, cNow - 1)) {	// 좌 방향
    				if (map[rNow][cNow - 1] == 1 || map[rNow][cNow - 1] == 3
    						|| map[rNow][cNow - 1] == 4 || map[rNow][cNow - 1] == 5) {
    					visit[rNow][cNow - 1] = true;
    					queue.add(new int[] {rNow, cNow - 1});
    				}
    			}
    			
    			if (check(rNow, cNow + 1)) {	// 우 방향
    				if (map[rNow][cNow + 1] == 1 || map[rNow][cNow + 1] == 3
    						|| map[rNow][cNow + 1] == 6 || map[rNow][cNow + 1] == 7) {
    					visit[rNow][cNow + 1] = true;					
    					queue.add(new int[] {rNow, cNow + 1});
    				}
    			}
    		} else if (map[rNow][cNow] == 2) { 	// 상, 하
    			
    			if (check(rNow - 1, cNow)) {	// 상
    				if (map[rNow - 1][cNow] == 1 || map[rNow - 1][cNow] == 2
    						|| map[rNow - 1][cNow] == 5 || map[rNow - 1][cNow] == 6) {
    					visit[rNow - 1][cNow] = true;
    					queue.add(new int[] {rNow - 1, cNow});
    				}
    			}
    			
    			if (check(rNow + 1, cNow)) {	// 하
    				if (map[rNow + 1][cNow] == 1 || map[rNow + 1][cNow] == 2
    						|| map[rNow + 1][cNow] == 4 || map[rNow + 1][cNow] == 7) {
    					visit[rNow + 1][cNow] = true;
    					queue.add(new int[] {rNow + 1, cNow});
    				}
    			}
    			
    		} else if (map[rNow][cNow] == 3) { 	// 좌, 우
    			if (check(rNow, cNow - 1)) {	// 좌
    				if (map[rNow][cNow - 1] == 1 || map[rNow][cNow - 1] == 3
    						|| map[rNow][cNow - 1] == 4 || map[rNow][cNow - 1] == 5) {
    					visit[rNow][cNow - 1] = true;
    					queue.add(new int[] {rNow, cNow - 1});
    				}
    			}
    			
    			if (check(rNow, cNow + 1)) {	// 우
    				if (map[rNow][cNow + 1] == 1 || map[rNow][cNow + 1] == 3
    						|| map[rNow][cNow + 1] == 6 || map[rNow][cNow + 1] == 7) {
    					visit[rNow][cNow + 1] = true;
    					queue.add(new int[] {rNow, cNow + 1});
    				}
    			}
    				
    		} else if (map[rNow][cNow] == 4) {	// 상, 우
    			if (check(rNow - 1, cNow)) {	// 상
    				if (map[rNow - 1][cNow] == 1 || map[rNow - 1][cNow] == 2
    						|| map[rNow - 1][cNow] == 5 || map[rNow - 1][cNow] == 6) {
    					visit[rNow - 1][cNow] = true;
    					queue.add(new int[] {rNow - 1, cNow});
    				}
    			}
    			
    			if (check(rNow, cNow + 1)) {	// 우
    				if (map[rNow][cNow + 1] == 1 || map[rNow][cNow + 1] == 3
    						|| map[rNow][cNow + 1] == 6 || map[rNow][cNow + 1] == 7) {
    					visit[rNow][cNow + 1] = true;
    					queue.add(new int[] {rNow, cNow + 1});
    				}
    			}
    			
    		} else if (map[rNow][cNow] == 5) { 	// 하, 우
    			if (check(rNow + 1, cNow)) {	// 하
    				if (map[rNow + 1][cNow] == 1 || map[rNow + 1][cNow] == 2
    						|| map[rNow + 1][cNow] == 4 || map[rNow + 1][cNow] == 7) {
    					visit[rNow + 1][cNow] = true;					
    					queue.add(new int[] {rNow + 1, cNow});
    				}
    			}
    			
    			if (check(rNow, cNow + 1)) {	// 우
    				if (map[rNow][cNow + 1] == 1 || map[rNow][cNow + 1] == 3
    						|| map[rNow][cNow + 1] == 6 || map[rNow][cNow + 1] == 7) {
    					visit[rNow][cNow + 1] = true;
    					queue.add(new int[] {rNow, cNow + 1});
    				}
    			}
    			
    		} else if (map[rNow][cNow] == 6) { 	// 하, 좌
    			if (check(rNow + 1, cNow)) {	// 하
    				if (map[rNow + 1][cNow] == 1 || map[rNow + 1][cNow] == 2
    						|| map[rNow + 1][cNow] == 4 || map[rNow + 1][cNow] == 7) {
    					visit[rNow + 1][cNow] = true;
    					queue.add(new int[] {rNow + 1, cNow});
    				}
    			}
    			
    			if (check(rNow, cNow - 1)) {	// 좌
    				if (map[rNow][cNow - 1] == 1 || map[rNow][cNow - 1] == 3
    						|| map[rNow][cNow - 1] == 4 || map[rNow][cNow - 1] == 5) {
    					visit[rNow][cNow - 1] = true;					
    					queue.add(new int[] {rNow, cNow - 1});
    				}
    			}
    			
    		} else { 	// 상, 좌
    			if (check(rNow - 1, cNow)) {	// 상
    				if (map[rNow - 1][cNow] == 1 || map[rNow - 1][cNow] == 2
    						|| map[rNow - 1][cNow] == 5 || map[rNow - 1][cNow] == 6) {
    					visit[rNow - 1][cNow] = true;					
    					queue.add(new int[] {rNow - 1, cNow});
    				}
    			}	
    			
    			if (check(rNow, cNow - 1)) {	// 좌
    				if (map[rNow][cNow - 1] == 1 || map[rNow][cNow - 1] == 3
    						|| map[rNow][cNow - 1] == 4 || map[rNow][cNow - 1] == 5) {
    					visit[rNow][cNow - 1] = true;					
    					queue.add(new int[] {rNow, cNow - 1});
    				}
    			}
    		}			
    	}	// checkConnection
    
    	private static boolean check(int rNow, int cNow) {
    		
    		// 범위를 벗어나거나 || 방문한 위치라면 false를 반환
    		if (rNow < 0 || rNow >= R || cNow < 0 || cNow >= C || visit[rNow][cNow])
    			return false;
    		return true;
    	}
    }
    ```