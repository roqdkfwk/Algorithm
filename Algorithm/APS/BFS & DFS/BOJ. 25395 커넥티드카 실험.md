# BOJ. 25395 커넥티드카 실험

<aside>
🚨 **문제 출처**

[B](https://www.acmicpc.net/problem/1167)OJ. 25395 커넥티드카 실험

</aside>

<aside>
📖 알고리즘 분류

- 그래프 이론
- 그래프 탐색
- 너비 우선 탐색
- 두 포인터
</aside>

<aside>
📖 **문제 아이디어**

- 범위 내의 차를 찾는다.
    
    → BFS
    
- 이미 연결된 차는 다시 고려할 필요가 없으니 방문처리배열 필요
- 시간을 줄이기 위해서 **가까운 차부터 순서대로 고려**한다.
- 이동할 수 있는 **범위를 벗어난 차가 나온 경우 함수를 종료**한다.
</aside>

<aside>
🚨 **실수했던 점**

- 방문처리를 안해서 고려했던 차를 계속 큐에 추가했었다.
- 가까운 차부터 고려하지 않고 순서대로 고려했고,
- 범위를 벗어난 차도 고려해서 시간 복잡도가 크게 증가했었다.
</aside>

<aside>
⌨️ **코드**

</aside>

- 시간초과
    - `searchLeft(Car car)`
        
        ```java
        static void searchLeft(Car car) {
        	int index = car.num;
        
        	if (index == 1) { // 가장 왼쪽 차량이면 더 이상 탐색할 필요 없음
        		return;
        	}
        	
        	// 시간초과 발생부분
        	// 기준 차량과 가까운 차부터 탐색하며 연료가 다 떨어지면 더 이상 탐색할 필요가 없다.
        	for (int i = 1; i < index - 1; i++) {
        		int distance = cars.get(index).pos - cars.get(i).pos;
        
        		if (!visit[i] && cars.get(index).fuel >= distance) {
        			visit[i] = true;
        			answer.add(i);
        			queue.add(cars.get(i));
        		}
        	}
        }
        ```
        
    
    - `searchRight(Car car)`
        
        ```java
        static void searchRight(Car car) {
        	int index = car.num;
        
        	if (index == N) { // 가장 오른쪽 차량이면 더 이상 탐색할 필요 없음
        		return;
        	}
        
        	// 위에서와 마찬가지로 연료가 다 떨어진 경우 탐색을 중지하면 된다.
        	for (int i = index + 1; i <= N; i++) {
        		int distance = cars.get(i).pos - cars.get(index).pos;
        
        		if (!visit[i] && cars.get(index).fuel >= distance) {
        			visit[i] = true;
        			answer.add(i);
        			queue.add(cars.get(i));
        		}
        	}
        }
        ```
        
    
    - 전체 코드
        
        ```java
        import java.util.*;
        import java.io.*;
        
        public class Main {
        	
        	static class Car {
        		int num, pos, fuel;
        		
        		public Car (int n, int p, int f) {
        			this.num = n;
        			this.pos = p;
        			this.fuel = f;
        		}
        	}
        
        	static int N, S;
        	static int[] pos, fuel;
        	static boolean[] visit;
        	static Queue<Car> queue;
        	static List<Car> cars;
        	static List<Integer> answer;
        	
        	public static void main(String[] args) throws IOException {
        		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        		StringBuilder sb = new StringBuilder();
        		StringTokenizer st;
        		
        		st = new StringTokenizer(br.readLine());
        		N = Integer.parseInt(st.nextToken());
        		S = Integer.parseInt(st.nextToken());
        		
        		pos = new int[N + 1];
        		fuel = new int[N + 1];
        		visit = new boolean[N + 1];
        		
        		st = new StringTokenizer(br.readLine());
        		for (int i = 1 ; i <= N; i++) {
        			pos[i] = Integer.parseInt(st.nextToken());
        		}
        		
        		st = new StringTokenizer(br.readLine());
        		for (int i = 1; i <= N; i++) {
        			fuel[i] = Integer.parseInt(st.nextToken());
        		}
        		
        		cars = new ArrayList<>();
        		cars.add(null);
        		for (int i = 1; i <= N; i++) {
        			cars.add(new Car(i, pos[i], fuel[i]));
        		}
        		
        		queue = new LinkedList<>();
        		queue.add(cars.get(S));		// 시작하는 커넥티드카
        		
        		answer = new ArrayList<Integer>();
        		answer.add(cars.get(S).num);
        		visit[cars.get(S).num] = true;
        		
        		while (!queue.isEmpty()) {	// bfs
        			Car curr = queue.poll();
        			
        			searchLeft(curr);		  // 좌로 탐색
        			searchRight(curr);		// 우로 탐색
        		}
        		
        		answer.sort(null);
        		for (int num : answer) {
        			sb.append(num).append(" ");
        		}
        		
        		System.out.println(sb);
        	}
        	
        	static void searchLeft(Car car) {
                int index = car.num;
                
                if (index == 1) { // 가장 왼쪽 차량이면 더 이상 탐색할 필요 없음
                    return;
                }
                
                for (int i = 1; i < index; i++) {
                	int distance = cars.get(index).pos - cars.get(i).pos;
                	
                	if (!visit[i] && cars.get(index).fuel >= distance) {
                		visit[i] = true;
                		answer.add(i);
                		queue.add(cars.get(i));
                	}
               }
            }
            
            static void searchRight(Car car) {
                int index = car.num;
                
                if (index == N) { // 가장 오른쪽 차량이면 더 이상 탐색할 필요 없음
                    return;
                }
                
                for (int i = index + 1; i <= N; i++) {
                	int distance = cars.get(i).pos - cars.get(index).pos;
                	
                	if (!visit[i] && cars.get(index).fuel >= distance) {
                		visit[i] = true;
                		answer.add(i);
                		queue.add(cars.get(i));
                	}
               }
            }
        }
        ```
        
- 정답 코드
    - `searchLeft(Car car)`
        
        ```java
        static void searchLeft(Car car) {
        	int index = car.num;
        
        	if (index == 1) { // 가장 왼쪽 차량이면 더 이상 탐색할 필요 없음
        			return;
        	}
        
        	for (int i = index - 1; i > 0; i--) {
        			int distance = cars.get(index).pos - cars.get(i).pos;
        
        		// 남은 차는 고려하지 않고 함수를 종료한다.
        		if (cars.get(index).fuel < distance)
        				break;
        
        		if (!visit[i]) {
        				visit[i] = true;
        				answer.add(i);
        				queue.add(cars.get(i));
        		}
        	}
        }
        ```
        
    
    - `searchRight(Car car)`
        
        ```java
        static void searchRight(Car car) {
        	int index = car.num;
        
        	if (index == N) { // 가장 오른쪽 차량이면 더 이상 탐색할 필요 없음
        			return;
        	}
        
        	for (int i = index + 1; i <= N; i++) {
        			int distance = cars.get(i).pos - cars.get(index).pos;
        
        		// 남은 차는 고려하지 않고 함수를 종료한다.
        		if (cars.get(index).fuel < distance)
        				break;
        
        		if (!visit[i]) {
        				visit[i] = true;
        				answer.add(i);
        				queue.add(cars.get(i));
        		}
        	}
        }
        ```
        
    
    - 전체 코드
        
        ```java
        import java.util.*;
        import java.io.*;
        
        public class BOJ_25395 {
        
        	static class Car {
        		int num, pos, fuel;
        
        		public Car(int n, int p, int f) {
        			this.num = n;
        			this.pos = p;
        			this.fuel = f;
        		}
        	}
        
        	static int N, S;
        	static int[] pos, fuel;
        	static boolean[] visit;
        	static Queue<Car> queue;
        	static List<Car> cars;
        	static List<Integer> answer;
        
        	public static void main(String[] args) throws IOException {
        		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        		StringBuilder sb = new StringBuilder();
        		StringTokenizer st;
        
        		st = new StringTokenizer(br.readLine());
        		N = Integer.parseInt(st.nextToken());
        		S = Integer.parseInt(st.nextToken());
        
        		pos = new int[N + 1];
        		fuel = new int[N + 1];
        		visit = new boolean[N + 1];
        
        		st = new StringTokenizer(br.readLine());
        		for (int i = 1; i <= N; i++) {
        			pos[i] = Integer.parseInt(st.nextToken());
        		}
        
        		st = new StringTokenizer(br.readLine());
        		for (int i = 1; i <= N; i++) {
        			fuel[i] = Integer.parseInt(st.nextToken());
        		}
        
        		cars = new ArrayList<>();
        		cars.add(null);
        		for (int i = 1; i <= N; i++) {
        			cars.add(new Car(i, pos[i], fuel[i]));
        		}
        
        		queue = new LinkedList<>();
        		queue.add(cars.get(S)); // 시작하는 커넥티드카
        
        		answer = new ArrayList<Integer>();
        		answer.add(cars.get(S).num);
        		visit[cars.get(S).num] = true;
        
        		while (!queue.isEmpty()) {	// bfs
        			Car curr = queue.poll();
        
        			searchLeft(curr);		// 좌로 탐색
        			searchRight(curr);		// 우로 탐색
        		}
        
        		answer.sort(null);
        		for (int num : answer) {
        			sb.append(num).append(" ");
        		}
        
        		System.out.println(sb);
        	}
        
        	static void searchLeft(Car car) {
        		int index = car.num;
        
        		if (index == 1) { // 가장 왼쪽 차량이면 더 이상 탐색할 필요 없음
        			return;
        		}
        
        		for (int i = index - 1; i > 0; i--) {
        			int distance = cars.get(index).pos - cars.get(i).pos;
        
        			if (cars.get(index).fuel < distance)
        				break;
        
        			if (!visit[i]) {
        				visit[i] = true;
        				answer.add(i);
        				queue.add(cars.get(i));
        			}
        		}
        	}
        
        	static void searchRight(Car car) {
        		int index = car.num;
        
        		if (index == N) { // 가장 오른쪽 차량이면 더 이상 탐색할 필요 없음
        			return;
        		}
        
        		for (int i = index + 1; i <= N; i++) {
        			int distance = cars.get(i).pos - cars.get(index).pos;
        
        			if (cars.get(index).fuel < distance)
        				break;
        
        			if (!visit[i]) {
        				visit[i] = true;
        				answer.add(i);
        				queue.add(cars.get(i));
        			}
        		}
        	}
        }
        
        ```