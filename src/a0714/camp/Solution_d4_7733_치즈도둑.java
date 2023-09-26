package a0714.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solution_d4_7733_치즈도둑 {
	
	static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
	static int[][] map;
	static boolean[][] visited;
	static int N;
	
	public static void dfs(int x, int y) {
		int newX, newY;
		
		visited[x][y] = true;
		
		for(int i=0;i<4;i++) {
			newX = x + dx[i];
			newY = y + dy[i];
			
			if(newX>=0 && newX<N && newY>=0 && newY<N) {
				if(!visited[newX][newY] && map[newX][newY]!=0)
					dfs(newX, newY);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input_d4_7733.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;

		int answer, temp, T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			answer = 0;
			
			//치즈 배열
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0;j<N;j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			
			//100일간 갉아 먹기
			for(int d=0;d<=100;d++) {
				temp = 0; //덩어리의 수
				visited = new boolean[N][N]; //visited 초기화
				
				//해당 일과 동일한 맛의 치즈 먹음 ==> 누적됨
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(map[i][j] == d) {
							map[i][j] = 0;
							visited[i][j] = true;
						}
					}
				}
				
				//순회하며 덩어리의 수 계산
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(!visited[i][j] && map[i][j] != 0) {
							temp += 1;
							dfs(i, j);
						}
					}
				}
				answer = Math.max(answer, temp);
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

}
