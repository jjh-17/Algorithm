package a0823;

import java.util.*;
import java.io.*;

public class swea_d4_3124_최소스패닝트리 {
	static final StringBuilder sb = new StringBuilder();
	static int V, E; //정점, 간선의 수
	static int A, B, C; //정점 A, B를 연결하는 간선의 가중치 C
	
	
	//Kruskal
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}
	}
	static Edge[] edgeList;
	static int[] parents;
	static PriorityQueue<Edge> pqEdge = new PriorityQueue<>();
	
	//Prim - Priority Queue
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
	static Node[] G; //그래프
	static final PriorityQueue<int[]> pq = new PriorityQueue<>(
			(o1, o2) -> Integer.compare(o1[1], o2[1]));
	static boolean[] v; //각 정점의 visited 여부
	static int[] minEdge; //각 정점의 최소 가중치 간선값 저장
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_3124_최소스패닝트리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//V, E 입력
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			//Kruskal 초기화
			edgeList = new Edge[E];
			parents = new int[V+1];
			
//			//Prim 초기화
//			G = new Node[V+1];
//			v = new boolean[V+1];
//			minEdge = new int[V+1];
			
			//A, B, C 입력
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				
				//Kruskal
//				edgeList[i] = new Edge(A, B, C);
				pqEdge.add(new Edge(A, B, C));
				
//				//Prim
//				G[A] = new Node(B, C, G[A]);
//				G[B] = new Node(A, C, G[B]);
			}
			
			sb.append("#").append(t).append(" ");
//			primPQ();
			kruskal();
		}
		
		br.close();
		System.out.println(sb.toString());
	}
	
	//Kruskal
	static void kruskal() {
		long cost=0;
		int cnt=0;
		
		Arrays.sort(edgeList);
		makeParents();
		
		for(Edge edge : edgeList) {
			if(isUnion(edge.from, edge.to)) {
				cost += edge.weight;
				if(++cnt == V) break;
			}
		}
		
		sb.append(cost).append("\n");
	}
	
	static boolean isUnion(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA==rootB) return false;
		
		parents[rootB] = rootA;
		return true;
	}
	
	static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a]=find(parents[a]);
	}
	
	static void makeParents() {
		parents = new int[V+1];
		for(int i=1;i<=V;i++) parents[i]=i;
	}
	
	
	//Prim - PriorityQueue
	static void primPQ() {
		long result=0;
		int cnt=0;
		
		for(int i=2;i<=V;i++) minEdge[i] = Integer.MAX_VALUE;
		minEdge[1] = 0;
		
		pq.offer(new int[] {1, minEdge[1]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int minVertex = cur[0];
			int minWeight = cur[1];
			
			if(v[minVertex]) continue;
			
			v[minVertex] = true;
			result += minWeight;
			
			if(cnt++ == V) break;
			
			for(Node node=G[minVertex];node!=null;node=node.link) {
				if(!v[node.vertex] && minEdge[node.vertex]>node.weight) {
					minEdge[node.vertex] = node.weight;
					pq.offer(new int[] {node.vertex, minEdge[node.vertex]});
				}
			}
			
		}
		
		sb.append(result).append("\n");
	}

}
