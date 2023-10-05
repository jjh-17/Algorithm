package a1005.camp;

import java.util.*;
import java.io.*;

public class bj_g2_17136_색종이붙이기 {

	static final StringBuilder sb = new StringBuilder();
	static final int rest[] = new int[] {5, 5, 5, 5, 5};
	static final int size[] = new int[] {1, 2, 3, 4, 5};
	static final int N = 10, map[][] = new int[N][N];
	static int ANS = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g2_17136_색종이붙이기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
//		입력
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		알고리즘
		
		
		
//		출력
		sb.append(ANS==Integer.MAX_VALUE ? -1 : ANS);
		System.out.println(sb.toString());
		br.close();
	}
	
//	현재 위치, 색종이 사용 횟수
	static void dfs(int i, int j, int cnt) {
		if(i==N) {
			ANS = Integer.min(ANS, cnt);
			return;
		}
		
//		현 위치를 좌상단 꼭지점으로 두었을 때, 색종이를 붙일 수 있으면 붙인다.
		if(map[i][j]==1) {
			for(int s=0;s<5;s++) {
//				색종이 s가 더 이상 남아있지 않거나 필드를 벗어나면 다음 색종이 탐색
				if(rest[s]==0 || i+size[s]-1>=N || j+size[s]-1>=N) continue;
				
				
			}
		}
		
		
//		다음 인덱스로 이동
		int nj = j+1==N ? 0 : j+1;
		int ni = nj==0 ? i+1 : i;
		dfs(ni, nj, cnt);
	}

	static int[] getNewLoc(int i, int j, int di, int dj) {
		
		
		
	}
}
