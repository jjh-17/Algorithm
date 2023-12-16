package dfs_bfs;

import java.util.*;
import java.io.*;

public class bj_g5_1759_암호만들기 {

	static final StringBuilder sb = new StringBuilder();
	static int L, C;
	static char[] cand, map;
	static int num1, num2; //모음 개수, 자음 개수
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_1759_암호만들기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cand = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<C;i++) cand[i] = st.nextToken().charAt(0);
		
		
		map = new char[L];
		Arrays.sort(cand);
		num1=0; num2=0;
		dfs(0, 0);
		
		
		br.close();
		System.out.println(sb.toString());
	}
	
	static void dfs(int cnt, int start) {
		if(cnt==L) {
			if(num1>=1 && num2>=2) {
				for(char m : map) sb.append(m);
				sb.append("\n");
			}
			return;
		}
		
		for(int i=start;i<C;i++) {
			boolean flag = cand[i]=='a' || cand[i]=='e' ||cand[i]=='i' ||cand[i]=='o' ||cand[i]=='u';
			
			if(flag) ++num1;
			else ++num2;
			map[cnt]=cand[i];
			dfs(cnt+1, i+1);
			if(flag) --num1;
			else --num2;		
		}
		
	}

}
