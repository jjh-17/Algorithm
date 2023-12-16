package dp;

import java.util.*;
import java.io.*;

public class bj_s2_11048_이동하기 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {1, 0},
					   dj = {0, 1};
	static int N, M;
	static int[][] candy;
	static long[][] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s2_11048_이동하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		candy = new int[N+1][M+1];
		dp = new long[N+1][M+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) candy[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//DP
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				dp[i][j] = Long.max(dp[i-1][j-1], Long.max(dp[i-1][j], dp[i][j-1])) + candy[i][j];
			}
		}
		
		//출력
		sb.append(dp[N][M]);
		System.out.println(sb.toString());
		br.close();
	}
}
