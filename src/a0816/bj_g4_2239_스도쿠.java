package a0816;


import java.util.*;
import java.io.*;

//해결 중
public class bj_g4_2239_스도쿠 {
	
	static final StringBuilder sb = new StringBuilder();
	static final int N=9, n=3;
	static final int[][] MAP = new int[N][N]; //9*9스도쿠 판
	static final List<int[]>EMPTY_IDX = new ArrayList<>(); //스도쿠 판에서 빈 공간(row 순으로 정렬됨)
	static boolean flag = false;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_2239_스도쿠.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//MAP
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) {
				MAP[i][j] = str.charAt(j)-'0';
				if(MAP[i][j]==0) EMPTY_IDX.add(new int[] {i, j});
			}
		}
		
		dfs(0);
		
		
		br.close();
		System.out.println(sb.toString());
		
	}
	
	
	static void dfs(int idx) {
		if(flag) return; //빠른 종료
		
		//종료 조건
		if(idx==EMPTY_IDX.size()) {
			flag = true;
			for(int[] M : MAP) {
				for(int m : M) sb.append(m);
				sb.append("\n");
			}
			return;
		}
		
		int[] arr = EMPTY_IDX.get(idx);
	
		for(int d=1;d<=9;d++) {
			if(checkRow(arr[0], d) && checkCol(arr[1], d) && checkSquare(arr[0], arr[1], d)) {
				MAP[arr[0]][arr[1]] = d;
				dfs(idx+1);
			}
		}
		MAP[arr[0]][arr[1]] = 0;
		
	}
	
	
	//가로줄 내 존재 여부 확인
	static boolean checkRow(int ci, int v) {
		for(int j=0;j<N;j++) {
			if(MAP[ci][j]==v) return false;
		}
		
		return true;
	}
	
	//세로줄 내 존재 여부 확인
	static boolean checkCol(int cj, int v) {
		for(int i=0;i<N;i++) {
			if(MAP[i][cj]==v) return false;
		}
		
		return true;
	}
	
	//3*3 사각형 내 존재 여부 확인
	static boolean checkSquare(int ci, int cj, int v) {	
		int si = n*(ci/n), sj = n*(cj/n);
		for(int i=si;i<si+n;i++) {
			for(int j=sj;j<sj+n;j++) {
				if(MAP[i][j]==v) return false;
			}
		}
		
		return true;
	}
}
