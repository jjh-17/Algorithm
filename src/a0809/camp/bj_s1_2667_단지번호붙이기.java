package a0809.camp;

import java.util.*;
import java.io.*;

public class bj_s1_2667_단지번호붙이기 {
	
	static int N, cnt;
	static char[][] map;
	static boolean[][] v;
	static final List<Integer> answer = new ArrayList<>();
	static final int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};
	static final StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_2667_단지번호붙이기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//단지 수 입력
		N = Integer.parseInt(br.readLine());
		
		//단지 정보 입력
		map = new char[N][N]; v = new boolean[N][N];
		for(int i=0;i<N;i++) map[i] = br.readLine().toCharArray();
		
		//단지 순회
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!v[i][j] && map[i][j]=='1') {
					cnt=0;
//					dfs(i, j);
					bfs(i, j);
					answer.add(cnt);
				}
			}
		}
		
		//리스트 오름차순 정렬
		Collections.sort(answer);
		
		br.close();
		sb.append(answer.size()).append("\n");
		for(int ans : answer) sb.append(ans).append("\n");
		System.out.println(sb.toString());

	}
	
	public static void dfs(int i, int j) {
		++cnt; v[i][j] = true;
		
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			
			//신규 좌표가 N*N 이내, 집이 있으며, 들르지 아니한 곳
			if(0<=ni&&ni<N && 0<=nj&&nj<N && map[ni][nj]=='1' && !v[ni][nj])
				dfs(ni, nj);
		}
	}
	
	public static void bfs(int i, int j) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		//초기화
		queue.offerLast(new int[] {i, j});
		v[i][j] = true; ++cnt;
		
		///순회
		while(!queue.isEmpty()) {
			int[] ij = queue.pollFirst();
			
			for(int d=0;d<4;d++) {
				int ni = ij[0] + di[d];
				int nj = ij[1] + dj[d];
				
				//신규 좌표가 N*N 이내, 집이 있으며, 들르지 아니한 곳
				if(0<=ni&&ni<N && 0<=nj&&nj<N && map[ni][nj]=='1' && !v[ni][nj]) {
					queue.offerLast(new int[] {ni, nj});
					++cnt; v[ni][nj] = true;
				}
			}
		}
	}

}
