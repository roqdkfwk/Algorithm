# 이진검색(BinarySearch)

## 이진검색(BinarySearch)

- 자료의 가운데에 있는 항목의 키 값과 비교하여 다음 검색의 위치를 결정하고 검색을
계속 진행하는 방법
    - 목적 키를 찾을 때까지 이진 검색을 순환적으로 반복 수행함으로써 검색 범위를 
    반으로 줄여가면서 보다 빠르게 검색을 수행함
- 이진 검색을 하기 위해서는 자료가 정렬된 상태여야 한다.
- 검색과정
    - 자료의 중앙에 있는 원소를 고른다.
    - 중앙 원소의 값과 찾고자 하는 목표 값을 비교한다.
    - 중앙 원소의 값과 찾고자 하는 목표 값이 일치하면 탐색을 종료한다.
    - 목표 값이 중앙 원소의 값보다 작으면 자료의 왼쪽 반에 대해서 새로 검색을 수행하고, 크다면 자료의 오른쪽 반에 대해서 새로 검색을 수행한다.
    - 찾고자 하는 값을 찾을 때까지 위의 과정을 반복한다.

- 반복문을 이용한 이진검색

```java
static boolean binarySearch(int[] arr, int key) {
		
		int left = 0;
		int right = arr.length - 1;
		
		while (left <= right) {
			
			int mid = (left + right) / 2;
			
			if (arr[mid] == key) return true;
			else if (arr[mid] > key) {
				right = mid - 1;
			} else 
				right = mid + 1;
		}
		
		return false;
	}
```

- 재귀를 이용한 이진검색

```java
public static boolean binarySearch(int st, int ed) {

		if (st > ed) return false; // 교차가 된 경우 못찾은 것이니 그만함

		// 조심할 점
		// st와 ed가 너무 큰 수인 경우 st + ed가 int형의 범위를 벗어날 수도 있다.
		int mid = (st + ed) / 2;

		// key값과 같다면
		if (arr[mid] == key)
			return true;
		// key값 보다 크다면(왼쪽 구간)
		else if (arr[mid] > key)
			return binarySearch(st, mid - 1);
		// key값 보다 작다면(오른쪽 구간)
		else
			return binarySearch(mid + 1, ed);

	}
```