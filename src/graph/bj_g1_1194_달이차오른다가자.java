package graph;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g1_1194_달이차오른다가자 {

	//출력 변수
	static final StringBuilder sb = new StringBuilder();
	
	//민식이 정보
	static class Minsic{
		int i, j;		//좌표
		int cnt, key;	//이동 횟수, 현재 key 상태
		
		public Minsic(int i ,int j, int cnt, int key) {
			this.i=i; this.j=j; 
			this.cnt=cnt; this.key=key;
		}
	}
	
	//미로 정보
	static int N, M;				//미로의 세로, 가로 크기
	static char[][] map;			//미로 정보
	static int si, sj;				//시작 위치	
	
	//이동 정보
	static final int[] di = {-1, 0, 1, 0},	//4방 탐색
					   dj = {0, 1, 0, -1};
	static boolean[][][] v;					//각 열쇠를 이용한 방문 여부
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g1_1194_달이차오른다가자.txt"));
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M]; v = new boolean[N][M][64];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j]=='0') {
					si=i; sj=j;
				}
			}
		}
		
		bfs(si, sj);
		
		//출력
		System.out.println(sb.toString());
		br.close();
	}

	
	static void bfs(int i, int j) {
		final ArrayDeque<Minsic> queue = new ArrayDeque<>();
		
		v[i][j][0]=true;
		queue.offerLast(new Minsic(i, j, 0, 0));
		
		while(!queue.isEmpty()) {
			Minsic cur_Minsic = queue.pollFirst(); //현 좌표의 민식이 정보
			
			for(int d=0;d<4;d++) {
				int ni = cur_Minsic.i+di[d];
				int nj = cur_Minsic.j+dj[d];
				
				//범위를 벗어나거나 벽 도달 시 넘어감
				if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]=='#'
						|| v[ni][nj][cur_Minsic.key]) continue;
				
				//출구에 도달
				if(map[ni][nj]=='1') {
					sb.append(cur_Minsic.cnt+1);
					return;
				}
				
				//빈 칸에 도달
				if(map[ni][nj]=='.' || map[ni][nj]=='0') {
					v[ni][nj][cur_Minsic.key]=true;
					queue.offerLast(new Minsic(ni, nj, cur_Minsic.cnt+1, cur_Minsic.key));
					continue;
				}
				
				//열쇠에 도달
				if('a'<=map[ni][nj] && map[ni][nj]<='f') {
					v[ni][nj][cur_Minsic.key]=true;
					queue.offerLast(new Minsic(ni, nj, cur_Minsic.cnt+1, 
									cur_Minsic.key | (1<<(map[ni][nj]-'a'))));
					continue; 
				}
				
				//문에 도달
				if('A'<=map[ni][nj] && map[ni][nj]<='F') {
					if((cur_Minsic.key&(1<<(map[ni][nj]-'A')))
							== (int)Math.pow(2, map[ni][nj]-'A')) {
						v[ni][nj][cur_Minsic.key]=true;
						queue.offerLast(new Minsic(ni, nj, cur_Minsic.cnt+1, cur_Minsic.key));
					}
					
				}
			}
		}
		sb.append(-1);
	}
}
