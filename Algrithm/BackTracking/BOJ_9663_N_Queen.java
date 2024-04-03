package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663_N_Queen {

	static int N;
	static boolean[][] chess;
	static int[] dr = {-1, -1, -1};	// 좌상, 상, 우상
	static int[] dc = {-1, 0, 1};	// 좌상, 상, 우상
	static int count = 0;
	static boolean flag;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		chess = new boolean[N][N];
		
		queen(0);
		System.out.println(count);
	}
	
	static void queen(int row) {	// 다음 queen을 놓을 열을 매개변수로 받음
		
		// row == N이라면 마지막(N - 1) 행에 퀸을 놓은 것이므로 퀸을 놓을 수 있는 경우의 수를 1만큼 증가시킴
		if (row == N) {
			count++;
			return;
		}
		
		for (int col = 0; col < N; col++) {
			
			flag = true;
			for (int i = 0; i < 3; i++) {
				
				int C = col + dc[i];
				int R = row + dr[i];
				while (R >= 0 && C >= 0 && C < N) {	// queen이 배열의 범위를 벗어나지 않으면 계속 진행
					
					if (chess[R][C] == true) {	// R번째 열에 놓이는 퀸을 기준으로 좌상, 상, 우상 방향에 또 다른 퀸이 존재한다면 while문을 종료 
						flag = false;
						break;
					}
					
					R += dr[i];
					C += dc[i];
				}
				
				if (!flag) break;
			}
			
			if (flag) {
				
				// 좌상, 상, 우상 방향에 또 다른 퀸이 없다면 while문을 빠져나와 해당 행, 열에 퀸을 놓고
				chess[row][col] = true;
				// 다음 행에 퀸을 놓을 수 있는 자리를 찾는다.
				queen(row + 1);
				// (row, col)에 대한 탐색을 마쳤으면 (row, col + 1)에 대한 탐색을 수행하기 위해 퀸을 제거
				chess[row][col] = false;
			}
		}	// c에 대한 for문
	}	// queen 메소드
}