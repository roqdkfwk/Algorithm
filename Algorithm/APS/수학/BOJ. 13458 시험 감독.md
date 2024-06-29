# BOJ. 13458 시험 감독

<aside>
🚨 **문제 출처**

[BOJ. 13458 시험 감독](https://www.acmicpc.net/problem/13458)

</aside>

<aside>
📖 알고리즘 분류

- 수학
- 사칙연산
</aside>

<aside>
❓ **생각해볼 것**

- 처음에 `ans`의 타입을 `int`로 해서 틀렸었다.
- `N`이 커지고 `A`가 커지면 `ans`가 `int` 범위를 넘을 수 있다는 것을 생각하지 못함
</aside>

<aside>
⌨️ **코드**

</aside>

- 전체 코드
    
    ```java
    package 수학;
    
    public class BOJ_13458_시험_감독 {
    	
    	static int N;	// 시험장의 수
    	static ArrayList<Integer> stu;	// 시험장에 있는 응시자의 수
    	static int B, C;	// 감독관, 부감독관이 감시할 수 있는 응시자의 수
    	static long ans;	// 정답
    	
    	public static void main(String[] args) throws NumberFormatException, IOException {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringTokenizer st;
    		
    		N = Integer.parseInt(br.readLine());
    		ans = N;	// 감독관의 수는 최소한 시험장의 수 만큼은 필요
    		
    		stu = new ArrayList<Integer>();
    		st = new StringTokenizer(br.readLine());
    		for (int i = 0; i < N; i++) 
    			stu.add(Integer.parseInt(st.nextToken()));
    				
    		st = new StringTokenizer(br.readLine());
    		B = Integer.parseInt(st.nextToken());
    		C = Integer.parseInt(st.nextToken());
    		
    		for (int i = 0; i < N; i++) {
    			if (stu.get(i) > B) {	// 총감독관이 감독할 수 있는 학생보다 더 많은 학생이 있는 경우
    				if (((stu.get(i) - B) % C) == 0) 
    					ans += ((stu.get(i) - B) / C);
    				else 
    					ans += ((stu.get(i) - B) / C) + 1;
    			}
    		}	// for문
    		
    		System.out.println(ans);
    		
    	}	// main
    }
    
    ```