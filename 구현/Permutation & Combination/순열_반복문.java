package 순열조합;

public class 순열_반복문 {

	static int[] Set = {1, 2, 3, 4};
	
	public static void main(String[] args) {
		Permutation(Set.length, 3);
	}
	
	// n개 중 r개를 선택하는 순열을 구현하는 메소드
	static void Permutation(int n, int r) {
		
		for (int i = 0; i < Set.length; i++) {
			for (int j = 0; j < Set.length; j++) {
				if (i == j)	// 같은 값을 두 번 선택할 수는 없으므로 i == j인 경우는 고려하지 않고 넘어간다. 
					continue;
				for (int k = 0; k < Set.length; k++) {
					if (i == k || j == k)	// 위와 마찬가지로 동일한 값을 두 번 선택한 경우 넘어간다.
						continue;
					System.out.println(Set[i] + " " + Set[j] + " " + Set[k]);
				}
			}
		}
	}	// Permutation
}
