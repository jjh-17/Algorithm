package a0824.camp;

import java.util.*;
import java.io.*;

//정점의 수가 간선에 비해 적을 때 유용
//특정 정점에서 다른 정점까지의 이동 경로 중 최소 비용
public class DijkstraMain {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] g = new int[N][N];
		boolean[] v = new boolean[N];
		int[] dist = new int[N]; //다익스트라 d[]
		
		int start = sc.nextInt(), end=sc.nextInt(); //시작 정점, 끝 정점
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) 
				g[i][j] = sc.nextInt();
			dist[i] = Integer.MAX_VALUE; //최소값 갱신을 위해 큰 값으로 초기화
		}

		int cnt=0; //최소 신장 트리 비용, 
		dist[start] = 0; //0번 정점을 트리 구성의 시작으로 설정
		for(int i=0;i<N;i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE;
			
			//step1: 미방문(비트리) 정점 중 최소간선비용의 정점 선택
			for(int j=0;j<N;j++) {
				if(!v[j] && min>dist[j]) {
					minVertex = j;
					min = dist[j];
				}
			}
			
			if(minVertex==-1) break;
			
			//step2: 방문(트리) 정점에 추가
			v[minVertex] = true; //방문 처리
			
			System.out.println(Arrays.toString(v));
			System.out.println("minVertex = " + minVertex);
			System.out.println("min = " + min);
			
			if(minVertex == end) break;
			
			//step3: 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선 비용 고려
			for(int j=0;j<N;j++) {
				//미방분, weight가 0이 아닐 때 dist 최신화
				if(!v[j] && g[minVertex][j]!=0 && dist[j]>(min+g[minVertex][j])) {
					dist[j] = min + g[minVertex][j];
				}
			}
			
			System.out.println(Arrays.toString(dist));
			System.out.println("===========================");
		}		
		sc.close();
		System.out.println(dist[end]);
	}
}

/*
5
0 4
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0

output==> 8
 */
