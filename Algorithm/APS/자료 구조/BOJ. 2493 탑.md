# BOJ. 2493 탑

---

<aside>
🚨 **문제 출처**

[**B](https://www.acmicpc.net/problem/2252)OJ. 2493 탑**

</aside>

<aside>
📖 자료구조 분류

- 스택
</aside>

<aside>
📖 **문제 아이디어**

- 탑의 레이저는 왼쪽으로만 발사되므로 순차적으로 비교
- 직전 탑과 비교하는 연속적인 구조
- 최근에 탐색한 탑 중 가장 높은 탑만을 고려한다.
→ 높이가 낮은 탑은 더 이상 필요하지 않기 때문에 버린다.
→ 이러한 특징 때문에 **최근의 정보만 저장하고 처리**하는 스택이 적합
</aside>

<aside>
❓ **생각해볼 것**

- 근데 내가 푼 방식이면 큐도 될지도?
- 큐는 안되고 정렬하면 우선순위 큐는 가능 → 근데 이건 스택이랑 다를 바가 없음
</aside>

<aside>
⌨️ **코드**

</aside>

- 풀이(커스텀 클래스 사용)

```java
public class BOJ_2493 {
	
	static class Tower {
		int height, index;
		
		public Tower (int height, int index) {
			this.height = height;
			this.index = index;
		}
	}
	
	static int N;
	static List<Tower> towers;
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		answer = new int[N];
		
		towers = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			towers.add(new Tower(Integer.parseInt(st.nextToken()), i));
		}
		
		Stack<Tower> stack = new Stack<>();
		stack.add(towers.get(N - 1));
		for (int i = N - 2; i >= 0; i--) {
			if (!stack.isEmpty() && stack.peek().height <= towers.get(i).height) {
				while (!stack.isEmpty() && stack.peek().height <= towers.get(i).height) {
					answer[stack.peek().index - 1] = towers.get(i).index;
					stack.pop();
				}
				
				stack.add(towers.get(i));
			} else {
				stack.add(towers.get(i));
			}
		}
		
		while (!stack.isEmpty()) {
			Tower curr = stack.pop();
			answer[curr.index - 1] = 0;
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}
```

| 메모리 | 시간 |
| --- | --- |
| 91916KB | 712ms |

- 풀이(커스텀 클래스 미사용)

```java
public class BOJ_2493_Stack {
	
	static int N;
	static int[] heights;
	static int[] result;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());	// 탑의 수
        heights = new int[N]; 					// 탑의 높이를 저장하는 배열
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        result = new int[N];					// 결과를 저장할 배열
        Stack<Integer> stack = new Stack<>();	// 스택을 사용하여 각 탑의 인덱스를 저장 (탑 번호)

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {	// 현재 탑의 높이보다 낮은 탑은 신호를 받을 수 없으므로 제거
                stack.pop();
            }
            
            if (!stack.isEmpty()) {												// 스택이 비어 있지 않으면, 스택의 최상단에 있는 탑이 신호를 받음
                result[i] = stack.peek() + 1; 									// 1-based index
            } else {
                result[i] = 0;													// 수신할 탑이 없으면 0
            }
            
            stack.add(i);														// 현재 탑의 인덱스를 스택에 추가
        }

        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }
        
        System.out.println(sb);
    }
}
```

| 메모리 | 시간 |
| --- | --- |
| 83360KB | 660ms |