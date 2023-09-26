package a0808.camp;

import java.util.*;
import java.io.*;

public class swea_d4_1233_사칙연산유효성검사 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static char[] map;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_1233_사칙연산유효성검사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 10;
		for(int t=1;t<=T;t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new char[N];
			int answer = 1;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				int node = Integer.parseInt(st.nextToken());
				map[node-1] = st.nextToken().charAt(0);
			}
			
			boolean flag = false; //true: 연산자만 받음, false: 숫자만 받음
			for(int i=N-1;i>=0;i--) {
				if(!flag && (map[i]=='+' || map[i]=='-' || map[i]=='*' || map[i]=='/')) { //숫자만 받다가 연산자가 온 경우, 연산자만 받도록 수정
					flag = true;
				} else if(flag && (map[i]!='+' && map[i]!='-' && map[i]!='*' && map[i]!='/')) { //연산자만 받아야 하는데 숫자를 받은 경우
					answer = 0;
					break;
				}
			}
			sb.append(answer).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}

}
