package graph;

import java.util.*;
import java.io.*;

public class swea_d6_1263_사람네트워크2 {

	static final StringBuilder sb = new StringBuilder();
	static final int INF = 1001;
	static int N;
	static int dp[][];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d6_1263_사람네트워크2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
//			입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			dp = new int[N][N];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					dp[i][j] = Integer.parseInt(st.nextToken());
					
//					자기 자신과의 연결이 아니며, 연결 정보가 없는 경유 INF로 설정
					if(i!=j && dp[i][j]==0) dp[i][j] = INF;
				}
			}
			
			// 경유지-->출발지-->목적지 3중 루프
			for(int k=0; k<N; ++k) {
				for(int i=0; i<N; ++i) {
					// 출발지와 경유지가 같다면 패스
					if(i==k) continue;
					for(int j=0; j<N; ++j) {
						// 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
						if(i==j || k==j) continue;
						if(dp[i][j] > dp[i][k]+dp[k][j])
						   dp[i][j] = dp[i][k]+dp[k][j];
					}
				}
			}
			
//			완전 탐색으로 이용하여 dp 최솟값 추출
			int min = Integer.MAX_VALUE;
			for(int i=0;i<N;i++) {
				int sum = 0;
				for(int j=0;j<N;j++) sum+=dp[i][j];
				min = Integer.min(min, sum);
			}
			
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
}
