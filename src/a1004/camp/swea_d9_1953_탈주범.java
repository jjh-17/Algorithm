package a1004.camp;

import java.util.*;
import java.io.*;

public class swea_d9_1953_탈주범 {

	static final StringBuilder sb = new StringBuilder();
	static final int di[] = {-1, 0, 1, 0};
	static final int dj[] = {0, 1, 0, -1};
	static final boolean out[][] = {
			{false, false, false, false},
			{true, true, true, true},
			{true, false, true, false},
			{false, true, false, true},
			{true, true, false, false},
			{false, true, true, false},
			{false, false, true, true},
			{true, false, false, true}
	};
	static int N, M, R, C, L;
	static int map[][];
	static boolean v[][];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_1953_탈주범.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
//			입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M]; v = new boolean[N][M];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
//			bFS
			sb.append("#").append(t).append(" ");
			bfs(R, C);
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
	static void bfs(int i, int j) {
		int ans=0;
		final ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		v[i][j] = true;
		queue.offerLast(new int[] {i, j, 1});
		++ans;
		
		while(!queue.isEmpty()) {	
			int[] cur = queue.pollFirst();
			if(cur[2]==L) break;
			
			for(int d=0;d<4;d++) {
//				1. 현 터널에서 나갈 수 있는 방향인지 확인
				if(!out[map[cur[0]][cur[1]]][d]) continue;
				
//				2. 신규 좌표가 범위 내이며, 터널이 있고, 들르지 아니한 곳인지 확인
				int ni = cur[0] + di[d];
				int nj = cur[1] + dj[d];
				if(ni<0 || ni>=N || nj<0 || nj>=M 
						|| map[ni][nj]==0 || v[ni][nj]) continue;
				
//				3. 신규 좌표의 터널로 들어갈 수 있는지 확인(신규 터널 방향 확인)
				int nd = d>=2 ? d-2 : d+2;
				if(out[map[ni][nj]][nd]) {
					v[ni][nj] = true;
					queue.offerLast(new int[] {ni, nj, cur[2]+1});
					++ans;
				}
			}
		}
		
		sb.append(ans).append("\n");
	}
}
