package a1010.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d9_2383_점심식사시간 {

	static final StringBuilder sb = new StringBuilder();
	static int N, ANS;
	static final int P=1;
	static final List<int[]> people = new ArrayList<>();	// 사람 위치 정보
	static final List<int[]> stairs = new ArrayList<>();	// 계단 위치 정보
	static int[][] map, dist;								// 지도 정보, 각 사람과 계단 사이의 거리
	static int[] dest;										// 사람 별 선택한 계단과의 거리
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_2383_점심식사시간.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
//		T=1;
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			people.clear(); stairs.clear();
			
//			입력
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
//			지도 정보 입력 및 사람, 계단의 위치 정보 저장
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) 	  people.add(new int[] {i, j});
					else if(map[i][j]>=2) stairs.add(new int[] {i, j});
				}
			}
			
//			모든 사람과 계단 사이의 거리 저장
			dist = new int[people.size()][2];
			for(int i=0;i<people.size();i++) {
				int[] p = people.get(i);
				for(int j=0;j<2;j++) {
					int[] s = stairs.get(j);
					dist[i][j] = Math.abs(p[0]-s[0]) + Math.abs(p[1]-s[1]);
				}		
			}
			
//			알고리즘
			dest = new int[people.size()];
			ANS = Integer.MAX_VALUE;
			permR(0);
			
//			출력
			sb.append(ANS).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void permR(int cnt) {
		if(cnt==people.size()) {
			move();
			return;
		}
		
		for(int i=0;i<2;i++) {
			dest[cnt]=i;
			permR(cnt+1);
		}
	}
	
//	현 dest를 기점으로 이동 개시
	static void move() {
		int time=0, P = people.size();					// 걸린 시간
		boolean complete = false;						// 모든 사람의 이동 완료 여부
		final int[] restD = new int[P];					// 사람 별 계단 까지 남은 거리
		final List<Integer>[] waiting = new List[2];	// 계단 별 각 사람들의 남은 계단 이용 거리
		
		for(int i=0;i<P;i++) restD[i] = dist[i][dest[i]];
		for(int i=0;i<2;i++) waiting[i] = new ArrayList<Integer>();
		
		while(!complete) {
//			1. 모든 사람들의 이동 여부를 true로 설정 및 시간 증가
			complete = true;
			++time;
			
//			2. 아직 이동이 끝나지 않은 사람이 있다면 이동 개시
			for(int p=0;p<P;p++) {
				if(restD[p]>=0) {
					--restD[p];
					complete = false;
					if(restD[p]+1==0) {
						int[] stair = stairs.get(dest[p]);
						waiting[dest[p]].add(map[stair[0]][stair[1]]);
					}
				}
			}
			
//			3. 계단에 내려가기가 끝나지 않은 사람이 있다면 내려가기 개시
			for(int i=0;i<2;i++) {
				for(int s=0;s<Integer.min(waiting[i].size(), 3);s++) {
					if(waiting[i].get(s)==0) {
						waiting[i].remove(s);
						--s;
					} else {
						waiting[i].set(s, waiting[i].get(s)-1);
						complete = false;
					}
				}
			}
		}
		
		ANS = Integer.min(ANS, time);
	}
}