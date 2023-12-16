package graph;

import java.util.*;
import java.io.*;

public class bj_g3_2206_벽부수고이동하기 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};
	static int N, M;
	static int[][] map;
	static boolean[][][] v;
	
	static class Info{
		int i, j, cntW, cntN;
		public Info(int i, int j, int cntW, int cntN) {
			this.i=i; this.j=j; 
			this.cntW=cntW; this.cntN = cntN;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_2206_벽부수고이동하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		v = new boolean[N][M][2]; //각 좌표애 벽을 부순 상태로 
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) map[i][j] = str.charAt(j)-'0';
		}
		
		//알고리즘
		bfs();
		
		
		//출력
		System.out.println(sb.toString());
		br.close();
	}
	
	static void bfs() {
		final ArrayDeque<Info> queue = new ArrayDeque<>();
		
		//1*1 판정
		if(N==1 && M==1) {
			sb.append(1);
			return;
		}

		v[0][0][0] = true;
		queue.offerLast(new Info(0, 0, 0, 1));
		
		while(!queue.isEmpty()) {
			Info cur = queue.pollFirst();
			
			//벽을 부수지 않고 이동
			for(int d=0;d<4;d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				
				//신규 좌표가 필드를 벗어나면 넘어감
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				
				//도착지에 도달
				if(ni==N-1 && nj==M-1) {
					sb.append(cur.cntW + cur.cntN + 1);
					return;
				}
				
				//벽을 부수지 않고 이동
				//해당 좌표에 들른 적이 없으며, 장애물이 없다면
				if(!v[ni][nj][cur.cntW] && map[ni][nj]==0) {
					v[ni][nj][cur.cntW] = true;
					queue.offerLast(new Info(ni, nj, cur.cntW, cur.cntN+1));
				}
				
				//벽을 부수고 이동
				//벽 부순 횟수가 0, 벽 부순 상태로 해당 좌표에 들른 적이 없으며, 해당 좌표에 벽이 있다면
				if(cur.cntW==0 && !v[ni][nj][1] && map[ni][nj]==1) {
					v[ni][nj][1] = true;
					queue.offerLast(new Info(ni, nj, 1, cur.cntN));
				}
			}	
		}	
		sb.append(-1);
	}
}