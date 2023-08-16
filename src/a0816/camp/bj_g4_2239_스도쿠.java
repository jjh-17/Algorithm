package a0816.camp;


import java.util.*;
import java.io.*;

//해결 중
public class bj_g4_2239_스도쿠 {
	
	static final StringBuilder sb = new StringBuilder();
	static final int N=9, n=3;
	static final int[][] MAP = new int[N][N]; //9*9스도쿠 판
	static final List<Integer>[] EMPTY = new ArrayList[N]; //스도쿠 판에서 빈 공간(row 순으로 정렬됨)
	static final boolean[][] PV = new boolean[N][N]; //순열 생성을 위한 visited
	static final int[][] arr = new int[N][N];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_2239_스도쿠.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//MAP
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			EMPTY[i] = new ArrayList<Integer>();
			for(int j=0;j<N;j++) {
				MAP[i][j] = str.charAt(j)-'0';
				if(MAP[i][j]==0) EMPTY[i].add(j);
			}
		}
		
//		perm(0, 0);
		System.out.println(checkCol());
		
		br.close();
		System.out.println(sb.toString());
		
	}
	
	
	//각 row에 대한 순열 생성
	static void perm(int ci, int cnt) {
		if(cnt==EMPTY[ci].size()) {
			//ROW 순열 MAP에 할당
			for(int i=0;i<cnt;i++) MAP[ci][EMPTY[ci].get(i)] = arr[ci][i]+1;
			
			if(ci==N-1) {
				if(checkCol()) {
					for(int[] M : MAP) {
						for(int m : M) sb.append(m).append(" ");
						sb.append("\n");
					}
					return;
				} return;
			}
			
			
//			if((ci+1)%n == 0) { //현재 i가 3의 배수
//				if(checkSquare(ci-2)) { //3개 사각형 확인
//					if(ci==N-1) { //9개 row를 채움
//						if(checkCol()) { //모든 col 확인 
//							for(int[] M : MAP) {
//								for(int m : M) sb.append(m).append(" ");
//								sb.append("\n");
//							}
//						} else return;
//					}
//				} else return;
//			}
			perm(ci+1, 0);
			return;
		}
		
		for(int i=0;i<EMPTY[ci].size();i++) {
			if(PV[ci][i]) continue;
			PV[ci][i] =  true;
			arr[ci][cnt] = EMPTY[ci].get(i);
			perm(ci, cnt+1);
			PV[ci][i] = false;
		}
	}
	
	//모든 세로줄 확인
	static boolean checkCol() {
		boolean[] v;
		
		for(int j=0;j<N;j++) {
			v = new boolean[N];
			for(int i=0;i<N;i++) {
				if(v[MAP[i][j]-1]) return false;
				v[MAP[i][j]-1] = true;	
			}
		}
		
		return true;
	}
	
	//(ci, 0) 부터 3*3 사각형 3개 확인
	static boolean checkSquare(int ci) {
		boolean[] v;
		
		for(int d=0;d<n;d++) {
			v = new boolean[N];
			for(int i=ci;i<ci+n;i++) {
				for(int j=n*d;j<n*(d+1);j++) {
					if(v[MAP[i][j]-1]) return false;
					v[MAP[i][j]-1] = true;
				}
			}
		}
		
		return true;
	}
}
