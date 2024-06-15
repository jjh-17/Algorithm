package two_pointer;

import java.util.*;
import java.io.*;

public class bj_g4_13144_ListOfniqueNumbers {
	
	static long ans;
	static int N;
	static int[] arr;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g4_13144_ListOfniqueNumbers.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0;i<N;i++)	arr[i] = Integer.parseInt(st.nextToken());
		
//		알고리즘
		solution();
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
	static void solution() {
		int start = 0;	// 시작 idx
		int end = 0;	// 마지막 idx
		v = new boolean[N+1];
		
		while(start < N) {
//			1. end를 최대한 우측으로 이동
			while(end<N && !v[arr[end]])
				v[arr[end++]] = true;
			
//			2. [start, start ~ end] 까지의 수열 개수 추가
			ans += end-start;
			v[arr[start++]] = false;
		}
	}
}
