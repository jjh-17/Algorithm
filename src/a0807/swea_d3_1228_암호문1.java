package a0807;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d3_1228_암호문1 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static final ArrayDeque<String> deque = new ArrayDeque<>(), temp1 = new ArrayDeque<>(), temp2 = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_1228_암호문1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			deque.clear(); //ArrayDeque 초기화
			
			//N 입력
			int N = Integer.parseInt(br.readLine());
			
			//원본 암호문 저장
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<10;i++) deque.offerLast(st.nextToken());
		
			//명령어 개수
			int M = Integer.parseInt(br.readLine());
			
			//명령어 처리
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				temp1.clear(); temp2.clear();//ArrayDeque 초기화
				st.nextToken(); //instruction 제거
				int X = Integer.parseInt(st.nextToken()), Y = Integer.parseInt(st.nextToken()); //넣을 위치, 넣을 개수
				for(int y=0;y<Integer.min(Y, 10);y++) temp1.offerLast(st.nextToken()); //min(Y, 10)개 값 저장
				
				if(0<=X && X<10) { //넣을 위치가 10미만인 경우
					for(int j=0;j<X;j++) temp2.offerLast(deque.pollFirst()); //temp2에 deque의 X만큼 추가
					while(!temp1.isEmpty()) temp2.offerLast(temp1.pollFirst());
					while(!temp2.isEmpty()) deque.offerFirst(temp2.pollLast());
					while(deque.size()>10) deque.pollLast();
				}	
			}
			while(!deque.isEmpty()) sb.append(deque.pollFirst()).append(" ");
			sb.append("\n");
		}
		
		br.close();
		System.out.println(sb.toString());
		
	}

}
