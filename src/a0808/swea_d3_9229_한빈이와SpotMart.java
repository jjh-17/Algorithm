package a0808;

import java.util.*;
import java.io.*;

public class swea_d3_9229_한빈이와SpotMart {

	final static StringBuilder sb = new StringBuilder();
	static int[] map;
	static int MAX, N, M; //현재 최대 무게
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_9229_한빈이와SpotMart.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			//과자의 수, 제한 무게
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
			
			//무게
			map = new int[N]; MAX = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) map[i] = Integer.parseInt(st.nextToken());
			comb(0, 0, 0);
			sb.append(MAX==0 ? -1 : MAX).append("\n");
		}
		
		br.close();
		System.out.println(sb.toString());
	}
	
	//2개 조합
	public static void comb(int cnt, int start, int sum) {
		if(cnt==2) { //종료
			if(MAX < sum) MAX = sum;
			return;
		}
		
		for(int i=start;i<N;i++) {
			if(sum+map[i] <= M) comb(cnt+1, i+1, sum+map[i]);
		}
		
	}

}
