package dp;

import java.util.*;
import java.io.*;

public class bj_g5_15989_123더하기4 {

	private static final StringBuilder sb = new StringBuilder();
	static int T, n;
	private static int[] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_15989_123더하기4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			n = Integer.parseInt(br.readLine());
			dp = new int[n+1];
			
//			알고리즘
			for(int i=1;i<=3;i++) {
				for(int j=0;j<n;j++) {
					 int c=1;
				}
			}
			
			sb.append(dp[n]).append("\n");
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}

}
