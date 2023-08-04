package a0802.camp;

import java.util.*;
import java.io.*;

public class DiceTest {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuffer sb = new StringBuffer();
	static int N, arr[];
	static boolean[] visited;
	static int count = 0;
	
	//동일 숫자 포함 순열 - 재귀
	public static void permD(int cnt) {
		if(cnt==N) {
			System.out.println(Arrays.toString(arr));
			count++;
			return;
		}
		
		for(int i=1;i<=6;i++) {
			arr[cnt] = i;
			permD(cnt+1);
		}
	}
		
	//동일 숫자 미포함 순열 - 재귀
	public static void perm(int cnt) {
		if(cnt==N) {
			System.out.println(Arrays.toString(arr));
			count++;
			return;
		}
		
		for(int i=1;i<=6;i++) {
			if(visited[i])
				continue;
			visited[i] = true;
			arr[cnt] = i;
			perm(cnt+1);
			visited[i] = false;
		}
	}
		
	//동일 숫자 포함 조합 - 재귀
	public static void combD(int cnt, int start) {
		if(cnt==N) {
			System.out.println(Arrays.toString(arr));
			count++;
			return;
		}
		
		for(int i=start;i<=6;i++) {
			arr[cnt] = i;
			combD(cnt+1, i);
		}
	}
		
	//동일 숫자 미포함 조합 - 재귀
	public static void comb(int cnt, int start) {
		if(cnt==N) {
			System.out.println(Arrays.toString(arr));
			count++;
			return;
		}
		
		for(int i=start;i<=6;i++) {
			if(visited[i])
				continue;
			visited[i] = true;
			arr[cnt] = i;
			comb(cnt+1, i);
			visited[i] = false;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		N = Integer.parseInt(br.readLine()); //주사위 던지는 횟수
		int M = Integer.parseInt(br.readLine()); //주사위 던지기 모드
		arr = new int[N];
		visited = new boolean[7];
		
		switch(M) {
		case 1: //중복 순열
			permD(0);
			break;
		case 2: //순열
			perm(0);
			break;
		case 3: //중복 조합
			combD(0, 1);
			break;
		case 4: //조합
			comb(0, 1);
			break;
		}
		System.out.println("개수: " + count);
	}

}
