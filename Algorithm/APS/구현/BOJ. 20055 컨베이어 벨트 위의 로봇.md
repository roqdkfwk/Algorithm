# BOJ. 20055 컨베이어 벨트 위의 로봇

<aside>
🚨 **문제 출처**

[BOJ. 20055 컨베이어 벨트 위의 로봇](https://www.acmicpc.net/problem/20055)

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

- `Rotate(int count)`
    - 문제의 조건을 만족시키기까지 몇 단계가 진행되었는지 체크할 변수 `count`
    - 벨트를 회전시킨 후 로봇을 앞으로 한 칸씩 옮기고 벨트의 내구도를 감소시키는 과정을 반복
    
    <aside>
    🚨 **실수했던 점**
    
    로봇을 한 칸 앞으로 옮기는 과정에서 앞의 벨트의 로봇 존재 유무를 체크하지 않아 틀렸었다.
    
    `robot[N - 1]`은 어차피 `false`이니 상관없지만 이보다 앞에 위치한 로봇들의 
    경우를 고려했어야 한다.
    
    예를 들어, 5번째 벨트의 내구도가 0이라면 4번째 벨트 위에 위치한 로봇은 5번째 벨트로 옮길 수 없고 이에 따라 3번째 벨트 위에 위치한 로봇도 4번째 벨트 위로 
    옮길 수 없으므로 옮길 위치에 로봇이 있는지 확인했어야 했다.
    
    </aside>
    
    ```java
    for (int i = N - 1; i > 0; i--) {
    
    		// 처음에 if 조건에 !robot[i]를 쓰지 않아서 틀렸었다.
    		if (robot[i - 1] && A[i] >= 1) {
    			robot[i] = true;
    			robot[i - 1] = false;
    			A[i]--;
    		}
    }
    ```
    
    ```java
    private static int Rotate(int count) {
    		
    		while (countZero()) {
    			
    			// 벨트를 한 칸 만큼 회전시킴
    			int tmp = A[A.length - 1];
    			for (int i = A.length - 1; i > 0; i--)
    				A[i] = A[i - 1];
    			A[0] =tmp;
    			
    			// 벨트 위의 로봇도 회전시킴
    			for (int i = robot.length - 1; i > 0; i--)
    				robot[i] = robot[i - 1];
    			robot[0] = false;
    			robot[N - 1] = false;	// 내리는 위치에 도달한 로봇은 내림
    
    			for (int i = N - 1; i > 0; i--) {
    				
    				// (i - 1)번째에 로봇이 있고 i번째 벨트의 내구도가 1 이상이라면
    				// 로봇을 한 칸 옮기고 (i - 1)번째 벨트는 비게 되니 false처리해주며 i번째 벨트의 내구도를 1만큼 감소시킨다.
    				if (robot[i - 1] && !robot[i] && A[i] >= 1) {
    					robot[i] = true;
    					robot[i - 1] = false;
    					A[i]--;
    				}
    			}
    			
    			// 첫 번째 자리의 벨트의 내구도가 1 이상이면 로봇을 올리고 내구도를 1만큼 감소시킨다.
    			if (A[0] > 0) {
    				robot[0] = true;
    				A[0]--;
    			}
    			
    			count++;
    		}
    		
    		return count;
    	}	// Rotate
    ```
    

- `countZero()`
    - 내구도가 0인 벨트가 K개 이상이라면 반복을 멈추어야 한다.
    - 내구도가 0인 벨트의 개수를 세는 메소드
    
    ```java
    private static boolean countZero() {
    		
    		int cnt = 0;
    		
    		for (int i = 0; i < 2 * N; i++) {
    			
    			if (A[i] == 0) cnt++;
    			
    			if (cnt >= K) return false;
    		}
    		
    		return true;
    	}	// countZero
    ```
    

- 전체 코드
    
    ```java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    
    public class BOJ_20055_컨베이어_벨트_위의_로봇 {
    
    	static int N, K;
    	static int[] A;
    	static boolean[] robot;
    	static int ans;
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		K = Integer.parseInt(st.nextToken());
    		
    		A = new int[2 * N];
    		robot = new boolean[N];
    		
    		st = new StringTokenizer(br.readLine());
    		for (int i = 0; i < A.length; i++)
    			A[i] = Integer.parseInt(st.nextToken());
    		
    		ans = Rotate(0);
    		System.out.println(ans);
    		
    	}	// main
    	
    	private static int Rotate(int count) {
    		
    		while (countZero()) {
    			
    			// 벨트를 한 칸 만큼 회전시킴
    			int tmp = A[A.length - 1];
    			for (int i = A.length - 1; i > 0; i--)
    				A[i] = A[i - 1];
    			A[0] =tmp;
    			
    			// 벨트 위의 로봇도 회전시킴
    			for (int i = robot.length - 1; i > 0; i--)
    				robot[i] = robot[i - 1];
    			robot[0] = false;
    			robot[N - 1] = false;	// 내리는 위치에 도달한 로봇은 내림
    
    			for (int i = N - 1; i > 0; i--) {
    				
    				// (i - 1)번째에 로봇이 있고 i번째 벨트의 내구도가 1 이상이라면
    				// 로봇을 한 칸 옮기고 (i - 1)번째 벨트는 비게 되니 false처리해주며 i번째 벨트의 내구도를 1만큼 감소시킨다.
    				if (robot[i - 1] && !robot[i] && A[i] >= 1) {
    					robot[i] = true;
    					robot[i - 1] = false;
    					A[i]--;
    				}
    			}
    			
    			// 첫 번째 자리의 벨트의 내구도가 1 이상이면 로봇을 올리고 내구도를 1만큼 감소시킨다.
    			if (A[0] > 0) {
    				robot[0] = true;
    				A[0]--;
    			}
    			
    			count++;
    		}
    		
    		return count;
    	}	// Rotate
    
    	private static boolean countZero() {
    		
    		int cnt = 0;
    		
    		for (int i = 0; i < 2 * N; i++) {
    			
    			if (A[i] == 0) cnt++;
    			
    			if (cnt >= K) return false;
    		}
    		
    		return true;
    	}	// countZero
    }
    ```