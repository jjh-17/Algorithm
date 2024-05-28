package greedy;

import java.util.*;
import java.io.*;

public class bj_g5_12904 {

	static String S;
	static StringBuilder T;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_12904.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		S = br.readLine();
		T = new StringBuilder(br.readLine());
		
//		알고리즘
		final ArrayDeque<StringBuilder> deque = new ArrayDeque<>();
		deque.offerLast(T);
		
		while(!deque.isEmpty()) {
			StringBuilder sb = new StringBuilder(deque.pollFirst());

//			S와 동일한 문자열이면 종료
			if(sb.toString().equals(S)) {
				ans = 1;
				break;
			}
			
//			길이가 1이면 종료
			if(sb.length()==1)
				continue;
			
//			마지막 문자에 따라 1, 2번 로직
			if(sb.toString().endsWith("A")) {
//				뒤에 A 제거
				sb.deleteCharAt(sb.length()-1);
				deque.offerLast(sb);
			} else {
//				뒤에 B 제거 후 문자열 뒤집기
				sb.deleteCharAt(sb.length() -1);
				deque.offerLast(sb.reverse());
			}
		}
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
}
