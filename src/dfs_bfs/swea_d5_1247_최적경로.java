package dfs_bfs;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d5_1247_최적경로 {

	static final StringBuilder sb = new StringBuilder();
	static int N, MIN_L;
	static int[] X, Y; //회사 - 고객 - 집 좌표	
	static boolean[] V;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub	
		System.setIn(new FileInputStream("res\\input_swea_d5_1247_최적경로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//입력
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			X = new int[N+2]; Y = new int[N+2]; V = new boolean[N+2];
			for(int i=0;i<N+2;i++) {
				X[i] = Integer.parseInt(st.nextToken());
				Y[i] = Integer.parseInt(st.nextToken());
			}
			
			MIN_L = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			
			sb.append("#").append(t).append(" ").append(MIN_L).append("\n");
		}
			
		br.close();
		System.out.println(sb.toString());
	}
	
	//현재 idx, 현재 거리 합, 고려한 좌표 개수
	static void dfs(int idx, int sum, int considered) {
		if(MIN_L <= sum) return;
		
		if(considered==N) {
			MIN_L = Integer.min(MIN_L, sum + getLength(X[idx], Y[idx], X[1], Y[1]));
			return;
		}
		
		//1~N번째 좌표 탐색
		for(int i=2;i<=N+1;i++) {
			if(V[i]) continue;
			V[i] = true;
			dfs(i, sum + getLength(X[idx], Y[idx], X[i], Y[i]), considered+1);
			V[i] = false;
		}
	}

	
	//두 점 사이 거리
	static int getLength(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
}
