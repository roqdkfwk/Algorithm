package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686_치킨_배달 {

	static int N;
	static int[][] map;
	static int[][] dist;	// 집집마다 치킨 거리를 저장할 배열
	static int[] minDist;	// 
	static boolean[] visit;
	static int H;	// 집의 개수
	static int C;	// 치킨집의 개수
	static int M;	// 폐업 후 남길 치킨집의 최대 수
	static List<int[]> home;	// 집의 위치를 저장할 리스트
	static List<int[]> chicken;	// 치킨집의 위치를 저장할 리스트
	static int ans = Integer.MAX_VALUE;	// 치킨 거리의 최솟값
	
	// 도시의 치킨 거리 = 모든 집의 치킨 거리의 합
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
			
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {	// 집인 경우, 집의 개수를 1만큼 증가시키고 집의 위치를 home에 추가
					H++;
					home.add(new int[] {r, c});
				} else if (map[r][c] == 2) {	// 치킨집인 경우, 치킨집의 개수를 1만큼 증가시키고 치킨집의 위치를 chicken에 추가
					C++;
					chicken.add(new int[] {r, c});
				}
			}	// c에 대한 for문
		}	// r에 대한 for문
		
		dist = new int[H][C];
		
		visit = new boolean[C];
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < C; c++) {
				
				dist[r][c] = Math.abs(home.get(r)[0] - chicken.get(c)[0])
							+ Math.abs(home.get(r)[1] - chicken.get(c)[1]); 
			}
		}	// dist배열을 완성시키는 for문
		
		choose(0, 0);
		System.out.println(ans);	
	}
	
	static void choose(int idx, int count) {	// 치킨집, 치킨 거리, 선택한 치킨 집의 개수

		// 마지막 치킨집까지 고려가 끝났거나 || 최대로 선택 가능한 치킨집의 개수를 채운 경우
		if (idx == C || count == M) {
			if (count == 0) {
				return;
			} else {
				ans = Math.min(ans, calDistance());
				return;				
			}
		}
		
		// 치킨집을 선택하는 경우
		visit[idx] = true;
		choose(idx + 1, count + 1);
		// 치킨집을 선택하지 않는 경우
		visit[idx] = false;
		choose(idx + 1, count);
	}	// choose 메소드
	
	// 선택된 치킨집들을 기준으로 도시의 치킨 거리를 계산하는 메소드
	static int calDistance() {
		
		int sum = 0;
		
		minDist = new int[H];
		for (int i = 0; i < H; i++) minDist[i] = Integer.MAX_VALUE;
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < C; j++) {
				if (visit[j]) {
					
					if (minDist[i] > dist[i][j]) minDist[i] = dist[i][j];
				}
			}
		}
		
		for (int i = 0; i < H; i++) sum += minDist[i];
		
		return sum;
	}	// calDistance 메소드
}