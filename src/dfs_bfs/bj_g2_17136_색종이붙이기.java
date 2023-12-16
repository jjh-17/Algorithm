package dfs_bfs;

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
		dfs(0, 0, 0);
		
//		출력
		sb.append(ANS==Integer.MAX_VALUE ? -1 : ANS);
		System.out.println(sb.toString());
		br.close();
	}
	
//	현재 위치, 색종이 사용 횟수
	static void dfs(int i, int j, int cnt) {
//		탐색 종료
		if(i==N) {
			ANS = Integer.min(ANS, cnt);
			return;
		}
		
		if(ANS < cnt) return;
		
//		다음 인덱스로 이동
		if(map[i][j]==0) {
			int nj = j+1==N ? 0 : j+1;
			int ni = nj==0 ? i+1 : i;
			dfs(ni, nj, cnt);
			return;
		}
		
//		 백업
		int back[][] = new int[N][N];
		for(int a=0;a<N;a++) {
			for(int b=0;b<N;b++) back[a][b] = map[a][b];
		}
			
		for(int s=0;s<5;s++) {
//			색종이 s가 더 이상 남아있지 않거나 필드를 벗어나면 다음 색종이 탐색
			if(rest[s]==0 || i+size[s]-1>=N || j+size[s]-1>=N) continue;
			
			if(check(i, j, size[s])) {
				--rest[s];
				
				int nj = j+size[s]==N ? 0 : j+size[s];
				int ni = nj==0 ? i+1 : i;
				dfs(ni, nj, cnt+1);
				
//				복원
				++rest[s];
				for(int a=0;a<N;a++) {
					for(int b=0;b<N;b++) map[a][b] = back[a][b];
				}
			}
				
		}
	}

//	색종이를 덮을 수 있는지 판단
	static boolean check(int i, int j, int size) {
		for(int a=i;a<i+size;a++) {
			for(int b=j;b<j+size;b++) {
				if(map[a][b]==1) map[a][b]=0;
				else return false;
			}
		}
		
		return true;
	}
}
