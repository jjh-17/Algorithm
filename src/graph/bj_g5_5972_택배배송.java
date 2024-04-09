package graph;

import java.util.*;
import java.io.*;

public class bj_g5_5972_택배배송 {

	static class Node {
		int vertex;
		int weight;
		Node link;
		
		public Node(int vertex, int weight, Node link) {
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
	}
	static final PriorityQueue<int[]> pq = new PriorityQueue<>(
			(o1, o2) -> Integer.compare(o1[1], o2[1]));
	static int N, M;
	static Node[] G;
	static int[] dist;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_5972_택배배송.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = new Node[N+1];
		dist = new int[N+1];
		v = new boolean[N+1];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			G[A] = new Node(B, C, G[A]);
			G[B] = new Node(A, C, G[B]);
		}
		
		// 알고리즘
		solution();
		
		// 출력
		System.out.println(dist[N]);
		br.close();
	}

	static void solution() {
		int cnt=0;
		
		// 정점 별 최소 간선 길이 초기화
		for(int i=1;i<=N;i++)	dist[i] = Integer.MAX_VALUE;
		dist[1] = 0;
		
		// 정점 별 누적 여물
		pq.offer(new int[] {1, 0});
		
		while(!pq.isEmpty()) {
			// 현재 최소 누적 여물을 가지는 헛간 번호와 그 누적 여물
			int[] cur = pq.poll();
			int minVertex = cur[0];
			int minWeight = cur[1];
			
			// 현재 헛간에 이미 들렀다면 넘어감
			if(v[minVertex])	continue;
			v[minVertex] = true;
			
			// 이전까지 N-1번 고려하였다면 탈출
			if(cnt++ == N-1) break;
			
			// 현재 헛간과 연결된 헛간 순회
			for(Node node=G[minVertex];node!=null;node=node.link) {
				// 들르지 아니한 곳 && 누적 여물이 더 작아진다면
				if(!v[node.vertex] && dist[node.vertex]>(minWeight + node.weight)) {
					dist[node.vertex] = minWeight + node.weight;
					pq.offer(new int[] {node.vertex, dist[node.vertex]});
				}
			}
		}
	}
}
