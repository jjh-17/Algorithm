package graph;

import java.util.*;
import java.io.*;

public class bj_g3_4179 {

	static int R, C, ans;
	static char[][] map;
	static int[][] fires, jihuns;
	static final int[] di = {1, 0, -1, 0};
	static final int[] dj = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g4_4179_불.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int ji=0, jj=0;
		List<int[]> fire_list = new ArrayList<>();
		
		map = new char[R][C];
		fires = new int[R][C];	jihuns = new int[R][C];
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++)	{
				char c = str.charAt(j);
				map[i][j] = c;
				if(c=='J') { 
					ji = i;	jj = j;
					jihuns[ji][jj] = 1;
				}
				if(c=='F')	{
					fire_list.add(new int[] {i, j});
					fires[i][j] = 1;
				}
			}
		}
		
//		알고리즘
		spreadFire(fire_list);
		solution(ji, jj);
		
//		출력
		System.out.println(ans==0 ? "IMPOSSIBLE" : ans);
		br.close();
	}
	
//	불을 퍼짐
	static void spreadFire(List<int[]> fire_list) {
		final ArrayDeque<int[]> deque = new ArrayDeque<>();
		final boolean[][] v = new boolean[R][C];
		int time = 1;
		
		for(int[] fire : fire_list) {
			deque.offerLast(new int[] {fire[0], fire[1]});
			v[fire[0]][fire[1]] = true;
		}
		
		while(!deque.isEmpty()) {
			++time;
			
			int T=deque.size();
			for(int t=0;t<T;t++) {
				int[] fire = deque.pollFirst();
				for(int d=0;d<4;d++) {
					int ni = fire[0] + di[d];
					int nj = fire[1] + dj[d];
					if(checkPath(ni, nj, v) && map[ni][nj]!='#')	{
						v[ni][nj] = true;	fires[ni][nj] = time;
						deque.offerLast(new int[] {ni, nj});
					}
				}
			}
		}
	}

//	지훈의 현재 위치
	static void solution(int ji, int jj) {
		final ArrayDeque<int[]> deque = new ArrayDeque<>();
		final boolean[][] v = new boolean[R][C];
		int time = 1;
		
//		첫 시작이 Edge인 경우
		if(checkEdge(ji, jj)) {
			ans = time;
			return;
		}
		
		
		deque.offerLast(new int[] { ji, jj });
		v[ji][jj] = true;
		while(!deque.isEmpty()) {
			++time;
			
			int T = deque.size();
			for(int t=0;t<T;t++) {
				int[] pos = deque.pollFirst();
				for(int d=0;d<4;d++) {
					int ni = pos[0] + di[d];
					int nj = pos[1] + dj[d];
					
					if(checkPath(ni, nj, v) && map[ni][nj]=='.' && (fires[ni][nj]>time || fires[ni][nj]==0)) {
						jihuns[ni][nj] = time;
						
//						탈출
						if(checkEdge(ni, nj)) {
							ans = time;
							return;
						}
						
//						이동
						v[ni][nj] = true;
						deque.offerLast(new int[] {ni, nj});
					}
				}
			}
		}
	}
	
	static boolean checkPath(int ni, int nj, boolean[][] v) {
		return 0<=ni && ni<R && 0<=nj && nj<C && !v[ni][nj];
	}
	
	static boolean checkEdge(int i, int j) {
		return i==0 || i==R-1 || j==0 || j==C-1;
	}
}
