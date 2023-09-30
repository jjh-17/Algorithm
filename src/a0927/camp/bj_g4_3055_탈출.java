package a0927.camp;

import java.util.*;
import java.io.*;

public class bj_g4_3055_탈출 {

	static final StringBuilder sb = new StringBuilder();
	static final int di[] = {-1, 0, 1, 0},
					 dj[] = {0, 1, 0, -1};
	static final char EMPTY='.', ROCK='X', WATER='*', BIBER='D';
	static int R, C, ANS;
	static char map[][];
	static boolean v[][];
	static List<int[]> water = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_3055_탈출.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
			
//		입력
		int si=0, sj=0;		//고슴도치의 위치
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C]; v = new boolean[R][C];
			
		for(int i=0;i<R;i++) {
			String input = br.readLine();
			
			for(int j=0;j<C;j++) {
				map[i][j] = input.charAt(j);
				
				switch(map[i][j]) {
				case 'S': 
					si=i; sj=j; map[i][j]=EMPTY; 
					break;
				case WATER:
					water.add(new int[] {i, j});
					break;
				}
			}
		}
			
//		BFS
		ANS=-1;
		bfs(si, sj);
		sb.append(ANS==-1 ? "KAKTUS" : ANS);
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
	static void bfs(int i, int j) {
		final ArrayDeque<int[]> queue = new ArrayDeque<>();
		int time=0;
		
		v[i][j] = true;
		queue.offerLast(new int[] {i, j});
		
		while(!queue.isEmpty()) {
			++time;
			
//			물 확산
			spreadWater();
			
//			고슴도치의 이동
			int L = queue.size();
			for(int l=0;l<L;l++) {
				int cur[] = queue.pollFirst();
				
				for(int d=0;d<4;d++) {
					int ni = cur[0] + di[d];
					int nj = cur[1] + dj[d];

//					신규 좌표가 필드를 벗어나면 넘어감
					if(ni<0 || ni>=R || nj<0 || nj>=C) continue;
					
//					신규 좌표에 비버 굴이 있다면 탐색 종료
					if(map[ni][nj]==BIBER) {
						ANS = time;
						return;
					}
					
//					신규 좌표가 들르지 아니한 곳이며, 빈 곳이면 이동
					if(!v[ni][nj] && map[ni][nj]==EMPTY) {
						v[ni][nj] = true;
						queue.offerLast(new int[] {ni, nj});
					}
				}
			}
		}
		
	}
	
//	물 확장
	static void spreadWater() {
		List<int[]> newWater = new ArrayList<>();
		
		for(int[] cor : water) {
			for(int d=0;d<4;d++) {
				int ni = cor[0] + di[d];
				int nj = cor[1] + dj[d];
				
//				확장 좌표가 필드를 벗어나면 넘어감
				if(ni<0 || ni>=R || nj<0 || nj>=C) continue;
				
//				확장 좌표가 빈 칸인 경우에만 확장
				if(map[ni][nj]==EMPTY) {
					map[ni][nj] = WATER;
					newWater.add(new int[] {ni, nj});
				}
			}
		}
		water = newWater;
	}
}
