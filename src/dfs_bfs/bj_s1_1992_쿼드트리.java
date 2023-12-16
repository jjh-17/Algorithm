package dfs_bfs;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_s1_1992_쿼드트리 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] MAP;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_1992_쿼드트리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력
		N = Integer.parseInt(br.readLine());
		MAP = new int[N][N];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) MAP[i][j] = str.charAt(j)-'0';
		}
		
		dfs(0, 0, N);
		br.close();
		System.out.println(sb.toString());
	}
	
	public static void dfs(int si, int sj, int n) {
		if(checkSame(si, sj, n)) {
			sb.append(MAP[si][sj]);
			return;
		}
		sb.append("("); 
		dfs(si, sj, n/2); 
		dfs(si, sj+n/2, n/2);
		dfs(si+n/2, sj, n/2); 
		dfs(si+n/2, sj+n/2, n/2);  
		sb.append(")");	
	}
	
	public static boolean checkSame(int si, int sj, int n) {
		for(int i=si;i<si+n;i++) {
			for(int j=sj;j<sj+n;j++) {
				if(MAP[i][j]!=MAP[si][sj]) return false;
			}
		}
		
		return true;
	}

}
