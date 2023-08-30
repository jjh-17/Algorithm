package a0830.camp;

import java.util.*;
import java.io.*;

public class MinCoinTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		int money = Integer.parseInt(br.readLine());
		
		int[] dp = new int[money+1];
		dp[0] = 0;
		for(int i=1;i<=money;i++) {
			//1원
			dp[i] = dp[i-1]+1;
			
			//4원
			if(i>=4 && dp[i]>dp[i-4]+1) dp[i] = dp[i-4]+1;
			
			//6원
			if(i>=6 && dp[i]>dp[i-6]+1) dp[i] = dp[i-6]+1;
		}
		
		System.out.println(dp[money]);
	}
}
