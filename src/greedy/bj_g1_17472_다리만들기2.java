package greedy;

import java.util.*;
import java.io.*;

//해결 중
public class bj_g1_17472_다리만들기2 {

	static final StringBuilder sb = new StringBuilder();
	static final int di[] = {-1, 0, 1, 0};
	static final int dj[] = {0, 1, 0, -1};
	static int N, M;				//섬의 세로, 가로 길이
	static int map[][], dp[][];		//지도 정보, 각 섬에서 이동 시의 최단 거리
	static boolean v[][], b[][][];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g1_17472_다리만들기2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M]; dp = new int[N+1][M+1];
		v = new boolean[N][M]; b = new boolean[N][M][4];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		map을 순회하며 각 섬에 번호 붙이기
		int num=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0 && !v[i][j]) dfs(i, j, ++num);
				dp[i+1][j+1] = Integer.MAX_VALUE;
			}
		}
		
//		map을 순회하며 각 섬에서 모든 방향으로 다리 놓기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]>0 && checkEdge(i, j)) {
					for(int d=0;d<4;d++) {
						if(b[i][j][d]) continue;
					}
				}
			}
		}
		
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
//	섬의 번호 붙이기
	static void dfs(int i, int j, int num) {
		v[i][j] = true;
		map[i][j] = num;
		
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
			if(map[ni][nj]!=0 && !v[ni][nj])
				dfs(ni, nj, num);
		}
	}
	
	static boolean checkEdge(int i, int j) {
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
			if(map[ni][nj]==0) return true;
		}
		
		return false;
	}
}
