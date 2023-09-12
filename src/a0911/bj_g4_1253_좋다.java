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
		
//		//양수
//		while(true) {
//			cur_max_end_idx = cur_max_start_idx-1;
//			cur_max = arr[cur_max_end_idx];
//			cur_max_start_idx = getFirstTargetIdx(0, cur_max_end_idx, cur_max);
//			if(cur_max_start_idx==0 || cur_max<0) break;
//			
//			next_max_start_idx = cur_max_start_idx;
//			while(true) {
//				next_max_end_idx = next_max_start_idx-1;
//				next_max = arr[next_max_end_idx];
//				next_max_start_idx = getFirstTargetIdx(0, next_max_end_idx, next_max);	
//
//				//다음 큰 수 2개로도 cur_max를 만들지 못하면 넘어간다.
//				if(next_max*2<cur_max || next_max_start_idx==0) break;
//		
//				//다음 큰 수가 2개 이상이며, 그 2배가 현재 큰 수 인 경우
//				if((next_max*2==cur_max && next_max_end_idx-next_max_start_idx>=1) 
//						|| getFirstTargetIdx(0, next_max_end_idx, cur_max-next_max)<next_max_end_idx) {
//					ANS += (cur_max_end_idx - cur_max_start_idx+1);
//					break;
//				}
//			}
//		}	
		
		
//		System.out.println(getLastTargetIdx(2, 4, 10));
		
		//음수
		long cur_min, next_min;
		int cur_min_start_idx, next_min_start_idx;
		int cur_min_end_idx=-1, next_min_end_idx;
		
		while(true) {
			cur_min_start_idx = cur_min_end_idx+1;
			cur_min = arr[cur_min_start_idx];
			cur_min_end_idx = getLastTargetIdx(cur_min_start_idx, N-1, cur_min);
			if(cur_min_end_idx==N-1 || cur_min>=0) break;
			
			next_min_end_idx = cur_min_end_idx;
			while(true) {
				next_min_start_idx = next_min_end_idx+1;
				next_min = arr[next_min_start_idx];
				next_min_end_idx = getLastTargetIdx(next_min_start_idx, N-1, next_min);
				
				//다음 작은 수 2개로도 cur_min을 만들지 못하면 넘어간다.
				if(next_min*2>cur_min || next_min_end_idx==0) break;
				
				//다음 큰 수가 2개 이상이며, 그 2배가 현재 큰 수 인 경우
				if((next_min*2==cur_min && next_min_end_idx-next_min_start_idx>=1) 
						//여기가 문젠데 말이지
						|| getLastTargetIdx(next_min_start_idx, N-1, next_min-cur_min)<=next_min_end_idx) {
					System.out.println(cur_min_start_idx + " " + cur_min_end_idx + " " + next_min_start_idx + " " 
										+ next_min_end_idx + " " + next_min);
					ANS += (cur_min_end_idx - cur_min_start_idx+1);
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
	static int getFirstTargetIdx(int start, int end, long target) {
		int mid;
		
		while(start<=end) {
			mid = (start+end)/2;
			if(arr[mid]<target) start=mid+1;
			else end=mid-1;
		}

		return start;
	}
	
	// 중복 배열 내 target index 최댓값 반환
	static int getLastTargetIdx(int start, int end, long target) {
		int mid;
		
		while(start<=end) {
			mid = (start+end)/2;
			if(arr[mid]<=target) start=mid+1;
			else end=mid-1;
		}
		return end;
	}
}
