package graph;

import java.util.*;
import java.io.*;

//플로이드 워샬
public class bj_s2_1058_친구 {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, dp[][];
	static char map[][];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s2_1058_친구.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		dp = new int[N][N];
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) map[i][j] = str.charAt(j);
		}
		
//		플로이드 워샬
		for(int c=0;c<N;c++) {			// 친구 C
			for(int a=0;a<N;a++) {		// 친구 A
				if(a==c) continue;
				for(int b=0;b<N;b++) {	// 친구 B
					if(c==a || a==b) continue;
					
//					A와 C, B와 C가 친구거나, A와 B가 친구라면 dp는 1
					if((map[a][c]=='Y' && map[c][b]=='Y')
							|| map[a][b]=='Y') dp[a][b]=1;
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			int sum = 0;
			for(int j=0;j<N;j++) sum+=dp[i][j];
			max = Integer.max(max, sum);
		}
		
//		출력
		sb.append(max);
		System.out.println(sb.toString());
		br.close();
	}

}
