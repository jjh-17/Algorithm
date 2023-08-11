package samsung;


import java.util.*;
import java.io.*;

public class bj_g2_19236_청소년상어 {
	
	//물고기 번호, 방향 수
	static final int N = 4, M = 8;
	
	//방향 정보
	static final int[] di = {-1, -1, 0, 1, 1, 1, 0, 1};
	static final int[] dj = {0, -1, -1, -1, 0, 1, 1, 1, 0};
	
	//물고기 정보
	static int[][] map = new int[N][N]; //각 물고기의 위치 정보
	static int[][] sortedMap = new int[N*N][3]; //물고기 번호 순 - 위치 및 방향 정보 
	static boolean[] v = new boolean[N*N]; //상어가 먹었는지 여부
	
	//모든 경우에 대하여 상어가 먹을 수 있는 물고기 최대합, 현재 상어가 먹을 수 있는 물고기 최대합. 현재 상어 이동 방향(1~8)
	static int SUM_MAX = Integer.MIN_VALUE, SUM_CURRENT, SHARK_DIR;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g2_19236_청소년상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//물고기 정보 입력
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); //물고기 번호
				sortedMap[map[i][j]-1] = new int[] {i, j, Integer.parseInt(st.nextToken())};
			}
		}		
		
		showInfo();
		swap(0, 0, 1, 1);
		System.out.println();
		showInfo();
		
		
		
	}
	
	//물고기의 이동 후 위치 업데이트
	public static void fishMove() {
		//sortedMap 순회
		for(int i=0;i<N*N;i++) {
			if(v[i]) continue; //상어가 먹음
			
			while(true) {
				int ni = sortedMap[i][0] + di[sortedMap[i][1]-1];
				int nj = sortedMap[i][1] + dj[sortedMap[i][1]-1];
			}
		}
	}
	
	//(i1, j1)과 (i2, j2) 교환
	public static void swap(int i1, int j1, int i2, int j2) {
		int n1 = map[i1][j1], n2 = map[i2][j2]; //두 위치 물고기 번호
		
		sortedMap[i][j]
		
	}
	
	public static void showInfo() {
		for(int[][] b : map) {
			for(int[] c : b) System.out.print(Arrays.toString(c));
			System.out.println();
		}
		
		for(int[] a : sortedMap)
			System.out.print(Arrays.toString(a) + " ");
		System.out.println();
		
		System.out.println(Arrays.toString(v));
		
	}
}
