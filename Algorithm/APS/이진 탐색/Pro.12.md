# Pro. 12

---

- 시간초과

```java
class UserSolution {

    static int found;
    static boolean[] foundCard;
    static List<Integer>[] diff;

    public void playGame(int N) {
        found = 0;
        foundCard = new boolean[2 * N];
        diff = new ArrayList[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            diff[i] = new ArrayList<>(); // 1만큼 차이
        }

        for (int A = 0; A < (2 * N) - 1; A++) {
            if (foundCard[A]) continue;
            for (int B = A + 1; B < 2 * N; B++) {			    	// A와의 차이가 1 이하인 모든 숫자의 위치를 찾음
                if (foundCard[B]) continue;

                if (Solution.checkCards(A, B, 1)) {		  		// 차이가 1 이하인 경우
                    if (Solution.checkCards(A, B, 0)) {			// 같은 경우
                        foundCard[A] = foundCard[B] = true;
                        found++;
                    } else {						              			// 차이가 1인 경우
                        diff[A].add(B);
                    }
                }
            }   // B에 대한 for

            // 차이가 1인 숫자들끼리 짝지음
            if (diff[A].isEmpty()) {            // 빈 배열인 경우
                continue;
            } else if (diff[A].size() == 2) {   // 사이즈가 2인 경우 두 숫자는 같은 경우
                Solution.checkCards(diff[A].get(0), diff[A].get(1), 0);
                foundCard[diff[A].get(0)] = foundCard[diff[A].get(1)] = true;
                found++;
            } else {                            // 사이즈가 4인 경우 세 가지 경우의 수가 존재
                if (Solution.checkCards(diff[A].get(0), diff[A].get(1), 0)) {
                    Solution.checkCards(diff[A].get(2), diff[A].get(3), 0);
                    foundCard[diff[A].get(0)] = foundCard[diff[A].get(1)]
                            = foundCard[diff[A].get(2)]
                            = foundCard[diff[A].get(3)]
                            = true;
                    found += 2;
                } else if (Solution.checkCards(diff[A].get(0), diff[A].get(2), 0)) {
                    Solution.checkCards(diff[A].get(1), diff[A].get(3), 0);
                    foundCard[diff[A].get(0)] = foundCard[diff[A].get(1)]
                            = foundCard[diff[A].get(2)]
                            = foundCard[diff[A].get(3)]
                            = true;
                    found += 2;
                } else {
                    Solution.checkCards(diff[A].get(0), diff[A].get(3), 0);
                    Solution.checkCards(diff[A].get(1), diff[A].get(2), 0);
                    foundCard[diff[A].get(0)] = foundCard[diff[A].get(1)]
                            = foundCard[diff[A].get(2)]
                            = foundCard[diff[A].get(3)]
                            = true;
                    found += 2;
                }
            }

            if (found == N) break;
        }   // A에 대한 for
    }   // playGame
}
```