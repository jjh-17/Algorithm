package a1004.camp;

import java.util.*;
import java.io.*;

public class bj_p5_1786_찾기 {

	static final StringBuilder sb = new StringBuilder();
	static String T, P;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_p5_1786_찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		T = br.readLine();
		P = br.readLine();
		
//		KMP 알고리즘
//		1. 부분 일치 테이블 생성
		int lenT = T.length(), lenP = P.length();
		int pi[] = new int[lenP];
		
//		i: 접미사, j: 접두사
		for(int i=1, j=0;i<lenP;i++) {
			while(j>0 && P.charAt(i)!=P.charAt(j)) j=pi[j-1];
			pi[i] = P.charAt(i)==P.charAt(j) ? ++j : 0;
		}
		
//		2. 문자열 탐색
		int cnt=0;
		final ArrayList<Integer> list = new ArrayList<>();
		for(int i=0, j=0;i<lenT;++i) {
//			2.1. 원본 문자열과 비교할 패턴 문자열의 시작 인덱스 연산
			while(j>0 && T.charAt(i)!=P.charAt(j)) j=pi[j-1];
			
//			2.2. 원본 문자열과 패턴 문자열의 문자가 일치하는 경우
			if(T.charAt(i)==P.charAt(j)) {
				if(j==lenP-1) {
					++cnt;
					list.add(i-j+1);
					j=pi[j];
				} else ++j;
			}
		}

//		출력
		sb.append(cnt).append("\n");
		for(int l : list) sb.append(l).append(" ");
		System.out.println(sb.toString());
		br.close();
		
	}

}
