package a0816;

import java.util.*;
import java.io.*;

public class bj_g4_6987_월드컵 {

	static final int N=6; //나라의 수 상수
	static final StringBuilder sb = new StringBuilder();
	
	//경기 정보(총 15번)
	static final int[] A = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4}, //현재 팀
					   B = {1, 2, 3 ,4 ,5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5}; //상대 팀
	static int[] W=new int[N], //idx번 나라의 승점 
				 D=new int[N], //idx번 나라의 비긴 횟수
				 L=new int[N]; //idx번 나라의 패점
	static int ANS; //정답
	static boolean flag=false; //dfs 탈출 조건
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_6987_월드컵.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		for(int i=0;i<4;i++) {
			ANS=0; flag = false;
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				W[j] = Integer.parseInt(st.nextToken());
				D[j] = Integer.parseInt(st.nextToken());
				L[j] = Integer.parseInt(st.nextToken());
			}
			
			if(checkInit()) dfs(0);
			
			sb.append(ANS).append(" ");	
		}

		br.close();
		System.out.println(sb.toString());
	}
	
	
	//c: 나라번호
	//c번 나라의 승점을 c번 나라를 제외한 나라의 패점에 분배
	static void dfs(int idx) {
		if(flag) return;
		
		if(idx==15) {
			flag = true;
			ANS = 1;
			return;
		}
		
		//idx 경기 승리
		if(W[A[idx]]>0 && L[B[idx]]>0) {
			--W[A[idx]]; --L[B[idx]];
			dfs(idx+1);
			++W[A[idx]]; ++L[B[idx]];
		}
		
		//idx 경기 비김
		if(D[A[idx]]>0 && D[B[idx]]>0) {
			--D[A[idx]]; --D[B[idx]];
			dfs(idx+1);
			++D[A[idx]]; ++D[B[idx]];
		}
		
		//idx 경기 짐
		if(L[A[idx]]>0 && W[B[idx]]>0) {
			--L[A[idx]]; --W[B[idx]];
			dfs(idx+1);
			++L[A[idx]]; ++W[B[idx]];
		}
	}
	
	//경기의 초기 조건: 각 나라의 승/무승부/패의 합이 5여야 함
	static boolean checkInit() {
		for(int i=0;i<N;i++) {
			if(W[i]+D[i]+L[i] != 5) return false;
		}
		return true;
	}
}
