package a0714;

import java.util.*;
import java.io.*;
import java.lang.Math;



class Info {
	int T = 0; //점수
	int K = 0; //현재 칼로리
	
	Info(int t, int k) {
		T = t;
		K = k;
	}
}

public class Solution_d3_5215_햄버거다이어트 {
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("res/input_d3_5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N, L, t, k;
		Queue<Info> queue;
		int answer;
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T;test_case++) {
			answer = 0;
			st = new StringTokenizer(br.readLine(), " "); //" "단위로 끊어 읽기
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			queue = new LinkedList<>();
			
			queue.add(new Info(0, 0));
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				t = Integer.parseInt(st.nextToken()); //현재 재료의 맛
				k = Integer.parseInt(st.nextToken()); //현재 재료의 칼로리
				
				//해당 재료를 "넣었을 때"와 "안 넣었을 때" 추가
				int length = queue.size();
				for(int j=0;j<length;j++) {
					Info info = queue.poll();
					if(info.K + k <= L) {
						queue.add(new Info(info.T + t, info.K + k)); //재료 추가
						answer = Math.max(answer, info.T + t);
					}
					queue.add(new Info(info.T, info.K)); //재료 추가하지 않음
				}
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
		
	}

}
