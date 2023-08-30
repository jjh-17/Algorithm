package a0802;

import java.util.*;
import java.io.*;

public class bj_15650_N과M2_서울_20반_지준호 {

	static int[] arr;
	static boolean[] v;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	
	public static void comb(int cnt, int start) {
		if(cnt==M) {
			for(int a:arr)
				sb.append(a).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i=start;i<N;i++) {
			if(v[i])
				continue;
			v[i] = true;
			arr[cnt] = i+1;
			comb(cnt+1, i+1);
			v[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_15650_N과M2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		v = new boolean[N]; arr = new int[M];
		
		comb(0, 0);
		System.out.print(sb.toString());
	}

}
