package simulation;

import java.util.*;
import java.io.*;

public class bj_g4_3190_뱀 {
	
	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, 0, 1, 0};
	static final int[] dj = {0, 1, 0, -1};
	static int N, K, L;	// 보드의 크기, 사과의 개수, 방향 변환 횟수, 뱀의 이동 방향
	static final int BLANK=0, SNAKE=1, APPLE=2;
	static int[][] map;			
	static char[] dirInfo;		//
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_3190_뱀.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
//		1. 보드 크기
		N = Integer.parseInt(br.readLine());
		
//		2. 보드 초기화
		map = new int[N+1][N+1];
		map[1][1] = SNAKE;
		
//		3. 사과 위치 저장
		K = Integer.parseInt(br.readLine());
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = APPLE;
		}
		
//		4. 뱀의 회전 
		L = Integer.parseInt(br.readLine());
		dirInfo = new char[10001];
		for(int i=0;i<L;i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			dirInfo[time] = dir;
		}
		
//		====알고리즘====
//		1. 뱀이 차지하는 위치 정보
		final ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offerLast(new int[] {1, 1});
		
		int time=0, dir=1;
		while(true) {
			++time;
			int[] cur = queue.peekLast();
			int ni = cur[0] + di[dir];
			int nj = cur[1] + dj[dir];
			
//			신규 좌표가 벽이면 탈출
			if(ni<1 || ni>N || nj<1 || nj>N) {
				sb.append(time);
				break;
			}
			
			if(map[ni][nj]==BLANK) {		// 신규 좌표가 빈칸
				int[] tail = queue.pollFirst();
				map[tail[0]][tail[1]] = BLANK;
				
				queue.offerLast(new int[] {ni, nj});
				map[ni][nj] = SNAKE;
			} else if(map[ni][nj]==SNAKE) {	// 신규 좌표가 뱀
				sb.append(time);
				break;
			} else if(map[ni][nj]==APPLE) {	// 신규 좌표가 사과
				queue.offerLast(new int[] {ni, nj});
				map[ni][nj] = SNAKE;
			}
			
			if(time<=10000) {
				if(dirInfo[time]=='L') dir = (dir-1)==-1 ? 3 : dir-1;
				else if(dirInfo[time]=='D') dir = (dir+1)%4;
			}
		}
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}

}
