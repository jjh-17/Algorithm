package a0808.camp;


import java.util.*;
import java.io.*;

public class swea_d4_1868_파핑파핑지회찾기 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] di = {-1, -1, 0, 1, 1, 1, 0, -1}, dj = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, count;
	static char[][] map;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_1868_파핑파핑지회찾기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			//N 및 지도 초기화
			N = Integer.parseInt(br.readLine());
			map = new char[N][N]; count = 0;
			for(int i=0;i<N;i++) map[i] = br.readLine().toCharArray();
			
			//미개척 구역 중, 주변에 지뢰가 없는 경우 탐지
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j] == '.') { //현재 위치가 미개척 구역일 때
						dfs(i, j);
						++count;
					}
				}
			}
			
			//미개척 구역 중 주변에 폭탄이 있는 경우
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j] == '.') { //현재 위치가 미개척 구역일 때
						++count;
					}
				}
			}
			sb.append(count).append("\n");
		}
		
		br.close();
		System.out.println(sb.toString());
	}

	
	//0에서부터 퍼짐
	public static void dfs(int i, int j) {
		//현재 위치값이 0이 아님
		if(check8(i, j) != 0) return;
		
		map[i][j] = 'C';
		
		//현 위치 기준 8방 탐색, 각 위치에서 값이 0이면 해당 방향으로 dfs
		for(int d=0;d<8;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(0<=ni&&ni<N && 0<=nj&&nj<N && map[ni][nj]=='.') {
				dfs(ni, nj);
			}	
		}
	}
	
	//좌표 i, j에 대하여 8방 탐색 진행 결과 탐지한 지뢰의 수 반환
	public static int check8(int i, int j) {
		int cnt = 0;
		
		for(int d=0;d<8;d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(0<=ni&&ni<N && 0<=nj&&nj<N && map[ni][nj]=='*')
				++cnt;
		}
		
		return cnt;
	}
	
	public static void show() {
		for(char[] m : map)
			System.out.println(Arrays.toString(m));
		System.out.println();
	}
}
