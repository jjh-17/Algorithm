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
	static boolean v[][], mark[][];
	static int ans;
	
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
			map = new int[N][M]; v = new boolean[N][M]; mark = new boolean[N][M];
			ans=0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
//			DFS
			sb.append("#").append(t).append(" ");
			bfs(R, C);
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
//	이동 중 멈추지 않는다고 가정하고, 끝까지 이동했을 때 들릴 수 있는 모든 곳이 예상 지점임
	static void bfs(int i, int j) {
		int ans=0;
		final ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		v[i][j] = true;
		queue.offerLast(new int[] {i, j, 1});
		++ans;
		
		while(!queue.isEmpty()) {	
			int[] cur = queue.pollFirst();
			if(cur[2]==L) continue;
			
			for(int d=0;d<4;d++) {
				int ni = i + di[d];
				int nj = j + dj[d];
				
//				신규 좌표가 터널을 벗어나면 넘어감
				if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj]) continue;
				
				int nd = d+2>=4 ? d-2 : d+2;
				if(out[map[i][j]][d] && out[map[ni][nj]][nd]) {
					queue.offerLast(new int[] {ni, nj, cur[2]+1});
					v[ni][nj] = true;
				}
			}
		}
		
		sb.append(ans).append("\n");
	}
}
