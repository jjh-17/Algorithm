package a0821.camp;


import java.util.*;
import java.io.*;

public class bj_g4_14502_연구소 {
	
	static final StringBuilder sb = new StringBuilder();
	static final List<int[]> wallCand = new ArrayList<>(); //추가 벽 후보군
	static final int[] di = {-1, 0, 1, 0},
					   dj = {0, 1, 0, -1};
	static int N, M, ans;
	static int[][] map, map2; //바이러스 확산 전 공간 정보, 바이러스 확산 이후 공간 정보
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_14502_연구소.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M=Integer.parseInt(st.nextToken());
		
		map = new int[N][M]; map2 = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) wallCand.add(new int[] {i, j}); 
			}
		}
		
		ans = Integer.MIN_VALUE;
		comb(0, 0);
		
		sb.append(ans);
		br.close();
		System.out.println(sb.toString());
		
	}
	
	//임의의 빈칸 3개
	static void comb(int start, int cnt) {
		if(cnt==3) {
			//visited 초기화, 바이러스 확산 전 초기 값을 map2에 저장
			v = new boolean[N][M];
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) map2[i][j] = map[i][j];
			}
			
			//공간을 흩으며 들르지 아니하고 감염 구역인 곳에 대하여 바이러스 확산 수행
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(!v[i][j] && map2[i][j]==2)
						spread(i, j);
				}
			}
			setSafe(); //현재 안전구역 수 계산
			
			return;
		}
		
		for(int i=start;i<wallCand.size();i++) {
			int[] cor = wallCand.get(i);
			map[cor[0]][cor[1]] = 1;
			comb(i+1, cnt+1);
			map[cor[0]][cor[1]] = 0;
		}
	}
	
	//바이러스 확산
	static void spread(int i, int j) {
		//현 구역 visited 처리
		v[i][j] = true;
		
		//4방 탐색
		for(int d=0;d<4;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			
			//N*M이내, 아직 들르지 아니한 곳, 빈칸인 신규 좌표에 대하여 spread 재수행
			if(0<=ni&&ni<N && 0<=nj&&nj<M && !v[ni][nj] && map2[ni][nj]==0) {
				map2[ni][nj] = 2;
				spread(ni, nj);	
			}
		}
	}
	
	//안전 구역 개수 계산 - map2 완전 탐색
	static void setSafe() {
		int sum = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map2[i][j]==0) ++sum;
			}
		}
		
		ans = Integer.max(sum, ans);
	}

}
