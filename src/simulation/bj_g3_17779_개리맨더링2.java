package simulation;

import java.util.*;
import java.io.*;

public class bj_g3_17779_개리맨더링2 {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, DIFF;
	static int[] people;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g3_17779_개리맨더링2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		알고리즘
		DIFF = Integer.MAX_VALUE;
		people = new int[5];
		for(int d1=1;d1<=N-2;d1++) {
			for(int d2=1;d1+d2+1<=N;d2++) {
//				5번 선거구
				people[4] = (d1+1)*(d2+1);
				for(int x=1;x+d1+d2<=N;x++) {
					for(int y=d1+1;y+d2<=N;y++) {
//						1번 선거구
						
						
//						2번 선거구
						
						
//						3번 선거구
						
						
//						4번 선거구
						
						
					}
				}
			}
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
	static int getC(int n) {
		return (n*(n+1))/2;
	}
	
	

}
