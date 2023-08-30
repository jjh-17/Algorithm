package a0830.camp;

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
 		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void BFS() {
		ArrayDeque<Info> queue = new ArrayDeque<>();
		
		queue.offerLast(new Info(0, 0, 0, 0));
		while(!queue.isEmpty()) {
			Info info = queue.pollFirst();
			
			//종료 조건
			if(info.i==H-1 && info.j==W-1) {
				sb.append(info.cntK + info.cntM).append("\n");
				return;
			}
			
			//말처럼 이동
			if(info.cntK<K) {
				
			}
			
			//일반 이동
			for(int d=0;d<4;d++) {
				int ni = info.i + diMonkey[d];
				int nj = info.j + djMonkey[d];
				
				//신규 좌표가 필드를 벗어남
				if(ni<0 || ni>=H || nj<0 || nj>=W) continue;
				
				//신규 좌표가 장애물
				if(field[ni][nj]==1) continue;
				
				
				
			}
		}
	}

}
