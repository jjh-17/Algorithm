package dfs_bfs;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g5_2138_전구와스위치 {
	
	static int N;
	static boolean[] input, target;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_2138_전구와스위치.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		입력
		N = Integer.parseInt(br.readLine());
		input = new boolean[N+1];
		target = new boolean[N+1];
		
		String str = br.readLine();
		for(int i=1;i<=N;i++)	input[i] = str.charAt(i-1) == '1';
		
		str = br.readLine();
		for(int i=1;i<=N;i++)	target[i] = str.charAt(i-1) == '1';
		
//		알고리즘
		int ans1 = solution(getInputCopy(true));
		int ans2 = solution(getInputCopy(false));
		int ans;
		
		if(ans1==-1)		ans = ans2;
		else if(ans2==-1)	ans = ans1;
		else				ans = Math.min(ans1, ans2);
		
		
//		츨력
		System.out.println(ans);
		br.close();
	}
	
//	i-1/i/i+1이 아닌, i/i+1/i+2가 바뀌는 것으로 생각
	static int solution(boolean[] input) {
		int cnt = 0;
		
//		반복
		for(int i=0;i<=N-2;i++) {
			if(input[i]!=target[i]) {
				++cnt;
				input[i] = !input[i];
				input[i+1] = !input[i+1];
				input[i+2] = !input[i+2];
			}
		}
		
//		N-1번째 스위치
		if(input[N-1]!=target[N-1]) {
			++cnt;
			input[N-1] = !input[N-1];
			input[N] = !input[N];
		}
		
		return input[N]==target[N] ? cnt : -1;
	}

//	input의 Copy 반환
	static boolean[] getInputCopy(boolean flag) {
		boolean[] ninput = new boolean[N+1];
		ninput[0] = flag;
		for(int i=1;i<=N;i++)
			ninput[i] = input[i];
		return ninput;
	}
}