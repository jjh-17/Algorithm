package a0831;

import java.util.*;
import java.io.*;

public class bj_g3_1600_말이되고픈원숭이 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] diHorse = {-2, -1, 1, 2, 2, 1, -1, -2},
			 		   djHorse = {1, 2, 2, 1, -1, -2, -2, -1},
			 		   diMonkey = {-1, 0, 1, 0},
			 		   djMonkey = {0, 1, 0, -1};
	static int K, W, H;
	static int[][] field; //필드 정보 - 장애물

	static class Info {
		int i, j;
		int cntK, cntM;
		public Info(int i, int j, int cntK, int cntM) {
			this.i = i; this.j = j;
			this.cntK = cntK; this.cntM = cntM;
		}
		
		@Override
		public String toString() {
			return "(" + cntK + " " + cntM + ")";
		}
	}
	static Info[][] state; //각 좌표 상태
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_1600_말이되고픈원숭이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		field = new int[H][W]; state = new Info[H][W];
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
				state[i][j] = new Info(i, j, 0, 0);
			}
		}
		
		//BFS
		BFS();

		//출력
		System.out.println(sb.toString());
		br.close();
	}
	
	static void BFS() {
		final ArrayDeque<Info> queue = new ArrayDeque<>();
		
		//필드가 1*1인 경우
		if(H==1 && W==1) {
			sb.append(field[0][0]==0 ? 0 : -1);
			return;
		}
		
		queue.offerLast(new Info(0, 0, 0, 0));
		while(!queue.isEmpty()) {
			Info info = queue.pollFirst();
			int ci = info.i, cj = info.j;
			int cntK = info.cntK, cntM = info.cntM;
			
			//말처럼 이동
			if(cntK<K) {
				for(int d=0;d<8;d++) {
					int ni = ci + diHorse[d];
					int nj = cj + djHorse[d];
					
					//신규 좌표가 필드를 벗어나거나, 장애물이 위치하거나, (0, 0)인 경우 continue
					if(ni<0 || ni>=H || nj<0 || nj>=W 
							|| field[ni][nj]==1 || (ni==0 && nj==0)) continue;

					//신규 좌표가 도착지인 경우 ==> 종료 
					if(ni==H-1 && nj==W-1) {
						sb.append(cntK+1+cntM);
						return;
					}					
					
					//신규 좌표에 최초 입성
					//OR 현 좌표에서 이동 시의 cntK와 기존 cntK가 동일할 때, 이동 시 총횟수가 기존 총횟수부다 작을 때
					//OR 현 좌표에서 이동 시의 cntK가 기존 cntK보다 작을 때 갱신 ==> 왜 cntK가 작으면 항상 좋은 것인가?
					int ncntK = state[ni][nj].cntK, ncntM = state[ni][nj].cntM;
					if(ncntK+ncntM==0 || (ncntK==cntK+1 && ncntK+ncntM>cntK+1+cntM) || ncntK>cntK+1) {
						queue.offerLast(new Info(ni, nj, cntK+1, cntM));
						state[ni][nj].cntK = cntK+1;
						state[ni][nj].cntM = cntM;		
					}
				}
			}
			
			//일반 이동
			for(int d=0;d<4;d++) {
				int ni = ci + diMonkey[d];
				int nj = cj + djMonkey[d];
				
				//신규 좌표가 필드를 벗어나거나, 장애물이 위치하거나, (0, 0)인 경우 continue
				if(ni<0 || ni>=H || nj<0 || nj>=W 
						|| field[ni][nj]==1 || (ni==0 && nj==0)) continue;
				
				//신규 좌표가 도착지인 경우 ==> 종료 
				if(ni==H-1 && nj==W-1) {
					sb.append(cntK+cntM+1);
					return;
				}
				
				//신규 좌표에 최초 입성
				//OR 현 좌표에서 이동 시의 cntK와 기존 cntK가 동일할 때, 이동 시 총횟수가 기존 총횟수부다 작을 때
				//OR 현 좌표에서 이동 시의 cntK가 기존 cntK보다 작을 때 갱신 ==> 일반적으로는 cntK가 작을수록 유리하다. 
				int ncntK = state[ni][nj].cntK, ncntM = state[ni][nj].cntM;
				if(ncntK+ncntM==0 || (ncntK==cntK && ncntK+ncntM>cntK+cntM+1) || ncntK>cntK) {
					queue.offerLast(new Info(ni, nj, cntK, cntM+1));
					state[ni][nj].cntK = cntK;
					state[ni][nj].cntM = cntM+1;		
				}
			}
		}
		sb.append(-1);
	}
}
