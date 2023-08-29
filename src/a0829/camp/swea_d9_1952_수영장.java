package a0829.camp;

import java.util.*;
import java.io.*;

public class swea_d9_1952_수영장 {

	static final StringBuilder sb = new StringBuilder();
	static int costDay, costMonth, costMonth3, costYear;
	static int[] plan = new int[13];
	
	//현재 달에서의 최소비용
	static int[] dp = new int[13];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_1952_수영장.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//요금 입력
			st = new StringTokenizer(br.readLine());
			costDay = Integer.parseInt(st.nextToken());
			costMonth = Integer.parseInt(st.nextToken());
			costMonth3 = Integer.parseInt(st.nextToken());
			costYear = Integer.parseInt(st.nextToken());
			
			//이용 계획 입력 및 DP
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=12;i++) {
				//이용 계획 입력
				plan[i] = Integer.parseInt(st.nextToken());
				
				//==DP==
				//일일 이용권 비용, 1달 이용권 비용 비교
				dp[i] = dp[i-1] + Integer.min(costDay*plan[i], costMonth);
				
				//3달 이용권 비용 비교
				if(i<3) continue;
				dp[i] = Integer.min(dp[i], dp[i-3]+costMonth3);
			}
			
			//연간 이용권과 비교
			dp[12] = Integer.min(dp[12], costYear);
			
			//출력
			sb.append("#").append(t).append(" ").append(dp[12]).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
