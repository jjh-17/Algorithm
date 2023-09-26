package a0818.camp;

import java.util.*;
import java.io.*;

public class bj_s1_2178_미로탐색 {
	
	static final int[] di = {-1, 0, 1, 0},
					   dj = {0, 1, 0, -1};
	static int N, M, MIN;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_2178_미로탐색.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//N, M 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		MIN = Integer.MAX_VALUE;
		
		//MAP
		map = new char[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) map[i][j] = str.charAt(j);
		}
		
		bfs();
		System.out.println(MIN);
	}
	
	static void bfs() {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offerLast(new int[] {0, 0, 1});
		map[0][0] = '0';
		
		while(!queue.isEmpty()) {
			int[] arr = queue.pollFirst();
			if(arr[0]==N-1 && arr[1]==M-1) {
				MIN = arr[2];
				return;
			}
			
			
			for(int d=0;d<4;d++) {
				int ni = arr[0]+di[d];
				int nj = arr[1]+dj[d];
				if(0<=ni&&ni<N && 0<=nj&&nj<M && map[ni][nj]=='1') {
					map[ni][nj] = '0';
					queue.offerLast(new int[] {ni, nj, arr[2]+1});
				}
			}
		}
		
	}

}
