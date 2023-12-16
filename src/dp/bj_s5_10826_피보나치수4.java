package dp;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;

public class bj_s5_10826_피보나치수4 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static BigDecimal[] pivo;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s5_10826_피보나치수4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		N = Integer.parseInt(br.readLine());
		pivo = new BigDecimal[N+1];
		
		if(N>0) {
//			1. 10까지 초기화
			pivo[0]=new BigDecimal(0); pivo[1]=new BigDecimal(1);
			for(int i=2;i<=Integer.min(10, N);i++) pivo[i] = pivo[i-1].add(pivo[i-2]);
		} else {
			pivo[0] = new BigDecimal(0);
		}

//		2. 출력
		sb.append(getPivo(N));
		System.out.println(sb.toString());
		br.close();
	}
	
	static BigDecimal getPivo(int n) {
		if(pivo[n]!=null) return pivo[n];

		BigDecimal a, b, c;
		if(n%2==0) {
			a = getPivo(n/2); b = getPivo(n/2-1);
			pivo[n] = a.multiply(b.multiply(new BigDecimal(2)).add(a));
		}
		else {
			a = getPivo(n/2); c = getPivo(n/2+1);
			pivo[n] = c.multiply(c).add(a.multiply(a));
		}
		
		return pivo[n];
	}
}
