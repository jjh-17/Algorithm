package a0809.camp;


import java.util.*;
import java.io.*;

public class swea_d4_1861_정사각형방 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1}; //상우하좌 탐색
	static int N, CNT;
	static int MAX, MAX_LOC; //최대 방문 방 갯수, 출발해야할 방 번호
	static int[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_1861_정사각형방.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			//방의 크기 입력
			N = Integer.parseInt(br.readLine());
			
			//방 정보 입력
			map = new int[N][N]; v = new boolean[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//최대 이동 가능한 방의 개수 초기화
			MAX = Integer.MIN_VALUE;
			
			//map 순회
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					//이전에 들른 곳은 더이상 고려할 필요 없음. 이미 그 이전 위치에서 연결 될 수 있는 방의 최댓값을 가진다.
					//또한, 주위에 현재 방번호 보다 1작은 곳이 존재한다면 현재 방을 포함하는 최대 연결선이 존재하므로 넘어간다.
					if(!v[i][j] && !checkLower(i, j)) {
						CNT=0; //현 좌표에서 최대 이동 가능한 방의 개수
//						dfs(i, j);
						bfs(i, j);
						if(MAX < CNT) {
							MAX_LOC = map[i][j];
							MAX = CNT;
						} else if(MAX == CNT) {
							MAX_LOC = Integer.min(MAX_LOC, map[i][j]);
						}
					}
				}
			}
			sb.append(MAX_LOC).append(" ").append(MAX).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}
	
	public static void dfs(int i, int j) {
		v[i][j] = true; ++CNT;
		
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			
			//다음 방이 N*N이내에 위치하며, 들르지 않은 방이고, 현재 번호보다 1 클 때
			if(0<=ni&&ni<N && 0<=nj&&nj<N && !v[ni][nj] && map[ni][nj]==map[i][j]+1) {
				dfs(ni, nj);
				return;
			}
		}
	}
	
	public static void bfs(int i, int j) {
		 ArrayDeque<int[]> queue = new ArrayDeque<>();
		 
		 //초기화
		 queue.offerLast(new int[] {i, j});
		 
		 while(!queue.isEmpty()) {
			int[] ij = queue.pollFirst();
			
			v[i][j] = true; ++CNT;	
			for(int d=0;d<4;d++) {
				int ni = ij[0] + di[d];
				int nj = ij[1] + dj[d];
				
				//다음 방이 N*N이내에 위치하며, 들르지 않은 방이고, 현재 번호보다 1 클 때
				if(0<=ni&&ni<N && 0<=nj&&nj<N && !v[ni][nj] && map[ni][nj]==map[i][j]+1) {
					dfs(ni, nj);
					return;
				}
			}
		 }
	}

	//현 좌표 기준 4방향에 방번호가 1작은 곳이 존재하는 지 확인
	public static boolean checkLower(int i, int j) {
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			
			if(0<=ni&&ni<N && 0<=nj&&nj<N && !v[ni][nj] && map[ni][nj]==map[i][j]-1)
				return true;
		}
		return false;
	}
}
