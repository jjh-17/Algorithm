package a0818;


import java.util.*;
import java.io.*;

public class swea_d4_1219_길찾기 {
	
	//공통
	static final StringBuilder sb = new StringBuilder();
	static final int START=0, END=99;
	static int N, ANS;
	static boolean[]v;
	static boolean flag;
	
	//인접 행렬
	static int[][] map1;

	
	//인접 리스트
	static List<Integer>[] map2;
	
	//인접 노드
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}
	static Node[] map3;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_1219_길찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int T = 10;
		while(T-->0) {
			//초기화
			ANS = 0; flag = false;
			
			//테스트 케이스 번호, 길의 총 개수 입력
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			v = new boolean[100];
			
			//그래프 초기화
			map1 = new int[100][100];
			
			map2 = new List[100]; 
			for(int i=0;i<100;i++) map2[i] = new ArrayList<Integer>();
			
			map3 = new Node[100];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				map1[s][e]=1;
				map2[s].add(e);
				map3[s] = new Node(e, map3[s]);
			}
			
			
//			dfs1(START);			
//			bfs1(START);
//			dfs2(START);
//			bfs2(START);
			dfs3(START);
//			bfs3(START);
			
			sb.append("#").append(t).append(" ").append(ANS).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}
	
	//인접 행렬 dfs
	static void dfs1(int s) {
		if(flag) return;
		
		if(s==END) {
			ANS=1;
			flag = true;
			return;
		}
		
		v[s] = true;		
		for(int e=0;e<100;e++) {
			if(map1[s][e]==1 && !v[e]) dfs1(e);
		}
	}
	
	//인접 행렬 dfs
	static void bfs1(int s) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.offerLast(0); v[0] = true;
		
		while(!queue.isEmpty()) {
			int start = queue.pollFirst();
			
			if(start == END) {
				ANS=1;
				return;
			}
			
			for(int end=0;end<100;end++) {
				if(map1[start][end]==1 && !v[end]) {
					v[end] = true;
					queue.offerLast(end);
				}
			}
		}
	}
	
	//인접 리스트 dfs
	static void dfs2(int s) {
		if(flag) return;
		
		if(s==END) {
			ANS=1;
			flag = true;
			return;
		}
		
		v[s] = true;		
		for(int e : map2[s]) {
			if(!v[e]) dfs2(e);
		}
	}

	
	//인접 리스트 bfs
	static void bfs2(int s) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.offerLast(0); v[0] = true;
		
		while(!queue.isEmpty()) {
			int start = queue.pollFirst();
			
			if(start == END) {
				ANS=1;
				return;
			}
			
			for(int end : map2[start]) {
				if(!v[end]) {
					v[end] = true;
					queue.offerLast(end);
				}
			}
		}
	}
	
	//인접 노드 dfs
	static void dfs3(int s) {
		if(flag) return;
		
		if(s==END) {
			ANS=1;
			flag = true;
			return;
		}
		
		v[s] = true;		
		for(Node e=map3[s];e!=null;e=e.link) {
			if(!v[e.vertex]) dfs3(e.vertex);
		}
	}
		
	//인접 노드 bfs
	static void bfs3(int s) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.offerLast(0); v[0] = true;
		
		while(!queue.isEmpty()) {
			int start = queue.pollFirst();
			
			if(start == END) {
				ANS=1;
				return;
			}
			
			for(Node e=map3[start];e!=null;e=e.link) {
				if(!v[e.vertex]) {
					v[e.vertex] = true;
					queue.offerLast(e.vertex);
				}
			}
		}
	}
}