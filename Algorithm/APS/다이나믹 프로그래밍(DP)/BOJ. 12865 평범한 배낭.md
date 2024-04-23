# BOJ. 12865 평범한 배낭

<aside>
🚨 **문제 출처**

[BOJ. 12865 평범한 배낭](https://www.acmicpc.net/problem/12865)

</aside>

<aside>
📖 알고리즘 분류

- 다이나믹 프로그래밍
- 배낭 문제
</aside>

<aside>
✅ **비슷한 문제**

- [BOJ. 2096 내려가기](https://www.acmicpc.net/problem/2096)
    - [내려가기 풀이](https://github.com/roqdkfwk/Algorithm/blob/master/Algorithm/APS/%EB%8B%A4%EC%9D%B4%EB%82%98%EB%AF%B9%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D(DP)/BOJ.%202096%20%EB%82%B4%EB%A0%A4%EA%B0%80%EA%B8%B0.md)
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
    
    public class BOJ_12865_평범한_배낭 {
    
    	static int N;	// 물품의 수
    	static int K;	// 최대 무게
    	static int[] W;	// 각 물건의 무게
    	static int[] V;	// 각 물건의 가치
    	static int[] DP;
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		K = Integer.parseInt(st.nextToken());
    		W = new int[K + 1];
    		V = new int[K + 1];
    		DP = new int[K + 1];
    		
    		for (int i = 1; i <= N; i++) {
    			
    			st = new StringTokenizer(br.readLine());
    			W[i] = Integer.parseInt(st.nextToken());
    			V[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		for (int i = 1; i <= N; i++) {
    			for (int j = K; j >= W[i]; j--) {
    				
    				DP[j] = Math.max(DP[j], DP[j - W[i]] + V[i]);
    			}
    		}
    		
    		System.out.println(DP[K]);
    	}
    }
    ```
