# BOJ. 20056 마법사 상어와 파이어볼

---

<aside>
🚨 **문제 출처**

[BOJ. 20056 마법사 상어와 파이어볼](https://www.acmicpc.net/problem/20056)

</aside>

<aside>
📖 알고리즘 분류

- 구현
- 시뮬레이션
</aside>

<aside>
📖 **문제 아이디어**

- 딱히 없다.
- 성실하게 구현하면 된다.
</aside>

<aside>
❓ **생각해볼 것**

- 딱히 없다.
</aside>

<aside>
⌨️ **코드**

</aside>

- `moveBall(Ball ball)`
    - 파이어볼을 움직이는 메소드
    
    ```java
    static void moveBall(Ball ball) {
    	int r = ball.r;
    	int c = ball.c;
    	int m = ball.m;
    	int s = ball.s;
    	int d = ball.d;
    		
    	int nr = (r + (s % N) * dr[d] + N) % N;
    	int nc = (c + (s % N) * dc[d] + N) % N;
    		
    	tmpGrid[nr][nc].add(new Ball(nr, nc, m, s, d));
    }
    ```
    

- 전체 코드
    
    ```java
    public class BOJ_20056 {
    	
    	static class Ball {
    		int r, c, m, s, d;
    		
    		public Ball (int r, int c, int m, int s, int d) {
    			this.r = r;
    			this.c = c;
    			this.m = m;
    			this.s = s;
    			this.d = d;
    		}
    	}
    
    	static int N, M, K;
    	static List<Ball> grid;
    	static List<Ball>[][] tmpGrid;
    	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    	static int[] dc = {0, 1, 1, 1, 0, -1, - 1, -1};
    	static int answer;
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		M = Integer.parseInt(st.nextToken());
    		K = Integer.parseInt(st.nextToken());
    		
    		grid = new ArrayList<>();
    		for (int i = 0; i < M; i++) {
    			st = new StringTokenizer(br.readLine());
    			grid.add(new Ball(
    					Integer.parseInt(st.nextToken()) - 1,	// r
    					Integer.parseInt(st.nextToken()) - 1,	// c
    					Integer.parseInt(st.nextToken()),		// m
    					Integer.parseInt(st.nextToken()),		// s
    					Integer.parseInt(st.nextToken()))		// d
    					);
    		}
    		
    		tmpGrid = new ArrayList[N][N];
    		for (int r = 0; r < N; r++) {
    			for (int c = 0; c < N; c++) {
    				tmpGrid[r][c] = new ArrayList<>();
    			}
    		}
    		for (int n = 0; n < K; n++) {
    			// 1. 파이어볼을 이동한다.
    			for (Ball ball : grid) {
    				moveBall(ball);
    			}
    			grid = new ArrayList<>();
    			
    			// 2. 이동 후 파이어볼이 2개 이상인 경우를 체크하고 나눈다.
    			for (int r = 0; r < N; r++) {
    				for (int c = 0; c < N; c++) {
    					if (!tmpGrid[r][c].isEmpty()) {			// 파이어볼이 있는 경우
    						if (tmpGrid[r][c].size() == 1) {	// 한 개의 파이어볼만 있는 경우
    							grid.add(tmpGrid[r][c].get(0));
    						} else {							// 여러 개의 파이어볼이 합쳐진 경우
    							int count = 0;
    							int mass = 0;
    							int speed = 0;
    							for (Ball ball : tmpGrid[r][c]) {
    								count += (ball.d % 2);
    								mass += ball.m;
    								speed += ball.s;
    							}
    							
    							mass /= 5;
    							if (mass == 0) {
    							  // continue로 넘어가는데 tmpGrid[r][c]를 초기화 시키기지 않아서 틀렸었다.
    								tmpGrid[r][c] = new ArrayList<>();				// 초기화시키고
    								continue;										// 파이어볼의 질량이 0인 경우 모두 소멸한다.
    							}
    							
    							speed /= tmpGrid[r][c].size();
    							if (count == 0 || count == tmpGrid[r][c].size()) {	// 합쳐진 파이어볼의 방향이 모두 짝수이거나 홀수였던 경우
    								for (int i = 0; i < 8; i += 2) {
    									grid.add(new Ball(r, c, mass, speed, i));	// 0, 2, 4, 6 방향으로 네 개의 파이어볼을 추가한다.
    								}
    							} else {
    								for (int i = 1; i < 8; i += 2) {
    									grid.add(new Ball(r, c, mass, speed, i));	// 1, 3, 5, 7 방향으로 네 개의 파이어볼을 추가한다.
    								}
    							}
    						}
    					}
    					
    					tmpGrid[r][c] = new ArrayList<>();
    				}	// c
    			}	// r
    		}
    		
    		for (Ball ball : grid) {
    			answer += ball.m;
    		}
    		
    		System.out.println(answer);
    	}
    	
    	static void moveBall(Ball ball) {
    		int r = ball.r;
    		int c = ball.c;
    		int m = ball.m;
    		int s = ball.s;
    		int d = ball.d;
    		
    		int nr = (r + (s % N) * dr[d] + N) % N;
    		int nc = (c + (s % N) * dc[d] + N) % N;
    		
    		tmpGrid[nr][nc].add(new Ball(nr, nc, m, s, d));
    	}
    }
    
    ```