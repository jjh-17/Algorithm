package dp;

import java.util.*;
import java.io.*;

public class bj_s3_14501_퇴사 {

	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_s3_14501_퇴사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+2];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			for(int j=i+T;j<=N+1;j++) dp[j] = Integer.max(dp[j], dp[i]+P);
		}
		
//		출력
		sb.append(dp[N+1]);
		System.out.println(sb.toString());
		br.close();
	}
}
