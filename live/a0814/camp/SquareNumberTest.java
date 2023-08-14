package a0814.camp;

import java.util.*;
import java.io.*;

public class SquareNumberTest {

	static long X;
	static int N, CNT1, CNT2;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Long.parseLong(st.nextToken()); N = Integer.parseInt(st.nextToken());
		
		CNT1=0;
		System.out.println(exp1(X, N) + " " + CNT1);
		
		CNT2=0;
		System.out.println(exp2(X, N) + " " + CNT2);
	}
	
	
	//재귀 - 분할 정복 미적용
	static long exp1(long x, int n) {
		++CNT1;
		if(n==1) return x;
		return x*exp1(x, n-1);
	}
	
	//재귀 - 분할 정복 적용
	static long exp2(long x, int n) {
		++CNT2;
		if(n==1) return x;
		long y = exp2(x, n/2);
		return (n%2==0)? y*y : y*y*x;
	}
}
