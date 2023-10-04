package a1004.camp;

import java.util.*;
import java.io.*;

public class swea_d4_5643_키순서 {

	static final StringBuilder sb=  new StringBuilder();
	static int N, M;
	static int map[][];		//map[a][b] : a와 b 중 더 큰 학생
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_5643_키순서.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
			
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
//			입력
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			map = new int[N+1][N+1]; 
					
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = b; map[b][a] = b;
			}
					
//			플로이드 워샬
			for(int k=1;k<=N;k++) {			// 학생 k
				for(int i=1;i<=N;i++) {		// 학생 i
					if(k==i) continue;
					for(int j=1;j<=N;j++) {	// 학생 j
						if(k==j || i==j) continue;
						if(map[i][k]==k && map[k][j]==j) {
							map[i][j]=j; map[j][i]=j;
						}
					}
				}
			}
					
			int ans = 0;
			for(int i=1;i<=N;i++) {
				boolean flag = true;
				
				for(int j=1;j<=N;j++) {
					if(i==j) continue;
					if(map[i][j]==0) {
						flag = false;
						break;
					}
				}
				
				if(flag) ++ans;
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
				
//		출력
		System.out.println(sb.toString());
		br.close();
	}
}
