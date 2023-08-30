package a0807;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d4_7733_치즈도둑 {

	static final StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int N, MAX, day;
	static boolean[][] v;
	static int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1}; //상 우 하 좌 
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_7733_치즈도둑.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());		
			map = new int[N][N]; MAX = 0;
			
			//Map 초기화
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}			
			
			
			//100일간 순회
			for(int d=0;d<=100;d++) {
				int cnt=0;
				v = new boolean[N][N]; day = d;
				
				//dfs
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(map[i][j]<=day) {
							v[i][j] = true;
							continue;
						}
						
						//요정이 먹지 않고, 아직 들르지 않은 곳
						if(!v[i][j]) {
//							dfs(i, j);
							bfs(i, j);
							cnt++;
						}
					}
				}
				
				MAX = Math.max(MAX, cnt);
			}
			sb.append(MAX).append("\n");
		}
		
		br.close();
		System.out.println(sb.toString());
	}
	
	//dfs
	public static void dfs(int i, int j) {
		v[i][j] = true;
	
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			
			//신규 idx가 N*N 내에 존재, 아직 들르지 않은 곳, 요정이 먹지 않은 곳
			if(0<=ni&&ni<N && 0<=nj&&nj<N && !v[ni][nj] && map[ni][nj]>day) {
				dfs(ni, nj);
			}
		}
	}
	
	//bfs
	public static void bfs(int i, int j) {
		v[i][j] = true;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		
		q.offerLast(new int[] {i, j});
		while(!q.isEmpty()) {
			int[] ij = q.pollFirst();
			for(int d=0;d<4;d++) {
				int ni = ij[0] + di[d];
				int nj = ij[1] + dj[d];
				
				//신규 idx가 N*N 내에 존재, 아직 들르지 않은 곳, 요정이 먹지 않은 곳
				if(0<=ni&&ni<N && 0<=nj&&nj<N && !v[ni][nj] && map[ni][nj]>day) {
					q.offerLast(new int[] {ni, nj});
					v[ni][nj] = true;
				}
			}
		}
	}

}
