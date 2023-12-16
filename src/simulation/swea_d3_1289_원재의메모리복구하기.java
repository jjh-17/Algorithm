package simulation;


import java.util.*;
import java.io.*;

public class swea_d3_1289_원재의메모리복구하기 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_d3_1289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		char[] target;
		char state = '0';
		int count = 0;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			count = 0;
			state = '0';
			sb.append("#").append(t).append(" ");
			
			target = br.readLine().toCharArray();
			for(char c : target) {
				//동일한 bit일 경우 넘어감
				if(state==c)
					continue;
				
				//다른 bit
				count+=1;
				state = state=='0' ? '1' : '0';
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}

}
