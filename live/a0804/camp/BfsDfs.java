package a0804.camp;

import java.util.*;
import java.util.Arrays;
import java.io.*;

public class BfsDfs {

	//상 우 하 좌
	static final int[] di = {-1, 0, 1, 0};
	static final int[] dj = {0, 1, 0, -1};

	static int N=5, C=0;
	static int[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		map = new int[N][N];
		v = new boolean[N][N];
		C=1;
		
//		dfs(N/2, N/2);
		bfs(N/2, N/2);
		for(int[] m:map)
			System.out.println(Arrays.toString(m));
		System.out.println();
	}
	
	
	//DFS는 방문 처리를 먼저할 것!
	static void dfs(int i, int j) {		
		v[i][j] = true;
		map[i][j] = C++;
		
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(0<=ni&&ni<N && 0<=nj&&nj<N && !v[ni][nj])
				dfs(ni, nj);
		}
	}
	
	static void bfs(int i, int j) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		//초기값 설정
		v[i][j] = true;
		q.offerLast(new int[] {i, j});
		
		//BFS
		while(!q.isEmpty()) {
			int[] cor = q.pollFirst();
			map[cor[0]][cor[1]] = C++;
			
			for(int d=0;d<4;d++) {
				int ni = cor[0] + di[d];
				int nj = cor[1] + dj[d];
				if(0<=ni&&ni<N && 0<=nj&&nj<N && !v[ni][nj]) {
					q.offerLast(new int[] {ni, nj});
					v[ni][nj] = true;
				}
			}
		}
	}

}
