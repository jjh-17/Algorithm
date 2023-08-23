package a0823.camp;

import java.util.*;
import java.io.*;

public class bj_d4_1238_Contact {

	static final StringBuilder sb = new StringBuilder();
	static int L, start;
	static List<Integer>[] map;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_d4_1238_Contact.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		for(int t=1;t<=T;t++) {
			//초기화
			map = new List[101];
			for(int i=1;i<=100;i++) map[i] = new ArrayList<>();
			v = new boolean[101];
			
			//입력
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<L/2;i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(!map[a].contains(b)) map[a].add(b);
			}
			
			
			sb.append("#").append(t).append(" ");
			bfs();
			
		}
		br.close();
		System.out.println(sb.toString());
	}
	
	static void bfs() {
		int ans=0;
		final ArrayDeque<Integer> queue = new ArrayDeque<>(); //현재 번호
		
		queue.offerLast(start);
		v[start] = true;
		boolean flag = true;
		
		while(true) {
			int L = queue.size();
			if(L==0) break;
			
			ans = Integer.MIN_VALUE; //마지막 연락이 아니므로 ans 초기화
			for(int l=0;l<L;l++) {
				int s = queue.pollFirst();
				ans = Integer.max(ans, s);
				for(int end : map[s]) {
					if(v[end]) continue;
					v[end] = true;
					queue.offerLast(end);
				}
			}
		}
		
		sb.append(ans).append("\n");
	}

}
