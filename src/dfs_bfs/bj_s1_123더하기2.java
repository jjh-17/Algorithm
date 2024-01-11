package dfs_bfs;

import java.util.*;
import java.io.*;

public class bj_s1_123더하기2 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] arr = new int[4];
	static int n, k, count;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_123더하기2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
//		알고리즘
		count=0;
		flag = false;
		dfs(0, "");
		if(count<k) sb.append(-1);
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
	
	static void dfs(int sum, String str) {
		if(flag) return;
			
		for(int i=1;i<=3;i++) {
			if(sum+i < n) {
				++arr[i];
				dfs(sum+i, str+i);
				--arr[i];
			} else if(sum+i == n) {
				++count;
				if(count==k) {
					flag = true;
					for(int j=0;j<str.length();j++) sb.append(str.charAt(j)).append("+");
					sb.append(i);
					return;
				}
			}
		}
	}

}
