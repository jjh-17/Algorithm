package dp;

import java.util.*;
import java.io.*;

public class swea_d3_3307_최장증가부분수열 {

	static final StringBuilder sb = new StringBuilder();
	static int N, max;			//수열의 길이, 최장증가부분수열의 길이
	static int arr[], lis[];	//수열, i번째 원소까지의 최장증가부분수열의 길이
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_swea_d3_3307_최장증가부분수열.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
//			입력
			N = Integer.parseInt(br.readLine());
			arr = new int[N]; lis = new int[N];
			max=1;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				lis[i] = 1;
				
//				값이 증가하는 구간 ==> 부분 수열 길이 증가
				for(int j=0;j<i;j++) {
					if(arr[j]<=arr[i] && lis[i]<lis[j]+1) {
						lis[i]=lis[j]+1;
						max = Integer.max(max, lis[i]);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}	
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}

}
