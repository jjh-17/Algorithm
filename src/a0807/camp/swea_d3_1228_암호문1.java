package a0807.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d3_1228_암호문1 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static final ArrayDeque<String> stack = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_1228_암호문1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			//N 입력
			int N = Integer.parseInt(br.readLine());
			
			//원본 암호문 저장
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<Math.min(N, 10);i++) stack.offerLast(st.nextToken());
			
			//명령어 개수
			int M = Integer.parseInt(br.readLine());
			
			//명령어 처리
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				st.nextToken(); //instruction 제거
				int X = Integer.parseInt(st.nextToken()), Y = Integer.parseInt(st.nextToken());
				
				//필요한 공간만큼 stack을 비운다
				int L = stack.size();
				for(int j=0;j<L-X;j++) stack.pollLast();
				
				//Y만큼 넣을 값을 입력 받는다. 단, stack의 크기는 10이하 유지
				for(int j=0;j<Y;j++) {
					String Z = st.nextToken();
					if(stack.size()<10) stack.offerLast(Z);
				}
			}
			
			while(!stack.isEmpty()) sb.append(stack.pollFirst()).append(" ");
			sb.append("\n");
		}
		
		br.close();
		System.out.println(sb.toString());
		
	}

}
