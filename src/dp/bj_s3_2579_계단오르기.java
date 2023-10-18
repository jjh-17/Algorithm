package dp;

import java.util.*;
import java.io.*;

//계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
//연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
//마지막 도착 계단은 반드시 밟아야 한다.

public class bj_s3_2579_계단오르기 {

	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s3_2579_계단오르기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][2];
		for(int i=1;i<=N;i++) {
			int score = Integer.parseInt(br.readLine());
			
//			1칸 이동
			if(i-1>=0 && dp[i][0]<dp[i-1][0]+score && dp[i-1][1]<2) {
				dp[i][0] = dp[i-1][0] + score;
				dp[i][1] = dp[i-1][1] + 1;
			}
			
//			2칸 이동
			if(i-2>=0 && dp[i][0]<=dp[i-2][0]+score) {
				dp[i][0] = dp[i-2][0] + score;
				dp[i][1] = 1;
			}
			
//			for(int j=0;j<=N;j++) System.out.print(dp[j][0] + " ");
//			System.out.println();
		}
		
		sb.append(dp[N][0]);
		System.out.println(sb.toString());
		br.close();
	}

}
