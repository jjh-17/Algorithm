package a0817.camp;


import java.util.*;
import java.io.*;

public class GraphMatrix {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, E, G[][];
	static boolean[] V;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		N = Integer.parseInt(br.readLine()); //정점 개수
		E = Integer.parseInt(br.readLine()); //간선 개수
		G = new int[N][N];
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			G[a][b] = 1; G[b][a] = 1;
		}
		
		System.out.println();
		for(int[] g : G) System.out.println(Arrays.toString(g));
		
		System.out.println("=====DFS=====");
		V = new boolean[N]; dfs(0);
		System.out.println();
		V = new boolean[N]; dfsR(0);
		System.out.println();
		
		System.out.println("=====BFS=====");
		V = new boolean[N]; bfs(0);
		System.out.println();
		V = new boolean[N]; bfsR(0);
		System.out.println();
	}
	
	static void dfs(int i) {
		V[i] = true;
		System.out.print((char)(i+'A') + " ");
		for(int j=0;j<N;j++) {
			if(G[i][j]!=0 && !V[j]) dfs(j);
		}
	}
	
	static void bfs(int i) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		V[i] = true; queue.offerLast(i);
		while(!queue.isEmpty()) {
			i = queue.pollFirst();
			System.out.print((char)(i+'A') + " ");
			
			for(int j=0;j<N;j++) {
				if(G[i][j]!=0 && !V[j]) {
					V[j] = true;
					queue.offerLast(j);
				}
			}
		}
	}

	
	static void dfsR(int i) {
		V[i] = true;
		System.out.print((char)(i+'A') + " ");
		for(int j=N-1;j>=0;j--) {
			if(G[i][j]!=0 && !V[j]) dfs(j);
		}
	}
	
	static void bfsR(int i) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		V[i] = true; queue.offerLast(i);
		while(!queue.isEmpty()) {
			i = queue.pollFirst();
			System.out.print((char)(i+'A') + " ");
			
			for(int j=N-1;j>=0;j--) {
				if(G[i][j]!=0 && !V[j]) {
					V[j] = true;
					queue.offerLast(j);
				}
			}
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
