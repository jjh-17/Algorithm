package a0814.camp;

import java.util.*;
import java.io.*;

public class bj_s2_2630_색종이만들기 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static final int[] answer = new int[2];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s2_2630_색종이만들기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, N);
		sb.append(answer[0]).append("\n").append(answer[1]);
		br.close();
		System.out.println(sb.toString());
	}
	
	 public static void dfs(int si, int sj, int n) {
		 int result = checkMap(si, sj, n);
		 if(result==-1) { //4등분 해서 나눌 것
			 dfs(si, sj, n/2); dfs(si+n/2, sj, n/2);
			 dfs(si, sj+n/2, n/2); dfs(si+n/2, sj+n/2, n/2);
		 } else {
			 answer[result] += 1;
		 }
	 }
	
	//(si, sj)부터   n*n크기의 정사각형이 모두 동일한 값인지 확인
	public static int checkMap(int si, int sj, int n) {
		int result = map[si][sj];
		
		for(int i=si;i<si+n;i++) {
			for(int j=sj;j<sj+n;j++) {
				if(map[i][j] != result) return -1;
			}
		}
		return result;
	}

}
