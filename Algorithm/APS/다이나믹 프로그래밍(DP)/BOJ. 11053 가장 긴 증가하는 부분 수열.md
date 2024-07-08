# BOJ. 11053 가장 긴 증가하는 부분 수열

<aside>
🚨 **문제 출처**

[BOJ. 11053 가장 긴 증가하는 부분 수열](https://www.acmicpc.net/problem/11053)

</aside>

<aside>
📖 알고리즘 분류

- 다이나믹 프로그래밍
</aside>

<aside>
✅ **비슷한 문제**

- [BOJ. 11054 가장 긴 바이토닉 부분 수열](https://www.acmicpc.net/problem/11054)
- [BOJ. 11055 가장 큰 증가하는 부분 수열](https://www.acmicpc.net/problem/11055)
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
    static void Dynamic(int idx) {
    		
    	if (DP[idx] == 0) 
    		DP[idx] = 1;
    	
    	// 배열의 마지막 인덱스부터 시작할 필요가 없다
    	for (int i = N - 1; i >= 0; i--) {
    		
    		if (A[i] < A[idx])
    			DP[idx] = Math.max(DP[idx], DP[i] + 1);
    	}
    }	// Dynamic
    ```
    

- `Dynamic(int idx)` 수정 후
    
    ```java
    static void Dynamic(int idx) {
    		
    	if (DP[idx] == 0) 
    		DP[idx] = 1;
    	
      // 매개변수로 받는 idx보다 앞에 위치한 숫자에서 시작
    	for (int i = idx - 1; i >= 0; i--) {
    		
    		if (A[i] < A[idx])
    			DP[idx] = Math.max(DP[idx], DP[i] + 1);
    	}
    }	// Dynamic
    ```
    

- 전체 코드
    
    ```java
    public class BOJ_11053_가장_긴_증가하는_부분_수열 {
    
    	static int N;
    	static int[] A, DP;
    	static int maxLength = 0;
    	
    	public static void main(String[] args) throws NumberFormatException, IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		N = Integer.parseInt(br.readLine());
    		
    		A = new int[N];
    		DP = new int[N];
    		st = new StringTokenizer(br.readLine());
    		for (int i = 0; i < N; i++) 
    			A[i] = Integer.parseInt(st.nextToken());
    		
    		DP[0] = 1;
    		for (int i = 1; i < N; i++)
    			Dynamic(i);
    		
    		for (int i = 0; i < N; i++) 
    			maxLength = Math.max(maxLength, DP[i]);
    		
    		System.out.println(maxLength);
    	}	// main
    	
    	static void Dynamic(int idx) {
    		
    		if (DP[idx] == 0) 
    			DP[idx] = 1;
    		
    		for (int i = idx - 1; i >= 0; i--) {
    			
    			if (A[i] < A[idx])
    				DP[idx] = Math.max(DP[idx], DP[i] + 1);
    		}
    	}	// Dynamic
    }
    ```