package a0731.camp;


import java.util.*;
import java.io.*;

/*
 * N*N 판 존재
 * 1개 Cell에는 1개의 코어 혹은 전선 존재 가능
 * 판 가장자리에서 파워 제공 ==> 가장 자리에 위치한 코어는 전선 연결 없이 파워 공급 받음
 * 전선은 직선으로만 연결 가능, 교차 불가능
 * 
 * 최대한 많은 코어에 전원을 연결하였을 때, 전선 길이의 합 출력
 * 		==> 여러 방법이 있을 경우, 전선 길이 최소 출력
 */
public class swea_프로세서연결하기 {
	
	static int N;
	static int wire_sum = -1;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_프로세서연결하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<T;t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

}
