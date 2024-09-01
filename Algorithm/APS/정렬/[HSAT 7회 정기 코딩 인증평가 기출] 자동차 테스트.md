# [HSAT 7회 정기 코딩 인증평가 기출] 자동차 테스트

---

### 문제 정보

**Java기준**

**제한시간 : `3초`**

**제한메모리 : `1024MB`**

**문제 출처 :** https://softeer.ai/practice/6247

---

### 정답 코드

- `Collections 프레임워크의 binarySearch()메소드 사용`
- `시간복잡도가 O(logn)으로 제한시간 내에 계산 가능`

```java
public class Main {

    static int n, q;
    static ArrayList<Integer> arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  	StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

 		arr = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr.add(Integer.parseInt(st.nextToken()));
		
        Collections.sort(arr);

        for (int i = 0; i < q; i++) {
            int num = Integer.parseInt(br.readLine());            
            int idx = Collections.binarySearch(arr, num);
            int pre = idx;
            int pos = n - 1 - pre;

            if (idx < 0) {
                sb.append("0\n");
            } else {
                sb.append(pre * pos).append("\n");
            }
        }

        System.out.print(sb);
	}	// main
}
```

---

### 시간초과

- `indexOf() 메소드의 시간복잡도는 O(n)이므로 1≤n≤50,000이고 1≤q≤200,000이면 제한시간 초과`

```java
public class Main {

    static int n, q;
    static ArrayList<Integer> arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

    		arr = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr.add(Integer.parseInt(st.nextToken()));
		
        Collections.sort(arr);

        for (int i = 0; i < q; i++) {
            int num = Integer.parseInt(br.readLine());            
            int idx = arr.indexOf(num);
            int pre = idx;
            int pos = n - 1 - pre;

            if (idx == -1) {
                sb.append("0\n");
            } else {
                sb.append(pre * pos).append("\n");
            }
        }

        System.out.print(sb);
	}	// main
}
```
