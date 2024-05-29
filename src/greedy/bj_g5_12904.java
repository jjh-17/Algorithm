package greedy;

import java.util.*;
import java.io.*;

public class bj_g5_12904 {

	static String S, T;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_12904.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		S = br.readLine();
		T = br.readLine();
		
//		알고리즘
		solution();
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
	static void solution() {
		int sL = S.length(), tL;
		
		while((tL=T.length()) > sL) {
			char end = T.charAt(tL-1);
			T = T.substring(0, tL-1);
			
//			마지막 문자가 B이면 뒤집음
			if(end == 'B')
				T = new StringBuilder(T).reverse().toString();
		}
		
		ans = S.equals(T) ? 1 : 0;
	}
	
}
