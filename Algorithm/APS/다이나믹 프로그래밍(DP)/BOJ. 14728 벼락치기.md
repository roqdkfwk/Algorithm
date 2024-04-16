# BOJ. 14728 벼락치기

<aside>
🚨 **문제 출처**

[BOJ. 14728 벼락치기](https://www.acmicpc.net/problem/14728)

</aside>

<aside>
✅ **비슷한 문제**

- [BOJ. 1149 RGB거리](https://www.acmicpc.net/problem/1149)
</aside>

<aside>
📖 **문제 아이디어**

- 특별한 아이디어가 필요 없는 DP문제
</aside>

<aside>
⌨️ **코드**

</aside>

- 전체 코드
    
    ```java
    1차원 배열을 이용한 풀이
    
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    
    public class BOJ_14728_벼락치기_1차원배열 {
    
    	static int N, T;	// 단원 개수, 총 시간
    	static int[] K;	// 단원 별 예상 공부 시간
    	static int[] S;	// 문제 배점
    	static int[] DP;
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		T = Integer.parseInt(st.nextToken());
    		
    		K = new int[N + 1];
    		S = new int[N + 1];
    		for (int i = 1; i < N + 1; i++) {
    			
    			st = new StringTokenizer(br.readLine());
    			K[i] = Integer.parseInt(st.nextToken());
    			S[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		DP = new int[T + 1];
    		for (int i = 1; i < N + 1; i++) {
    			for (int k = T; k >= K[i]; k--) {
    				DP[k] = Math.max(DP[k], DP[k - K[i]] + S[i]);
    			}
    		}	// i에 대한 for문
    		
    		System.out.println(DP[T]);
    	}	// main
    }
    
    ```
    
    ```jsx
    2차원 배열을 이용한 풀이
    
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    
    public class BOJ_14728_벼락치기_2차원배열 {
    
    	static int N, T;	// 단원 개수, 총 시간
    	static int[] K;	// 단원 별 예상 공부 시간
    	static int[] S;	// 문제 배점
    	static int[][] DP;
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		T = Integer.parseInt(st.nextToken());
    		
    		K = new int[N + 1];
    		S = new int[N + 1];
    		for (int i = 1; i < N + 1; i++) {
    			
    			st = new StringTokenizer(br.readLine());
    			K[i] = Integer.parseInt(st.nextToken());
    			S[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		DP = new int[N + 1][T + 1];
    		for (int i = 1; i < N + 1; i++) {
    			for (int k = 0; k <= T; k++) {
    				
    				if (k >= K[i]) {
    					DP[i][k] = Math.max(DP[i - 1][k], DP[i - 1][k - K[i]] + S[i]);
    				} else {
    					DP[i][k] = DP[i - 1][k];
    				}
    			}
    		}	// i에 대한 for문
    		
    		System.out.println(DP[N][T]);
    	}	// main
    }
    ```