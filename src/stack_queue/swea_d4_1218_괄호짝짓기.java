package stack_queue;

import java.util.*;
import java.io.*;

public class swea_d4_1218_괄호짝짓기 {

	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_1218_괄호짝짓기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1;t<=10;t++) {
			sb.append("#").append(t).append(" ");
			int N = Integer.parseInt(br.readLine());
			ArrayDeque<Character> s = new ArrayDeque<>();
			String str = br.readLine();
			
			for(int i=0;i<N;i++) {
				char c = str.charAt(i);
				
				if(s.isEmpty()) {
					if(c==')' || c=='}' || c==']' || c=='>') //아무것도 없는데 닫는 괄호가 온 경우
						break; 
					s.offerLast(c);
				} else {
					if(c==')') {
						if(s.peekLast()=='(') s.pollLast();
						else break;
					} else if(c=='}') {
						if(s.peekLast()=='{') s.pollLast();
						else break;
					} else if(c==']') {
						if(s.peekLast()=='[') s.pollLast();
						else break;
					} else if(c=='>') {
						if(s.peekLast()=='<') s.pollLast();
						else break;
					} else {
						s.offerLast(c);
					}
				}
			}
			sb.append(s.isEmpty() ? 1 : 0).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
		
	}

}
