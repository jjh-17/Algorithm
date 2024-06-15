package bruteForce;

import java.util.*;
import java.io.*;

public class bj_g4_2179_비슷한단어 {

	static int N;
	static String[] words;
	static final StringBuilder sb = new StringBuilder();
	static String ans1, ans2;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g4_2179_비슷한단어.txt"));
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		입력
		N = Integer.parseInt(br.readLine());
		
		words = new String[N];
		for(int i=0;i<N;i++)	words[i] = br.readLine();
		
//		알고리즘
		solution();
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}

	
	static void solution() {
		int l=-1;
		
//		1. 두 문자 비교 시작
		for(int i=0;i<N;i++) {
			for(int j=i+1;j<N;j++) {
				String word1 = words[i];
				String word2 = words[j];
				int nl = 0;
				int K = Integer.min(word1.length(), word2.length());
				
//				2. 비교할 두 문자의 길이가 현재 접두사 길이 이하일 경우 비교 불필요
				if(K <= l)	continue;
				
//				3. 두 문자 비교 후 최대 접두사 길이 도출
				for(int k=0;k<K;k++) {
					if(word1.charAt(k) == word2.charAt(k))	++nl;
					else									break;
				}
				
//				4. 신규 접두사 길이가 기존보다 길다면 대체
				if(nl > l) {
					l = nl;
					ans1 = word1;
					ans2 = word2;
				}
			}
		}
		
		sb.append(ans1).append("\n").append(ans2);
	}
}
