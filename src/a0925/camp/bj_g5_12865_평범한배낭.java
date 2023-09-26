package a0925.camp;

import java.util.*;
import java.io.*;

public class bj_g5_12865_평범한배낭 {

	static final StringBuilder sb = new StringBuilder();
	static int N, K, W, V;	//물건의 개수, 버틸 수 있는 무게, 각 물건의 무게, 각 물건의 가치
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input_bj_g5_12865_평범한배낭.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[K+1];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			
//			DP
			for(int k=K;k>=W;k--) dp[k] = Integer.max(dp[k], dp[k-W]+V);
		}
		
//		출력
		sb.append(dp[K]);
		System.out.println(sb.toString());
		br.close();
	}

}
