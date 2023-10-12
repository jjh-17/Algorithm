package a1012.camp;

import java.util.*;
import java.io.*;

public class bj_g4_17069_파이프옮기기2 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static long[][][] dp;
	static final int R=0, D=1, RD=2;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_17069_파이프옮기기2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		DP
		dp = new long[N][N][3];
		dp[0][1][R]=1L;

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(checkR(i, j)) dp[i][j+1][R] += dp[i][j][R] + dp[i][j][RD];
				if(checkD(i, j)) dp[i+1][j][D] += dp[i][j][D] + dp[i][j][RD];
				if(checkRD(i, j)) dp[i+1][j+1][RD] += dp[i][j][R] + dp[i][j][D] + dp[i][j][RD];
			}
		}
		
//		출력
		sb.append(dp[N-1][N-1][R] + dp[N-1][N-1][D] + dp[N-1][N-1][RD]);
		System.out.println(sb.toString());
		br.close();
	}
	
//	좌표 (i, j)에서 우측 방향으로 파이프를 옮길 수 있는지 여부
	static boolean checkR(int i, int j) {
		return j<N-1 && map[i][j+1]==0;
	}
	
//	좌표 (i, j)에서 하단 방향으로 파이프를 옮길 수 있는지 여부
	static boolean checkD(int i, int j) {
		return i<N-1 && map[i+1][j]==0;
	}
	
//	좌표 (i, j)에서 우하단 방향으로 파이프를 옮길 수 있는지 여부
	static boolean checkRD(int i, int j) {
		return i<N-1 && j<N-1 && map[i+1][j]==0 && map[i+1][j+1]==0 && map[i][j+1]==0;
	}
}