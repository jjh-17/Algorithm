package a0801;

import java.util.*;
import java.io.*;

//발표
public class swea_2805_농작물수확하기_서울_20반_지준호 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_2805_농작물수확하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int answer; //총 수익량
		char[][] map; //농장 수익 정보
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			answer = 0;
			int N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for(int i=0;i<N;i++) 
				map[i] = br.readLine().toCharArray();
			
			//가운데 세로줄 제외, 양 날개 수확량 합산
			int half = N/2;
			for(int i=0;i<half;i++) {
				for(int j=-i;j<=i;j++) {
					answer += (map[half+j][i] - '0');
					answer += (map[half+j][N-1-i] - '0');
				}
			}
			
			//가운데 세로줄 수확량 합산
			for(int i=0;i<N;i++)
				answer += (map[i][half] - '0');
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
