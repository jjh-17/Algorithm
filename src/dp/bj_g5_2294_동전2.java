package dp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g5_2294_동전2 {
	
	static int n, k;
	static int[] coins, dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_2294_동전2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[k+1];
		coins = new int[n];
		for(int i=0;i<n;++i) coins[i] = Integer.parseInt(br.readLine());
		
//		DP
		for(int i=1;i<=k;i++) {
			for(int coin : coins) {
				if(i-coin==0) {
					dp[i]=1;
					continue;
				}
				
				if(i-coin>0 && dp[i-coin]>0) {
					if(dp[i]==0)	dp[i] = dp[i-coin]+1;
					else			dp[i] = Math.min(dp[i], dp[i-coin]+1);
				}
			}
		}
		
//		출력
		System.out.println(dp[k]==0 ? -1 : dp[k]);
		br.close();	
	}

}

