# Topological Sorting By Queue

- 위상 정렬 방법(Queue 사용)
    - 진입 차수가 0인 모든 노드를 Queue에 삽입
    - Queue가 공백 상태가 될 때까지 반복 수행
        - Queue에서 원소를 꺼내 해당 노드에서 나가는 간선을 그래프에서 제거한다.
        (연결 된 노드의 진입 차수를 감소시킨다.)
        - **새롭게 진입 차수가 0이 된 노드를 Queue에 삽입한다.**
    - Queue에서 꺼내지는 순서 (Queue에 들어오는 순서)가 정렬을 수행한 결과
        
        ```java
        public static String[] cook = { "", "재료구매", "양념장만들기", "고기재우기", "고기손질", "제육볶음만들기", "식사", "뒷정리", "채소손질", "밥하기" };
        
        	public static void main(String[] args) {
        		Scanner sc = new Scanner(input);
        		
        		int V = sc.nextInt();	// 정점의 수
        		int E = sc.nextInt();	// 간선의 수, 방향이 있음
        		
        		int[][] adj = new int[V + 1][V + 1];	// 정점의 번호는 1번부터 시작
        		int[] degree = new int[V + 1];	// 진입차수 저장
        		
        		for (int i = 0; i < E; i++) {
        			int A = sc.nextInt();
        			int B = sc.nextInt();
        					
        			adj[A][B] = 1;	// 가중치가 없으므로 1로 표기, 유향이므로 adj[B][A]는 초기화하지 않는다.
        			degree[B]++;	// 진입차수 증가
        		}
        		
        		Queue<Integer> queue = new LinkedList<>();
        		
        		// queue로 위상정렬 구현 1단계
        		// 진입차수가 0인 정점들을 queue에 추가
        		for (int i = 1; i < V + 1; i++) {
        			if (degree[i] == 0) queue.offer(i);
        		}
        		
        		StringBuilder sb = new StringBuilder();
        		
        		// queue로 위상정렬 구현 2단계
        		// 큐가 공백상태가 될 때까지 돌린다
        		while (!queue.isEmpty()) {
        			
        			// 2-1 하나 꺼냄
        			int curr = queue.poll();
        			sb.append(cook[curr] + "\n");
        			
        			// 2-2 연결되어 있는 간선을 제거
        			for (int i = 1; i < V + 1; i++) {
        				if (adj[curr][i] == 1) {
        					degree[i]--;	// 진입차수 1만큼 감소
        					
        					// 아예 간선을 삭제한다면
        					// adj[curr][i] = 0;
        					
        					// 2-3 진입차수가 0이라면 다음 순서로 queue에 추가
        					if (degree[i] == 0) queue.offer(i);
        				}
        				
        			}
        		}
        		
        		System.out.println(sb);
        	}
        ```