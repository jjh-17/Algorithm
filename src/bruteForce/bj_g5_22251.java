package bruteForce;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g5_22251 {

//	최대 층, 자리 수, LED 변환 최대 횟수, 현재 층, 답
	static int N, K, P, X, ans;
	
//	0~9까지의 LED 상태
	static final boolean[][] lights = {
			{true, true, true, false, true, true, true},
			{false, false, true,false, false, true, false},
			{true, false, true, true, true, false, true},
			{true, false, true, true, false, true, true},
			{false, true, true, true, false, true, false},
			{true, true, false, true, false, true, true},
			{true, true, false, true, true, true, true},
			{true, false, true, false, false, true, false},
			{true, true, true, true, true, true, true},
			{true, true, true, true, false, true, true}
	};
	static final int[][] nums = new int[10][10];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_22251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		1. 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
//		2. 각 숫자에서 다른 숫자로 변하기 위한 LED 변환 횟수
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(i==j)	continue;
				
				int cnt=0;
				for(int l=0;l<7;l++) {
					if(lights[i][l]!=lights[j][l])	++cnt;
				}
				nums[i][j] = cnt;
			}
		}
		
		
//		솔루션
		solution(0, P, 0);
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
//	이전 자릿수, 남은 변환 횟수, 이전 값
	static void solution(int idx, int rest, int bef) {
		if(idx==K+1) {
			if(bef!=X && 1<=bef && bef<=N)	++ans;
			return;
		}
		
		int n = (X % (int)Math.pow(10, idx+1) / (int)Math.pow(10, idx));
		for(int i=0;i<10;i++) {
//			n에서 i로 바뀔 때 남은 횟수
			int nrest = rest-nums[n][i];
			int cur = bef + i * (int)Math.pow(10, idx);
			
			if(cur > N)	return;
			if(nrest >= 0)	solution(idx+1, nrest, cur);
		}
	}
}
