package a1011.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d4_8458_원점으로집합 {

	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_8458_원점으로집합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
//			입력
			int N = Integer.parseInt(br.readLine());
			
			int[] dist = new int[N];
			int maxD=0;
			for(int n=0;n<N;n++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				dist[n] = Math.abs(i) + Math.abs(j);
				maxD = Integer.max(maxD, dist[n]);
			}
			
//			알고리즘
//			1. 원점까지의 거리가 모두 짝수거나 모두 홀수가 아니라면 모든 점의 원점 회귀 불가
			int rest = dist[0]%2;
			boolean noHope = false;
			for(int d : dist) {
				if(rest!=d%2) {
					noHope = true;
					break;
				}
			}
			if(noHope) {
				sb.append(-1).append("\n");
				continue;
			}
			
//			2. 원점까지의 거리가 모두 짝수거나 모두 홀수인 경우
			int day=0;
			while(maxD-day > 0) {
				++day;
				maxD-=day;
			}
			
			++day;
			if(maxD==)
			
			if(maxD>0) {
				if(maxD%2 != day%2) day+=2;
			}
			
			sb.append(day).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}


























































