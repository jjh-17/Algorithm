package dfs_bfs;

import java.util.*;
import java.io.*;

public class bj_g3_15684_사다리조작 {

	static final StringBuilder sb = new StringBuilder();
	static int N, M, H, CNT, MIN,
			   MAP[][];
	static boolean flag = false;
	static final List<int[]> LINE_CAND = new ArrayList<>(); //가로선 후보군 가로출 i에서 세로줄 j~j+1
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_15684_사다리조작.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken()); M=Integer.parseInt(st.nextToken()); H=Integer.parseInt(st.nextToken());
		MIN = Integer.MAX_VALUE; MAP = new int[H+1][N+1]; //0은 빈칸, 1은 가로선
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			MAP[a][b]=b+1; MAP[a][b+1]=b;
		}
		
		//가로선 후보군 추출: i가로줄의 j~j+1 세로줄
		for(int i=1;i<=H;i++) {
			for(int j=1;j<=N-1;j++) {
				if(MAP[i][j]==0 && MAP[i][j+1]==0) LINE_CAND.add(new int[] {i, j});
			}
		}
		
		dfs(0, 0);		
		
		br.close();
		System.out.println(MIN==Integer.MAX_VALUE ? -1 : MIN);
	}
	
	
	//가로줄 a에서 
	static void dfs(int idx, int cnt) {
		if(flag) return;
		
		if(checkResult()) {
			MIN = Integer.min(MIN, cnt);
			if(cnt==0) flag = true;
			return;
		}
		
		if(cnt==3) return;
		
		for(int i=idx;i<LINE_CAND.size();i++) {
			int[] cor = LINE_CAND.get(i);
			if(MAP[cor[0]][cor[1]]==0 && MAP[cor[0]][cor[1]+1]==0) {
				MAP[cor[0]][cor[1]]=cor[1]+1; MAP[cor[0]][cor[1]+1]=cor[1];
				dfs(idx+1, cnt+1);
				MAP[cor[0]][cor[1]]=0; MAP[cor[0]][cor[1]+1]=0;
			}
		}
		
	}
	
	
	//Result 적용
	static boolean checkResult() {
		for(int d=1;d<=N;d++) {
			int j=d;
			for(int i=1;i<=H;i++) j = MAP[i][j]!=0 ? MAP[i][j] : j;
			if(j!=d) return false;
		}
		
		return true;
	}

}
