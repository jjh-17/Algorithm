package a0825.camp;


import java.util.*;
import java.io.*;

public class bj_g1_17143_낚시왕 {

	static class Shark implements Comparable<Shark>{
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r; //행
			this.c = c; //열
			this.s = s; //속력
			this.d = d; //이동 방향
			this.z = z; //크기
		}
		
		@Override
		public int compareTo(Shark o) {
			return Integer.compare(this.r, o.r);
		}
	}
	static final StringBuilder sb = new StringBuilder();
	static final PriorityQueue<Shark> pq = new PriorityQueue<>();
	static final List<Shark> list = new ArrayList<>();
	static final int[] di = {-1, -1, 1, 0, 0},
					   dj = {-1, 0, 0, 1, -1};
	static int R, C, M;
	static int[][] map;
	
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
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			list.add(new Shark(r, c, s, d, z));
		}
	}

	
	//상어 위치 갱신
	static void sharkMove() {
		//각 상어 이동
		for(int i=0;i<list.size();i++) {
			Shark shark = list.get(i); //상어 정보
			int ni = shark.r, nj = shark.c; //상어 위치 정보
			int[] darr = new int[2];
			int stand, cidx;
			
			if(shark.d==1 || shark.d==2) {
				darr[0]=1; darr[1]=2;
				stand = R;
				cidx = shark.d-1;
			} else {
				darr[0]=3; darr[1]=4;
				stand = C;
				cidx = shark.d-3;
			}
			
			for(int j=0;j<shark.s%(2*stand-2);j++) {
				ni += di[cidx]; nj += dj[cidx];
				if(shark.r<1 || shark.r>R || shark.c<1 || shark.c>C) {
					cidx = (cidx+1)%2;
					ni += 2*di[cidx]; nj += 2*dj[cidx];
				}
			}
			
			
		}
	}
	
	//상어의 최종 상태 갱신
	static void sharkEat() {
		
	}
}
