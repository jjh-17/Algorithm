package dp;

import java.util.*;
import java.io.*;

public class bj_g4_2631_줄세우기 {

	static int N, ans;
	static int[] arr, dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g4_2631_줄세우기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

//		입력
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		for(int i=0;i<N;i++)	arr[i] = Integer.parseInt(br.readLine());
		
//		알고리즘
		solution();
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
//	최장 증가 부분 수열(LST) 구하기
	static void solution() {
		int max = 0;
		dp = new int[N];
		
//		1. 학생 순서 순회
		for(int i=0;i<N;i++) {
//			2. 현재 idx까지의 LST를 1로 지정
			dp[i] = 1;
			
//			3. 현재 idx 이전까지 순회하며 각 idx의 LST 최신화
			for(int j=0;j<i;j++) {
				if(arr[j] < arr[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
					max = Integer.max(max, dp[i]);
				}
			}
		}
		
		ans = N - max;
	}
	
}
