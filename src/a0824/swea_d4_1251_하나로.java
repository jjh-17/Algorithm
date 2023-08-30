package a0824;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d4_1251_하나로 {

	static class Vertex {
		int vertex;
		double weight;
		
		public Vertex(int vertex, double weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	static final StringBuilder sb = new StringBuilder();
	static final PriorityQueue<Vertex> pq = new PriorityQueue<>((o1, o2)->Double.compare(o1.weight, o2.weight));
	
	static int N;
	static double[][] G;
	static int[] X, Y;
	static double[] dist;
	static boolean[] v;
	static double E; //환경 부담 세율
	static double result;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d4_1251_하나로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1, st2;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//입력
			N = Integer.parseInt(br.readLine());
			
			//공간 할당
			G = new double[N][N];
			X = new int[N]; Y = new int[N];
			dist = new double[N];
			v = new boolean[N];
			
			//입력
			st1 = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				X[i] = Integer.parseInt(st1.nextToken());
				Y[i] = Integer.parseInt(st2.nextToken());
				dist[i] = Double.MAX_VALUE;
			}
			E = Double.parseDouble(br.readLine());
			
			//그래프 초기화
			for(int i=0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					double e = E * (Math.pow(Math.abs(X[i]-X[j]), 2) + Math.pow(Math.abs(Y[i]-Y[j]), 2));
					G[i][j] = e; G[j][i] = e;
				}
			}
			
			//Prim PQ
			primPQ();
			sb.append("#").append(t).append(" ").append(Math.round(result)).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void primPQ() {
		int cnt=0, minVertex=0;
		double minWeight=0;
		result = 0f;
		
		dist[0] = 0;
		pq.clear();
		pq.offer(new Vertex(0, 0));
		
		while(!pq.isEmpty()) {
			//미방문 정점 중 최소 간선 비용 정점 선택
			Vertex cur = pq.poll();
			minVertex = cur.vertex;
			minWeight = cur.weight;
			
			//현 정점이 들른 곳이면 넘어감
			if(v[minVertex]) continue;
			
			//현 정점을 방문 정점으로 변경
			v[minVertex] = true;
			result += minWeight;
			
			//정점 방문 횟수가 N-1개면 탈출
			if(cnt++ == N-1) break;
			
			//트리에 추가된 새로운 정점 기준, 비트리 정점과의 간선 비용 비교
			for(int j=0;j<N;j++) {
				if(!v[j] && G[minVertex][j]!=0f && dist[j] > G[minVertex][j]) {
					dist[j] = G[minVertex][j];
					pq.offer(new Vertex(j, dist[j]));
				}
			}
		}	
	}
}
