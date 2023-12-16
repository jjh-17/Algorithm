package simulation;

import java.util.*;
import java.io.*;

public class bj_s1_16926_배열돌리기1 {

	static final StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int N, M, R;
			
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_16926_배열돌리기1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		rotate();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++)
				sb.append(map[i][j]).append(" ");
			sb.append("\n");
		}
		
		br.close();
		System.out.println(sb.toString());
	}
	
	public static void rotate() {
		int temp;
		
		//둘 중 작은 값을 2로 나눈 몫으로 돌린다. 즉, 사각형의 개수이다.
		for(int i=0;i<Integer.min(N, M)/2;i++) {
			for(int r=0;r<R%(2*(N-1-2*i + M-1-2*i));r++) {
				temp = map[i][i];
				
				//최상단
				for(int j=i;j<M-1-i;j++) map[i][j] = map[i][j+1];
				
				//최우측
				for(int j=i;j<N-1-i;j++) map[j][M-1-i] = map[j+1][M-1-i];
				
				//최하단
				for(int j=M-1-i;j>i;j--) map[N-1-i][j] = map[N-1-i][j-1];
				
				//최좌측
				for(int j=N-1-i;j>i;j--) map[j][i] = map[j-1][i];
				
				map[i+1][i] = temp;
			}
			
		}
	}
}
