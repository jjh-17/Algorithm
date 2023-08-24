package a0824.camp;

import java.util.*;
import java.io.*;

public class bj_g4_4485_녹색옷입은애가젤다지 {

	static class Vertex implements Comparable<Vertex>{
		int i, j;
		int weight;
		
		public Vertex(int i, int j, int weight) {
			this.i = i;
			this.j = j;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static final StringBuilder sb = new StringBuilder();
	static final PriorityQueue<Vertex> pq = new PriorityQueue<>();
	static final int[] di = {-1, 0, 1, 0},
					   dj = {0, 1, 0, -1};
	static int N;
	static int[][] map;
	static int[][] minEdge;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_4485_녹색옷입은애가젤다지.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t=1;
		while(true) {
			//입력
			N = Integer.parseInt(br.readLine());
			if(N==0) {
				System.out.println(sb.toString());
				br.close();
				return;
			}	
			
			map = new int[N][N];
			minEdge = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					minEdge[i][j] = Integer.MAX_VALUE;
				}
			}
			
			primPQ();
			sb.append("Problem ").append(t++).append(": ").append(minEdge[N-1][N-1]).append("\n");
		}
	}
	
	static void primPQ() {
		minEdge[0][0]=0;
		pq.clear();
		pq.offer(new Vertex(0, 0, map[0][0]));
		
		while(!pq.isEmpty()) {
			//미방문 정점 중 최소 weight의 정점 정보 얻기
			Vertex cur = pq.poll();
			int minI = cur.i;
			int minJ = cur.j;
			int minWeight = cur.weight;
			
			for(int d=0;d<4;d++) {
				int ni = minI + di[d];
				int nj = minJ + dj[d];
				if(0<=ni&&ni<N && 0<=nj&&nj<N && minEdge[ni][nj]>(minWeight + map[ni][nj])) {
					minEdge[ni][nj] = minWeight + map[ni][nj];
					pq.offer(new Vertex(ni, nj, minWeight+map[ni][nj]));
				}
			}
		}
	}
}
