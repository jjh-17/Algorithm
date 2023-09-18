package a0911;

import java.util.*;
import java.io.*;

public class bj_g4_1253_좋다 {

	static final StringBuilder sb = new StringBuilder();
	static int N, arr[];	//수의 개수, N개 수 배열
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_1253_좋다.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
		
		//====알고리즘====
		Arrays.sort(arr);	//정렬
		int cnt=0;
		
		for(int i=0;i<N;i++) {
			BREAK_J:
			for(int j=0;j<N;j++) {
//				i, j가 동일한 경우 넘어감
				if(i==j) continue;
				
				int target = arr[i]-arr[j];
				int k = lowerBound(0, N-1, target);
				if(k!=-1) {
					for(;k<N && arr[k]==target;k++) {
						if(k!=i && k!=j) {
							++cnt;
							break BREAK_J;
						}
					}
				}
			}
		}
	
		//출력
		sb.append(cnt);
		System.out.println(sb.toString());
		br.close();
	}

//	lowerbound 이분 탐색 - target과 일치하는 가장 빠른 idx를 반환한다.
	static int lowerBound(int start, int end, int target) {
		while(start<end) {
			int mid = (start+end)/2;
			if(arr[mid]<target) start = mid+1;
			else end = mid;
		}
		
		return arr[start]==target ? start : -1;
	}
}
