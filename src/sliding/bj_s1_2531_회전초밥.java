package sliding;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_s1_2531_회전초밥 {

	static int N, D, K, C;	//초밥 개수, 가짓수, 연속 먹는 개수, 쿠폰
	static int ans;
	static int[] arr;		// 초밥 판 상태
	static int[] ate;		// 각 초밥을 먹은 개수
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_s1_2531_회전초밥.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		ate = new int[D];
		arr = new int[N];
		for(int i=0;i<N;i++)
			arr[i] = Integer.parseInt(br.readLine());
		
//		알고리즘
//		1) 쿠폰을 고려하여 초기화
		ans = -1;
		int temp=1;
		++ate[C-1];
		
//		2) 첫번째 접시에서 K개 먹음
		for(int i=0;i<K;i++) {
			++ate[arr[i]-1];
			
//			처음 먹은 초밥일 때만 먹은 개수 증가
			if(ate[arr[i]-1]==1) ++temp;
		}
		ans = temp;
		
//		3) 2번째 접시부터 모든 접시 순회
		for(int i=1;i<N;i++) {
//			3-1) 이전에 먹은 초밥 개수 감소
			--ate[arr[i-1]-1];
			if(ate[arr[i-1]-1]==0)			--temp;
			
//			3-2) 이번에 먹은 초밥 개수 증가
			++ate[arr[(i+K-1)%N]-1];
			if(ate[arr[(i+K-1)%N]-1]==1)	++temp;
			
//			3-3) 최댓값
			ans = Math.max(ans, temp);
		}
		
		
//		출력
		System.out.println(ans);
		br.close();
	}

}
