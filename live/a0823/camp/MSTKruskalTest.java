package a0823.camp;

import java.util.*;
import java.io.*;

public class MSTKruskalTest {

	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}	
	}
	
	static Edge[] edgeArr; //간선 저장 배열
	static int V, E; //정점, 간선 수
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_live_kruskal_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
		
		edgeArr = new Edge[E];
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeArr[i] = new Edge(from, to, weight);
		}
		
		//Kruskal
		Kruskal();
	}
	
	//Kruskal 알고리즘
	static void Kruskal() {
		//간선 리스트 가중치 기준 오름차순 정렬
		Arrays.sort(edgeArr);
		
		//V개 정점에 대하여 make set 작업
		makeParents();
		
		
		int cost = 0; //MST 비용 누적
		int count = 0 ; //연결 간선 개수
		
		//모든 간선 순회
		for(Edge edge : edgeArr) {
			//간선에 연결된 두 정점의 union 확인
			if(union(edge.from, edge.to)) {
				//코스트 추가
				cost += edge.weight;
				
				//모든 연결 간선을 확인하였다면 break
				if(++count==V-1) break;
			}
		}
		
		System.out.println("총 코스트: " + cost);
		System.out.println("총 간선 확인 수: " + count);
	}
	
	//각 정점이 자기 자신을 대표하도록 함
	//자기의 Root를 자신으로 설정
	static void makeParents() {
		parents = new int[V];
		
		for(int i=0;i<V;i++) {
			parents[i] = i;
		}
	}

	//정점 a의 Root를 반환
	static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}
	
	//서로소 확인 
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		//동일 루트면 false ==> 사이클 발생
		if(aRoot == bRoot) return false;
		
		//다른 루트면 a를 b의 부모로 설정
		parents[bRoot] = aRoot;
		return true;
	}
}
