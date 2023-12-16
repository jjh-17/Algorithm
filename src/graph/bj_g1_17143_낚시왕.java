package graph;

import java.util.*;
import java.io.*;

public class bj_g1_17143_낚시왕 {

	static class Shark {
		int r, c, s, d, z;
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
		@Override
		public String toString() {
			return "위치 (" + r + " " + c + "), 속도" + s + " " + getDir(d) + " 크기" + z; 
		}
	}
	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, 1, 0, 0},
			   	       dj = {0, 0, 1, -1};
	static List<Shark> list = new ArrayList<>();
	static int R, C, M;
	static int[][] map;
	static int ans;
	static int captureIdx=-1;
	
	static String getDir(int d) {
		if(d==1) return "상";
		else if(d==2) return "하";
		if(d==3) return "우";
		else return "좌";
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g1_17143_낚시왕.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		for(int i=0;i<=R;i++) {
			for(int j=0;j<=C;j++) map[i][j]=-1;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			list.add(new Shark(r, c, s, d, z));
			map[r][c] = list.size()-1;
		}
		
		//알고리즘
		fishing();
		
		//출력
		sb.append(ans);
		System.out.println(sb.toString());
		br.close();
	}
	
	static void fishing() {
		for(int j=1;j<=C;j++) {	
			capture(j);
			sharkMove();
			sharkUpdate();
		}
	}
	
	//낚시꾼이 j 세로줄의 상어를 잡는다.
	static void capture(int j) {
		captureIdx=-1;
		for(int i=1;i<=R;i++) {
			if(map[i][j] != -1) {
				Shark shark = list.get(map[i][j]);
	
				ans+=shark.z;
				captureIdx = map[i][j];
				list.remove(map[i][j]);
				map[i][j]=-1;
				
				return;
			}
		}
	}

	//상어 이동
	static void sharkMove() {
		Shark shark;
		int dir;
		int[] darr = new int[2];
		
		for(int i=0;i<list.size();i++) {
			shark = list.get(i);
			map[shark.r][shark.c]=-1;
			
			if(dj[shark.d-1]==0) { //상어 상하 이동
				dir = shark.d-1; darr[0]=0; darr[1]=1;
				
				for(int j=0;j<shark.s%(2*R-2);j++) {
					shark.r+=di[darr[dir]];
					if(shark.r==0 || shark.r==R+1) {
						dir = (dir+1)%2;
						shark.r+=2*di[darr[dir]];
					}
				}
			} else { //상어 좌우 이동
				dir=shark.d-3; darr[0]=2; darr[1]=3;
				
				for(int j=0;j<shark.s%(2*C-2);j++) {
					shark.c+=dj[darr[dir]];
					if(shark.c==0 || shark.c==C+1) {
						dir = (dir+1)%2;
						shark.c+=2*dj[darr[dir]];
					}
				}
			}
			
			//최종 정보 저장
			list.set(i, new Shark(shark.r, shark.c, shark.s, darr[dir]+1, shark.z));
			
		}
	}
	
	//상어가 서로를 먹은 이후  ==> 최종 map 상태
	static void sharkUpdate() {
		for(int i=0;i<list.size();i++) {
			Shark shark = list.get(i);
			if(map[shark.r][shark.c]==-1) { //빈 공간
				map[shark.r][shark.c] = i;
			} else { //상어가 있음
				Shark ori = list.get(map[shark.r][shark.c]);
				if(ori.z < shark.z) //새로온 상어가 더 큼
					list.set(map[shark.r][shark.c], shark);
				list.remove(i--);
			}
		}		
	}
}