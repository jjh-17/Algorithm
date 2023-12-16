package simulation;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d4_8382_방향전환 {

	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_8382_방향전환.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
//			입력
			st = new StringTokenizer(br.readLine());
			int si = Integer.parseInt(st.nextToken());
			int sj = Integer.parseInt(st.nextToken());
			int ei = Integer.parseInt(st.nextToken());
			int ej = Integer.parseInt(st.nextToken());
			
//			알고리즘
//			1. 가로축, 세로축중 가장 가까운 길이만큼 대각선 이동
			int minD = Integer.min(Math.abs(ei-si), Math.abs(ej-sj));
			int cnt = 2*minD;
			
			if(si<ei) {
				si += minD;
				if(sj<ej)	sj += minD;	// 우상단
				else		sj -= minD;	// 좌상단
			} else {
				si -= minD;
				if(sj<ej)	sj += minD;	// 우하단
				else		sj -= minD;	// 좌하단
			}
			
//			2. 가로축 혹은 세로축 이동
			int dcnt=0;
			if(si!=ei && sj==ej)		dcnt = Math.abs(ei-si);
			else if(si==ei && sj!=ej)	dcnt = Math.abs(ej-sj);
			
			for(int d=1;d<=dcnt;++d) {
				if(d%2==1)	++cnt;
				else		cnt+=3;
			}
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}