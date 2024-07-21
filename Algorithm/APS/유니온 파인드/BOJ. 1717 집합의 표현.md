# BOJ. 1717 집합의 표현

<aside>
🚨 **문제 출처**

[BOJ. 1717 집합의 표현](https://www.acmicpc.net/problem/1717)

</aside>

<aside>
📖 알고리즘 분류

- 자료 구조
- 분리 집합
</aside>

<aside>
📖 **문제 아이디어**

- 모든 행성을 연결하는데 필요한 **최소 비용**을 구하는 문제이므로 **크루스칼 알고리즘**
</aside>

<aside>
❓ **생각해볼 것**

- 건물의 개수 **N이 최대 100,000**개 이고 도로를 만드는 **비용이 최대 1,000,000**이므로 `maxCost`, `minCost`는 Long 타입으로 설정
- 다른 알고리즘으로도 풀어보자.
</aside>

<aside>
⌨️ **코드**

</aside>

- `union(int a, int b)`
    - 서로소 집합을 합치는 메소드
    
    ```java
    private static void union(int a, int b) {
    		
    	a = findset(a);
    	b = findset(b);
    		
    	if (a != b)	// 두 집합이 서로소 집합이라면
    		prnt[a] = b;	// b를 포함하는 집합의 대표자를 a의 대표자로 설정(집합을 합침)		
    }	// union
    ```
    

- `findset(int a)`
    - 집합의 대표자를 찾는 메소드
    
    ```java
    private static int findset(int a) {
    		
    	if (a != prnt[a])	// a를 포함하는 집합의 대표자가 a가 아니라면
    		prnt[a] = findset(prnt[a]);
    		
    	return prnt[a];
    }	// find
    ```
    

- 틀린 코드
    
    ```java
    public class BOJ_1717_집합의_표현 {
    	
    		// 나머지 코드 동일
    		
    		for (int i = 0; i < m; i++) {
    			
    			st = new StringTokenizer(br.readLine());
    			int oper = Integer.parseInt(st.nextToken());
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			
    			if (oper == 0) union(a, b);
    			else {
    			
    				// 틀린 부분
    				// union하는 과정에서 자식 노드들의 대표자가 모두 갱신되는 것은 아니기 때문에
    				// findset(a) == findset(b)를 만족하더라도 prnt(a) == prnt(b)를 만족하지 않을 수도 있다.
    				if (prnt(a) == prnt(b))
    					System.out.println("YES");
    				else
    					System.out.println("NO");
    			}
    		}
    	}	// main
    
    	// 나머지 코드 동일
    }
    ```
    
- 정답 코드
    
    ```java
    public class BOJ_1717_집합의_표현 {
    	
    	static int n, m;	// 마지막 집합, 관계
    	static int[] prnt;	// 부모 번호
    	
    	public static void main(String[] args) throws IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		st = new StringTokenizer(br.readLine());
    		n = Integer.parseInt(st.nextToken());
    		m = Integer.parseInt(st.nextToken());
    
    		prnt = new int[n + 1];
    		for (int i = 1; i < n + 1; i++)	// 부모 번호를 자기 자신으로 설정
    			prnt[i] = i;
    		
    		for (int i = 0; i < m; i++) {
    			
    			st = new StringTokenizer(br.readLine());
    			int oper = Integer.parseInt(st.nextToken());
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			
    			if (oper == 0) union(a, b);
    			else {
    				if (findset(a) == findset(b))
    					System.out.println("YES");
    				else
    					System.out.println("NO");
    			}
    		}
    	}	// main
    
    	private static int findset(int a) {
    		
    		if (a != prnt[a])	// a를 포함하는 집합의 대표자가 a가 아니라면
    			prnt[a] = findset(prnt[a]);
    		
    		return prnt[a];
    	}	// find
    
    	private static void union(int a, int b) {
    		
    		a = findset(a);
    		b = findset(b);
    		
    		if (a != b)	// 두 집합이 서로소 집합이라면
    			prnt[a] = b;	// b를 포함하는 집합의 대표자를 a의 대표자로 설정(집합을 합침)		
    	}	// union
    }
    ```