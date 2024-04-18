package two_pointer;

import java.util.*;
import java.io.*;

public class bj_g4_1806_부분합 {

	static int[] arr;
	static int N, S, ans;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g4_1806_부분합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			arr[i] = Integer.parseInt(st.nextToken());
			
//		알고리즘
		solution();
		
//		출력
		System.out.println(ans==Integer.MAX_VALUE ? 0 : ans);
		br.close();
	}

	static void solution() {
		int L=0, R=0, sum=arr[0];
		ans = Integer.MAX_VALUE;

		while(true) {
//			sum이 S 이상이면 L을 1씩 증가시키며 최소 길이 알아냄
			if(sum>=S) {
				ans = Integer.min(ans, R-L+1);
				sum-=arr[L++];
				continue;
			} 
			
//			sum이 S보다 작은데 R이 현재 우측 끝점이면 sum>=S를 만족하는 방법 X
			if(R==N-1)	return;
			sum+=arr[++R];
		}
	}
}
