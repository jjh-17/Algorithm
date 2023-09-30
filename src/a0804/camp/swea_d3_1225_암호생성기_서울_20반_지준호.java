package a0804.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d3_1225_암호생성기_서울_20반_지준호 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_1225_암호생성기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		ArrayDeque<Integer> q;
		
		int T = 10;
		for(int t=1;t<=T;t++) {
			int test = Integer.parseInt(br.readLine());
			sb.append("#").append(test).append(" ");
			q = new ArrayDeque<Integer>();
			st = new StringTokenizer(br.readLine());
			
			//큐 초기화
			for(int i=0;i<8;i++)
				q.offerLast(Integer.parseInt(st.nextToken()));
			
			//암호화
			boolean flag = true;
			while(flag) {
				for(int cnt=1;cnt<=5;cnt++) {
					int p = q.pollFirst();
					if(p-cnt<=0) {
						q.offerLast(0);
						flag = false;
						break;
					}
					q.offerLast(p-cnt);
				}
			}
			
			while(!q.isEmpty())
				sb.append(q.pollFirst()).append(" ");
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}

}
