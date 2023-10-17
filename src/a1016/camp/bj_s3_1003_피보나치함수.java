package a1016.camp;

import java.io.*;
import java.util.*;

public class bj_s3_1003_피보나치함수 {

	static final StringBuilder sb = new StringBuilder();
	static final int[][] dp = new int[41][2];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s3_1003_피보나치함수.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		dp[0][0]=1; dp[0][1]=0; dp[1][0]=0; dp[1][1]=1;
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(br.readLine());
			int[] cor = getDP(N);
			sb.append(cor[0]).append(" ").append(cor[1]).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static int[] getDP(int n) {
		if(n>=2 && dp[n][0]==0 && dp[n][1]==0) {
			int[] cor1 = getDP(n-1);
			int[] cor2 = getDP(n-2);
			dp[n][0] = cor1[0] + cor2[0];
			dp[n][1] = cor1[1] + cor2[1];
		}
		
		return new int[] {dp[n][0], dp[n][1]};
	}

}
