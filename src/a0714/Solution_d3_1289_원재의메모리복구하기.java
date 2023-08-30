package a0714;

import java.util.*;
import java.io.*;

public class Solution_d3_1289_원재의메모리복구하기 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d3_1289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String cb = "0", s;
		int answer;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				s = st.nextToken();
				System.out.println(s);
				if(s == cb)
					continue;
				
				answer += 1;
				if(s == "0")
					s = "1";
				else
					s = "0";
			}
			System.out.println();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);

	}

}
