package a1004.camp;

import java.util.*;
import java.io.*;

public class swea_d9_4014_활주로건설 {

	static final StringBuilder sb = new StringBuilder();
	static final int H=1;	// 활주로 높이
	static int N, X;		// 필드 길이, 경사로 길이
	static int map[][];		// 필드 정보
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_4014_활주로건설.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
//			입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			
//			알고리즘
//			1. 각 row 확인
			for(int i=0;i<N;i++) {
				int j=0;
				boolean flag = true;
				while(j<N) {
					
				}
			}
			
			
		}
		
		
//		입력
		
		
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}

}
