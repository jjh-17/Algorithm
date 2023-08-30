package a0821;


import java.util.*;
import java.io.*;

public class bj_g3_2252_줄세우기 {

	//공통
	static class Node{
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
		
		@Override
		public String toString() {
			return vertex + "-->" + link;
		}
	}
	static final StringBuilder sb = new StringBuilder();
	static int N, M;
	static Node[] G;
	
	//bfs
	static int[] inDegree; //각 노드별 진입차수 
	
	//dfs
	static final ArrayDeque<Integer> stack = new ArrayDeque<>();
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_2252_줄세우기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		
		G = new Node[N+1]; 
		inDegree = new int[N+1]; //BFS
		v = new boolean[N+1]; //DFS
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
			G[from] = new Node(to, G[from]);
			++inDegree[to]; //BFS
		}
		
		//BFS
//		topologicalSortBFS();
		
		//DFS
		v = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			if(!v[i]) topologicalSortDFS(i);
		}
		while(!stack.isEmpty()) sb.append(stack.pollLast()).append(" ");
		
		br.close();
		System.out.println(sb.toString());
		
	}
	
	//위상 정렬 - BFS
	static void topologicalSortBFS() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		//진입 차수 0인 vertex 큐에 추가
		for(int i=1;i<=N;i++) {
			if(inDegree[i]==0) queue.offerLast(i);
		}
		
		while(!queue.isEmpty()) {
			//큐에서 노드 번호 poll
			int vertex = queue.pollFirst();
			
			//출력문에 poll한 노드 번호 저장
			sb.append(vertex).append(" ");
			
			//현 노드 순회
			for(Node node=G[vertex];node!=null;node=node.link) {
				--inDegree[node.vertex];
				if(inDegree[node.vertex]==0) 
					queue.offerLast(node.vertex);
			}			
		}
	}
	
	//위상 정렬 - dfs
	static void topologicalSortDFS(int i) {
		v[i] = true;
		for(Node j=G[i];j!=null;j=j.link) {
			if(!v[j.vertex]) topologicalSortDFS(j.vertex);
		}
		stack.offerLast(i);
	}

}
