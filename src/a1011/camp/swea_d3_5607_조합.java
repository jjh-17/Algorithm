package a1011.camp;

import java.util.*;
import java.io.*;

public class swea_d3_5607_조합 {

	static final StringBuilder sb = new StringBuilder();
	static final int P = 1234567891;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_5607_조합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
//			입력
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
//			알고리즘
			sb.append(nCr(N, R, P)).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	/*
	 * [페르마의 소정리]
	 * - a는 p의 배수가 아니며, p는 소수일 때
	 * 		(a^p)%p == a%p
	 * 			==> (a^(p-1))%p == 1%p
	 *					==> (a^(p-2))%p == (1/p)%p
	 * 
	 * nCr % p == (n! * ((r! * (n-r)!)^-1)) % p
	 * 		   == (n! * (1/r!) * (1/(n-r)!)) % p
	 * 		   
	 */
	static long nCr(int N, int R, int P){
//		R==0이면 N과 상관없이 항상 1
		if(R==0) return 1L;

//		팩토리얼 값을 저장하는 배열
		long[] fac = new long[N+1];
		fac[0]=1;
		for(int i=1;i<=N;i++) fac[i] = fac[i-1]*i%P;
		
		return (fac[N] * power(fac[R], P-2, P) % P
					   * power(fac[N-R], P-2, P) % P)
					   % P;
	}

	static long power(long X, long Y, long P) {
		long res = 1L;
		X %= P;

		while(Y>0) {
			if(Y%2==1) res = (res * X) % P;
			Y = Y>>1;   // y/=2
			X = (X*X)%P;
		}
		return res;
	}

}
