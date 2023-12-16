package simulation;

import java.util.*;
import java.io.*;

public class swea_d3_10580_전봇대 {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, CNT;
	static int[][] line;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_10580_전봇대.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			
			//초기화
			N = Integer.parseInt(br.readLine());
			line = new int[N][2]; CNT = 0;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				line[i][0] = Integer.parseInt(st.nextToken());
				line[i][1] = Integer.parseInt(st.nextToken());
			}
		
			//순회
			for(int i=0;i<N;i++) checkCross(i);
			sb.append(CNT).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}

	//i번째 줄과  i이후 줄들의 교차 여부 확인
	public static void checkCross(int start) {	
		int[] L1 = line[start];
		for(int i=start+1;i<N;i++) {
			int[] L2 = line[i];
			if((L1[0]<L2[0] && L1[1]>L2[1]) || ((L1[0]>L2[0] && L1[1]<L2[1])))
				++CNT;
		}
	}
	
}
