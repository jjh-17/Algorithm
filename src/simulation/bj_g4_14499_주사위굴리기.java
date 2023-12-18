package simulation;

import java.io.*;
import java.util.*;

public class bj_g4_14499_주사위굴리기 {

	static final StringBuilder sb = new StringBuilder();
	static int N, M, X, Y, K;
	static int[][] map;
	static int[] dice;
	
//	동서북남 이동
	static final int[] di = {0, 0, 0, -1, 1};
	static final int[] dj = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_14499_주사위굴리기.txt"));
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
//		알고리즘
//		1. 주사위의 모든 면 초기화
		dice = new int[7];
		
//		2. 주사위 이동 명령 순회
		for(int k=0;k<K;k++) {
			int d = Integer.parseInt(st.nextToken());
			
//			신규 좌표가 지도 안인지 확인
			int nX = X + di[d];
			int nY = Y + dj[d];
			if(nX<0||nX>=N || nY<0||nY>=M) continue;
			
//			현재 좌표 및 지면과 맞닿은 주사위 면 번호 최신화
			X = nX; Y = nY;
			changeDice(d);
			
//			주사위 상단에 적힌 숫자 추가
			sb.append(dice[6]).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void changeDice(int d) {
		int temp = dice[1];
		switch(d) {
		case 1: //동
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = temp;
			break;
		case 2: //서
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = temp;
			break;
		case 3:  //북
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = temp;
			break;
		case 4: //남
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = temp;	
		}
		
//		지도 값에 따른 주사위 및 지도값 변환 수행
		if(map[X][Y]==0) map[X][Y] = dice[1];
		else {
			dice[1] = map[X][Y];
			map[X][Y]=0;
		}
	}
}