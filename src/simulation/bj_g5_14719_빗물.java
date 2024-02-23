package simulation;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g5_14719_빗물 {
	
	static int H, W, ans;
	static int[] arr;
	static final List<Integer> list = new ArrayList<>(); // 증가하다가 감소하는 idx 저장
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_14719_빗물.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		ans = 0;
		
		st = new StringTokenizer(br.readLine());
		arr = new int[W];
		for(int i=0;i<W;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
//		알고리즘
//		1) W<=2 이면 빗물을 담을 수 없음
		if(W<=2) {
			System.out.println(ans);
			br.close();
		}
		
//		2) 높이 배열 순회
		for(int i=0;i<W;i++) {
			
		}

		
		
//		출력
		System.out.println(ans);
		br.close();
	}

}
