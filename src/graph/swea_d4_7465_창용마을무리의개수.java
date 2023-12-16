package graph;

import java.util.*;
import java.io.*;

public class swea_d4_7465_창용마을무리의개수 {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, M;
	static class Node {
		int vertex;
		Node link;
		Node() {}
		Node(int vertex, Node link){
			this.vertex = vertex;
			this.link = link;
		}
	}
	static Node[] G;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_7465_창용마을무리의개수.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
//			입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			G = new Node[N+1];
			v= new boolean[N+1];
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				G[from] = new Node(to, G[from]);
				G[to] = new Node(from, G[to]);
			}
			
//			각 사람 순회
			int ans=0;
			for(int i=1;i<=N;i++) {
				if(!v[i]) {
					dfs(i);
					++ans;
				}
			}
			
//			출력
			sb.append(ans).append("\n");
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
		
	}
	
	static void dfs(int num) {
		v[num]=true;
		for(Node link=G[num];link!=null;link=link.link) {
			if(!v[link.vertex]) dfs(link.vertex);
		}
	}
}