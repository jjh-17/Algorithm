package a0825.camp;

import java.util.*;
import java.io.*;

//인접 리스트 기반
public class DijkstraTest {

	static class Node {
		int vertex, weight;
		Node link;
		public Node(int vertex, int weight, Node link) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_live_DijkstraTest.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Node[] G = new Node[V]; //인접 리스트
		boolean[] v = new boolean[V]; //방문 처리
		int[] dist = new int[V]; //시작점에서 자신으로 오는 최단거리
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			G[from] = new Node(to, weight, G[from]);
		}
		
		//알고리즘 시작
		for(int i=0;i<V;i++) dist[i] = Integer.MAX_VALUE;
		int min=0, cnt=0;
		dist[start] = 0;
		for(int i=0;i<V;i++) {
			//step1 : 미방문 정점 중 출발지에서 가장 가까운 정점을 경유지로 선택
			
			
		}
		
		
	}

}
