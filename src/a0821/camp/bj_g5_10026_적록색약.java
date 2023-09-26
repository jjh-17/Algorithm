package a0821.camp;

import java.util.*;
import java.io.*;

public class bj_g5_10026_적록색약 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, 0, 1, 0},
					   dj = {0, 1, 0, -1};
	static int N, CNT1, CNT2;
	static char[][] map;
	static boolean[][] v1, v2;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_10026_적록색약"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i=0;i<N;i++) {
			String str  = br.readLine();
			for(int j=0;j<N;j++) map[i][j] = str.charAt(j); 
		}
		
		v1 = new boolean[N][N]; v2 = new boolean[N][N];
		CNT1=0; CNT2=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
//				if(!v1[i][j]) { dfs1(i, j); ++CNT1; }
//				if(!v2[i][j]) { dfs2(i, j); ++CNT2; }
				
				if(!v1[i][j]) { bfs1(i, j); ++CNT1; }
				if(!v2[i][j]) { bfs2(i, j); ++CNT2; }
			}
		}
		
		br.close();
		sb.append(CNT1).append(" ").append(CNT2);
		System.out.println(sb.toString());
		
	}
	
	//적록 색맹이 아닌 사람
	static void dfs1(int i, int j) {
		v1[i][j] = true;
		
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(0<=ni&ni<N && 0<=nj&&nj<N && !v1[ni][nj] && map[i][j]==map[ni][nj])
				dfs1(ni, nj);
		}
	}
	
	//적록 색맹인 사람
	static void dfs2(int i, int j) {
		v2[i][j] = true;
		
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(0<=ni&ni<N && 0<=nj&&nj<N && !v2[ni][nj]) {
				if(map[i][j]=='B' && (map[ni][nj]=='R' || map[ni][nj]=='G')) continue;
				if((map[i][j]=='R' || map[i][j]=='G') && map[ni][nj]=='B') continue;
				dfs2(ni, nj);
			}
		}
	}
	
	//적록 색맹이 아닌 사람
	static void bfs1(int i, int j) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		queue.offerLast(new int[] {i, j});
		v1[i][j] = true;
		
		while(!queue.isEmpty()) {
			int[] cor = queue.pollFirst();
			
			for(int d=0;d<4;d++) {
				int ni = cor[0] + di[d];
				int nj = cor[1] + dj[d];
				if(0<=ni&ni<N && 0<=nj&&nj<N && !v1[ni][nj] && map[cor[0]][cor[1]]==map[ni][nj]) {
					v1[ni][nj] = true;
					queue.offerLast(new int[] {ni, nj});
				}
					
			}
		}
	}

	//적록색맹인 사람
	static void bfs2(int i, int j) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		queue.offerLast(new int[] {i, j});
		v2[i][j] = true;
		
		while(!queue.isEmpty()) {
			int[] cor = queue.pollFirst();
			
			for(int d=0;d<4;d++) {
				int ni = cor[0] + di[d];
				int nj = cor[1] + dj[d];
				if(0<=ni&ni<N && 0<=nj&&nj<N && !v2[ni][nj]) {
					if(map[cor[0]][cor[1]]=='B' && (map[ni][nj]=='R' || map[ni][nj]=='G')) continue;
					if((map[cor[0]][cor[1]]=='R' || map[cor[0]][cor[1]]=='G') && map[ni][nj]=='B') continue;
					
					v2[ni][nj] = true;
					queue.offerLast(new int[] {ni, nj});
				}
					
			}
		}
	}
}
