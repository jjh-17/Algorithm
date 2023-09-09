package a0831;

import java.util.*;
import java.io.*;

public class bj_g5_17070_파이프옮기기1 {

	static final StringBuilder sb = new StringBuilder();
	static int N, ANS;
	static int[][] map;
	static String state;
	static final int[] di= {0, 1, 1}, dj= {1, 1, 0};
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_17070_파이프옮기기1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//dp
		ANS=0;
		dfs(0, 1, "row");
		
		
		//출력
		sb.append(ANS);
		System.out.println(sb.toString());
		br.close();
	}
	
	static void dfs(int i, int j, String state) {
		if(i==N-1 && j==N-1) {
			++ANS;
			return;
		}
		
		if(state.equals("row")) {
			if(j+1<N && map[i][j+1]==0) dfs(i, j+1, "row");
		} else if(state.equals("col")) {
			if(i+1<N && map[i+1][j]==0) dfs(i+1, j, "col");
		} else {
			if(j+1<N && map[i][j+1]==0) dfs(i, j+1, "row");
			if(i+1<N && map[i+1][j]==0) dfs(i+1, j, "col");
		}
		
		//3개 state 공통
		for(int d=0;d<3;d++) {
			int ni=i+di[d];
			int nj=j+dj[d];
			if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj]==1) return;
		}
		dfs(i+1, j+1, "rc");
	}
}
