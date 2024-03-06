package binary_search;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g5_2467_용액 {

	static int N;
	static int L, R, SUM;
	static int[] arr, ans = new int[2];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_2467_용액.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0;i<N;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
//		이분 탐색
		L = 0;	R = N-1;
		SUM = Integer.MAX_VALUE;
		while(true) {
//			탈출 조건
			if(L>=R) break;
			
//			현재 특성값과 새로운 특성값 비교
			int sum = arr[L] + arr[R];
			if(Math.abs(sum) < SUM) {
				SUM = Math.abs(sum);
				ans[0] = L;	ans[1] = R;
			}
			
//			L 
			if(sum < 0)			++L;
			else if(sum > 0)	--R;
			else				break;
		}		
		
//		출력
		System.out.println(arr[ans[0]] + " " + arr[ans[1]]);
		br.close();
	}

}
