package simulation;

import java.util.*;
import java.io.*;

public class swea_d2_1974_스도쿠검증 {
	
	static final StringBuilder sb = new StringBuilder();
	static final int N = 9;
	static final int[][] map = new int[N][N];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d2_1974_스도쿠검증.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
//			입력
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int ans = checkRows();
			if(ans==1) ans = checkCols();
			if(ans==1) ans = checkSquares();
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
	static int checkRows() {
		for(int i=0;i<N;i++) {
			boolean[] v = new boolean[N+1];
			
			for(int j=0;j<N;j++) {
				if(v[map[i][j]]) return 0;
				v[map[i][j]] = true;
			}
		}
		
		return 1;
	}
	
	static int checkCols() {
		for(int j=0;j<N;j++) {
			boolean[] v = new boolean[N+1];
			
			for(int i=0;i<N;i++) {
				if(v[map[i][j]]) return 0;
				v[map[i][j]] = true;
			}
		}
		
		return 1;
	}
	
	static int checkSquares() {
		for(int a=0;a<3;a++) {
			for(int b=0;b<3;b++) {
				boolean[] v = new boolean[N+1];
				for(int i=3*a;i<3*a+3;i++) {
					for(int j=3*b;j<3*b+3;j++) {
						if(v[map[i][j]]) return 0;
						v[map[i][j]] = true;
					}
				}
			}
		}
		
		return 1;
	}
}