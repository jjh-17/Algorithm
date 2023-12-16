package greedy;

import java.util.*;
import java.io.*;

public class swea_d4_1249_보급로 {

	static class Vertex implements Comparable<Vertex>{
		int i, j;
		int weight;
		
		public Vertex(int i, int j, int weight){
			this.i = i; 
			this.j = j;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static final int[] di = {-1, 0, 1, 0},
					   dj = {0, 1, 0, -1};
	static final StringBuilder sb = new StringBuilder();
	static final PriorityQueue<Vertex> pq = new PriorityQueue<>();
	static int[][] map;
	static int[][] dist;
	static int N;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_1249_보급로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//입력
			N = Integer.parseInt(br.readLine());
			
			//공간 할당
			dist = new int[N][N];
			map = new int[N][N];
			
			//공간 정보 입력
			for(int i=0;i<N;i++) {
				String str = br.readLine();
				for(int j=0;j<N;j++) {
					map[i][j] = str.charAt(j) - '0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			//dijkstra
			dijkstra(0, 0);
			sb.append("#").append(t).append(" ").append(dist[N-1][N-1]).append("\n");
			
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void dijkstra(int si, int sj) {
		pq.clear();
		pq.offer(new Vertex(si, sj, 0));
		dist[si][sj] = 0;
		
		while(!pq.isEmpty()) {
			//미방문 정점 중 최소 간선 비용 정점 및 비용 설정
			Vertex cur = pq.poll();
			int minI = cur.i;
			int minJ = cur.j;
			int minWeight = cur.weight;
			
			for(int d=0;d<4;d++) {
				int ni = minI + di[d];
				int nj = minJ + dj[d];
				if(0<=ni&&ni<N && 0<=nj&&nj<N && dist[ni][nj]>(minWeight + map[ni][nj])) {
					dist[ni][nj] = minWeight + map[ni][nj];
					pq.offer(new Vertex(ni, nj, dist[ni][nj]));
				}
			}
		}
	}

}
