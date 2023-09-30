package a0927.camp;

import java.util.*;
import java.io.*;

public class swea_d5_7793_오나의여신님 {
	
	static final StringBuilder sb = new StringBuilder();
	static final int di[] = {-1, 0, 1, 0},
					 dj[] = {0, 1, 0, -1};
	static final char SAFE='.', ROCK='X', DEMON='*', GOD='D';
	static int N, M, ANS;
	static char map[][];
	static boolean v[][];
	static List<int[]> demons = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d5_7793_오나의여신님.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			int si=0, sj=0;		//수연이 위치
			demons.clear();		//악마의 손아귀 비우기
			
//			입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M]; v = new boolean[N][M];
			
			for(int i=0;i<N;i++) {
				String input = br.readLine();
				for(int j=0;j<M;j++) {
					map[i][j] = input.charAt(j);
					switch(map[i][j]) {
					case 'S': 
						si=i; sj=j; map[i][j]=SAFE; 
						break;
					case DEMON:
						demons.add(new int[] {i, j});
						break;
					}
				}
			}
			
//			BFS
			ANS=-1;
			bfs(si, sj);
			sb.append(ANS==-1 ? "GAME OVER" : ANS).append("\n");
		}
		
		
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
			
//			악마 영역 확산
			spreadDemon();
			
//			수연의 이동
			int R = queue.size();
			for(int r=0;r<R;r++) {
				int cur[] = queue.pollFirst();
				
				for(int d=0;d<4;d++) {
					int ni = cur[0] + di[d];
					int nj = cur[1] + dj[d];
					
//					신규 좌표가 필드를 벗어나면 넘어감
					if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
					
//					신규 좌표에 신이 있다면 탐색 종료
					if(map[ni][nj]==GOD) {
						ANS = time;
						return;
					}
					
//					신규 좌표가 들르지 아니한 곳이며, 안전지대면 이동
					if(!v[ni][nj] && map[ni][nj]==SAFE) {
						v[ni][nj] = true;
						queue.offerLast(new int[] {ni, nj});
					}
				}
			}
		}
		
	}
	
//	악마의 손아귀 확장
	static void spreadDemon() {
		List<int[]> newDemons = new ArrayList<>();
		
		for(int[] cor : demons) {
			for(int d=0;d<4;d++) {
				int ni = cor[0] + di[d];
				int nj = cor[1] + dj[d];
				
//				확장 좌표가 필드를 벗어나면 넘어감
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				
//				확장 좌표가 빈 칸인 경우에만 확장
				if(map[ni][nj]==SAFE) {
					map[ni][nj] = DEMON;
					newDemons.add(new int[] {ni, nj});
				}
			}
		}
		demons = newDemons;
	}
}