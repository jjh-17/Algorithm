package a0801.camp;


import java.util.*;
import java.io.*;

public class PermMain {

	static int N=4, R=2, C=0;
	static int[] a={1 ,2, 3, 4}, b=new int[R];
	static boolean[] visited = new boolean[N];
	static int v = 0;

	
	//중복 허용 순열 생성
	static void permR(int cnt) {
		if(cnt==R) {
			System.out.println(Arrays.toString(b));
			++C;
			return;
		}
		
		for(int i=0;i<N;i++) {
			b[cnt] = a[i];
			permR(cnt+1);
		}
	}
	
	//중복 불가 순열 생성
	static void perm(int cnt) {
		if(cnt==R) {
			System.out.println(Arrays.toString(b));
			++C;
			return;
		}		
		
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			b[cnt] = a[i];
			perm(cnt+1);
			visited[i] = false;
		}
	}
	
	//중복 불가 순열 생성2(구현 실패)
	static void perm2(int cnt) {
		if(cnt==R) {
			System.out.println(Arrays.toString(b));
			++C;
			return;
		}		
		
		for(int i=0;i<N;i++) {
			if((v & (1<<i))!=0) continue;
			v = v | (1<<i);
			b[cnt] = a[i];
			perm2(cnt+1);
			v = v & ~(1<<i);
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		C=0;
		perm(0);
		System.out.println(C);
		System.out.println();
		
		C=0;
		perm2(0);
		System.out.println(C);
		System.out.println();
		
		C=0;
		permR(0);
		System.out.println(C);
	}

}
