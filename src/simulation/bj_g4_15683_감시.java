package simulation;

import java.util.*;
import java.io.*;

public class bj_g4_15683_감시 {

	static final StringBuilder sb = new StringBuilder();
	
	//CCTV 위치 정보 및 종류
	static final List<int[]> cctvies = new ArrayList<>();
	
	//4방 탐색
	static final int[] di = {-1, 0, 1, 0},
					   dj = {0, 1, 0, -1};
	
	//CCTV 종류 별 확인 방향
	static final int[][][] dd = {
			{{-1}},
			{ {0}, {1}, {2}, {3} },
			{ {0, 2}, {1, 3} },
			{ {0, 1}, {1, 2}, {2, 3}, {3, 0} },
			{ {3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0} },
			{ {0, 1, 2, 3} }
	};
	
	//벽, 감시 공간
	static final int EMPTY=0, WALL=6, WATCH=7;
	static int N, M, ans;
	static int[][] map;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_15683_감시.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; ans=0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				//CCTV 정보 저장, 빈칸 개수 구하기
				if(1<=map[i][j] && map[i][j]<=5) cctvies.add(new int[] {i, j, map[i][j]});
				else if(map[i][j]==EMPTY) ++ans;			
			}
		}
		
		dfs(0, ans);
		
		br.close();
		sb.append(ans);
		System.out.println(sb.toString());
	}

	//현재 cctv 번호, 현재 사각지대 수
	static void dfs(int cnt, int cans) {
		if(cnt==cctvies.size()) {
			ans = Integer.min(ans, cans);
			return;
		}
		
		int[] cctv = cctvies.get(cnt);
		
		//후보군 초기화
		final List<int[]>[] cand = new List[4];
		for(int i=0;i<4;i++) cand[i] = new ArrayList<>();
		
		//4방 탐색을 진행하며 각 방향 별 감시 가능 영역 위치 정보 저장
		for(int d=0;d<4;d++) {
			int ni = cctv[0] + di[d];
			int nj = cctv[1] + dj[d];
			
			while(0<=ni&&ni<N && 0<=nj&&nj<M && map[ni][nj]!=WALL) {
				if(map[ni][nj]==EMPTY) cand[d].add(new int[] {ni, nj});
				ni+=di[d]; nj+=dj[d];
			}
		}
	
		//현 CCTV의 모든 방향 가능성에 대하여 dfs
		for(int i=0;i<dd[cctv[2]].length;i++) {
			int sum=0;
			
			//추가 감시 구역 수 계산 및 map 변경
			for(int d : dd[cctv[2]][i]) {
				sum += cand[d].size();
				for(int[] cor : cand[d]) map[cor[0]][cor[1]] = WATCH;
			}
			
			//dfs
			dfs(cnt+1, cans-sum);
			
			//map 복원
			for(int d : dd[cctv[2]][i]) {
				for(int[] cor : cand[d]) map[cor[0]][cor[1]] = EMPTY;
			}
		}
	}
}
