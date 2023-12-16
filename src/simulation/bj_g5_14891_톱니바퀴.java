package simulation;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g5_14891_톱니바퀴 {

	static final StringBuilder sb = new StringBuilder();
	static final List<Integer>[] state = new List[5];
	static int K;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_14891_톱니바퀴.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		for(int i=1;i<=4;i++) {
			String input = br.readLine();
			state[i] = new ArrayList<Integer>();
			for(int j=0;j<8;j++) state[i].add(input.charAt(j) - '0');
		}
	
		K = Integer.parseInt(br.readLine());
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			boolean dir = Integer.parseInt(st.nextToken())==1;
			
//			n번 톱니바퀴 회전
			int L = state[n].get(6);
			int R = state[n].get(2);
			if(dir) state[n].add(0, state[n].remove(7));
			else 	state[n].add(7, state[n].remove(0));
			
//			n번 톱니바퀴 우측 톱니바퀴 순차적으로 회전
			int tempN = n+1;
			boolean ndir = dir;
			while(tempN<=4) {
				if(R==state[tempN].get(6)) break;
				else {
					ndir = !ndir;
					R = state[tempN].get(2);
					if(ndir) state[tempN].add(0, state[tempN].remove(7));
					else	 state[tempN].add(7, state[tempN].remove(0));
					++tempN;
				}
			}
			
//			n번 톱니바퀴 좌측 톱니봐퀴를 순차적으로 회전
			tempN = n-1;
			ndir = dir;
			while(tempN>0) {
				if(state[tempN].get(2)==L) break;
				else {
					ndir = !ndir;
					L = state[tempN].get(6);
					if(ndir) state[tempN].add(0, state[tempN].remove(7));
					else	 state[tempN].add(7, state[tempN].remove(0));
					--tempN;
				}
			}
		}
		
		int sum=0;
		for(int i=1;i<=4;i++) sum += state[i].get(0)*(int)Math.pow(2, i-1);
		
//		출력
		sb.append(sum);
		System.out.println(sb.toString());
		br.close();
	}
}
