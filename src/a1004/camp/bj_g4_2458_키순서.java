package a1004.camp;

import java.util.*;
import java.io.*;

public class bj_g4_2458_키순서 {

	static final StringBuilder sb=  new StringBuilder();
	static int N, M;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_2458_키순서.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		
		
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}

}
