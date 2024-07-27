# BOJ. 1562 계단 수

<aside>
🚨 **문제 출처**

[BOJ. 1562 계단 수](https://www.acmicpc.net/problem/1562)

</aside>

<aside>
📖 알고리즘 분류

- 다이나믹 프로그래밍
- 비트마스킹
- 비트필드를 이용한 다이나믹 프로그래밍
</aside>

<aside>
✅ **비슷한 문제**

- [BOJ. 2098 외판원 순회](https://www.acmicpc.net/problem/2098)
</aside>

<aside>
📖 **문제 아이디어**

- 어렵다.
- **비트마스킹을 이용해서 사용한 숫자를 체크**
- `DP[자릿수][마지막 자릿수][사용한 숫자]`순서로 배열을 선언한다. 단, `자릿수 = 1` 일 때는 일의 자릿수가 아닌 제일 앞의 자릿수를 뜻한다.
- `DP[n][k][visit]` 형태로 `Bottom-Up` 방법을 사용
    
    ```java
    DP[1][1][0,000,000,010] = 1 -> 첫 번째 자릿수가 1인 경우의 수는 1가지
    DP[1][2][0,000,000,100] = 1 -> 첫 번째 자릿수가 2인 경우의 수는 1가지
    ...
    DP[1][9][1,000,000,000] = 1 -> 첫 번째 자릿수가 9인 경우의 수는 1가지
    ```
    
- 위의 DP배열의 설정을 이해하고 계단 수의 특징을 잘 생각해보면 아래와 같은 점화식을 구할 수 있다.
    
    ```java
    DP[N][K][visit | (1 << K)] = DP[N - 1][K - 1][visit] + DP[N - 1][K + 1][visit];
    
    N번째 자릿수가 K로 끝나는 경우의 수 = (N - 1)번째 자릿수가 (K - 1)로 끝나는 경우의 수 + (N - 1)번째 자릿수가 (K + 1)로 끝나는 경우의 수
    
    ==========점화식 설명==========
    
    visit은 현재까지 사용한 숫자를 나타낸다.
    (1 << K)는 N번째 자릿수로 K를 사용했음을 나타낸다.
    
    예를 들어 N = 6, K = 5이고 visit이 0,000,011,100 이었다면
    5번째 자릿수까지는 2, 3, 4를 사용해서 계단 수를 만들고 6번째 자릿수는 5를 이용해서 계단 수를 만드는 것이므로
    자릿수 = 6, 마지막 자릿수 = 5이고 newVisit = 0,000,111,100이 되어야 한다. (newVisit = visit | (1 << K))
    
    그리고 **newVisit은 visit과 (1 << K)의 or연산**으로 구할 수 있다.
    
    visit = 0,000,011,100, (1 << K) = 0,000,100,000 이므로
    		 0,000,011,100
    	or 0,000,100,000
    	 = 0,000,111,100 이므로 2, 3, 4, 5를 사용했음을 나타내고
    	DP[N][K][visit | (1 << K)] = DP[6][5][0,000,111,100]이 되므로 6번째 자릿수가 5이고 2, 3, 4, 5를 사용해 만든 계단 수라는 뜻이다.
    ```
    
</aside>

<aside>
⌨️ **코드**

</aside>

- 전체 코드
    
    ```java
    public class BOJ_1562__계단_수 {
    
    	static int N;	// 길이
    	static long[][][] dp;
    	static final int mod = 1_000_000_000;
    	static long ans;
    	
    	public static void main(String[] args) throws NumberFormatException, IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		
    		N = Integer.parseInt(br.readLine());
    		dp = new long[N + 1][10][(1 << 10)];
    		
    		// 첫번째 자리 숫자가 k인 경우의 수는 1
    		// (1 << k) 연산으로 사용한 숫자 체크
    		for (int k = 1; k <= 9; k++)
    			dp[1][k][1 << k] = 1;
    		
    		for (int n = 2; n <= N; n++) {
    			for (int k = 0; k <= 9; k++) {
    				for (int visit = 0; visit < (1 << 10); visit++) {
    					
    					if (k == 0)
    						dp[n][k][visit | (1 << k)] += dp[n - 1][k + 1][visit] % mod;
    					else if (k == 9)
    						dp[n][k][visit | (1 << k)] += dp[n - 1][k - 1][visit] % mod;
    					else
    						dp[n][k][visit | (1 << k)] += dp[n - 1][k - 1][visit] % mod + dp[n - 1][k + 1][visit] % mod;
    					
    					dp[n][k][visit | (1 << k)] %= mod;
    				}
    			}
    		}
    		
    		for (int i = 0; i <= 9; i++) {
    			ans += (dp[N][i][(1 << 10) - 1] % mod);
    			ans %= mod;
    		}
    		
    		System.out.println(ans);
    	}	// main
    }
    ```
