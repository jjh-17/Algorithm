package a0821.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g3_16236_아기상어 {
	
	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, 0, 0, 1}, //상 좌 우 하
					   dj = {0, -1, 1, 0};
	static int N, ans;
	static int[][] map;
	static boolean[][] v;
	static boolean doesEat;
	
	//상어 위치, 크기, 현재 크기에서 먹은 물고기 수
	static int si, sj, ssize, seat; 
	static int ti, tj, td;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_16236_아기상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					si=i; sj=j;
					ssize=2; seat = 0;
				}
			}
		}
		
		//알고리즘
		ans=0;
		moveShark();
		
		br.close();
		sb.append(ans);
		System.out.println(sb.toString());
	}
	
	//상어 이동
	static void moveShark() {
		ans=0;
		
		do {
			v = new boolean[N][N];
			td = 0;
			doesEat = false;
			
			bfs(si, sj);
			
			if(doesEat) {
				map[si][sj]=0; map[ti][tj] = 9;
				si=ti; sj=tj; ans+=td; ++seat;
				if(seat==ssize) {
					seat=0;
					++ssize;
				}
			}
			
		} while(doesEat);
	}
	
	static void bfs(int i, int j) {
		final ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		v[i][j] =  true; 		
		queue.offerLast(new int[] {i, j});
		while(!queue.isEmpty()) {
			//물고기와의 임시 거리가 MAX_VALUE가 아닌 경우. 즉, 먹을 수 있는 물고기가 있는 경우 
			if(doesEat) break;
			
			ti = Integer.MAX_VALUE; tj = Integer.MAX_VALUE;
			++td;
			
			int L = queue.size();
			for(int l=0;l<L;l++) {
				int[] cor = queue.pollFirst();
				
				for(int d=0;d<4;d++) {
					int ni = cor[0]+di[d];
					int nj = cor[1]+dj[d];
					
					//신규 좌표가 N*N이내, 들르지 아니한 곳, 빈 칸이거나 상어 보다 작은 물고기가 위치한 곳일 때 이동 가능
					if(0<=ni&&ni<N && 0<=nj&&nj<N && !v[ni][nj] && map[ni][nj]<=ssize) {
						v[ni][nj] = true;
						
						//물고기가 위치하며, 그 크기가 상어보다 작다면 상어가 먹을 물고기 후보군임
						if(0<map[ni][nj] && map[ni][nj]<ssize) {
							doesEat = true;
							
							//신규 물고기가 현재 물고기보다 위에 있거나, 좌측에 위치할 때 물고기 변경
							if(ni<ti || (ni==ti && nj<tj)) {
								ti=ni; tj=nj;
							}
						}
						queue.offerLast(new int[] {ni, nj});
					}
				}
			}
		}
	}
}
