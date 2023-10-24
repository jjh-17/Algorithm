package dfs_bfs;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g3_16637_괄호추가하기 {

	static final StringBuilder sb = new StringBuilder();
	static int N, MAX;
	static final List<Character> ops = new ArrayList<>();
	static final List<Integer> nums = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_16637_괄호추가하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		ops.add('X');
		N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		for(int i=0;i<input.length();i++) {
			char c = input.charAt(i);
			if('0'<=c && c<='9')	nums.add(c-'0');
			else					ops.add(c);
		}
		
//		알고리즘
		MAX = Integer.MIN_VALUE;
		dfs(1, nums.get(0));
		
//		출력
		sb.append(MAX);
		System.out.println(sb.toString());
		br.close();
	}
	
//	현재 숫자 및 연산자 번호, 연산 누적 값
	static void dfs(int idx, int pre) {
		if(idx==nums.size()) {
			MAX = Math.max(MAX, pre);
			return;
		}
		
//		괄호 미추가
		dfs(idx+1, calc(ops.get(idx), pre, nums.get(idx)));
		
//		괄호 추가
		if(idx+1<nums.size()) {
			dfs(idx+2, calc(ops.get(idx), pre, calc(ops.get(idx+1), nums.get(idx), nums.get(idx+1))));
		}
	}
	
	static int calc(char op, int n1, int n2) {
		switch(op) {
			case '+':	return n1+n2;
			case '-':	return n1-n2;
			default:	return n1*n2;
		}
	}
}
