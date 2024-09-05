# [HSAT 5회 정기 코딩 인증평가 기출] 성적 평가

---

### 문제 정보

**Java기준**

**제한시간 : `2초`**

**제한메모리 : `1024MB`**

**문제 출처 :** https://softeer.ai/practice/6250/history?questionType=ALGORITHM

---

### 정답 코드

```java
public class 성적_평가 {
	
	static class Person implements Comparable<Person> {
		int num, score;
		
		public Person() {}
		public Person(int num, int score) {
			this.num = num;
			this.score = score;
		}
		@Override
		public int compareTo(Person p) {
			return p.score - this.score;
		}
	}	// Person
	
	static int N;	// 참가자 수
	static int[][] ranking;	// 세 번의 대회의 등수를 저장할 배열
	static int[] sum;	//	세 대회 점수의 합을 저장할 배열 
	static int[] totalRanking;	// 세 대회 점수를 합한 등수를 저장할 배열
	static List<Person>[][] person;	// person[i번째 대회][0~1000점]
	static int rank, score;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		person = new ArrayList[3][1001];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1001; j++) {
				person[i][j] = new ArrayList<Person>();				
			}
		}
		
		N = Integer.parseInt(br.readLine());
		sum = new int[N];
		ranking = new int[3][N];
		totalRanking = new int[N];
		
		for (int i = 0; i < 3; i++) {
			rank = 1;
			
			st = new StringTokenizer(br.readLine());
			for (int idx = 0; idx < N; idx++) {
				score = Integer.parseInt(st.nextToken());
				person[i][score].add(new Person(idx, score));
			}
			
			for (int j = 1000; j >= 0; j--) {
				for (Person per : person[i][j]) {
					ranking[i][per.num] = rank;	// i번째 대회의 num번째 참가자의 등수는 rank
					sum[per.num] += per.score;	// i번째 대회의 num번째 참가자의 점수를 누적
				}
				rank += person[i][j].size();
			}	// j에 대한 for문
		}	// i에 대한 for문
		
		
		PriorityQueue<Person> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.add(new Person(i, sum[i]));
		}

		rank = 1;
		score = 0;
		Arrays.fill(totalRanking, 1);
		int idx = 0;
		while (!pq.isEmpty()) {
			Person p = pq.poll();
			if (p.score == score) {	// 점수가 같으면 이전 참가자와 같은 등수를
				totalRanking[p.num] = totalRanking[idx];
			} else {	// 점수가 다르면 앞의 참가자 수만큼 등수를 매김
				idx = p.num;
				score = p.score;
				totalRanking[idx] = rank;
			}
			rank++;
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(ranking[i][j] + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(totalRanking[i] + " ");
		}
	}	// main	
}
```

---