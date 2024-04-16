# BOJ. 2096 내려가기

<aside>
🚨 **문제 출처**

[BOJ. 2096 내려가기](https://www.acmicpc.net/problem/2096)

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
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    
    public class BOJ_2096_내려가기 {
    
    	static int N;
    	static int[][] score;
    	static int[][] maxDP;
    	static int[][] minDP;
    	static int MAX, MIN;
    	
    	public static void main(String[] args) throws NumberFormatException, IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		N = Integer.parseInt(br.readLine());
    		score = new int[N + 1][3];
    		for (int i = 1; i < N + 1; i++) {
    			
    			st = new StringTokenizer(br.readLine());
    			score[i][0] = Integer.parseInt(st.nextToken());
    			score[i][1] = Integer.parseInt(st.nextToken());
    			score[i][2] = Integer.parseInt(st.nextToken());
    		}
    		
    		maxDP = new int[N + 1][3];
    		minDP = new int[N + 1][3];
    		for (int i = 0; i < 3; i++)  
    			maxDP[1][i] = minDP[1][i] = score[1][i];
    		
    		for (int i = 2; i < N + 1; i++) {
    			
    			maxDP[i][0] = Math.max(maxDP[i - 1][0], maxDP[i - 1][1]) + score[i][0];
    			minDP[i][0] = Math.min(minDP[i - 1][0], minDP[i - 1][1]) + score[i][0];
    
    			int max = 0;
    			max = Math.max(maxDP[i - 1][0], maxDP[i - 1][1]);
    			max = Math.max(max, maxDP[i - 1][2]);
    			maxDP[i][1] = max + score[i][1];
    			
    			int min = 987654321;
    			min = Math.min(minDP[i - 1][0], minDP[i - 1][1]);
    			min = Math.min(min, minDP[i - 1][2]);
    			minDP[i][1] = min + score[i][1];
    			
    			maxDP[i][2] = Math.max(maxDP[i - 1][1], maxDP[i - 1][2]) + score[i][2];
    			minDP[i][2] = Math.min(minDP[i - 1][1], minDP[i - 1][2]) + score[i][2];
    		}
    		
    		MAX = Math.max(maxDP[N][0], maxDP[N][1]);
    		MAX = Math.max(MAX, maxDP[N][2]);
    		
    		MIN = Math.min(minDP[N][0], minDP[N][1]);
    		MIN = Math.min(MIN, minDP[N][2]);
    		
    		System.out.println(MAX + " " + MIN);
    	}	// main
    }
    
    ```