package a0825;

import java.util.*;
import java.io.*;

//정점볻 간선의 수가 더 상대적으로 많음 ==> Prim!
public class bj_g4_1735_최단경로 {
	
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
	static final StringBuilder sb = new StringBuilder();
	static final PriorityQueue<int[]> pq = new PriorityQueue<>(
			(o1, o2) -> Integer.compare(o1[1], o2[1]));
	static int V, E, K;
	static Node[] G;
	static int[] dist;
	static boolean[] v;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_1735_최단경로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		//공간 할당
		G = new Node[V+1];
		dist = new int[V+1];
		v = new boolean[V+1];
				
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			G[from] = new Node(to, weight, G[from]);
		}
		
		//dijkstra
		dijkstra();
		
		//출력
		for(int i=1;i<=V;i++) {
			if(i==K) sb.append(0).append("\n");
			else sb.append(dist[i]==Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
		}
		
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void dijkstra() {		
		int cnt=0;
		
		//각 정점별 최소 간선 길이 초기화
		for(int i=1;i<=V;i++) dist[i] = Integer.MAX_VALUE;
		dist[K] = 0; //시작 정점
		
		pq.offer(new int[] {K, 0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int minVertex = cur[0];
			int minWeight = cur[1];

			//각 정점 방문처리
			if(v[minVertex]) continue;
			v[minVertex] = true;
			
			//이전까지 V-1번 고려하였다면 탈출 
			if(cnt++ == V-1) break;
			
			//각 정점은 여러 노드를 가질 수 있음
			for(Node node=G[minVertex];node!=null;node=node.link) {
				if(!v[node.vertex] && dist[node.vertex]>(minWeight + node.weight)) {
					dist[node.vertex] = minWeight + node.weight;
					pq.offer(new int[] {node.vertex, dist[node.vertex]});
				}
			}
			
		}
	}

}
