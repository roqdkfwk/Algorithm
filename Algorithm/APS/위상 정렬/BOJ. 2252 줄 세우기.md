# BOJ. 2252 줄 세우기

---

<aside>
🚨 **문제 출처**

[BOJ. 2252 줄 세우기](https://www.acmicpc.net/problem/2252)

</aside>

<aside>
📖 알고리즘 분류

- 그래프 이론
- 위상 정렬
- 방향 비순환 그래프
</aside>

<aside>
📖 **문제 아이디어**

- 학생 간의 순서 관계가 있고
- 방향성이 있는 관계이며
- 사이클이 없고
- 답이 여러 가지인 경우 등등
- 위의 조건으로 보아 위상 정렬임을 유추
</aside>

<aside>
❓ **생각해볼 것**

- **학생의 수가 최대 32,000**이므로 2차원 형태의 인접행렬을 선언하면 메모리초과
</aside>

<aside>
⌨️ **코드**

</aside>

- `union(int x, int y)`

  - 서로소 집합을 합치는 메소드

  ```java
  static void union(int x, int y) {

  	p[findset(y)] = findset(x);
  }
  ```

- `findset(int x)`

  - 집합의 대표자를 찾는 메소드

  ```java
  static int findset(int x) {

  	if (x != p[x])
  		p[x] = findset(p[x]);
  	return p[x];
  }
  ```

- 배열

  ```java
  public class BOJ_2522 {

  	static int N, M;
  	static int[] degree;
  	static List<Integer>[] adj;

  	public static void main(String[] args) throws IOException {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          StringBuilder sb = new StringBuilder();
          StringTokenizer st;

          st = new StringTokenizer(br.readLine());
          N = Integer.parseInt(st.nextToken());
          M = Integer.parseInt(st.nextToken());

          adj = new List[N + 1];
          degree = new int[N + 1];
          for (int i = 0; i <= N; i++) {
          	adj[i] = new ArrayList<>();
          }

          for (int i = 0; i < M; i++) {
              st = new StringTokenizer(br.readLine());
              int A = Integer.parseInt(st.nextToken());
              int B = Integer.parseInt(st.nextToken());

              adj[A].add(B);
              degree[B]++;
          }

          Queue<Integer> queue = new LinkedList<>();
          for (int i = 1; i <= N; i++) {
              if (degree[i] == 0) {
                  queue.add(i);
              }
          }

          while (!queue.isEmpty()) {
              int curr = queue.poll();
              sb.append(curr).append(" ");

              for (int next : adj[curr]) {
              	degree[next]--;
                  if (degree[next] == 0) {
                      queue.add(next);
                  }
              }
          }

          System.out.println(sb);
      }
  }
  ```

  | 메모리  | 시간  |
  | ------- | ----- |
  | 50700KB | 412ms |

- 리스트

```java
public class BOJ_2522 {

	static int N, M;
	static int[] degree;
	static List<List<Integer>> adj;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList<>();
        degree = new int[N + 1];
        for (int i = 0; i <= N; i++) {
        	adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adj.get(A).add(B);
            degree[B]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current).append(" ");

            for (int next : adj.get(current)) {
            	degree[next]--;
                if (degree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        System.out.println(sb);
    }
}
```

| 메모리  | 시간  |
| ------- | ----- |
| 52012KB | 452ms |
