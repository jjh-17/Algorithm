package a0830;

import java.util.*;
import java.io.*;

//조합의 성질: nCk = n-1Ck-1 + n-1Ck
public class bj_s5_1010_다리놓기 {

	static final StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] dp = new int[31][31];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s5_1010_다리놓기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			//출력
			sb.append(comb(M, N)).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	

	//nCk
	static int comb(int n, int k) {
		if(k==n || k==0) return 1;
		
		if(dp[n][k]==0) dp[n][k] = comb(n-1, k-1) + comb(n-1, k);
		
		return dp[n][k];
	}
}
