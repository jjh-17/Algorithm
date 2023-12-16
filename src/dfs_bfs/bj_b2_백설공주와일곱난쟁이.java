package dfs_bfs;

import java.util.*;
import java.io.*;

public class bj_b2_백설공주와일곱난쟁이 {

	static final StringBuilder sb = new StringBuilder();
	static final int TARGET_SUM = 100, N=9, M=7; //목표 합, 전체 난쟁이 수, 목표 난쟁이 수
	static int sum=0; //현재 합
	static int[] arr = new int[N], targetArr = new int[M]; //전체 난쟁이 수, 7명의 난쟁이 수
	static boolean flag = false; //조합문 탈출 flag
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_b2_백설공주와일곱난쟁이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		//9명 난쟁이의 수 입력
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(br.readLine());
		
		//조합 수행
		comb(0, 0);
		
		System.out.println(sb.toString());
	}
	
	
	//9명 중 7명을 뽑는 경우의 수
	public static void comb(int start, int cnt) {
		if(flag) return;
		
		if(cnt==M) {
			setSum();
			if(sum==TARGET_SUM) {
				for(int t : targetArr) sb.append(t).append("\n");
				flag = true;
			}
			return;
		}
		
		for(int i=start;i<N;i++) {
			targetArr[cnt] = arr[i];
			comb(i+1, cnt+1);
		}
	}
	
	
	// 목표 난쟁이 수
	public static void setSum() {
		sum=0;
		for(int t : targetArr) sum += t;
	}

}
