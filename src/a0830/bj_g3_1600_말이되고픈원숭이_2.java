package a0830;

import java.util.*;
import java.io.*;

public class bj_g3_1600_말이되고픈원숭이_2 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] diHorse = {-2, -1, 1, 2, 2, 1, -1, -2},
			 		   djHorse = {1, 2, 2, 1, -1, -2, -2, -1},
			 		   diMonkey = {-1, 0, 1, 0},
			 		   djMonkey = {0, 1, 0, -1};
	static int K, W, H;
	static int[][] field; //장애물

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
		
//		for(Info[] s : state) System.out.println(Arrays.toString(s));
 		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void BFS() {
		ArrayDeque<Info> queue = new ArrayDeque<>();
		
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

					//신규좌표와 기존 위치의 cntK가 다르거나, 전체 횟수가 기존 위치의 것이 더 크다면 continue
					int ncntK = state[ni][nj].cntK, ncntM = state[ni][nj].cntM;
					if(ni==H-1 && nj==W-1) {
						sb.append(cntK+1+cntM);
						return;
					}
					
					//신규 좌표의 횟수가 0 OR 
					if(ncntK+ncntM==0 || ncntK+ncntM>cntK+1+cntM) {
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
				
				//신규 좌표가 비어있거나, 신규좌표 횟수보다 현재 횟수가 더 크거나, 같으나 신규좌표의 cntK가 현재 cntK보다 클 때
				int ncntK = state[ni][nj].cntK, ncntM = state[ni][nj].cntM;
				if(ni==H-1 && nj==W-1) {
					sb.append(cntK+cntM+1);
					return;
				}
				
				//신규좌표에 첫입성 OR 
				if(ncntK+ncntM==0 || ncntK!=cntK || ncntK+ncntM>cntK+cntM+1) {
					queue.offerLast(new Info(ni, nj, cntK, cntM+1));
					state[ni][nj].cntK = cntK;
					state[ni][nj].cntM = cntM+1;
				}
			}
		}
		
		sb.append(-1);
	}

}
