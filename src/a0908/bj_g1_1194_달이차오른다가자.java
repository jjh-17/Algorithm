package a0908;

import java.util.*;
import java.io.*;

public class bj_g1_1194_달이차오른다가자 {

	//출력 변수
	static final StringBuilder sb = new StringBuilder();
	
	//미로 정보
	static int N, M;		//미로의 세로, 가로 크기
	static char[][] map;	//미로
	static boolean[][] V;	//미로 각 위치의 visited 여부(누적됨)
	static int si, sj;		//시작 위치	
	
	//이동 정보
	static final int[] di = {-1, 0, 1, 0},
					   dj = {0, 1, 0, -1};
	
	//민식이 정보
	static class Minsic{
		//위치 정보
		int i, j;
		
		//이동 횟수
		int cnt=0;
		
		//갖고 있는 key 정보: 0~5 == 'a'~'f'
		boolean[] keys;

		public Minsic(int i, int j, int cnt, boolean[] keys) {
			this.i=i; 		this.j=j; 
			this.cnt=cnt; 	this.keys=keys;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g1_1194_달이차오른다가자.txt"));
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M]; V = new boolean[N][M];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j]=='0') {
					si=i; sj=j;
				}
			}
		}
		
		//출력
		System.out.println(sb.toString());
		br.close();
	}

	
	static void bfs(int i, int j) {
		final ArrayDeque<Minsic> queue = new ArrayDeque<>();
		
		V[i][j] = true;
		queue.offerLast(new Minsic(i, j, 0, new boolean[6]));
		
		while(!queue.isEmpty()) {
			Minsic ms = queue.pollFirst();
			
			for(int d=0;d<4;d++) {
				int ni = ms.i+di[d];
				int nj = ms.j+dj[d];
				
				//범위를 벗어났거나 들른 곳이면 넘어감
				if(ni<0 || ni>=N || nj<0 || nj>=M || V[ni][nj]) continue;
				
				//출구에 도달
				if(map[ni][nj]=='1') {
					sb.append(ms.cnt+1);
					return;
				}
				
				//벽 도달
				if(map[ni][nj]=='#') {
					V[ni][nj] = true;
					continue;
				}
				
				//빈칸에 도달
				if(map[ni][nj]=='.') {
					V[ni][nj] = true;
					queue.offerLast(new Minsic(ni, nj, ms.cnt+1, ms.keys));
				}
				
				//열쇠에 도달
				if('a'<=map[ni][nj] && map[ni][nj]<='f') {
					V[ni][nj] = true;
					ms.keys[map[ni][nj]-'a'] = true;
					queue.offerLast(new Minsic(ni, nj, ms.cnt+1, ms.keys));
					continue;
				}
				
				//문에 도달
				
				
				
				
			}
		}
	}
}
