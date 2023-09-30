package a0810.camp;


import java.util.*;
import java.io.*;

public class swea_d3_5215_햄버거다이어트 {

	static final StringBuilder sb = new StringBuilder();
	static int N, L, MAX;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_5215_햄버거다이어트.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			//제료의 수, 제한 칼로리
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); L = Integer.parseInt(st.nextToken());
			
			//[맛 점수, 칼로리] 배열 초기화
			MAX = Integer.MIN_VALUE;
			map = new int[N][2];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				map[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}	
			
			comb(-1, 0, 0);
			sb.append(MAX).append("\n");
		}
		
		br.close();
		System.out.println(sb.toString());
	}
	
	//부분 집합
	static void comb(int start, int sumT, int sumK) {
		for(int i=start+1;i<N;i++) {
			if(sumK + map[i][1] <= L) {
				MAX = Integer.max(MAX, sumT + map[i][0]);
				comb(i, sumT+map[i][0], sumK+map[i][1]);
			}
		}
		
	}

}
