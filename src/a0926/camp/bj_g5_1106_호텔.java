package a0926.camp;

import java.util.*;
import java.io.*;

//DP
public class bj_g5_1106_호텔 {

	static final StringBuilder sb = new StringBuilder();
	static int C, N;				//목표 최소 고객의 수, 홍보 가능한 도시의 개수
	static int costs[], values[];	//각 도시의 홍보 비용, 얻을 수 있는 고객의 수 배열
	static int[] dp;				//현재 고려하는 도시의 개수 내에서, 최소 0~C명을 늘이기 위한 최소 투자 비용
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_1106_호텔.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		costs = new int[N+1];	values = new int[N+1];
		dp = new int[C+1];
		
//		입력
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			costs[i] = Integer.parseInt(st.nextToken());	//홍보 비용
			values[i] = Integer.parseInt(st.nextToken());	//얻을 수 있는 고객의 수
		}
		
		for(int j=1;j<=C;j++) dp[j]  = Integer.MAX_VALUE;

//		DP		
		for(int i=1;i<=N;i++) {
			int start = 1;
			int end = values[i];
			
//			정수배를 하기 전 cost/value에 대한 dp
			for(int j=start;j<=Integer.min(end, C);j++) 
				dp[j] = Integer.min(dp[j], costs[i]);
			
//			정수배를 한 이후 cost/value에 대한 dp
			while(end+1<=C) {
				start = end+1;
				end += values[i];
				
				for(int j=start;j<=Integer.min(end, C);j++)
					dp[j] = Integer.min(dp[j], dp[j-values[i]] + costs[i]);
			}
		}
		
//		출력
		sb.append(dp[C]);
		System.out.println(sb.toString());
		br.close();
	}

}