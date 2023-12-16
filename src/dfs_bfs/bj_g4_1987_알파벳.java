package dfs_bfs;

import java.util.*;
import java.io.*;

public class bj_g4_1987_알파벳 {
	
	static final int[] di = {-1, 0, 1, 0},
					   dj = {0, 1, 0, -1};
	static int R, C, CNT;
	static char map[][];
	static boolean[] v = new boolean['Z'-'A'+1];
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_1987_알파벳.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		CNT=0;
		
		//칸 정보 입력
		map = new char[R][C];
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) map[i][j] = str.charAt(j);
		}
		
		dfs(0, 0, 1);
		
		System.out.println(CNT);	
	}
	
	static void dfs(int i, int j, int cnt) {
		v[map[i][j]-'A'] = true;
		for(int d=0;d<4;d++) {
			int ni = i + di[d], nj = j + dj[d];
			
			if(0<=ni&&ni<R && 0<=nj&&nj<C && !v[map[ni][nj]-'A']) {
				v[map[ni][nj]-'A'] = true;
				dfs(ni, nj, cnt+1);
				v[map[ni][nj]-'A'] = false;
			}
		}
		
		CNT = Integer.max(CNT, cnt);
	}
}
