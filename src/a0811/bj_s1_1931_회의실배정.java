package a0811;

import java.util.*;
import java.io.*;

public class bj_s1_1931_회의실배정 {

	static int N, MAX, R; //최대 회의 개수, 
	static int EX_END_TIME = Integer.MIN_VALUE; //이전 회의 종료 시간
	
	//종료 시간 순 정렬
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_1931_회의실배정.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//회의 개수 입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][2];
		
		//회의 시간 입력 및 최소 시작 시간, 최대 시작 시간 초기화
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		
		//정렬 - 종료 시간 --> 시작 시간
		Arrays.sort(map, (m1, m2) -> m1[1]!=m2[1] ? Integer.compare(m1[1], m2[1]) 
												  : Integer.compare(m1[0], m2[0]));
		
		//슨회
		int cnt=0;
		for(int i=0;i<N;i++) {
			if(EX_END_TIME <= map[i][0]) {
				EX_END_TIME = map[i][1];
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
}
