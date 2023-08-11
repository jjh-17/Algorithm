package a0803.camp;


import java.util.*;
import java.io.*;

public class SubMain {

	static int N=4, /*R=3,*/ C=0;
	static int[] a={1 ,2, 3, 4}/*, b=new int[R];*/;
	static boolean[] visited = new boolean[N];

	//부분집합 생성
	static void subs(int cnt, String str) {
		if(cnt==N) {
			System.out.println(str);
			++C;
			return;
		}		
		
//		visited[cnt] = true;
		subs(cnt+1, str+a[cnt] + "\t");
//		visited[cnt] = false;
		subs(cnt+1, str + "X\t");
	}
	
	//바이너리 카운팅을 통한 부분집합 생성 ==> 가장 효율적!!!!
	static void subS2() {
		for(int i=0;i<(1<<N);i++) {
			for(int j=0;j<N;j++) {
				if((i&(1<<j))!=0)
					System.out.print(a[j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		C=0;
		subs(0, "");
		System.out.println(C);
		System.out.println();
		
		subS2();
	}

}
