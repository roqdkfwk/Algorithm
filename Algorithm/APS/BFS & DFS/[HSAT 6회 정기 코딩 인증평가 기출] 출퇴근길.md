# [HSAT 6회 정기 코딩 인증평가 기출] 출퇴근길

---

### 문제 정보

**Java기준**

**제한시간 : `4초`**

**제한메모리 : `1024MB`**

**문제 출처 :** https://softeer.ai/practice/6248/history?questionType=ALGORITHM

---

### 정답 코드

```java
public class Main {

    static int n, m;
    static int S, T;
    static List<Integer>[] adj, adjR;
    static boolean[] fromS, toS, fromT, toT;
    static int answer = -2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        adjR = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
            adjR[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            adj[A].add(B);
            adjR[B].add(A);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        fromS = new boolean[n + 1];
        fromS[T] = true;
        dfs(S, adj, fromS);

        fromT = new boolean[n + 1];
        fromT[S] = true;
        dfs(T, adj, fromT);

        toS = new boolean[n + 1];
        dfs(S, adjR, toS);

        toT = new boolean[n + 1];
        dfs(T, adjR, toT);

        for (int i = 1; i <= n; i++) {
            if (fromS[i] && fromT[i] && toS[i] && toT[i]) {
                answer++;
            }
        }

        System.out.print(answer);
    }  // main

    static void dfs(int start, List<Integer>[] mat,  boolean[] visit) {
        if (visit[start]) {
            return;
        }
        
        visit[start] = true;
        for (int next : mat[start]) {
            dfs(next, mat, visit);
        }
    }
}
```

---