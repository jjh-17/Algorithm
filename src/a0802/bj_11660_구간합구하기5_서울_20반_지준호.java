package a0802;


import java.util.*;
import java.io.*;

public class bj_11660_구간합구하기5_서울_20반_지준호 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_11660_구간합구하기5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

		//각 좌표별 총합
		int[][] map = new int[N][N+1];
		int sum = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			sum=0;
			map[i][0] = 0;
			for(int j=1;j<=N;j++) {
				sum+=Integer.parseInt(st.nextToken());
				map[i][j] = sum;
			}
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
			
			sum=0;
			for(int j=x1-1;j<=x2-1;j++)
				sum += (map[j][y2] - map[j][y1-1]);
			sb.append(sum).append("\n");
		}

		System.out.print(sb.toString());
	}

}
