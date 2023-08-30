package a0714;

import java.util.*;
import java.io.*;

public class Solution_d1_2072_홀수만더하기 {
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("res/input_d1_2072.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int sum, n;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			sum = 0;
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0;i<10;i++) {
				n = Integer.parseInt(st.nextToken());
				if(n%2 == 1)
					sum+=n;
			}
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		
		br.close();
		System.out.print(sb);
		
	}
}
