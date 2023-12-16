package simulation;

import java.util.*;
import java.io.*;

public class bj_g4_2636_치즈 {

	static final StringBuilder sb= new StringBuilder();
	static final int[] di = {-1, 0, 1, 0};	//4방 탐색
	static final int[] dj = {0, 1, 0, -1};
	static int tcnt, time;		//초기 치즈의 개수, 현 탐색에서 사자진 치즈의 개수, 치즈가 사라지는데 걸리는 총 시간
	static int N, M;			//판 세로, 가로 길이
	static int map[][];			//판 정보
	static boolean v[][];		//각 판의 방문 여부
	static final List<int[]> outList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g4_2636_치즈.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M]; v = new boolean[N][M];
		tcnt=0; time=0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) ++tcnt;
			}
		}
		
//		for(int i=0;i<N;i++) System.out.println(Arrays.toString(map[i]));
//		System.out.println();
		
		while(tcnt>0) {
			outList.clear();
			v = new boolean[N][M];
			bfs(0, 0);
			
//			바깥에 위치한 치즈를 모두 없엔다.
			for(int[] cor : outList) map[cor[0]][cor[1]] = 0;
			
			++time;
			tcnt-=outList.size();
			if(tcnt==0) {
				sb.append(time).append("\n");
				sb.append(outList.size());
			}
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
//	바깥에서 치즈를 조여오는 식으로 탐색
	static void bfs(int i, int j) {
		final ArrayDeque<int[]> queue = new ArrayDeque<>();	//BFS 탐색 용
		final boolean[][] out = new boolean[N][M];
		
		v[i][j] = true;
		queue.offerLast(new int[] {i, j});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.pollFirst();
			
			for(int d=0;d<4;d++) {
				int ni = cur[0] + di[d];
				int nj = cur[1] + dj[d];
				
//				신규 좌표가 범위를 벗어나면 넘어감
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				
//				신규 좌표가 치즈라면, 해당 치즈를 경계 치즈로 임명
				if(map[ni][nj]==1) {
					if(!out[ni][nj]) {
						out[ni][nj]=true;
						outList.add(new int[] {ni, nj});
					}
					continue;
				}			
				
//				신규 좌표가 들르지 아니한 곳이면 방문 처리 및 queue에 추가
				if(!v[ni][nj]) {
					v[ni][nj]=true;
					queue.offerLast(new int[] {ni, nj});
				}
			}
		}
	}
}