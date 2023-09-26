package a0810.camp;

import java.util.*;
import java.io.*;

public class bj_g4_17406_배열돌리기4 {
	static int N, M, K; //배열 A의 크기, 회전 연산 횟수
	static int[][] map, tempMap;
	static int[] arrR, arrC, arrS, arr;
	static boolean[] v;
	static int MIN = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_17406_배열돌리기4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//N, M, K
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); 
		v = new boolean[K]; arrR = new int[K]; arrC = new int[K]; arrS = new int[K]; 
		arr = new int[K];
		
		//map 초기화
		map = new int[N][M]; tempMap = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		
		//연산
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			arrR[k] = Integer.parseInt(st.nextToken());
			arrC[k] = Integer.parseInt(st.nextToken());
			arrS[k] = Integer.parseInt(st.nextToken());
		}
		
		perm(0);
		
		br.close();
		System.out.println(MIN);
	}
	
	////(r-s, c-s) ~ (r+s, c+s) 정사각형 시계방향 회전
	public static void rotateR(int R, int C, int S) {
		for(int s=0;s<S;s++) { //돌릴 사격형의 개수
			//최상단
			int[] LU = new int[] {R-S-1+s, C-S-1+s}; //사각형 맨 좌측*상단 꼭지점 좌표
			int[] RU = new int[] {R-S-1+s, C+S-1-s}; //사각형 맨 우측*상단 꼭지점 좌표
			int[] RD = new int[] {R+S-1-s, C+S-1-s}; //사각형 맨 우측*하단 꼭지점 좌표
			int[] LD = new int[] {R+S-1-s, C-S-1+s}; //사각형 맨 좌측*하단 꼭지점 좌표
			
			int temp = tempMap[RU[0]][RU[1]];
			for(int d=0;d<2*(S-s);d++) tempMap[RU[0]][RU[1]-d] = tempMap[RU[0]][RU[1]-(d+1)]; //최상단
			for(int d=0;d<2*(S-s);d++) tempMap[LU[0]+d][LU[1]] = tempMap[LU[0]+(d+1)][LU[1]]; //최좌측
			for(int d=0;d<2*(S-s);d++) tempMap[LD[0]][LD[1]+d] = tempMap[LD[0]][LD[1]+(d+1)]; //최하단
			for(int d=0;d<2*(S-s);d++) tempMap[RD[0]-d][RD[1]] = tempMap[RD[0]-(d+1)][RD[1]]; //최우측
			tempMap[RU[0]+1][RU[1]] = temp;
		}
	}
	
	//순열
	public static void perm(int cnt) {
		if(cnt==K) {
			//tempMap 초기화
			setTempMap();
			for(int a: arr) rotateR(arrR[a], arrC[a], arrS[a]);			
			setMin();
			return;
		}
		
		for(int i=0;i<K;i++) {
			if(v[i]) continue;
			
			v[i] = true;
			arr[cnt] = i;
			perm(cnt+1);
			v[i] = false;
		}
	}
	
	public static void setMin() {
		int sum;
		for(int i=0;i<N;i++) {
			sum=0;
			for(int j=0;j<M;j++) sum+=tempMap[i][j];
			MIN = Integer.min(sum, MIN);
		}
	}

	public static void setTempMap() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) tempMap[i][j] = map[i][j];
		}
	}
}
