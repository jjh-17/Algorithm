package a0801.camp;

import java.util.*;
import java.io.*;

public class bj_N과M1_서울_20반_지준호 {
	
	static int N, M;
	static boolean[] visited;
	static int[] arr;
	
	public static void perm(int cnt) {
		if(cnt==M+1) {
			for(int a : arr)
				System.out.print(a+" ");
			System.out.println();
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			arr[cnt-1] = i+1;
			perm(cnt+1);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_N과M1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//입력 및 초기화
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		arr = new int[M];
		
		perm(1);
	}

}
