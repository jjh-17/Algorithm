package dp;

import java.util.*;
import java.io.*;

public class bj_s1_1149_RGB거리 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int costR[], costG[], costB[];	//RGB로 칠하는 비용
	static int dpR[], dpG[], dpB[];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_1149_RGB거리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		N = Integer.parseInt(br.readLine());
		
		costR = new int[N+1]; costG = new int[N+1]; costB = new int[N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			costR[i] = Integer.parseInt(st.nextToken());
			costG[i] = Integer.parseInt(st.nextToken());
			costB[i] = Integer.parseInt(st.nextToken());
		}
		
		//알고리즘
		dpR = new int[N+1]; dpG = new int[N+1]; dpB = new int[N+1];
		for(int i=1;i<=N;i++) {
			dpR[i] = Integer.min(dpG[i-1], dpB[i-1]) + costR[i];
			dpG[i] = Integer.min(dpR[i-1], dpB[i-1]) + costG[i];
			dpB[i] = Integer.min(dpR[i-1], dpG[i-1]) + costB[i];
		}
		
		//출력
		sb.append(Integer.min(dpR[N], Integer.min(dpG[N], dpB[N])));
		System.out.println(sb.toString());
		br.close();
	}
}
