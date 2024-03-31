package simulation;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g5_14719_빗물 {
	
	static int H, W, ans;
	static int[] arr;
	
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
		for(int i=0;i<W;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int L=arr[0], R, minH;
		for(int i=1;i<W-1;i++) {
//			우측 가장 높은 기둥
			R=0;
			for(int j=i+1;j<W;j++)
				R = Math.max(R, arr[j]);
			
//			좌측 최고 높이 기둥, 우측 최고 높이 기둥 중 가장 작은 기둥이 고일 수 있는 최대 높이
			minH = Math.min(L, R);
			if(arr[i] < minH)
				ans += (minH - arr[i]);
			
//			좌측 가장 높은 기둥
			L = Math.max(L,  arr[i]);
		}
		
//		출력
		System.out.println(ans);
		br.close();
	}
}
