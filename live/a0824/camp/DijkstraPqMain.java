package a0824.camp;

import java.util.*;
import java.io.*;

public class DijkstraPqMain {
	
	static class Vertex implements Comparable<Vertex>{
		int vertex; //정점 번호
		int weight; //트리정점과 연결하였을때의 간선 비용
		
		public Vertex(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int start = sc.nextInt(), end = sc.nextInt();
		int[][] g = new int[N][N];
		boolean[] v = new boolean[N];
		int[] dist = new int[N]; //Prim의 weight
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) 
				g[i][j] = sc.nextInt();
			dist[i] = Integer.MAX_VALUE;
		}

		int min=0, minVertex=0, cnt=0;		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		dist[start] = 0; //0번 정점에서 먼저 시작하도록 0으로 설정
		pq.offer(new Vertex(start, dist[start])); //정점, 가중치 추가

		while(!pq.isEmpty()) {
			//step1: 미방문(비트리) 정점 중 최소 간선 비용 정점 선택
			Vertex cur = pq.poll();
			minVertex = cur.vertex;
			min = cur.weight;
			
			//step2: 현 정점이 들른 곳이면 넘어감
			if(v[minVertex]) continue;
			
			
			if(minVertex==-1) break;
			
			//step3: 방문(트리) 정점 추가
			v[minVertex] = true;
			
			//step4: 정점 방문 횟수가 N-1개가 되면 탈출
			if(minVertex == end) break;
			
			//step5: 트리에 추가된 새로운 정점 기준, 비트리 정점과의 간선 비용 고려
			for(int j=0;j<N;j++) {
				if(!v[j] && g[minVertex][j]!=0 && dist[j]>(min + g[minVertex][j])) {
					dist[j] = min + g[minVertex][j];
					pq.offer(new Vertex(j, dist[j]));
				}
			}
		}
		System.out.println(dist[end]);
		sc.close();
	}
}
