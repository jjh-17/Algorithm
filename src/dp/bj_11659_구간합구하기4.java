package dp;

import java.io.*;
import java.util.*;

public class bj_11659_구간합구하기4 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_11659_구간합구하기4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken());
		int[] map = new int[N+1];
		
		//map 초기화
		st = new StringTokenizer(br.readLine());
		map[0] = 0;
		for(int i=1;i<=N;i++)
			map[i] = Integer.parseInt(st.nextToken()) + map[i-1];

		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
			sb.append(map[end] - map[start-1]).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
