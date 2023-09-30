package a0814.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g4_16234_인구이동 {

	static final int[] DI={-1, 0, 1, 0}, DJ={0, 1, 0, -1};
	static final ArrayDeque<int[]> queue = new ArrayDeque<>();
	static int N, L, R, CNT, SUM, NUM=0;
	static int[][] MAP_OLD, MAP_NEW; //기존 OLD
	static boolean[][] V;
	static boolean isSame; //전체 국경선이 열려있는가
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_16234_인구이동.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//N, L, R
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		
		//map
		MAP_OLD = new int[N][N]; MAP_NEW = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) MAP_OLD[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//인구 이동
		int answer=0;
		while(true) {
			V = new boolean[N][N];
			move();
			checkSame();
			if(isSame) break;
			++answer;
		}
		
		System.out.println(answer);
	}
	
	//MAP_OLD, MAP_NEW의 일치여부 확인
	static void checkSame() {
		isSame = true;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(MAP_OLD[i][j] != MAP_NEW[i][j]) isSame=false;
				MAP_OLD[i][j] = MAP_NEW[i][j];
			}
		}
	}
	
	//인구 이동
	static void move() {
		NUM=0; //연합 번호 초기화
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!V[i][j]) {
					CNT=0; SUM=0; ++NUM;
					dfs(i, j);
					while(!queue.isEmpty()) {
						int[] p = queue.pollFirst();
						MAP_NEW[p[0]][p[1]] = SUM/CNT;
					}
				}
			}
		}
	}
	
	
	//현 국가 위치 정보(i, j)
	static void dfs(int i, int j) {
		V[i][j]=true; ++CNT; SUM+=MAP_OLD[i][j];
		queue.offerLast(new int[] {i, j});
		for(int d=0;d<4;d++) {
			int ni = i+DI[d], nj = j+DJ[d];
			if(0<=ni&&ni<N && 0<=nj&&nj<N && !V[ni][nj]) {
				int diff = Math.abs(MAP_OLD[i][j]-MAP_OLD[ni][nj]);
				if(L<=diff && diff<=R) { //동일 연합
					dfs(ni, nj);
				}
			}
		}
	}
}
