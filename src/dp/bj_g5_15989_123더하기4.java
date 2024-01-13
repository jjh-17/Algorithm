package dp;

import java.util.*;
import java.io.*;

public class bj_g5_15989_123더하기4 {

	private static final StringBuilder sb = new StringBuilder();
	static int T, n;
	private static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_15989_123더하기4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			n = Integer.parseInt(br.readLine());
			
//			dp[a][b] =>	a까지의 합 방법 중 b이하의 수만 이용하면서 마지막에 b로 끝나는 경우의 수
			dp = new int[n+1][4];
			
//			알고리즘
			if(n>=1) { dp[1][1]=1; dp[1][2]=0; dp[1][3]=0; }
			if(n>=2) { dp[2][1]=1; dp[2][2]=1; dp[2][3]=0; }
			if(n>=3) { dp[3][1]=1; dp[3][2]=1; dp[3][3]=1; }
			for(int i=4;i<=n;i++) {
				dp[i][1] = dp[i-1][1];
				dp[i][2] = dp[i-2][1]+dp[i-2][2];
				dp[i][3] = dp[i-3][1]+dp[i-3][2]+dp[i-3][3];
			}
			
			sb.append(dp[n][1]+dp[n][2]+dp[n][3]).append("\n");
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}

}
