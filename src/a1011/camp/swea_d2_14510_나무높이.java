package a1011.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d2_14510_나무높이 {

	static final StringBuilder sb = new StringBuilder();
	static int N, maxH, day;	
	static int cntOdd, cntEven;	// 홀수, 짝수 날에 물을 주어야 하는 횟수
	static int[] H;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d2_14510_나무높이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
//			입력
			N = Integer.parseInt(br.readLine());
			H= new int[N];
			maxH=0; cntOdd=0; cntEven=0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				H[i] = Integer.parseInt(st.nextToken());
				maxH = Integer.max(maxH, H[i]);
			}
			
//			그리디 알고리즘
//			1. 홀수, 짝수 날에 물을 주어야하는 횟수 구하기
//				==> 두 횟수가 최대한 동일해야 쉬지 않고 물을 줄 수 있다.
			for(int h : H) {
				cntOdd += (maxH-h)%2;
				cntEven += (maxH-h)/2;
			}
			
//			2. 두 횟수의 균형 맞추기
//				==> 나무의 높이가 홀수이면 무조건 홀 수날에 물을 주어야 한다.
//					==> 홀수 날은 cntOdd번 이상이 보장되어야 한다.
			if(cntOdd<cntEven){ 
				while(Math.abs(cntEven-cntOdd)>1) {
					cntOdd+=2; cntEven-=1;
				}
			}
			
//			3. day 구하기
			if(cntOdd>cntEven)			day = 2*cntEven + 2*(cntOdd-cntEven)-1;
			else if(cntOdd<cntEven)		day = 2*cntOdd + 2*(cntEven-cntOdd);
			else						day = cntOdd + cntEven;
			
//			출력
			sb.append(day).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
