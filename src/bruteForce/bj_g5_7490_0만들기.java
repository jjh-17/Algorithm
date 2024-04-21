package bruteForce;

import java.util.*;
import java.io.*;

public class bj_g5_7490_0만들기 {

	static int N, T;
	static final List<String> list = new ArrayList<>();
	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_7490_0만들기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		테스트 케이스
		T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
//			입력
			N = Integer.parseInt(br.readLine());
			
//			알고리즘
			dfs("1", 2, 1, 0);
			
//			출력
			list.sort((String s1, String s2) -> s1.compareTo(s2));
			for(String s : list)
				sb.append(s).append('\n');
			sb.append('\n');
			list.clear();
		}
		System.out.println(sb.toString());
		br.close();
	}

//	현재 문자열, 번호, 숫자, 결과
	static void dfs(String str, int idx, int num, int result) {
		if(idx==N+1) {
			result += num;
			if(result==0)
				list.add(str);
			return;
		}
		
		dfs(str + " " + idx, idx+1, 10*num + (num>=0 ? idx : -1*idx), result);	// 빈 칸
		dfs(str + "+" + idx, idx+1, idx, result+num);							// +
		dfs(str + "-" + idx, idx+1, -1*idx, result+num);						// -
	}
	
}
