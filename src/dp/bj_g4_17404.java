package dp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g4_17404 {

	static final int MAX = 1000*1000+1;
	static int N, ans;			// 집의 개수, 정답
	static int[][] cost;		// 각 집을 RGB로 칠할 때의 코스트
	static int[][] dp;			// 각 집을 RGB로 칠했을 때의 최소 누적 코스트
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_17404.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		cost = new int[3][N];
		dp = new int[3][N];
		
		for(int j=0;j<N;j++) {
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<3;i++)
				cost[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		알고리즘
		ans = MAX;
		
//		1. 첫번째 집에 칠할 색 순회
		for(int i=0;i<3;i++) {
//			2. 첫번째 집을 RGB로 칠함
			for(int j=0;j<3;j++) 
				dp[j][0] = i==j ? cost[i][0] : MAX;
			
//			3. 마지막 집을 RGB로 칠했을 때의 최소 코스트 구함
			for(int j=1;j<N;j++) {
				dp[0][j] = Math.min(dp[1][j-1], dp[2][j-1]) + cost[0][j];
				dp[1][j] = Math.min(dp[0][j-1], dp[2][j-1]) + cost[1][j];
				dp[2][j] = Math.min(dp[0][j-1], dp[1][j-1]) + cost[2][j];
			}
			
//			4. 시작과 끝이 동일한 경우 제외
			for(int j=0;j<3;j++)
				ans = i==j ? ans : Math.min(ans, dp[j][N-1]);
		}
		
//		출력
		System.out.println(ans);
		br.close();
	}

}
