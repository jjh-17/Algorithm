package a0816.camp;

import java.util.*;
import java.io.*;

public class bj_g2_3109_빵집 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] DI = {-1, 0, 1},
					   DJ = {1, 1, 1};
	static final char EMPTY='.', BUILDING='x';
	static int R, C, CNT;
	static int CI;
	static char[][] MAP;
	static boolean[][] V;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g2_3109_빵집.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//R, C
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		MAP = new char[R][C]; CNT = 0; V = new boolean[R][C];
		CI=0;
		
		//MAP
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) MAP[i][j] = str.charAt(j);
		}
		
		for(int i=0;i<R;i++) {
			if(dfs(i, 0)) ++CNT;
		}
		
		
		br.close();
		System.out.println(CNT);
	}
	
	//현 위치, 현재 파이프 라인 경로 개수
	//파이프를 최대한 위로 설치하는 것이 가장 좋은 경우
	static boolean dfs(int i, int j) {
		if(j==C-1) { 
			V[i][j] = true;
			return true;
		}
		
		//탐색
		V[i][j] = true;
		for(int d=0;d<3;d++) {
			int ni = i + DI[d];
			int nj = j + DJ[d];
			
			//신규 좌표 조건: R*C격자 내부, 빈 공간일 것, 들르지 아니한 곳일 것
			if(0<=ni&&ni<R && 0<=nj&&nj<C && MAP[ni][nj]==EMPTY && !V[ni][nj]) {
				if(dfs(ni, nj)) return true;
			}
		}
		
		//현 경로를 실패한 경우, 해당 경로는 더이상 탐색할 필요 없음 ==> V 원상 복귀 불필요!!!!!
		return false;
	}
}
