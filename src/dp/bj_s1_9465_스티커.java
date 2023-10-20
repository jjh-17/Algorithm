package dp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_s1_9465_스티커 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] sticker;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_9465_스티커.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
//			입력
			N = Integer.parseInt(br.readLine());
			
			sticker = new int[2][N+1];
			for(int i=0;i<2;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1;j<=N;j++) sticker[i][j] = Integer.parseInt(st.nextToken());
			}
			
//			DP
			dp = new int[2][N+1];
			dp[0][1] = sticker[0][1]; dp[1][1] = sticker[1][1];
			for(int j=2;j<=N;j++) {
				dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + sticker[0][j];
				dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + sticker[1][j];
			}
			
			sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
