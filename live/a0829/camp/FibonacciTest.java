package a0829.camp;

import java.util.*;
import java.io.*;

public class FibonacciTest {

	static long totalCnt1, callCnt1[];
	static long totalCnt2, callCnt2[];
	static long memo[];
	
	public static void main(String[] args) throws Exception {
		
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		totalCnt1 = 0; totalCnt2=0;
		callCnt1 = new long[N+1]; callCnt2 = new long[N+1];
		memo = new long[N+1]; Arrays.fill(memo, -1);
		

		System.out.println(fiboRegression(N));
		System.out.println("fiboRegression: " + totalCnt1);
		
		
		memo[0] = 0; memo[1] = 1;
		System.out.println(fiboMemoization(N));
		System.out.println("fiboMemoization: " + totalCnt2);
	}

	static long fiboRegression(int n) {
		++totalCnt1;
		++callCnt1[n];
		
		if(n<2) return n;
		return fiboRegression(n-1) + fiboRegression(n-2);
	}
	
	static long fiboMemoization(int n) {
		++totalCnt2;
		++callCnt2[n];
		
		if(memo[n]==-1) memo[n] = fiboMemoization(n-1) + fiboMemoization(n-2);
		return memo[n];
	}
}
