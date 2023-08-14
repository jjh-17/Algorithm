package samsung;


import java.util.*;
import java.io.*;

//현재 정보 백업에 실패하고 있음. 그것만 해결되면 될 듯
public class bj_g2_19236_청소년상어 {
	
	//물고기 정보 - 위치 정보, 방향, 물고기 살아있는지 여부
	static class Fish{
		int i, j, dir; //위치 정보, 방향
		boolean life; //살아있는지
		public Fish(int i, int j, int dir, boolean life) {
			this.i = i; this.j = j; this.dir = dir; this.life = life;
		}	
		public Fish() {}
	}
		
	//상어 정보 - 위치 정보, 방향, 먹은 물고기 수
	static int sDir;
	
	static final int N = 4, DIR_NUM=8, N2 = 16;
	static final Fish[] MAP_1D = new Fish[N2 + 1]; //물고기 번호 별 정보, 백업
	static final int[][] MAP_2D = new int[N][N]; //각 좌표 별 물고기 번호
	static final int[] DI = new int[] {-1, -1, 0, 1, 1, 1, 0, -1}, // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
					   DJ = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
	static int MAX_EAT = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g2_19236_청소년상어.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//정보 입력 - 물고기 번호 별 INFO
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int num =Integer.parseInt(st.nextToken()), dir = Integer.parseInt(st.nextToken())-1;
				MAP_1D[num] = new Fish(i, j, dir, true);
				MAP_2D[i][j] = num;
				if(i==0 && j==0) { //(0, 0)에 상어 최초 입장
					sDir = dir;
					MAP_1D[num].life = false;
				}
			}
		}
		
		dfs(0, 0, sDir, MAP_2D[0][0]);
		System.out.println(MAX_EAT);
	}
	
	public static String getDir(int d) {
		if(d==0) return "↑";
		else if(d==1) return "↖";
		else if(d==2) return "←";
		else if(d==3) return "↙";
		else if(d==4) return "↓";
		else if(d==5) return "↘";
		else if(d==6) return "→";
		else if(d==7) return "↗";
		
		return "";
	}
	
	public static void dfs(int i, int j, int dir, int sum) { //현재 상어 위치, 방향, 먹은 물고기 번호 합
		//이전 상어 정보 백업
		System.out.println("상어 정보: (" + i + ", " + j + "), 방향" + getDir(dir) + " 먹은 합" + sum);
		for(int[] m : MAP_2D) System.out.println(Arrays.toString(m));
		
		for(int d=1;d<N;d++) { //상어의 최대 이동 거리 N-1
			int ni=i+DI[dir]*d, nj=j+DJ[dir]*d; //새로운 상어 좌표
			
			//범위 내라면, 물고기가 있을 경우에만 이동
			if(0<=ni&&ni<N && 0<=nj&&nj<N && MAP_1D[MAP_2D[ni][nj]].life) {
				//물고기 정보 백업
				Fish[] MAP_1D_BU = new Fish[N2+1]; int[][] MAP_2D_BU = new int[N][N];
				for(int a=1;a<=N2;a++) MAP_1D_BU[a] = new Fish(MAP_1D[a].i, MAP_1D[a].j ,MAP_1D[a].dir ,MAP_1D[a].life);
				for(int a=0;a<N;a++) { for(int b=0;b<N;b++) MAP_2D_BU[a][b] = MAP_2D[a][b]; }
				
				//상어가 물고기를 먹음
				MAP_1D[MAP_2D[ni][nj]].life = false;
				
				//물고기 이동
				fishMove(ni, nj);
				for(int[] m : MAP_2D) System.out.println(Arrays.toString(m));
				System.out.println();
				dfs(ni, nj, MAP_1D[MAP_2D[ni][nj]].dir, sum + MAP_2D[ni][nj]); //dfs
				
				//물고기 정보 복원
				for(int a=1;a<=N2;a++) {
					MAP_1D[a].i = MAP_1D_BU[a].i; MAP_1D[a].j = MAP_1D_BU[a].j;
					MAP_1D[a].dir = MAP_1D_BU[a].dir; MAP_1D[a].life = MAP_1D_BU[a].life;
				}
				for(int a=0;a<N;a++) { for(int b=0;b<N;b++) MAP_2D[a][b] = MAP_2D_BU[a][b]; }
			}
		}
		
		MAX_EAT = Integer.max(MAX_EAT, sum);
	}
	
	//물고기 위치 이동
	public static void fishMove(int si, int sj) {
		for(int i=1;i<N2+1;i++) { //물고기 번호 순으로 위치 이동
			if(!MAP_1D[i].life) continue; //빈 구역은 넘긴다.
			for(int j=0;j<DIR_NUM;j++) {
				int ni = MAP_1D[i].i+DI[MAP_1D[i].dir], nj = MAP_1D[i].j+DJ[MAP_1D[i].dir];
				if(!(si==ni&&sj==nj) && (0<=ni&&ni<N && 0<=nj&&nj<N)) { //위치 교환
					int ti = MAP_1D[i].i, tj = MAP_1D[i].j, nNum = MAP_2D[ni][nj];
					
					//MAP_1D
					MAP_1D[i].i = ni; MAP_1D[i].j = nj;
					MAP_1D[nNum].i = ti; MAP_1D[nNum].j = tj;
					
					//MAP_2D
					MAP_2D[ti][tj] = nNum; MAP_2D[ni][nj] = i;

					break;
				}
				
				MAP_1D[i].dir = (MAP_1D[i].dir+1)%DIR_NUM;
			}
		}
	}
}
