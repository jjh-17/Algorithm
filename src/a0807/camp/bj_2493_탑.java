package a0807.camp;


import java.util.*;
import java.io.*;

public class bj_2493_탑 {

	private static final StringBuilder sb = new StringBuilder();
	private static final ArrayDeque<int[]> stack = new ArrayDeque<>(); //new int[] {건물 번호, 높이}
	private static int[][] map;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_2493_탑.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//초기화
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr[0] = Integer.MAX_VALUE;
		
		stack.offerLast(new int[] {0, Integer.MAX_VALUE});
		for(int i=1;i<=N;i++) { //건물 번호
			arr[i] = Integer.parseInt(st.nextToken());
			int[] p = stack.peekLast();
			
			if(p[1]>arr[i]) { //peek 건물 높이 > 현재 건물 높이
				stack.offerLast(new int[] {i, arr[i]});
				sb.append(p[0]).append(" ");
			} else { //peek 건물 높이 <= 현재 건물 높이
				while(true) {
					p = stack.pollLast();
					if(p[1]>arr[i]) { //건물 높이가 감소하는 구간
						stack.offerLast(p);
						stack.offerLast(new int[] {i, arr[i]});
						sb.append(p[0]).append(" ");
						break;
					}
				}
			}	
		}
		
		br.close();
		System.out.println(sb.toString());
	}

}
