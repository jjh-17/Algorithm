package a0814;

import java.io.*;
import java.util.*;

public class bj_g4_17144_미세먼지안녕 {
	
	static final StringBuilder sb = new StringBuilder();
	static final int[] DI = {-1, 0, 1, 0}, DJ = {0, 1, 0, -1};
	static int R, C, T; //격자판 크기, 지날 시간
	static int[] CI=new int[2], CJ= {0, 0}; //공기 청정기 위치
	static int[][] mapOld;
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g4_17144_미세먼지안녕.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		
		// R, C, T
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		//격자 정보 입력
		mapOld = new int[R][C];
		int cnt=0;
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) mapOld[i][j] = Integer.parseInt(st.nextToken());
			if(mapOld[i][0]==-1) CI[cnt++] = i;
		}
			
		for(int t=0;t<T;t++) {
			spreadDust();
			cleaner();
		}
		
		System.out.println(getSum());
		
	}
	
	//공기 청정기 작동
	static void cleaner() {
		//위쪽 반시계
		for(int i=0;i<CI[0]-1;i++) mapOld[CI[0]-1-i][0] = mapOld[CI[0]-2-i][0]; //좌
		for(int j=0;j<C-1;j++) mapOld[0][j] = mapOld[0][j+1]; //상
		for(int i=0;i<CI[0];i++) mapOld[i][C-1] = mapOld[i+1][C-1]; //우
		for(int j=0;j<C-2;j++) mapOld[CI[0]][C-1-j] = mapOld[CI[0]][C-2-j]; //하
		mapOld[CI[0]][1] = 0;
		
		//아래 쪽 시계
		for(int i=CI[1]+1;i<R-1;i++) mapOld[i][0] = mapOld[i+1][0]; //좌
		for(int j=0;j<C-1;j++) mapOld[R-1][j] = mapOld[R-1][j+1]; //하
		for(int i=R-1;i>CI[1];i--) mapOld[i][C-1] = mapOld[i-1][C-1]; //우
		for(int j=C-1;j>1;j--) mapOld[CI[1]][j] = mapOld[CI[1]][j-1]; //상
		mapOld[CI[1]][1] = 0;
	}
	
	//미세먼지 확산
	static void spreadDust() {
		int[][]  mapNew = new int[R][C];
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				int cnt=0;
				mapNew[i][j] += mapOld[i][j];
				for(int d=0;d<4;d++) {
					int ni=i+DI[d], nj=j+DJ[d];
					if(0<=ni&&ni<R && 0<=nj&&nj<C && mapOld[ni][nj]!=-1) {
						mapNew[ni][nj] += (mapOld[i][j]/5);
						++cnt;
					}
				}
				mapNew[i][j] -= (mapOld[i][j]/5)*cnt;
			}
		}
		
		mapOld = mapNew;
	}
	
	static int getSum() {
		int sum=0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) sum+=mapOld[i][j];
		}
		
		return sum+2;
	}
}
