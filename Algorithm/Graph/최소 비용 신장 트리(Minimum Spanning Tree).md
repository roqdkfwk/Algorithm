# 최소 비용 신장 트리(Minimum Spanning Tree)

- **신장 트리**
    - 그래프의 모든 정점과 간선의 부분 집합으로 구성되는 트리

- **최소 신장 트리**
    - 신장 트리 중에서 사용된 **간선들의 가중치 합이 최소인 트리**
    - 무방향 가중치 그래프
    - N개의 정점을 가지는 그래프에 대해 **반드시 (N - 1)개의 간선을 사용**
    - 사이클을 포함하지 않는다.

- **사용하는 이유**
    - 도로망, 통신망, 유통망 등등 여러 분야에서 비용을 최소로 해야 이익을 볼 수 있다.
    - 대표적인 알고리즘으로 **크루스칼**, **프림**이 있다.
- **풀어 볼 만한 문제**
    - BOJ_1197 - [최소 스패닝 트리](https://www.acmicpc.net/problem/1197)
    - BOJ_1922 - [네트워크 연결](https://www.acmicpc.net/problem/1922)
    - SWEA_1251 - [하나로](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD&categoryId=AV15StKqAQkCFAYD&categoryType=CODE&problemTitle=1251&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)
    - SWEA_3289 - [서로소 집합](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr&categoryId=AWBJKA6qr2oDFAWr&categoryType=CODE&problemTitle=3289&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)