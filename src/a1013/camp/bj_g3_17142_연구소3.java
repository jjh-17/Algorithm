package a1013.camp;

import java.util.*;
import java.io.*;

public class bj_g3_17142_연구소3 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, 0, 1, 0};				// 4방 탐색
	static final int[] dj = {0, 1, 0, -1};
	static int N, M, ANS;								// 연구소 크기, 활성화할 바이러스 개수, 최소 시간
	static int[][] state;								// 연구소 정보
	static int[][] activated;							// 활성화된 바이러스 위치 저장
	static final List<int[]> virus = new ArrayList<>();	// 초기 바이러스 위치 저장
	static int empty;									// 빈 칸의 개수
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g3_17142_연구소3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		state = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				state[i][j] = Integer.parseInt(st.nextToken());
				if(state[i][j]==0) ++empty;
				else if(state[i][j]==2) virus.add(new int[] {i, j});
			}
		}
		
		
//		알고리즘
		ANS = Integer.MAX_VALUE;
		activated = new int[M][2];
		comb(0, 0);
		
//		출력
		sb.append(ANS==Integer.MAX_VALUE ? -1 : ANS);
		System.out.println(sb.toString());
		br.close();
	}
	
	static void comb(int start, int cnt) {
		if(cnt==M) {
			spread();
			return;
		}
		
		for(int i=start;i<virus.size();i++) {
			int[] cor = virus.get(i);
			activated[cnt][0] = cor[0]; activated[cnt][1] = cor[1];
			comb(i+1, cnt+1);
		}
	}

//	BFS 방식으로 
	static void spread() {
		final ArrayDeque<int[]> queue = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N];
		for(int[] cor : activated) {
			queue.offerLast(new int[] {cor[0], cor[1]});
			v[cor[0]][cor[1]] = true;
		}
		
		int time=0;
		int rest = empty;
		while(!queue.isEmpty() && rest>0) {
			++time;
			if(ANS <= time) return;
			
			int L = queue.size();
			for(int i=0;i<L;i++) {
				int[] cur = queue.pollFirst();
				
				for(int d=0;d<4;d++) {
					int ni = cur[0] + di[d];
					int nj = cur[1] + dj[d];
					
//					신규 좌표가 연구소 범위를 벗어나거나 이미 들른 곳, 벽이면 넘어감
					if(ni<0 || ni>=N || nj<0 || nj>=N 
							|| v[ni][nj] || state[ni][nj]==1) continue;
					
					queue.offerLast(new int[] {ni, nj});
					v[ni][nj] = true;
					if(state[ni][nj]==0) --rest;
				}
			}
		}
		
		if(rest==0) ANS = Integer.min(ANS, time);
	}
}
