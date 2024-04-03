package 순열조합;

import java.util.Arrays;

public class 순열_재귀 {

	static int[] Set = { 1, 2, 3, 4 };
	static int[] permArr;
	static boolean[] visit = new boolean[Set.length];

	public static void main(String[] args) {

		// 선택할 원소의 개수만큼의 크기를 갖는 순열 배열을 선언
		permArr = new int[3];
		Permutation(Set.length, 3, 0);
	}

	// n개 중 r개를 선택하는 순열을 구현하는 메소드, count는 선택한 수의 개수
	static void Permutation(int n, int r, int count) {

		if (count == r) {	// r개의 수를 모두 선택했으면 선택한 수를 출력 후 메소드를 종료
			System.out.println(Arrays.toString(permArr));
			return;
		}

		for (int i = 0; i < Set.length; i++) {
			if (visit[i]) continue; // 이미 선택한 원소이면 다음 원소로 넘어감

			visit[i] = true;	// 선택
			permArr[count] = Set[i];
			Permutation(n, r, count + 1);	// 다음 원소를 선택하기 위한 재귀호출
			visit[i] = false;	// 원복
		}
	} // Permutation
}
