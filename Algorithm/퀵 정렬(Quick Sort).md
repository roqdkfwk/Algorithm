# 퀵 정렬(Quick Sort)

- 주어진 배열을 두 개로 분할하고, 각각을 정렬한다.
병합정렬과 비슷하지만 다르다.

- 병합정렬과 다른 점
    - 병합 정렬은 그냥 두 부분으로 나누는 반면, 퀵 정렬은 분할할 때 기준 아이템
    (pivot item) 중심으로, 이보다 작은 것은 왼편, 큰 것은 오른편에 위치시킨다.
    - 각 부분 정렬이 끝난 후, 병합정렬은 “병합”이란 후처리 작업이 필요하지만 퀵 정렬은 필요로 하지 않는다.
    - 불안정 정렬
    - 시간 복잡도는 O(nlog n), 최악의 경우 O(n^2)이다.

- 동작 과정
    - 정렬한 배열 입력
    - 임의의 한 점을 pivot으로 설정(Partition 방법)
        - pivot보다 작은 값들은 왼쪽으로, 큰 값들은 오른쪽으로 이동

- 호어(Hoare)파티션
    - P값보다 큰 값은 오른쪽, 작은 값은 왼쪽 집합에 위치하도록 한다.
    - 피봇을 두 집합의 가운데에 위치시킨다.
    
    ```java
    static int[] arr = {5, 22, 32, 26, 57, 19, 32, 55, 84};
    
    	public static void main(String[] args) {
    		
    		quickSort(0, arr.length - 1);
    		System.out.println(Arrays.toString(arr));
    	}
    	
    	static void quickSort(int left, int right) {
    		
    		if (left < right) {
    			int pivot = partition(left, right);  // 피봇의 위치를 받아옴
    			quickSort(left, pivot - 1);  // 피봇은 자기 위치를 찾아갔으니
    			quickSort(pivot + 1, right); // 피봇기준 왼쪽, 오른쪽 다시 quickSort
    		}
    	}
    
    	// 호어파티션
    	static int partition(int left, int right) {
    		
    		// 제일 왼쪽의 값을 피봇으로 설정
    		int pivot = arr[left];
    		
    		int L = left + 1, R = right;	// 시작은 피봇 한 칸 옆부터
    		
    		while (L <= R) {
    			
    			// L : pivot보다 큰 값을 찾을 때 까지 이동
    			while (L <= R && arr[L] <= pivot) L++;
    			// R : pivot보다 작거나 같은 값을 만날 떄 까지 이동
    			while (arr[R] > pivot) R--;
    			
    			// 아직 교차하지 않았다면
    			if (L < R) {
    				int tmp = arr[L];
    				arr[L] = arr[R];
    				arr[R] = tmp;
    			}
    		}
    		
    		// R이 피봇이 위치해야할 곳 까지 이동을 마친 상태
    		int tmp = arr[left];
    		arr[left] = arr[R];
    		arr[R] = tmp;
    		
    		return R;	// 피봇값의 위치
    	}
    ```
    
- 로무토(Lomuto)파티션
    
    ```java
    static int[] arr = {5, 22, 32, 26, 57, 19, 32, 55, 84 };
    
    	public static void main(String[] args) {
    
    		quickSort(0, arr.length - 1);
    		System.out.println(Arrays.toString(arr));
    	}
    
    	static void quickSort(int left, int right) {
    
    		if (left < right) {
    			int pivot = partition(left, right);
    			quickSort(left, pivot - 1);
    			quickSort(pivot + 1, right);
    		}
    	}
    	
    	// 로무토 파티션
    	static int partition(int left, int right) {
    		
    		int pivot = arr[right];
    		int i = left - 1;
    		
    		for (int j = left; j < right; j++) {
    			
    			if (arr[j] <= pivot) {
    				i++;
    				int tmp = arr[i];
    				arr[i] = arr[j];
    				arr[j] = tmp;
    			}
    		}
    		
    		// pivot을 자기 위치로 보내야 함
    		int tmp = arr[i + 1];
    		arr[i + 1] = arr[right];
    		arr[right] = tmp;
    		
    		return i + 1;
    	}
    ```