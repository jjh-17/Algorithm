package dp;

import java.util.*;
import java.io.*;

public class bj_s3_2579_계단오르기 {

	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s3_2579_계단오르기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		int[] scores = new int[N+1];

		for(int i=1;i<=N;i++) scores[i] = Integer.parseInt(br.readLine());
		
//		DP
		if(N>=1) dp[1] = scores[1];
		if(N>=2) dp[2] = dp[1] + scores[2];
		for(int i=3;i<=N;i++) dp[i] = Integer.max(dp[i-3] + scores[i-1], dp[i-2]) + scores[i];
		
		sb.append(dp[N]);
		System.out.println(sb.toString());
		br.close();
	}

}
