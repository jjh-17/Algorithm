package a0802.camp;

import java.util.*;
import java.io.*;

public class bj_15649_N과M1_서울_20반_지준호 {

	static int[] arr;
	static boolean[] v;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	
	public static void perm(int cnt) {
		if(cnt==M) {
			for(int a:arr)
				sb.append(a).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(v[i])
				continue;
			v[i] = true;
			arr[cnt] = i+1;
			perm(cnt+1);
			v[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_15649_N과M1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		v = new boolean[N]; arr = new int[M];
		
		perm(0);
		System.out.print(sb.toString());
	}

}
