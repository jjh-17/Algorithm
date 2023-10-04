package a0821.camp;


import java.util.*;
import java.io.*;

public class TopologicalSort {

	//공통
	static final StringBuilder sb = new StringBuilder();
	static List<Integer>[] G;
	static int N, M; //정점, 간선 수
	
	//BFS
	static int[] inDegree;
	
	//DFS
	static final ArrayDeque<Integer> stack = new ArrayDeque<>();
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_live_topolgysort_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		
		G = new List[N+1]; 
		inDegree = new int[N+1]; v = new boolean[N+1];
		for(int i=1;i<=N;i++) G[i] = new ArrayList<>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			G[from].add(to); 
			++inDegree[to];
		}
		
		//bfs
		topologicalSortBFS();
		System.out.println();
		
		//dfs
		for(int i=1;i<=N;i++) {
			if(!v[i]) topologicalSortDFS(i);
		}
		while(!stack.isEmpty()) System.out.print(stack.pollLast() + " ");
		System.out.println();
		
		
		br.close();
	}
	
	//인접 리스트 위상 정렬 - BFS
	static void topologicalSortBFS() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		for(int i=1;i<=N;i++) {
			if(inDegree[i]==0) queue.offerLast(i);
		}
		
		while(!queue.isEmpty()) {
			int p = queue.pollFirst();
			System.out.print(p + " ");
			
			for(int j:G[p]) {
				if(--inDegree[j]==0) queue.offerLast(j);
			}
		}
	}
	
	//인접 리스트 위상 정렬 - DFS
	static void topologicalSortDFS(int i) {
		v[i] = true;
		
		for(int j=1;j<=G[i].size();j++) {
			if(!v[j]) topologicalSortDFS(j);
		}
		stack.offerLast(i);
	}
	
	

}
