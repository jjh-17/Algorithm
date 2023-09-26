package a0803.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_2961_도영이가만든맛있는음식_서울_20반_지준호 {

	static int diff = Integer.MAX_VALUE, N;
	static int[] S, B;
	
	static void subS(int cnt, int sumS, int sumB) {
		if(cnt==N) {
			if(sumB!=0)
				diff = Math.min(diff, Math.abs(sumS-sumB));
			return;
		}
		
		subS(cnt+1, sumS*S[cnt], sumB+B[cnt]);
		subS(cnt+1, sumS, sumB);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_2961_도영이가만든맛있는음식.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		S = new int[N]; B = new int[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		subS(0, 1, 0);
		
		System.out.println(diff);
	}

}
