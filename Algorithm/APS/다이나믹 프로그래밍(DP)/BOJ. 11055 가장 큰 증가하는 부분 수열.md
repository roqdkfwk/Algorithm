# BOJ. 11055 가장 큰 증가하는 부분 수열

<aside>
🚨 **문제 출처**

[BOJ. 11055 가장 큰 증가하는 부분 수열](https://www.acmicpc.net/problem/11055)

</aside>

<aside>
📖 알고리즘 분류

- 다이나믹 프로그래밍
</aside>

<aside>
✅ **비슷한 문제**

- [BOJ. 11053 가장 긴 증가하는 부분 수열](https://www.acmicpc.net/problem/11053)
- [BOJ. 11054 가장 긴 바이토닉 부분 수열](https://www.acmicpc.net/problem/11054)
- [BOJ. 11722 가장 긴 감소하는 부분 수열](https://www.acmicpc.net/problem/11722)
</aside>

<aside>
📖 **문제 아이디어**

- 특별한 아이디어가 필요 없는 DP문제
</aside>

<aside>
⌨️ **코드**

</aside>

- `Dynamic(int idx)`수정 전
    
    ```java
    private static void Dynamic(int idx) {
    		
    	if (DP[idx] == 0)
    		DP[idx] = A[idx];
    	
    	for (int i = idx - 1; i >= 0; i--) {
    		if (A[i] < A[idx])
    			DP[idx] = Math.max(DP[idx], DP[i] + A[idx]);
    	}
    }	// Dynamic
    ```
    

- 전체 코드
    
    ```java
    package DP;
    
    public class BOJ_11055_가장_큰_증가하는_부분_수열 {
    
    	static int[] A;	// 수열
    	static int N;	// 수열의 크기
    	static int[] DP;	// DP
    	static int ans;	// 정답
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		
    		A = new int[N];
    		DP = new int[N];
    		
    		st = new StringTokenizer(br.readLine());
    		for (int i = 0; i < N; i++)
    			A[i] = Integer.parseInt(st.nextToken());
    		DP[0] = A[0];
    		
    		for (int i = 1; i < N; i++) 
    			Dynamic(i);
    		
    		ans = 0;
    		for (int i = 0; i < N; i++)
    			if (ans < DP[i])
    				ans = DP[i];
    		
    		System.out.println(ans);
    	}	// main
    
    	private static void Dynamic(int idx) {
    		
    		if (DP[idx] == 0)
    			DP[idx] = A[idx];
    		
    		for (int i = idx - 1; i >= 0; i--) {
    			if (A[i] < A[idx])
    				DP[idx] = Math.max(DP[idx], DP[i] + A[idx]);
    		}
    	}	// Dynamic
    }
    
    ```