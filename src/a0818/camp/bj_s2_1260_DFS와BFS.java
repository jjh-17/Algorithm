package a0818.camp;

import java.util.*;
import java.io.*;

//해결 중
public class bj_s2_1260_DFS와BFS {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, M, V;
	static boolean[] v;
	static PriorityQueue<Integer>[] map;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s2_1260_DFS와BFS.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//N, M, V
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken()); v = new boolean[N];

		//map
		map = new PriorityQueue[N+1];
		for(int i=0;i<=N;i++) map[i] = new PriorityQueue<>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
			map[start].add(end); map[end].add(start);
		}
		
		//dfs
		v = new boolean[N+1];
		dfs(V);
		sb.append("\n");
		
		//bfs
		v = new boolean[N+1];
		bfs(V);
		br.close();
		System.out.println(sb.toString());
	}
	
	static void dfs(int s) {
		v[s] = true;
		
		sb.append(s).append(" ");
		Iterator it = map[s].iterator();
		while(it.hasNext()) {
			int end = (int)it.next();
			if(!v[end]) dfs(end);
		}
	}
	
	static void bfs(int s) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		queue.offerLast(s); v[s] = true;
		while(!queue.isEmpty()) {
			int start = queue.pollFirst();
			sb.append(start).append(" ");
			Iterator it = map[start].iterator();
			while(it.hasNext()) {
				int end = (int)it.next();
				if(!v[end]) {
					v[end] = true;
					queue.offerLast(end);
				}
			}
		}
	}

}
