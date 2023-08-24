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
		int[] minEdge = new int[N]; //자신과 트리의 정점들 간 최소 간선 비용
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) 
				g[i][j] = sc.nextInt();
			minEdge[i] = Integer.MAX_VALUE; //최소값 갱신을 위해 큰 값으로 초기화
		}

		int result=0, cnt=0; //최소 신장 트리 비용, 
		minEdge[0] = 0; //0번 정점을 트리 구성의 시작으로 설정
		for(int i=0;i<N;i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE;
			
			//step1: 미방문(비트리) 정점 중 최소간선비용의 정점 선택
			for(int j=0;j<N;j++) {
				if(!v[j] && min>minEdge[j]) {
					minVertex = j;
					min = minEdge[j];
				}
			}
			
			//step2: 방문(트리) 정점에 추가
			v[minVertex] = true; //방문 처리
			result += min; //시장트리 비용 누적
			
			if(cnt++ == N-1) break; 
			
			//step3: 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선 비용 고려
			for(int j=0;j<N;j++) {
				//미방분, weight가 0이 아닐 때 minEdge 최신화
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
