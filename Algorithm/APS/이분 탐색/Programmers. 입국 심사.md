# 입국심사

<aside>
🚨 **문제 출처**

**프로그래머스. 입국심사**

</aside>

<aside>
📖 풀이 알고리즘

- **이분탐색(BinarySearch)**
</aside>

<aside>
📖 **문제 아이디어**

- `'모든 사람이 삼사를 받는데 걸리는 시간의 최솟값'`을 찾는 **최적화 문제**
- 시간이 증가하면 심사할 수 있는 사람의 수도 증가한다. 즉, 답이 `'예'`인 지점부터 계속 `'예'`인 단조성을 가짐
- **최소 시간과 최대 시간 사이에 정답이 존재**
- 입력 값의 범위가 매우 크기 때문에 선형 탐색을 쓸 수 없다.

→ 따라서 이분탐색이라고 결정지을 수 있어야함

</aside>

<aside>
⌨️ **코드**

</aside>

- `calculate(long MID)`
    
    ```java
    private boolean calculate(long MID) {
        long count = 0;  // 심사할 수 있는 사람 수
        for (int i = 0; i < size; i++) {
            count += (MID / times[i]); // MID분이 지났을 때 i번째 심사관이 심사할 수 있는 사람 수를 모두 더함
            if (count >= n) {          // key : 일찍 종료하는 조건을 추가해야 시간 내에 들어올 수 있음
                return true;
            }
        }
            
        if (count < n) {
            return false;
        }
            
        return true;
    }
    ```
    

- 정답 코드
    
    ```java
    class Solution {
        
        int n, size;
        int[] times;
        
        public long solution(int n, int[] times) {
            this.n = n;
            this.times = times;
            this.size = times.length;
            
            long left = 0;
            long right = Long.MAX_VALUE;
            while (left < right) {
                long mid = left + (right - left) / 2;  // 오버플로우를 방지하기 위해서 왼쪽과 같이 표현하는 방법 익혀두기
                if (calculate(mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            
            return right;
        }
        
        private boolean calculate(long MID) {
            long count = 0;  // 심사할 수 있는 사람 수
            for (int i = 0; i < size; i++) {
                count += (MID / times[i]); // MID분이 지났을 때 i번째 심사관이 심사할 수 있는 사람 수를 모두 더함
                if (count >= n) {
                    return true;
                }
            }
            
            if (count < n) {
                return false;
            }
            
            return true;
        }
    }
    ```