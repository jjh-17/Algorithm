package a0817;

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
	static int AX, AY,	//사용자 A의 좌표
			   BX, BY,	//사용자 B의 좌표
			   ANS; //사용자 A, B의 충전량
	static int[] moveA, moveB, //A, B의 이동경로
				 DY = {0, -1, 0, 1, 0}, //숫자에 따른 i, j좌표 추가값
				 DX = {0, 0, 1, 0, -1};
	static BC[] BCS; //BC 모음
	
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
			ANS = 0;
			AX=0; AY=0; BX=9; BY=9;
			moveA = new int[M+1]; moveB = new int[M+1];
			moveA[0] = 0; moveB[0] = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=M;i++) moveA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=M;i++) moveB[i] = Integer.parseInt(st.nextToken());
			
			//BC 정보 입력
			BCS = new BC[A];
			for(int i=0;i<A;i++) {
				st = new StringTokenizer(br.readLine());
				BCS[i] = new BC(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1
								, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			//이동 순회
			for(int m=0;m<=M;m++) {
				//위치 변경
				AX += DX[moveA[m]]; AY += DY[moveA[m]]; 
				BX += DX[moveB[m]]; BY += DY[moveB[m]]; 
				setMax();
			}			
			sb.append("#").append(t).append(" ").append(ANS).append("\n");
		}		
		br.readLine();
		System.out.println(sb.toString());
	}	
	
	//A, B 현 위치에서 충전량 최댓값
	static void setMax() {
		int max=0;
		List<BC> la = new ArrayList<>(), lb = new ArrayList<>();
		
		//각 리스트에 A, B의 연결 가능 BC 저장
		for(BC bc : BCS) {
			if(checkInRange(bc, AX, AY)) la.add(bc);
			if(checkInRange(bc, BX, BY)) lb.add(bc);
		}	
		
		if(0<la.size() && 0<lb.size()) { //A, B모두 연결 가능
			for(int i=0;i<la.size();i++) { //완전 탐색
				BC a = la.get(i);
				for(int j=0;j<lb.size();j++) {
					BC b = lb.get(j);
					if(a.X==b.X && a.Y==b.Y) max = Integer.max(max, b.P);
					else max = Integer.max(max, a.P + b.P);
				}
			}
		} else if(0<la.size()) { //A만 연결 가능
			for(BC a : la) max = Integer.max(max, a.P);
		} else if(0<lb.size()) { //B만 연결 가능
			for(BC b : lb) max = Integer.max(max, b.P);
		}
		
		ANS += max;
	}
	
	//어떤 좌표 (i, j)가 BC bc 범위 내에 존재하는 가
	static boolean checkInRange(BC bc, int x, int y) {
		return Math.abs(bc.X-x)+Math.abs(bc.Y-y) <= bc.C;
	}

}
