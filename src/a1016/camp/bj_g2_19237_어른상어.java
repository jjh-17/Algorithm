package a1016.camp;

import java.util.*;
import java.io.*;

public class bj_g2_19237_어른상어 {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, M, K;							// 격자 크기, 상어가 든 격자 개수, 냄새가 사라지는데 필요한 이동 횟수
	static final int[] di = {0, -1, 1, 0, 0};	// 상 하 좌 우
	static final int[] dj = {0, 0, 0, -1, 1};
	
	static class Map {							// 각 격자내 정보
		int sharkNum, k;						// 상어 번호, 상어 냄새가 깃든 시간
		Map(int sharkNum, int k) {
			this.sharkNum = sharkNum; this.k = k;
		}
	}
	static Map[][] map;							// 격자 총 정보
	
	static class Shark{							// 상어 정보
		int i, j, dir;							// 상어 위치, 상어 방향
		Shark(int i, int j, int dir) {
			this.i = i; this.j=j; this.dir = dir;
		}
	}
	static Shark[] sharks;
	static int[][][] priorityDir;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g2_19237_어른상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
//		1. N, M, K 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
//		2. 격자 정보 및 각 상어 정보 입력
		map = new Map[N][N]; sharks = new Shark[M+1];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = new Map(num, 0);
				if(num>0) sharks[num] = new Shark(i, j, 0);
			}
		}
		
//		3. 각 상어의 초기 방향값 입력
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=M;i++) sharks[i].dir = Integer.parseInt(st.nextToken());
		
//		4. 각 상어의 방향 우선순위 입력
		priorityDir = new int[M+1][5][5];
		for(int i=1;i<=M;i++) {
			for(int j=1;j<=4;j++) {
				st = new StringTokenizer(br.readLine());
				for(int d=1;d<=4;d++) priorityDir[i][j][d] = Integer.parseInt(st.nextToken());
			}
		}
		
//		알고리즘
		
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}	
	
//	상어 이동
	static void move() {
//		각 상어 순회
		for(int i=1;i<=M;i++) {
//			현 상어가 격자 밖이면 넘어감
			if(sharks[i].dir==-1) continue;
			
//			4방 탐색
			int dir = sharks[i].dir, si = sharks[i].i, sj = sharks[i].j;
			for(int d=1;d<=4;d++) {
				int ndir = priorityDir[i][dir][d];
				int ni = si+di[dir], nj = sj+dj[dir];
				
//				1. 인접 칸이 격자 밖인 경우 넘어감
				if(ni<0||ni>=N || nj<0||nj>=N) continue;
				
//				2. 인접 칸에 상어 냄새가 없는 경우
				if(map[ni][nj].sharkNum==0) {
					map[ni][nj].sharkNum = i;
					
				}
				
//				3. 
				
//				4. 
				
			}
		}
	}

}
