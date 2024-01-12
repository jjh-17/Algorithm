package dp;

import java.util.*;
import java.io.*;

public class bj_s3_9095_123더하기 {

	static final StringBuilder sb = new StringBuilder();
	static int T, n;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s3_9095_123더하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			n = Integer.parseInt(br.readLine());
			dp = new int[n+1];
			
			if(n>=1) dp[1]=1;
			if(n>=2) dp[2]=2;
			if(n>=3) dp[3]=4;
			for(int i=4;i<=n;i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			sb.append(dp[n]).append("\n");
		}
		System.out.println(sb.toString());
	}

}
