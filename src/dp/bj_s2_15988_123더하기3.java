package dp;

import java.util.*;
import java.io.*;

public class bj_s2_15988_123더하기3 {

	static final StringBuilder sb = new StringBuilder();
    static int T, n;
    static long[] dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_s2_15988_123더하기3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
//      입력
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            n = Integer.parseInt(br.readLine());
            dp = new long[n+1];
            
//          DP
            if(n>=1) dp[1]=1;
            if(n>=2) dp[2]=2;
            if(n>=3) dp[3]=4;
            for(int i=4;i<=n;i++)
                dp[i] = (dp[i-3] + dp[i-2] + dp[i-1])%1_000_000_009;
            sb.append(dp[n]).append("\n");
        }
        
//      출력
        System.out.println(sb.toString());
        br.close();
	}

}
