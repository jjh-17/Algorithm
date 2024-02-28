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
		
//		알고리즘
		int L=0, R=0, minH;
		for(int i=1;i<W-1;i++) {
			L=0; 
			R=0;
			
			for(int l=0;l<i;l++)
				L = Math.max(L, arr[l]);
			for(int l=i+1;l<W;l++)
				R = Math.max(R, arr[l]);
			minH = Math.min(L, R);
			
			if(arr[i] < minH)
				ans += (minH-arr[i]);
		}
		
//		출력
		System.out.println(ans);
		br.close();
	}
}
