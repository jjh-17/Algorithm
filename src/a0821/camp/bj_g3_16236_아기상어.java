package a0821.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g3_16236_아기상어 {

	static class Info{
		int i, j, size;
		public Info() {}
		public Info(int i, int j, int size) {
			this.i=i; this.j=j; this.size=size;
		}
	}
	
	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, 0, 0, 1}, //상 좌 우 하
					   dj = {0, -1, 1, 0};
	static int N;
	static int[][] map;
	static Info shark;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_16236_아기상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) shark = new Info(i, j, 2);
			}
		}
		
		
		boolean flag = true;
		int ans = 0; //물고기 잡아먹은 시간
		int ceat = 0; //현재 아기 상어가 먹은 물고기 수
		while(flag) {
			flag = false;
			int d = Integer.MAX_VALUE;
			
			
			
			
			if(flag) { //물고기 잡아먹기 성공
				fish.remove(idx);
				ans+=d;
			} else { //물고기 잡아먹기 실패
				break;
			}
		}
		
		br.close();
		sb.append(ans);
		System.out.println(sb.toString());
	}
	
	static int getRange(int si, int sj, int di, int dj) {
		return Math.abs(si-di) + Math.abs(sj-dj);
	}

}
