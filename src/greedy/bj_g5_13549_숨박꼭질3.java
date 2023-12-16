package greedy;

import java.util.*;
import java.io.*;

public class bj_g5_13549_숨박꼭질3 {

	static final StringBuilder sb = new StringBuilder();
	static final int LIMIT = 100_000;
	static int N, K;
	static class Node implements Comparable<Node>{
		int vertex, time;
		
		public Node(int vertex, int time) {
			this.vertex = vertex;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_13549_숨박꼭질3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
//		====Dijkstra====
		final int[] time = new int[LIMIT+1];
		final PriorityQueue<Node> pq = new PriorityQueue<>();
		
//		위치별 최소 거리 초기화
		for(int i=0;i<=LIMIT;i++) time[i] = Integer.MAX_VALUE;
		time[N]=0;
				
//		PQ 초기값
		pq.offer(new Node(N, time[N]));
		
		while(!pq.isEmpty()) {
//			미방문 정점  중 출발지 N에서 가장 가까운 정점을 경유지로 선택
			Node cur = pq.poll();
			int minVertex = cur.vertex;
			int minTime = cur.time;			
			
//			현 정점이 도착지에 도달하면 종료한다.
			if(minVertex==K) break;
			
//			이동
			if(0<=minVertex-1 && time[minVertex-1]>minTime+1) {
				pq.add(new Node(minVertex-1, minTime+1));
				time[minVertex-1] = minTime+1;
			}
			
			if(minVertex+1<=LIMIT && time[minVertex+1]>minTime+1) {
				pq.add(new Node(minVertex+1, minTime+1));
				time[minVertex+1] = minTime+1;
			}
			
			if(2*minVertex<=LIMIT && time[2*minVertex]>minTime) {
				pq.add(new Node(2*minVertex, minTime));
				time[2*minVertex] = minTime;
			}
		}
		
//		출력
		sb.append(time[K]);
		System.out.println(sb.toString());
		br.close();
	}

}
