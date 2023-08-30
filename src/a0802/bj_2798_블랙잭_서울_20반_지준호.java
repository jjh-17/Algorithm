package a0802;

import java.util.*;
import java.io.*;

public class bj_2798_블랙잭_서울_20반_지준호 {

	static boolean[] v; //방문 여부
	static int N, M, diff=Integer.MAX_VALUE, answer;
	static int[] temp = new int[3];
	static int[] map;
	
	public static void comb(int cnt, int start) {
		if(cnt==3) {
			int sum=0;
			for(int a : temp)
				sum+=a;
			if(sum<=M && M-sum<diff) {
				diff = M-sum;
				answer = sum;
			}
			return;
		}
		
		for(int i=start;i<N;i++) {
			if(v[i])
				continue;
			v[i] = true;
			temp[cnt] = map[i];
			comb(cnt+1, i);
			v[i] =false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_2798_블랙잭.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		map = new int[N];
		for(int i=0;i<N;i++)
			map[i] = Integer.parseInt(st.nextToken());
		
		v = new boolean[N];

		comb(0, 0);
		System.out.println(answer);
	}

}
