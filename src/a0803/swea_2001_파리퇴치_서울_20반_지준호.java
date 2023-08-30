package a0803;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_2001_파리퇴치_서울_20반_지준호 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_2001_파리퇴치.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		int [][] map; // 각 row에는 col에 따른 누적값을 할당
		for(int t=1;t<=T;t++) {
			int max = 0; //파리 최대 죽인 횟수
			
			//N, M
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			//map 초기화
			map = new int[N][N+1];
			for(int i=0;i<N;i++) {
				map[i][0] = 0;
				st= new StringTokenizer(br.readLine());
				for(int j=1;j<=N;j++)
					map[i][j] = Integer.parseInt(st.nextToken()) + map[i][j-1];
			}
			
			//map 순회하며 최대값
			for(int i=0;i<=N-M;i++) {
				for(int j=1;j<=N-M+1;j++) {
					int temp = 0;
					for(int k=0;k<M;k++)
						temp += (map[i+k][j+M-1] - map[i+k][j-1]);
					max = Math.max(max, temp);
				}
			}
			
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
