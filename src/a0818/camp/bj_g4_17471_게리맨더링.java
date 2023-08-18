package a0818.camp;

import java.util.*;
import java.io.*;

public class bj_g4_17471_게리맨더링 {

	static final ArrayDeque<int[]> queue = new ArrayDeque<>();
	static int N, CNT, ANS, SUM;
	static int[][] map; //구역별 인접 정보
	static int[] people; //구역별 인구 수
	static boolean[] v;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_17471_게리맨더링.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//구역 수 입력
		N = Integer.parseInt(br.readLine());
		ANS=-1;
		//구역별 인구 수 입력
		people = new int[N+1]; v = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) people[i] = Integer.parseInt(st.nextToken());		
		
		//구역별 인접 정보 입력
		map = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int s=0;s<size;s++) {
				int j =Integer.parseInt(st.nextToken()); 
				map[i][j] = 1;
				queue.offerLast(new int[] {i, j});
			}
		}
		
		while(!queue.isEmpty()) {
			CNT=0;
			int[] arr = queue.pollFirst();
			int[] sumArr = new int[2];
			map[arr[0]][arr[1]]=0; map[arr[1]][arr[0]]=0;
			for(int i=1;i<=N;i++) {
				if(!v[i]) {
					if(CNT==2) break; //구역이 
					dfs();
					sumArr[CNT++] = SUM;
				}
			}
			map[arr[0]][arr[1]]=1; map[arr[1]][arr[0]]=1;
		}
		
	}
	
	static void dfs() {
		
	}

}
