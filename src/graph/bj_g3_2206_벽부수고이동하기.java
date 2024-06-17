package graph;

import java.util.*;
import java.io.*;

public class bj_g3_2206_벽부수고이동하기 {
	
	static final int D = 4;
	static final int[] di = {-1, 0, 1, 0};
	static final int[] dj = {0, 1, 0, -1};
	static int N, M, ans;
	static char[][] map;
	static class Info {
		int i, j, L, cnt_break;	// 좌표, 이동 거리, 벽 부수기 가능 여부
		
		Info(int i, int j, int L, int cnt_break) {
			this.i = i; this.j = j; 
			this.L = L;
			this.cnt_break = cnt_break;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_2206_벽부수고이동하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N+1][M+1];
		for(int i=1;i<=N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++)	map[i][j+1] = str.charAt(j);
		}
		
//		알고리즘
		solution();
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
	static void solution() {
		final ArrayDeque<Info> dq = new ArrayDeque<>();
		final boolean[][][] v = new boolean[2][N+1][M+1];
		
//		1 * 1인 경우
		if(N==1 && M==1) {
			ans = 1;
			return;
		}
		
		dq.offerLast(new Info(1, 1, 1, 0));
		v[0][1][1] = true;
		ans = -1;
		while(!dq.isEmpty()) {
			Info info = dq.pollFirst();
			
			for(int d=0;d<D;d++) {
				int ni = info.i + di[d];
				int nj = info.j + dj[d];
				
//				도착 여부 체크
				if(ni == N && nj == M) {
					ans = info.L + 1;
					return;
				}
				
//				맵 벗어남 여부 체크
				if(ni < 1 || ni > N || nj < 1 || nj > M)	continue;
				
//				이동 위치가 벽이며, 부수고 이동 가능한 경우
				if(map[ni][nj] == '1' && info.cnt_break == 0 && !v[1][ni][nj]) {
					v[1][ni][nj] = true;
					dq.offerLast(new Info(ni, nj, info.L + 1, 1));
					continue;
				}
				
//				이동 위치가 뚫려있는 경우
				if(map[ni][nj] == '0' && !v[info.cnt_break][ni][nj]) {
					v[info.cnt_break][ni][nj] = true;
					dq.offerLast(new Info(ni, nj, info.L + 1, info.cnt_break));
				}
			}
		}
	}
}