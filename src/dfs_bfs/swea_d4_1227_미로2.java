package dfs_bfs;

import java.util.*;
import java.io.*;

public class swea_d4_1227_미로2 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};
	static char[][] map = new char[100][100];
	static int si, sj; //시작점 i/j
	static int answer;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_1227_미로2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T=10;
		for(int t=1;t<=T;t++) {
			sb.append("#").append(br.readLine()).append(" ");
			answer = 0;
			for(int i=0;i<100;i++) {
				String str = br.readLine();
				map[i] = str.toCharArray();
				for(int j=0;j<100;j++) {
					if(map[i][j]=='2') { //시작점
						si = i; sj = j;
					}
				}
			}
			
//			dfs(si, sj);
			bfs(si, sj);
			sb.append(answer).append("\n");
		}
		
		br.close();
		System.out.println(sb.toString());
		
	}
	
	public static void dfs(int i, int j) {
		if(map[i][j] == '3') {
			answer = 1;
			return;
		}
		
		map[i][j] = '1';
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			
			if(0<=ni&&ni<100 && 0<=nj&&nj<100 && map[ni][nj]!='1') {
				dfs(ni, nj);
			}
		}
	}
	
	public static void bfs(int i, int j) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offerLast(new int[] {i, j});
		
		while(!queue.isEmpty()) {
			int[] ij = queue.pollFirst();
			if(map[ij[0]][ij[1]]=='3') {
				answer=1;
				return;
			}
			
			map[ij[0]][ij[1]] = '1';
			for(int d=0;d<4;d++) {
				int ni = ij[0] + di[d];
				int nj = ij[1] + dj[d];
				
				if(0<=ni&&ni<100 && 0<=nj&&nj<100 && map[ni][nj]!='1') {
					queue.offerLast(new int[] {ni, nj});
				}
			}
		}
	}

}
