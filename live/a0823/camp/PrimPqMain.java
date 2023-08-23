package a0823.camp;

import java.util.*;
import java.io.*;

public class PrimPqMain {
	
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
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(o1, o2) -> Integer.compare(o1[1], o2[1]));
		minEdge[0] = 0; //0번 정점에서 먼저 시작하도록 0으로 설정
		pq.offer(new int[] {0, minEdge[0]}); //정점, 가중치 추가
		
		System.out.println(Arrays.toString(minEdge));
		System.out.println();
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int minVertex = cur[0];
			int min = cur[1];
			
			if(v[minVertex]) continue;
			
			v[minVertex] = true;
			result += min;
			
			System.out.println("v = " + Arrays.toString(v));
			System.out.println("minVertex = " + minVertex);
			System.out.println("min = " + min);
			System.out.println("result = " + result);
			
			if(cnt++ == N-1) break;
			
			for(int j=0;j<N;j++) {
				if(!v[j] && g[minVertex][j]!=0 && minEdge[j]>g[minVertex][j]) {
					minEdge[j] = g[minVertex][j];
					pq.offer(new int[] {j, minEdge[j]});
				}
			}
			
			System.out.println("minEdge = " + Arrays.toString(minEdge));
			System.out.println("==============================================");
		}
		System.out.println(result);
		sc.close();
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
