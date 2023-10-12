package a1012.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;
import java.math.BigDecimal;

//해결 중
public class swea_d4_5604_구간합 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] arr = new int[10];
	static final BigDecimal[] total = new BigDecimal[16];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_5604_구간합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

//		초기화
		for(int i=1;i<=9;i++)	
			arr[i] = i + arr[i-1];
		
		total[0] = new BigDecimal(0);
		for(int i=1;i<=15;i++)	
			total[i] = total[i-1].multiply(new BigDecimal(10)).add(new BigDecimal(Math.pow(10, i-1)));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
//			입력
			st = new StringTokenizer(br.readLine());
			final long A = Long.parseLong(st.nextToken());	// 시작 값
			final long B = Long.parseLong(st.nextToken());	// 종료 값
			
			
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}

}
