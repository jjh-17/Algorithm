package exhaustiveSearch;

import java.util.*;
import java.io.*;

public class bj_g5_12919_A와B2 {
	
	static String S, T;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_12919_A와B2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		S = br.readLine();
		T = br.readLine();
		
//		알고리즘
		ans = S.equals(T) ? 1 : 0;
		dfs(T);
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
	// 현재 문자열, task 수행 횟수
	static void dfs(String t) {
//		종료 조건
		if(ans==1 || t.length()==S.length()) return;
		
//		A 제거
		if(t.charAt(t.length()-1) == 'A') {
			String nt = t.substring(0, t.length()-1);
			if(nt.equals(S)) {
				ans = 1;
				return;
			}
			dfs(nt);
		}
		
		
//		뒤집고 B제거
		if(t.charAt(0) == 'B') {
			StringBuilder sb = new StringBuilder(t);
			String nt = sb.reverse().toString().substring(0, t.length()-1);
			if(nt.equals(S)) {
				ans = 1;
				return;
			}
			dfs(nt);
		}
	}
}
