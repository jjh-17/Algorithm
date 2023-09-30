package a0822.camp;

import java.util.*;
import java.io.*;

public class bj_g5_13023_ABCDE {

	static final StringBuilder sb = new StringBuilder();
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}
	static Node[] g;
	static int N, M, ans;
	static int a, b;
	static boolean[] v;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_13023_ABCDE.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new Node[N];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			g[a] = new Node(b, g[a]);
			g[b] = new Node(a, g[b]);
		}
		
		for(int i=0;i<N;i++) {
			v = new boolean[N];
			flag = false;
			dfs(i, 1);
			
			if(flag) break;
		}
		
		sb.append(flag ? 1 : 0);
		System.out.println(sb.toString());
		br.close();
	}
	
	static void dfs(int a, int cnt) {
		if(flag) return;

		if(cnt==5) {
			flag = true;
			return;
		}
		
		v[a] = true;
		for(Node node=g[a];node!=null;node=node.link) {
			if(v[node.vertex]) continue;
			dfs(node.vertex, cnt+1);
		}
		v[a] = false;
	}
	
	
}
