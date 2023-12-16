package simulation;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;

public class bj_s3_9461_파도반수열 {
	
	static final StringBuilder sb = new StringBuilder();
	static final BigDecimal[] arr = new BigDecimal[101];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_s3_9461_파도반수열.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		초기화
		arr[1]=new BigDecimal(1); arr[2]=new BigDecimal(1); 
		arr[3]=new BigDecimal(1); arr[4]=new BigDecimal(2); 
		arr[5]=new BigDecimal(2); arr[6]=new BigDecimal(3); 
		arr[7]=new BigDecimal(4); arr[8]=new BigDecimal(5); 
		arr[9]=new BigDecimal(7); arr[10]=new BigDecimal(9);
		
//		입력
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(getP(N)).append("\n");
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
	static BigDecimal getP(int n) {
		if(arr[n]!=null || n<=9) return arr[n];
		
		arr[n] = getP(n-3).add(getP(n-2));
		return arr[n];
	}

}
