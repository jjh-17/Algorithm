package dfs_bfs;

import java.util.*;
import java.io.*;
import java.lang.Math;;

public class bj_s1_14940_쉬운최단거리 {
	
	static final StringBuilder sb = new StringBuilder();
	static final int di[] = {-1, 0, 1, 0};
	static final int dj[] = {0, 1, 0, -1};
	static int N, M, si, sj;
	static int[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_s1_14940_쉬운최단거리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					si = i;
					sj = j;
				}
			}
		}
		
//		알고리즘
		bfs();
		
		
//		출력
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!v[i][j] && map[i][j]==1)
					sb.append(-1 + " ");
				else
					sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void bfs() {
		final ArrayDeque<int[]> dq = new ArrayDeque<>();
		
		v[si][sj] = true;
		map[si][sj] = 0;
		dq.offerLast(new int[] {si, sj});
		
		while(!dq.isEmpty()) {
			int[] arr = dq.pollFirst();
			
			for(int d=0;d<4;d++) {
				int ni = arr[0] + di[d];
				int nj = arr[1] + dj[d];
				
				if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj] && map[ni][nj]>0) {
					v[ni][nj] = true;
					map[ni][nj] = map[arr[0]][arr[1]]+1;
					dq.offerLast(new int[] {ni, nj});
				}
			}
		}
	}
}
