package a1005.camp;

import java.util.*;
import java.io.*;

public class swea_d9_5656_벽돌깨기 {

	static final StringBuilder sb = new StringBuilder();
	static final int di[] = {-1, 0, 1, 0};
	static final int dj[] = {0, 1, 0, -1};
	static int N, W, H, MAX, total;
	static int map[][], loc[];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_swea_d9_5656_벽돌깨기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
//			입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W]; loc = new int[N]; MAX = 0;
			total=0;
			
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>0) ++total;
				}
			}
			
//			알고리즘
			permR(0, 0);	
			sb.append(total-MAX).append("\n");
		}
		
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
//	중복 가능 순열
	static void permR(int cnt, int sum) {
		if(cnt==N) {
			MAX = Integer.max(MAX, sum);
			return;
		}
		
//		백업
		int back[][] = new int[H][W];
		for(int a=0;a<H;a++) {
			for(int b=0;b<W;b++) back[a][b] = map[a][b];
		}
		
		boolean flag = true;
		for(int j=0;j<W;j++) {
			if(map[H-1][j]==0) continue;
			
			loc[cnt] = j;
			
//			조합
			flag = false;
			permR(cnt+1, sum+bfs(j));
			
//			복구
			for(int a=0;a<H;a++) {
				for(int b=0;b<W;b++) map[a][b] = back[a][b];
			}
		}
		
//		더이상 벽돌이 남아 있지 않은 경우
		if(flag)  MAX = Integer.max(MAX, sum);
	}
	
//	j번째 세로줄에 구슬을 떨어뜨렸을 때 
	static int bfs(int j) {
		final ArrayDeque<int[]> queue = new ArrayDeque<>();
		final ArrayDeque<int[]> delete = new ArrayDeque<>();
		final boolean v[][] = new boolean[H][W];
		
//		1. 구슬이 부딫히는 좌표 구하기
		int i;
		for(i=0;i<H;i++) {
			if(map[i][j]>0) break;
		}
		
//		2. 해당 좌표를 queue에 추가하고, queue가 완전히 빌때까지 탐색 개시
		queue.offerLast(new int[] {i, j});
		delete.offerLast(new int[] {i, j});
		v[i][j] = true;
		
		while(!queue.isEmpty()) {
			int cur[] = queue.pollFirst();
			
//			3. 4방 탐색
			for(int d=0;d<4;d++) {
//				4. d방향으로 현 좌표값-1 만큼 이동 개시
				int ni = cur[0], nj = cur[1];
				for(int r=0;r<map[cur[0]][cur[1]]-1;r++) {
//					5.신규 좌표의 타당성 검증
					ni+=di[d]; nj+=dj[d];
					if(ni<0 || ni>=H || nj<0 || nj>=W) break;	// 필드를 벗어나면 해당 방향 탐색 종료
					if(v[ni][nj] || map[ni][nj]==0) continue;	// 필드 내이나 들른 곳이나 벽돌이 없으면 다음 좌표로 이동
					queue.offerLast(new int[] {ni, nj});
					delete.offerLast(new int[] {ni, nj});
					v[ni][nj] = true;
				}
			}
		}
		
//		6. 벽돌 삭제
		int cnt = delete.size();
		while(!delete.isEmpty()) {
			int cor[] = delete.pollFirst();
			map[cor[0]][cor[1]]=0;
		}
		
//		7. 벽돌을 내림
		int mapNew[][] = new int[H][W];
		for(int b=0;b<W;b++) {
			final List<Integer> list = new ArrayList<>();
			for(int a=H-1;a>=0;a--) {
				if(map[a][b]>0) list.add(map[a][b]);
			}
			for(int l=0;l<list.size();l++) mapNew[H-1-l][b] = list.get(l);
		}
		map = mapNew;
		
		return cnt;
	}
}