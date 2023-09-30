package a0829.camp;

import java.util.*;
import java.io.*;

//발표
public class bj_s3_1463_1로만들기 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s3_1463_1로만들기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력
		N = Integer.parseInt(br.readLine());
		
		//값에 도달하기 위한 최소 연산 횟수 저장 
		dp = new int[N+1];
		
		//알고리즘
		for(int i=1;i<=N;i++) {
			if(i*3<=N) {
				if(dp[i*3]==0) dp[i*3] = dp[i]+1;
				else dp[i*3] = Integer.min(dp[i*3], dp[i]+1);
			}
			
			if(i*2<=N) {
				if(dp[i*2]==0) dp[i*2] = dp[i]+1;
				else dp[i*2] = Integer.min(dp[i*2], dp[i]+1);
			}
			
			if(i+1<=N) {
				if(dp[i+1]==0) dp[i+1] = dp[i]+1;
				else dp[i+1] = Integer.min(dp[i+1], dp[i]+1);
			}
			
//			System.out.println(Arrays.toString(dp));
		}
		

		//출력
		System.out.println(dp[N]);
		System.out.println(sb.toString());
		br.close();
	}

}
