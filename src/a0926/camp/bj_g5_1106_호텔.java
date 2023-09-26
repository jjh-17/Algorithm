package a0926.camp;

import java.util.*;
import java.io.*;

//DP
public class bj_g5_1106_호텔 {

	static final StringBuilder sb = new StringBuilder();
	static int C, N;	//목표 최소 고객의 수, 홍보 가능한 도시의 개수
	static int[][] dp;	//현재 고려하는 도시의 개수 내에서, 최소 0~C명을 늘이기 위한 최소 투자 비용
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_1106_호텔.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new int[N+1][C+1];
		
//		DP 초기값 설정
		for(int i=1;i<=C;i++) dp[0][i] = Integer.MAX_VALUE;
		
		for(int i=1;i<=N;i++) {
			st = 	new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());	//홍보 비용
			int value = Integer.parseInt(st.nextToken());	//얻을 수 있는 고객의 수
			int cnt=0;
			
			while(value*cnt+1<=C) {
				int start = value*cnt+1;
				int end = Integer.min(value*(cnt+1), C);
				
				for(int j=start;j<=end;j++) {
					dp[i][j] = Integer.min(dp[i-1][j], dp[i-1][Integer.max(j-value, 0)]+cost);
				}

				++cnt;
			}
		}

		for(int i=0;i<=N;i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		
		
//		출력
		sb.append(dp[N][C]);
		System.out.println(sb.toString());
		br.close();
	}

}
