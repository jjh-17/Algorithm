package a1013.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_s2_14889_스타트와링크 {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, DIFF;
	static boolean[] team;
	static int[][] synergy;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_s2_14889_스타트와링크.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		synergy = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) synergy[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		알고리즘
		DIFF = Integer.MAX_VALUE;
		team = new boolean[N];
		comb(0, 0);
		
//		출력
		sb.append(DIFF);
		System.out.println(sb.toString());
		br.close();
	}
	
	static void comb(int start, int cnt) {
		if(cnt==N/2) {
			int[] L = new int[N/2], S = new int[N/2];
			int li=0, si=0;
			for(int i=0;i<N;i++) {
				if(team[i]) L[li++]=i;
				else		S[si++]=i;
			}
		
			int totalL=0, totalS=0;
			for(int i=0;i<N/2;i++) {
				for(int j=0;j<N/2;j++) {
					totalS += synergy[S[i]][S[j]];
					totalL += synergy[L[i]][L[j]];
				}
			}
			
			DIFF = Integer.min(DIFF, (int)Math.abs(totalS-totalL));
			return;
		}
		
		for(int i=start;i<N;i++) {
			team[i] = true;
			comb(i+1, cnt+1);
			team[i] = false;
		}
	}
}
