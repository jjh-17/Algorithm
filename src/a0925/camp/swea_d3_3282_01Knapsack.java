package a0925.camp;

import java.util.*;
import java.io.*;

public class swea_d3_3282_01Knapsack {

	static final StringBuilder sb = new StringBuilder();
	static int N, K, V, C;		//물건 개수, 가방의 부피, 각 물건의 부피, 각 물건의 가치
	static int[] dp;			//각 부피 별 최대 가치
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_3282_01Knapsack.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
//			입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//물건의 개수
			K = Integer.parseInt(st.nextToken());	//가방의 가용 부피 
			dp = new int[K+1];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				V = Integer.parseInt(st.nextToken());	//현 물건의 부피
				C = Integer.parseInt(st.nextToken());	//현 물건의 가치

//				역순으로 구려하여 자신이 고려되지 않은 최적해 고려 가능
				for(int j=K;j>=V;j--) dp[j] = Integer.max(dp[j], dp[j-V]+C);
			}
			sb.append("#").append(t).append(" ").append(dp[K]).append("\n");
		}

//		출력
		System.out.println(sb.toString());
		br.close();
	}

}