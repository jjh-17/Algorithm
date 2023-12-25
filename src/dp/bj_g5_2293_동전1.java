package dp;

import java.util.*;
import java.io.*;

public class bj_g5_2293_동전1 {

	static final StringBuilder sb = new StringBuilder();
	static int N, K;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_2293_동전1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[K+1];	dp[0] = 1;
		for(int i=0;i<N;i++) {
			int coin = Integer.parseInt(br.readLine());
			for(int k=coin;k<=K;k++) dp[k] += dp[k-coin];
		}
		
//		출력
		sb.append(dp[K]);
		System.out.println(sb.toString());
		br.close();
	}

}
