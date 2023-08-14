package a0814.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_s1_1074_Z {

	static int N, R, C, TARGET;
	static int si, sj, num;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_1074_Z.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
	
		si=0; sj=0; num=0;
		dfs();
		
		System.out.println(TARGET);
	}		
	
	static void dfs() {
		if(N==1) {
			if(si==R && sj==C) { TARGET=num; return;}
			else if(si==R && sj+1==C) { TARGET=num+1; return;}
			else if(si+1==R && sj==C) { TARGET=num+2; return;}
			else if(si+1==R && sj+1==C) { TARGET=num+3; return;}
		} else {
			int dNum = (int) Math.pow(2, 2*(N-1));
			int dij = (int) Math.pow(2, N-1);
			 
			N-=1;
			if(si<=R&&R<si+dij && sj<=C&&C<sj+dij) {
				dfs();
				return;
			} else if(si<=R&&R<si+dij && sj+dij<=C&&C<sj+2*dij) {
				sj+=dij; num+=dNum; dfs();
				return;
			} else if(si+dij<=R&&R<si+2*dij && sj<=C&&C<sj+dij) {
				si+=dij; num+=2*dNum; dfs();
				return;
			} else {
				si+=dij; sj+=dij; num+=3*dNum; dfs();
				return;
			}
		}
	}
}
