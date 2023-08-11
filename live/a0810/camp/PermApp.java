package a0810.camp;

import java.util.*;
import java.io.*;

public class PermApp {

	static int N, R, C=0, F=0;
	static boolean[] v;
	static int[] a, b;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken());
		a = new int[N]; b = new int[R]; v = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) a[i] = Integer.parseInt(st.nextToken());
		
		
		C=0;
		System.out.println("[재귀 순열]");
		permRec(0);
		System.out.println(C + "\n");
		
		C=0; F=0;
		System.out.println("[비트마스크 순열, Static Flag]");
		permBit1(0);
		System.out.println(C + "\n");
	
		C=0;
		System.out.println("[비트마스크 순열 - 매게변수 Flag]");
		permBit2(0, 0);
		System.out.println(C + "\n");
		
		
		C=0;
		Arrays.sort(a);
		System.out.println("[NextPermutation]");
		do {
			++C;
			System.out.println(Arrays.toString(a));
		} while(permNext(a));
		System.out.println(C);
	}
	
	//순열 생성 - 재귀
	public static void permRec(int cnt) {
		if(cnt==R) {
			++C;
			System.out.println(Arrays.toString(b));
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(v[i]) continue;
			
			v[i] = true;
			b[cnt] = a[i];
			permRec(cnt+1);
			v[i] = false;
		}
	}
	
	//순열 생성 - 비트 마스킹1 - static flag
	public static void permBit1(int cnt) {
		if(cnt==R) {
			++C;
			System.out.println(Arrays.toString(b));
			return;
		}
		
		for(int i=0;i<N;i++) {
			//flag의 i번째 비트가 1이면 이미 들른 곳
			if((F&(1<<i))!=0) continue;
			b[cnt] = a[i];
			F = F|(1<<i);
			permBit1(cnt+1);
			F = F&(~(1<<i));
		}
	}
	
	
	//순열 생성 - 비트 마스킹2 - 파라미터 flag
	public static void permBit2(int cnt, int flag) {
		if(cnt==R) {
			++C;
			System.out.println(Arrays.toString(b));
			return;
		}
		
		for(int i=0;i<N;i++) {
			//flag의 i번째 비트가 1이면 이미 들른 곳
			if((flag&(1<<i))!=0) continue;
			b[cnt] = a[i];
			permBit2(cnt+1, flag|(1<<i));
		}
	}
	
	//순열 생성 - NextPermutation - 
	public static boolean permNext(int[] p) { //다음 순열을 원하는 기존 순열
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
