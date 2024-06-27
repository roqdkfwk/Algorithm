# Topological Sorting By Stack

- 위상정렬 방법(Stack 사용)
    - 진입 차수가 0인 모든 노드에서 DFS 탐색 수행
    - DFS 수행
        - 해당 노드를 방문 표시
        - 인접하면서 방문하지 않은 노드가 있다면 DFS 재귀 호출
        - **함수 리턴 하기 전 Stack에 현재 노드 저장**
    - Stack이 공백 상태가 될 때까지 pop
    - Stack에서 꺼내지는 순서를 뒤집으면 위상 정렬을 수행한 결과이다.
        
        ```java
        public static String[] cook = { "", "재료구매", "양념장만들기", "고기재우기", "고기손질", "제육볶음만들기", "식사", "뒷정리", "채소손질", "밥하기" };
        	static int V, E;
        	static int[][] adj;
        	static int[] degree;
        	static boolean[] visit;	// 방문 체크
        	static Stack<Integer> stack;	// 할 일을 담을 스택
        	
        	public static void main(String[] args) {
        		Scanner sc = new Scanner(input);
        		
        		V = sc.nextInt();
        		E = sc.nextInt();
        		
        		adj = new int[V + 1][V + 1];
        		degree = new int[V + 1];
        		visit = new boolean[V + 1];
        		stack = new Stack<>();
        		
        		for (int i = 0; i < E; i++) {
        			int A = sc.nextInt();
        			int B = sc.nextInt();
        					
        			adj[A][B] = 1;	// 가중치가 없으므로 1로 표기, 유향이므로 adj[B][A]는 초기화하지 않는다.
        			degree[B]++;	// 진입차수 증가
        		}
        		
        		for (int i = 1; i < V + 1; i++) {
        			
        			// 진입 차수가 0인 정점들을 전부 다 DFS 탐색
        			if (degree[i] == 0)
        				DFS(i);
        		}
        		
        		// 해당 라인이 실행된다는 뜻은 위상 정렬 끝
        		// 해당 작업은 stack에 모두 들어 있다.
        		while (!stack.isEmpty()) {
        			System.out.println(cook[stack.pop()]);
        		}
        	}
        	
        	static void DFS(int v) {
        		
        		visit[v] = true;	// 방문체크
        		
        		for (int i = 1; i < V + 1; i++) {
        			
        			// 인접하고 방문하지 않은 점이 있다면 방문
        			if (adj[v][i] != 0 && !visit[i]) 
        				DFS(i);
        		}
        		
        		// for문을 빠져나온다는 것은 방문할 수 있는 정점을 모두 방문했다는 의미
        		stack.add(v);
        	}
        ```