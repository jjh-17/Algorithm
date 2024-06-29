package graph;

import java.util.*;
import java.io.*;

public class bj_g3_17142_연구소3 {

	static final int D = 4;
	static final int[] di = {-1, 0, 1, 0};
	static final int[] dj = {0, 1, 0, -1};
	static int N, M, cnt_safe, ans;
	static int[][] map;
	
	static final List<Cord> viruses = new ArrayList<>();
	static Cord[] activated;
	static class Cord {
		int i, j;
		
		Cord(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g3_17142_연구소3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					++cnt_safe;	
					continue;
				}
				
				if(map[i][j] == 2)
					viruses.add(new Cord(i, j));
			}
		}
		
//		알고리즘
		activated = new Cord[M];
		if(cnt_safe==0)	ans = 0;
		else {
			ans = Integer.MAX_VALUE;
			comb(0, 0);
		}
		
//		출력
		System.out.println(ans==Integer.MAX_VALUE ? -1 : ans);
		br.close();
	}
	
//	바이러스 중 M개 조합
	static void comb(int start, int cnt) {
		if(cnt == M) {
			BFS();
			return;
		}
		
		for(int i=start;i<viruses.size();i++) {
			Cord cord = viruses.get(i);
			activated[cnt] = new Cord(cord.i, cord.j);
			comb(i+1, cnt+1);
		}
	}
	
	static void BFS() {
		final boolean[][] v = new boolean[N][N];
		final ArrayDeque<Cord> pq = new ArrayDeque<>();
		int time = 0;
		
//		바이러스 시작점 추가
		for(Cord act : activated) {
			pq.offerLast(new Cord(act.i, act.j));
			v[act.i][act.j] = true;
		}
		
//		BFS
		int cnt_safe2 = cnt_safe;
		while(!pq.isEmpty()) {
//			시간 단축 용 탈출 조건
			if(ans <= time++)	return;
			
			int L = pq.size();
			for(int i=0;i<L;i++) {
				Cord cord = pq.pollFirst();
				
				for(int d=0;d<D;d++) {
					int ni = cord.i + di[d];
					int nj = cord.j + dj[d];
					
//					범위를 벗어나거나 이미 들린 곳, 혹은 벽
					if(ni<0 || nj<0 || ni>=N || nj>=N || v[ni][nj] || map[ni][nj]==1)
						continue;
					
					pq.offerLast(new Cord(ni, nj));
					v[ni][nj] = true;
					if(map[ni][nj]==0)	--cnt_safe2;
					if(cnt_safe2==0) {
						ans = Integer.min(ans, time);
						return;
					}
				}
			}
		}
		
		ans = cnt_safe2==0 ? Integer.min(ans, time) : ans;
	}
}
