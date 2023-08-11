package a0810.camp;

import java.util.*;
import java.io.*;

public class CombApp {

	static int N, R, C=0, F=0;
	static boolean[] v;
	static int[] a, b;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken());
		a = new int[N]; b = new int[N]; v = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) a[i] = Integer.parseInt(st.nextToken());	
		
		C=0;
		System.out.println("[NextCombination]");
		while(++C<=R) b[N-C]=1;
		
		C=0;
		do {
			++C;
			for(int i=0;i<N;i++) {
				if(b[i]==0) continue;
				System.out.print(a[i] + "\t|");
			}
			System.out.println();
		} while(combNext(b));
		
		System.out.println(C);
	}
	
	//순열 생성 - NextCombination
	public static boolean combNext(int[] p) { //다음 순열을 원하는 기존 순열
		//1. 맨 뒤부터 탐색하며 꼭대기 찾기
		int N = p.length, i = N-1;
		while(i>0 && p[i-1]>=p[i]) --i;
		if(i==0) return false; //현재 순열이 가장 큰 순열
		
		//2. 꼭대기 직전 위치(i-1)에 교환할 한단계 큰 수 뒤쪽부터 찾기
		int j = N-1;
		while(p[i-1]>=p[j]) --j;
		
		
		//3. 꼭대기 직전(i-1)위치의 수와 한단계 큰 수 교환
		swap(p, i-1, j);
		
		
		//4. 꼭대기 자리부터 맨 뒤까지의 수를 오름차순 정렬
		int k=N-1;
		while(i<k) swap(p, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] p, int a, int b) {
		int temp = p[a];
		p[a] = p[b];
		p[b] = temp;
	}
	
}
