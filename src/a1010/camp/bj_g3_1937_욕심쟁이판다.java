package a1010.camp;

import java.util.*;
import java.io.*;

public class bj_g3_1937_욕심쟁이판다 {

	static final StringBuilder sb = new StringBuilder();
	static int N, ANS;						// 대나무 숲의 크기, 정답
	static int[][] map, dp;					// 대나무 숲 정보, 해당 좌표에서 최대한 뻗어나 갈 수있는 개수
	static final int di[] = {-1, 0, 1, 0};	// 4방 탐색
	static final int dj[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g3_1937_욕심쟁이판다.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N]; dp = new int[N][N];
		ANS = 0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		알고리즘
		int ans=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				ans = Integer.max(ans, dfs(i, j, 1));
			}
		}
		
		for(int[] d : dp) System.out.println(Arrays.toString(d));
		
//		출력
		sb.append(ANS);
		System.out.println(sb.toString());
		br.close();
	}
	
//	현 좌표에서 대나무가 더 많은 곳으로 이동
//	현 좌표 [i, j], 현재 이동 횟수 cnt
	static int dfs(int i, int j, int cnt) {
		dp[i][j] = cnt;
		
		for(int d=0;d<4;d++) {
			int ni = i+di[d];
			int nj = j+dj[d];
			
			if(ni<0||ni>=N || nj<0||nj>=N) continue;
			if(map[i][j] > map[ni][nj]) {
				if(dp[ni][nj]==0) dfs(ni, nj, cnt+1);
				else
					ANS = Integer.max(ANS, dp[ni][nj]+cnt-1);
			}
		}
		
		return 
	}

}


















