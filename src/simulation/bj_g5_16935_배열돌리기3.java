package simulation;

import java.util.*;
import java.io.*;

public class bj_g5_16935_배열돌리기3 {
	
	static int N, M, R;
	static int[][] map;
	static final StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_16935_배열돌리기3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		//N, M, R 초기화
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken());
		
		//배열 초기화
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		//연산 수행
		st = new StringTokenizer(br.readLine());
		for(int r=0;r<R;r++) func(Integer.parseInt(st.nextToken()));
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) sb.append(map[i][j]).append(" ");
			sb.append("\n");
		}
		
		br.close();
		System.out.println(sb.toString());
	}
	
	public static void func(int i) {
		if(i==1) reverseTB();
		else if(i==2) reverseLR();
		else if(i==3) rotateR();
		else if(i==4) rotateL();
		else if(i==5) rotateSubsR();
		else if(i==6) rotateSubsL();
	}
	
	//1번 연산 - 상하 반전
	public static void reverseTB() {
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<M;j++) {
				int temp = map[i][j];
				map[i][j] = map[N-1-i][j];
				map[N-1-i][j] = temp;
			}
		}
	}
	
	
	//2번 연산 - 좌우 반전
	public static void reverseLR() {
		for(int j=0;j<M/2;j++) {
			for(int i=0;i<N;i++) {
				int temp = map[i][j];
				map[i][j] = map[i][M-1-j];
				map[i][M-1-j] = temp;
			}
		}
	}
	
	
	//3번 연산 - 오른쪽 90도 회전
	public static void rotateR() {
		int[][] mapNew = new int[M][N];
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				 mapNew[i][j] = map[N-1-j][i];
			}
		}
		map = mapNew;
		//N, M 교환
		int temp = N; N = M; M = temp;
	}
	
	
	//4번 연산 - 왼쪽 90도 회전
	public static void rotateL() {
		int[][] mapNew = new int[M][N];
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				 mapNew[i][j] = map[j][M-1-i];
			}
		}
		map = mapNew;
		//N, M 교환
		int temp = N; N = M; M = temp;
	}
	
	//5번 연산 - 4분할 오른쪽 90도 회전
	public static void rotateSubsR() {
		int N2 = N/2, M2 = M/2;
		int[][] mapNew = new int[N][M];
		
		for(int i=0;i<N2;i++) {
			for(int j=0;j<M2;j++) {
				mapNew[i][j] = map[N2+i][j]; //4번 구역 --> 1번 구역
				mapNew[i][M2+j] = map[i][j]; //1번 구역 --> 2번 구역
				mapNew[N2+i][M2+j] = map[i][M2+j]; //2번 구역 --> 3번 구역
				mapNew[N2+i][j] = map[N2+i][M2+j]; //3번 구역 --> 4번 구역
			}
		}
		map = mapNew;
	}
	
	
	//6번 연산 - 4분할 왼쪽 90도 회전
	public static void rotateSubsL() {
		int N2 = N/2, M2 = M/2;
		int[][] mapNew = new int[N][M];
		
		for(int i=0;i<N2;i++) {
			for(int j=0;j<M2;j++) {
				mapNew[i][j] = map[i][M2+j]; //2번 구역 --> 1번 구역
				mapNew[i][M2+j] = map[N2+i][M2+j]; //3번 구역 --> 2번 구역
				mapNew[N2+i][M2+j] = map[N2+i][j]; //4번 구역 --> 3번 구역
				mapNew[N2+i][j] = map[i][j]; //1번 구역 --> 4번 구역
			}
		}
		map = mapNew;
	}

}
