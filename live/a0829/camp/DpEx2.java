package a0829.camp;

import java.util.*;
import java.io.*;

public class DpEx2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int N = 6;
		int[] dp = new int[N+1];
		
		//알고리즘
		dp[0]=1; dp[1]=2;
		for(int i=2;i<=N;i++) {
			dp[i] = dp[i-2] + 2*dp[i-1];
		}
		System.out.println(dp[N]);
		System.out.println(Arrays.toString(dp));
	}

}
