# BOJ. 1916 최소 비용 구하기

<aside>
🚨 **문제 출처**

[BOJ. 1916 최소 비용 구하기](https://www.acmicpc.net/problem/1916)

</aside>

<aside>
📖 **문제 아이디어**

도시는 정점으로, 버스 노선은 간선으로 생각하면 그래프 문제이고

특정 출발 지점에서 도착 지점까지 가는데 필요한 최소 비용을 구하는 문제로 간선의 가중치 합의 최솟값을 구하는 문제이므로 **다익스트라 알고리즘**으로 접근

</aside>

<aside>
❓ **생각해볼 것**
 - `Dijkstra` 메소드의 수행 과정에서 가중치가 최소인 간선을 포함하는 정점을 찾을 때, index를 -1로 초기화한 후 찾으면 index 에러가 발생하는 경우가 있다. index를 0으로 초기화하면 문제가 없었는데 -1인 경우 왜 이런 경우가 발생하는지 아직 찾지 못했다.
 - 두 개의 정점 사이에 가중치가 다른 여러 개의 노선이 존재하는 경우를 고려해서 우선순위 큐를 사용했다면 더 좋았을 것 같다. 이 문제에서는 해당 경우는 없었던 것 같다.

</aside>

```java
for (int i = 0; i < N - 1; i++) {
          
        int min = INF;
        int idx = -1;  // idx = -1인 경우 index에러가 발생했다. 이유는 아직 찾지 못함
          
        for (int j = 1; j < N + 1; j++) {
              
            if (!visit[j] && min > dist[j]) {
                min = dist[j];
                idx = j;
            }
        }
          
        visit[idx] = true;
          
        for (Node node : adj[idx]) {
            if (!visit[node.v] && dist[node.v] > dist[idx] + node.w)
                dist[node.v] = dist[idx] + node.w;
        }
    }
```

<aside>
⌨️ **코드**

</aside>

- `Dijkstra(int st)`
    - 다익스트라 알고리즘에 따라 가중치가 작은 간선들을 찾는 메소드
    
    ```java
    private static void Dijkstra(int st) {
            
        visit = new boolean[N + 1];
        dist[st] = 0;    // 시작 노드까지의 거리는 0
           
        for (int i = 0; i < N - 1; i++) {
              
            int min = INF;
            int idx = 0;
              
            for (int j = 1; j < N + 1; j++) {
                  
                if (!visit[j] && min > dist[j]) {
                    min = dist[j];
                    idx = j;
                }
            }
              
            visit[idx] = true;
              
            for (Node node : adj[idx]) {
                if (!visit[node.v] && dist[node.v] > dist[idx] + node.w)
                    dist[node.v] = dist[idx] + node.w;
            }
        }
    }    // Dijkstra
    ```
    

- 전체 코드
    
    ```java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;
    import java.util.StringTokenizer;
    
    public class BOJ_1916_최소_비용_구하기 {
    
        static int N, M;    // 도시의 개수, 버스의 개수
        static List<Node>[] adj;
        static int[] dist;
        static final int INF = 150000000;
        static boolean[] visit;
        static int Start, End;    // 출발 도시, 도착, 도시
        
        static class Node {
            
            int v, w;    // 도착 정점, 가중치
            
            public Node (int v, int w) {
                this.v = v;
                this.w = w;
            }
        }
        
        public static void main(String[] args) throws NumberFormatException, IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            
            adj = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++) 
                adj[i] = new ArrayList<>();
            
            for (int i = 0; i < M; i++) {
                
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                adj[from].add(new Node(end, weight));
            }    // i에 대한 for문
            
            dist = new int[N + 1];
            Arrays.fill(dist, INF);
            
            st = new StringTokenizer(br.readLine());
            Start = Integer.parseInt(st.nextToken());
            End = Integer.parseInt(st.nextToken());
            
            Dijkstra(Start);
            
            System.out.println(dist[End]);
        }    // main
    
        private static void Dijkstra(int st) {
            
            visit = new boolean[N + 1];
            dist[st] = 0;    // 시작 노드까지의 거리는 0
            
            
            for (int i = 0; i < N - 1; i++) {
                
                int min = INF;
                int idx = 0;
                
                for (int j = 1; j < N + 1; j++) {
                    
                    if (!visit[j] && min > dist[j]) {
                        min = dist[j];
                        idx = j;
                    }
                }
                
                visit[idx] = true;
                
                for (Node node : adj[idx]) {
                    if (!visit[node.v] && dist[node.v] > dist[idx] + node.w)
                        dist[node.v] = dist[idx] + node.w;
                }
            }
        }    // Dijkstra
    }
    ```