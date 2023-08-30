package a0804;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d9_숫자만들기_서울_20반_지준호 {

	//N, 최댓값, 최소값
	static int N, MAX, MIN;
	
	//연산자 변수
	static final char[] op = {'+', '-', '*', '/'}; //연산자 배열
	static final int[] opNum = new int[4]; //연산자 개수
	static char[] opPerm; //각 연산자 순열(N-1개)
	
	//숫자 변수
	static int[] nums; //숫자 입력 받은 순 나열
 		
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_숫자만들기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			MAX = Integer.MIN_VALUE; MIN = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			opPerm = new char[N-1]; nums = new int[N];
			
			//연산자 개수 Map 초기화
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) opNum[i] = Integer.parseInt(st.nextToken());
			
			//숫자 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++)
				nums[i] = Integer.parseInt(st.nextToken());
			
			//연산자 순열 생성
			permOp(0);
			
			sb.append("#").append(t).append(" ").append(MAX-MIN).append("\n");
		}
		br.close();
		System.out.print(sb.toString());
	}
	
	//연산자 순열 생성
	public static void permOp(int cnt) {
		if(cnt==N-1) {
			int num = nums[0];
			for(int i=0;i<N-1;i++) num = getResult(opPerm[i], num, nums[i+1]);
			MAX = Math.max(MAX, num); MIN=Math.min(MIN, num);
			return;
		}
		
		for(int i=0;i<4;i++) {
			if(opNum[i]==0) continue;
			opNum[i] -= 1;
			opPerm[cnt] = op[i];
			permOp(cnt+1);
			opNum[i] += 1;
		}
	}
	
	public static int getResult(char op, int a, int b) {
		int result = 0;
		
		if(op=='+') result = a+b;
		else if(op=='-') result = a-b;
		else if(op=='*') result = a*b;
		else if(op=='/') result = a/b;
		
		return result;
	}
}
