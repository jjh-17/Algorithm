package a0817.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d9_5644_무선충전 {

	static class BC{
		int X, Y,	//좌표
			C, 	  	//충전 범위
			P;		//처리량
		public BC(int X, int Y, int C, int P) {
			this.X=X; this.Y=Y; this.C=C; this.P=P;
		}
	}
	static final StringBuilder sb = new StringBuilder();
	static int M, A;
	static int AI, AJ,	//사용자 A의 좌표
			   BI, BJ,	//사용자 B의 좌표
			   SUMA, SUMB; //사용자 A, B의 충전량
	static int[] moveA, moveB;
	static BC[] INFO;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_5644_무선충전.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//총 이동 시간, BC의 개수 입력
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); A = Integer.parseInt(st.nextToken());
			
			//사용자 A, B의 이동 경로 저장 및 위치 초기화
			SUMA=0; SUMB=0;
			AI=0; AJ=0; BI=9; BJ=9;
			moveA = new int[M+1]; moveB = new int[M+1];
			moveA[0] = 0; moveB[0] = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=M;i++) moveA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=M;i++) moveB[i] = Integer.parseInt(st.nextToken());
			
			
			//BC 정보 입력
			INFO = new BC[A];
			for(int i=0;i<A;i++) {
				st = new StringTokenizer(br.readLine());
				INFO[i] = new BC(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1
								, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			
			//
			for(int m=0;m<=M;m++) {
				
			}
		}
		
		
		br.readLine();
		System.out.println(sb.toString());
	}
	
	//어떤 좌표 (i, j)가 BC bc 범위 내에 존재하는 가
	static boolean checkInRange(BC bc, int i, int j) {
		return Math.abs(bc.X-i)+Math.abs(bc.Y-j) <= bc.C;
	}

}
