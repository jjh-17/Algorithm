package a0830.camp;

import java.util.*;
import java.io.*;

public class BinomialCoefTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//입력
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] B = new int[N+1][K+1];
		
		for(int i=0;i<=N;i++) {
			for(int j=0, end=Integer.min(i, K); j<=end; j++) {
				if(j==0 || i==j) B[i][j] = 1;
				else B[i][j] = B[i-1][j-1] + B[i-1][j];
			}
		}
		
		System.out.println(B[N][K]);
	}
}
