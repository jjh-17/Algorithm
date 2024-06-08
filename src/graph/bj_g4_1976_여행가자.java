package graph;

import java.util.*;
import java.io.*;

public class bj_g4_1976_여행가자 {

	static int N, M;
	static String ans;
	static int[] plan;
	static int[] parents;
	static int[][] edges;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_1976_여행가자.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edges = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++)	edges[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		plan = new int[M+1];
		for(int i=0;i<M;i++)	plan[i] = Integer.parseInt(st.nextToken());
		
//		솔루션
		solution();
		
//		츨력
		System.out.println(ans);
		br.close();
	}

	static void solution() {
		ans = "YES";
		
//		부모 초기화
		parents = new int[N+1];
		for(int i=1;i<=N;i++)	parents[i] = i;
		
//		두 도시의 부모 최신화
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(edges[i][j]==1)	union(i, j);
			}
		}
	
//		플랜 내 모든 도시의 부모가 동일한지 판단
		int parent = parents[plan[0]];
		for(int i=1;i<M;i++) {
			if(parent != parents[plan[i]]) {
				ans  = "NO";
				return;
			}
		}
	}
	
//	x, y의 부모를 y, x의 부모로 치환
	static void union(int x, int y) {
//		x, y의 부모 찾기
		x = find(x);
		y = find(y);
		
//		부모가 동일하면 종료
		if(x==y)
			return;
		
//		첫번째 도시에 가깝게 부모 수정
		if(x > y)	parents[x] = y;
		else		parents[y] = x;
	}
	
//	x의 부모를 찾는 메서드
	static int find(int x) {
		if(x == parents[x])
			return x;
		
		parents[x] = find(parents[x]);
		return parents[x];
	}
}
