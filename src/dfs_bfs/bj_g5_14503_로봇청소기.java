package dfs_bfs;

import java.util.*;
import java.io.*;

public class bj_g5_14503_로봇청소기 {

	static final StringBuilder sb = new StringBuilder();
	static int N, M;
	static int r, c, d;
	static int[][] map;
	static final int[] di = {-1, 0, 1, 0};	//북, 동, 남, 서
	static final int[] dj = {0, 1, 0, -1};
	static final int EMPTY = 0, WALL=1, CLEANED = 2;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_14503_로봇청소기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		알고리즘
		int result=0;
		while(true) {
			boolean nowhere = true;
			
//			현재 칸 정소
			if(map[r][c]==EMPTY) {
				++result;
				map[r][c] = CLEANED;
			}
			
//			주변 4칸에서 청소되지 않은 칸 탐색
			for(int k=0;k<4;k++) {
				d = d-1>=0 ? d-1 : 3;
				int nr = r + di[d];
				int nc = c + dj[d];
				if(0<=nr&&nr<N && 0<=nc&&nc<M && map[nr][nc]==EMPTY) {
					r = nr;	c = nc;
					nowhere = false;
					break;
				}
			}
			
//			주변 4칸에 청소할 칸이 없어 후진 시도
			if(nowhere) {
				int back = (d+2)%4;
				r += di[back];	c += dj[back];
				if(0<=r&&r<N && 0<=c&&c<M && map[r][c]!=WALL)	continue;
				else											break;
			}
		}
		
//		출력
		sb.append(result);
		System.out.println(sb.toString());
		br.close();
	}
}
