package a0817;

import java.util.*;
import java.io.*;

public class bj_s2_4963_섬의개수 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] DI = {-1, -1, 0, 1, 1, 1, 0, -1}, //8방 탐색
					   DJ = {0, 1, 1, 1, 0, -1, -1, -1};
	static int W, H, CNT;
	
	static int[][] MAP;
	static boolean[][] V;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s2_4963_섬의개수.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			String str = br.readLine();
			if(str.equals("0 0")) break;
			
			st = new StringTokenizer(str);
			W = Integer.parseInt(st.nextToken()); H = Integer.parseInt(st.nextToken());
			CNT=0;
			MAP = new int[H][W]; V = new boolean[H][W];
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) MAP[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//dfs
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(MAP[i][j]==1 && !V[i][j]) {
						dfs(i, j);
						++CNT;
					}
				}
			}
			sb.append(CNT).append("\n");
		}
		
		
		br.close();
		System.out.println(sb.toString());
	}
	
	static void dfs(int ci, int cj) {
		for(int d=0;d<8;d++) {
			int ni = ci + DI[d], nj = cj + DJ[d];
			if(0<=ni&&ni<H && 0<=nj&&nj<W && !V[ni][nj] && MAP[ni][nj]==1) {
				V[ni][nj] = true;
				dfs(ni, nj);
			}
		}
	}

}
