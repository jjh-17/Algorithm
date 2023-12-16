package dp;

import java.util.*;
import java.io.*;

//DP
public class bj_s3_11727_2n타일링2 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s3_11727_2n타일링2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//입력
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
	
		//알고리즘
		if(N==1) sb.append(1);
		else if(N==2) sb.append(3);
		else {
			dp[1]=1; dp[2]=3;
			for(int i=3;i<=N;i++) {
				dp[i] += dp[i-1]; //i-1에서 직사각형을 세움
				dp[i] += dp[i-2]; //i-2에서 직사각형을 눕힘
				dp[i] += dp[i-2]; //i-2에서 2*2 정사각형을 놓음
				dp[i]%=10007;
			}	
			sb.append(dp[N]);
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
