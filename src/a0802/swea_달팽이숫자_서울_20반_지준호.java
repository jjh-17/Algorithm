package a0802;

import java.io.*;

public class swea_달팽이숫자_서울_20반_지준호 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_swea_달팽이숫자.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int N; //정사각형 크기
		int cU, cR, cD, cL; //상, 우, 하, 좌 경계값
		char dir; //달팽이 이동 방향
		int[][] map;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cU=0; cR=0; cD=0; cL=0;
			dir = 'R'; //최초 이동 방향 우측
			int n = 1;
			while(n<=N*N) {
				switch(dir) {
				case 'U':
					for(int i=N-1-cD;i>=cU;i--)
						map[i][cL] = n++;
					dir = 'R'; cL++;
					break;
				case 'R':
					for(int i=cL;i<=N-1-cR;i++)
						map[cU][i] = n++;
					dir = 'D'; cU++;
					break;
				case 'D':
					for(int i=cU;i<=N-1-cD;i++)
						map[i][N-1-cR] = n++;
					dir = 'L'; cR++;
					break;
				case 'L':
					for(int i=N-1-cR;i>=cL;i--)
						map[N-1-cD][i] = n++;
					dir = 'U'; cD++;
					break;
				}
			}
			
			sb.append("#").append(t).append("\n");
			for(int[] ma : map) {
				for(int m : ma)
					sb.append(m).append(" ");
				sb.append("\n");
			}
		}
		
		br.close();
		System.out.print(sb.toString());
	}

}
