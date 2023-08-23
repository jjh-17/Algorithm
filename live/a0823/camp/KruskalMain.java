package a0823.camp;

import java.util.*;
import java.io.*;


public class KruskalMain {

	static final StringBuilder sb = new StringBuilder();
	static int N, E;
	static int[][] G;
	static int[] P;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		G = new int[E][3];
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			G[i] = new int[] {from, to, weight};
		}
		
		
		Kruskal();
		
		
		br.close();
		System.out.println(sb.toString());
	}
	
	static void Kruskal() {
		//weight 오름차순
		Arrays.sort(G, (o1, o2)-> Integer.compare(o1[2], o2[2]));
		
		//parents
		makeParents();
		
		//
		int result=0, cnt=0;
		for(int[] edge : G) {
			if(union(edge[0], edge[1])) {
				result += edge[2];
				if(++cnt==N-1) break;
			}
		}
		System.out.println("전체 코스트: " + result);
		System.out.println("간선 확인 수: " + cnt);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		//동일 루트 == 사이클 == 동일 집합에 포함됨
		if(rootA==rootB) return false;
		
		 P[rootA] = rootB;
		 return true;
	}
	
	static int find(int v) {
		if(P[v]==v) return v;
		return P[v]=find(P[v]);
	}
	
	static void makeParents() {
		P = new int[N];
		for(int i=0;i<N;i++) P[i] = i;
	}
}

/*
5 10
0 1 5
0 2 10
0 3 8
0 4 7
1 2 5
1 3 3
1 4 6
2 3 1
2 4 3
3 4 1
 */
