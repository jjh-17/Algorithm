package a0801.camp;


import java.util.*;
import java.io.*;

public class CombMain {

	static int N=4, R=2, C=0;
	static int[] a={1 ,2, 3, 4}, b=new int[R];
	static boolean[] visited = new boolean[N];

	
	//중복 허용 순열 생성
	static void combR(int cnt, int start) {
		if(cnt==R) {
			System.out.println(Arrays.toString(b));
			++C;
			return;
		}
		
		for(int i=start;i<N;i++) {
			b[cnt] = a[i];
			combR(cnt+1, i);
		}
	}
	
	//중복 불가 순열 생성
	static void comb(int cnt, int start) {
		if(cnt==R) {
			System.out.println(Arrays.toString(b));
			++C;
			return;
		}		
		
		for(int i=start;i<N;i++) {
//			if(visited[i]) continue;
//			visited[i] = true;
			b[cnt] = a[i];
			comb(cnt+1, i+1);
//			visited[i] = false;
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		C=0;
		comb(0, 0);
		System.out.println(C);
		System.out.println();
		
		C=0;
		combR(0, 0);
		System.out.println(C);
	}

}
