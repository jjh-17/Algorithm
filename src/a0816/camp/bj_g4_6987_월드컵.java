package a0816.camp;

import java.util.*;
import java.io.*;

//해결 중
public class bj_g4_6987_월드컵 {

	static final int N=6;
	static final StringBuilder sb = new StringBuilder();
	static int[] W=new int[N], D=new int[N], L=new int[N], COMPARE = new int[N];
	static int ANS, SUM=0, CNT=0;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_6987_월드컵.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		for(int i=0;i<4;i++) {
			SUM=0;
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				W[j] = Integer.parseInt(st.nextToken()); SUM += W[j];
				D[j] = Integer.parseInt(st.nextToken());
				L[j] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(ANS).append(" ");
		}
		
		
//		dfs(0);
		System.out.println(CNT);
		
		br.close();
		System.out.println(sb.toString());
	}
	
	//각 나라의 승점을 분배.....?
	

}
