package graph;

import java.util.*;
import java.io.*;

public class bj_g3_1600_말이되고픈원숭이 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] diHorse = {-2, -1, 1, 2, 2, 1, -1, -2},
			 		   djHorse = {1, 2, 2, 1, -1, -2, -2, -1},
			 		   diMonkey = {-1, 0, 1, 0},
			 		   djMonkey = {0, 1, 0, -1};
	static int K, W, H;		//
	static int[][] field; 	//필드 정보 - 장애물
	
	//말 이동 횟수에 따른 좌표 방문 여부.
	//말-원숭이 이동 순서가 어떻든 BFS이므로 최단 경로 정보로 갱신된다.
	static boolean[][][] v;	

	static class Info {
		int i, j;		//현 좌표
		int cntK, cntM;	//말 이동 횟수, 원숭이 이동 횟수
		public Info(int i, int j, int cntK, int cntM) {
			this.i = i; this.j = j;
			this.cntK = cntK; this.cntM = cntM;
		}
	}
	
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
		
		field = new int[H][W];
		v = new boolean[H][W][K+1];
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
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
		
		//맵 사이즈 1*1
		if(H==1 && W==1) {
			sb.append(0);
			return;
		}
		
		queue.offerLast(new Info(0, 0, 0, 0));
		v[0][0][0]=true;
		
		while(!queue.isEmpty()) {
			Info cur = queue.pollFirst();
			
			//원숭이 이동
			for(int d=0;d<4;d++) {
				int ni = cur.i+diMonkey[d];
				int nj = cur.j+djMonkey[d];
				
				//신규 좌표가 필드를 벗어나거나 장애물이 있거나 이미 들른 곳이면 넘어감
				//이미 말로 cntK만큼 이동하였다면, 새로운 총 이동횟수는 반드시 기존 이동횟수보다 큼
				if(ni<0||ni>=H || nj<0 || nj>=W || field[ni][nj]==1 || v[ni][nj][cur.cntK]) continue;
				
				if(ni==H-1 && nj==W-1) {
					sb.append(cur.cntK + cur.cntM + 1);
					return;
				}
				
				v[ni][nj][cur.cntK]=true;
				queue.offerLast(new Info(ni, nj, cur.cntK, cur.cntM+1));
			}
			
			//말 이동
			if(cur.cntK==K) continue;
			for(int d=0;d<8;d++) {
				int ni = cur.i+diHorse[d];
				int nj = cur.j+djHorse[d];
				
				//신규 좌표가 필드를 벗어나거나 장애물이 있거나 이미 들른 곳이면 넘어감
				//이미 말로 cntK만큼 이동하였다면, 새로운 총 이동횟수는 반드시 기존 이동횟수보다 큼
				if(ni<0 || ni>=H || nj<0 || nj>=W || field[ni][nj]==1 || v[ni][nj][cur.cntK+1]) continue;
				
				if(ni==H-1 && nj==W-1) {
					sb.append(cur.cntK + cur.cntM + 1);
					return;
				}
				
				v[ni][nj][cur.cntK+1]=true;
				queue.offerLast(new Info(ni, nj, cur.cntK+1, cur.cntM));
			}
		}
		
		sb.append(-1);
	}
}