package dfs_bfs;

import java.util.*;
import java.io.*;

public class swea_d3_2806_NQueen {
	
	static final StringBuilder sb = new StringBuilder();
	static final int[] DI = {-1, -1, 0, 1, 1, 1, 0, -1}, //8방 탐색
			   DJ = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, CNT;
	static boolean[][] V;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_2806_NQueen.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine()); CNT = 0;
			V = new boolean[N][N];
			
			dfs(0);
			
			sb.append("#").append(t).append(" ").append(CNT).append("\n");
		}
		
		
		br.close();
		System.out.println(sb.toString());
	}
	
	static void dfs(int row) {
		if(row==N) {
			++CNT;
			return;
		}
		
		for(int j=0;j<N;j++) {
			V[row][j] = true;
			if(checkSafe(row, j)) dfs(row+1);
			V[row][j]  =false;
		}
	}
	
	//현재 위치가 안전한지 확인
	static boolean checkSafe(int i, int j) {
		for(int d=0;d<8;d++) {
			int ni = i+DI[d], nj = j+DJ[d];
			while(true) {
				if(ni<0 || N<=ni || nj<0 || N<=nj) break; //범위를 벗어나느 경우 break
				if(V[ni][nj]) return false;
				ni+=DI[d]; nj+=DJ[d];
			}
		}
		
		return true;
	}

}
