package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182_부분수열의_합 {
	
	static int N;
	static int[] arr;
	static int S;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		findSum(0, 0);
		
		// S == 0이면 아무것도 선택하지 않아도 count가 1만큼 증가하므로 다시 빼줌
		if (S == 0) count--;
		
		System.out.println(count);
	}
	
	static void findSum(int depth, int summation) {
		
		// 실수한 점
		// depth == N 이면 메소드를 중지시켜야하는데
		// summation == S 조건을 걸어서 메소드가 배열의 범위를 벗어나도 실행이 되었음
//		if (depth == N && summation == S) {
//			count++;		
//			return;
//		}
		
		if (depth == N) {	// depth == N이면 메소드를 중지하기 위해서 return
			if (summation == S) count++;		
			return;
		}
		
		// depth번 index에 해당하는 값을 선택하는 경우
		findSum(depth + 1, summation + arr[depth]);
		// depth번 index에 해당하는 값을 선택하지 않는 경우
		findSum(depth + 1, summation);
	}
}


































