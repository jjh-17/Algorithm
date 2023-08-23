package a0823.camp;

import java.util.*;
import java.io.*;

//정점의 수가 간선에 비해 적을 때 유용
public class PrimMain {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] g = new int[N][N];
		boolean[] v = new boolean[N];
		int[] minEdge = new int[N]; //Prim의 weight
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) 
				g[i][j] = sc.nextInt();
			minEdge[i] = Integer.MAX_VALUE;
		}

		int result=0, cnt=0;
		minEdge[0] = 0; //0번 정점에서 먼저 시작하도록 0으로 설정
		for(int i=0;i<N;i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE;
			
			for(int j=0;j<N;j++) {
				if(!v[j] && min>minEdge[j]) {
					minVertex = j;
					min = minEdge[j];
				}
			}
			v[minVertex] = true;
			result += min;
			
			if(cnt++ == N-1) break;
			
			for(int j=0;j<N;j++) {
				if(!v[j] && g[minVertex][j]!=0 && minEdge[j]>g[minVertex][j]) {
					minEdge[j] = g[minVertex][j];
				}
			}
		}
		
		sc.close();
		System.out.println(result);
	}
}

/*
5
0 5 10 8 7 
5 0 5 3 6 
10 5 0 1 3 
8 3 1 0 1 
7 6 3 1 0
 */
