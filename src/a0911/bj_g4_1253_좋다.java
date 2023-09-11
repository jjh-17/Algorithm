package a0911;

import java.util.*;
import java.io.*;

public class bj_g4_1253_좋다 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static long[] arr;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_1253_좋다.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		arr = new long[N];
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
		
		//====알고리즘====
		//오름차순 정렬
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		
		
		//
		long cur_max, next_max;
		int cur_max_start_idx=N, next_max_start_idx;
		int cur_max_end_idx, next_max_end_idx;
		int ANS=0;
		
		while(true) {
			if(cur_max_start_idx==0) break;
			cur_max_end_idx = cur_max_start_idx-1;
			cur_max = arr[cur_max_end_idx];
			cur_max_start_idx = getFirstIdx(0, cur_max_end_idx+1, cur_max);
			
			next_max_start_idx = cur_max_start_idx;
			while(true) {
				if(next_max_start_idx==0) break;
				next_max_end_idx = next_max_start_idx-1;
				next_max = arr[next_max_end_idx];
				next_max_start_idx = Arrays.binarySearch(arr, next_max);
				
				if(next_max*2<cur_max) break;
				
				//다음 큰 수가 2개 이상이며, 그 2배가 현재 큰 수 인 경우
				if((next_max*2==cur_max && next_max_end_idx-next_max_start_idx>=1) 
						|| getFirstIdx(0, next_max_end_idx+1, next_max)!=-1) {
					ANS += (cur_max_end_idx - cur_max_start_idx + 1);
					break;
				}
			}
		}	
	
		//출력
		sb.append(ANS);
		System.out.println(sb.toString());
		br.close();
	}
	
	// 중복 배열 내 target index 최솟값 반환
	static int getFirstIdx(int start, int end, long target) {
		int idxL=0, idxMid=(start+end)/2, idxR=end;
		
		while(idxL<=idxR) {
			idxMid = (idxL+idxR)/2;
			if(arr[idxMid]<target) idxL=idxMid+1;
			else end=idxMid-1;
		}

		return idxL;
	}
}
