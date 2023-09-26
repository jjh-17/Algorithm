package a0822.camp;

import java.util.*;
import java.io.*;

//정점에 비해 간선의 수가 적음 ==> Kruskal
public class swea_d4_3289_서로소집합 {

	static final StringBuilder sb = new StringBuilder();
	
	static class Edge {
		int from, to;

		public Edge(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
	}
	
	static int N, M, ans;
	static int op, a, b;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_3289_서로소집합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			setParents();
			for(int m=0;m<M;m++) {
				st = new StringTokenizer(br.readLine());
				op = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if(op==0) union(a, b);
				else sb.append(isUnion(a, b));
			}
			sb.append("\n");
		}
		
		
		br.close();
		System.out.println(sb.toString());
	}
	
	//두 정점의 합집합 여부 확인
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA!=rootB) parents[rootA] = rootB;
	}
	
	//주 정점의 합집합 여부 확인
	static int isUnion(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		//동일 루트 ==> 사이클 발생 ==> 동일 집합에 포함됨
		if(rootA==rootB) return 1;
		
		//동일 집합에 포함되지 않음 
		return 0;
	}
	
	//Root 찾기
	static int find(int v) {
		if(parents[v]==v) return v;
		return parents[v] = find(parents[v]);
	}
	
	//부모 초기화 - 자기 자신
	static void setParents() {
		parents = new int[N+1];
		for(int i=1;i<=N;i++) parents[i] = i;
	}

}
