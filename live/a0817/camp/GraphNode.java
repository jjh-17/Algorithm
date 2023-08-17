package a0817.camp;


import java.util.*;
import java.io.*;

public class GraphNode {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, E;
	static Node[] G;
	static boolean[] V;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		N = Integer.parseInt(br.readLine()); //정점 개수
		E = Integer.parseInt(br.readLine()); //간선 개수
		G = new Node[N];
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
			G[from] = new Node(to, G[from]); 
			G[to] = new Node(from, G[to]);
		}
		
		System.out.println();
		for(Node g : G) System.out.println(g); 
		
		
		System.out.println("=====DFS=====");
		V = new boolean[N]; dfs(0);
		System.out.println();
		
		System.out.println("=====BFS=====");
		V = new boolean[N]; bfs(0);
		System.out.println();
	}
	
	static void dfs(int i) {
		V[i] = true;
		System.out.print((char)(i+'A') + " ");
		for(Node j = G[i];j!=null;j=j.link) {
			if(!V[j.vertex]) dfs(j.vertex);
		}
	}
	
	static void bfs(int i) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		V[i] = true; queue.offerLast(i);
		while(!queue.isEmpty()) {
			i = queue.pollFirst();
			System.out.print((char)(i+'A') + " ");
			
			for(Node j = G[i];j!=null;j=j.link) {
				if(!V[j.vertex]) {
					V[j.vertex] = true;
					queue.offerLast(j.vertex);
				}
			}
		}
	}
	
	static class Node{
		int vertex;
		Node link;
		Node(int vertex, Node link){
			this.vertex = vertex;
			this.link = link;
		}
		
		@Override
		public String toString() {
			return vertex + "-->" + link;
		}
	}
}


/*
       A0
      /  \
     B1  C2
    /  \/
   D3  E4
    \  /
     F5 
       \G6

7
8
0 1
0 2
1 3
1 4
2 4
3 5
4 5
5 6

=====DFS=====
0->N: A B D F E C G 
N->0: A C E F G D B 
=====BFS=====
0->N: A B C D E F G
N->0: A C B E D F G


*/ 
