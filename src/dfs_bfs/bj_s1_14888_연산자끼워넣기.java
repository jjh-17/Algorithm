package dfs_bfs;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class bj_s1_14888_연산자끼워넣기 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr;
	static final int[] ops = new int[4];
	static int MAX, MIN;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_s1_14888_연산자끼워넣기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) ops[i] = Integer.parseInt(st.nextToken());
		
//		알고리즘
		MAX = Integer.MIN_VALUE;
		MIN = Integer.MAX_VALUE;
		DFS(arr[0], 1);
		
//		출력
		sb.append(MAX).append("\n").append(MIN);
		System.out.println(sb.toString());
		br.close();
	}
	
	static void DFS(int result, int cnt) {
		if(cnt==N) {
			MAX = Math.max(MAX, result);
			MIN = Math.min(MIN, result);
			return;
		}
		
		for(int i=0;i<4;i++) {
			if(ops[i]>0) {
				--ops[i];
				DFS(op(i, result, arr[cnt]), cnt+1);
				++ops[i];
			}
		}
	}
	
	static int op(int op_num, int a, int b) {
		switch(op_num) {
			case 0:		return a+b;
			case 1: 	return a-b;
			case 2:		return a*b;
			default:	return a/b;
		}
	}
}
