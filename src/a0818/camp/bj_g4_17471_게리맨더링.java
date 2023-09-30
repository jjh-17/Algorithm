package a0818.camp;

import java.util.*;
import java.lang.Math;
import java.io.*;

public class bj_g4_17471_게리맨더링 {

	static final List<int[]> edges = new ArrayList<>(); //간선 정보 저장 리스트 - 최초  1회만
	static final int[] result = new int[2]; //각 구역의 사람 수
	static int[][] map;
	static int N, CNT, DIFF, SUM;
	
	static int[] people; //구역별 인구 수
	static int[] group; //각 구역별 소속 선거구
	static boolean[] v, v2; 
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_17471_게리맨더링.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//구역 수 입력
		N = Integer.parseInt(br.readLine());
		DIFF = Integer.MAX_VALUE;
		
		//구역별 인구 수 입력
		people = new int[N+1]; v = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) people[i] = Integer.parseInt(st.nextToken());		
		
		//map 초기화, 간선 정보 저장
		map = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int s=0;s<size;s++) {
				int j = Integer.parseInt(st.nextToken());
				map[i][j]=1; map[j][i]=1;
				edges.add(new int[] {i, j});
			}
		}
		
		//간선 모든 조합
//		for(int i=0;i<edges.size();i++) comb(0, 0, i);
		
		
		group = new int[N+1];
		for(int i=1;i<N;i++) {
			comb2(1, 0, i);
		}
		
		br.close();
		System.out.println(DIFF==Integer.MAX_VALUE ? -1 : DIFF);
	}
	
	//정점을 최대 limit개 선정, 그룹화
	static void comb2(int start, int cnt, int limit) {
		if(cnt==limit) {
			CNT = 0;
			result[0]=0; result[1]=0;
			v = new boolean[N+1];
			
			for(int i=1;i<=N;i++) {
				if(v[i]) continue;
				dfs2(i, group[i]); ++CNT;
			}
			
			if(CNT==2) DIFF = Integer.min(DIFF, Math.abs(result[0] - result[1])); 
			
			return;
		}
		
		for(int i=start;i<=N;i++) {
			group[i]=1;
			comb2(i+1, cnt+1, limit);
			group[i]=0;
		}
	}
	
	//현재 정점, 목표 선거구 번호
	static void dfs2(int vertex, int num) {
		if(CNT>=2) return;
		
		result[CNT] += people[vertex];
		v[vertex] = true;
		for(int i=1;i<=N;i++) {
			if(!v[i] && map[vertex][i]==1 && group[i]==num) dfs2(i, num);
		}
	}
	
	//간선 limit개를 선정하여 제거 후, dfs  ==> 시간초과
	static void comb(int start, int cnt, int limit) {
		if(cnt==limit) {
			CNT=0; //현재 나뉜 구역 수
			result[0]=0; result[1]=0;
			v = new boolean[N+1]; //각 정점의 지남 여부
			
			//각 정점 순회
			for(int i=1;i<=N;i++) {
				if(v[i]) continue; //이미 지난 정점은 넘어감
				dfs(i); ++CNT;
			}
			
			if(CNT==2) DIFF = Integer.min(DIFF, Math.abs(result[0] - result[1])); 
			
			return;
		}
		
		for(int i=start;i<edges.size();i++) {
			int[] edge = edges.get(i);
			map[edge[0]][edge[1]]=0; map[edge[1]][edge[0]]=0;
			comb(i+1, cnt+1, limit);
			map[edge[0]][edge[1]]=1; map[edge[1]][edge[0]]=1;
		}
	}
	
	static void dfs(int vertex) {
		if(CNT>=2) return;
		
		result[CNT] += people[vertex];
		v[vertex] = true;
		for(int i=1;i<=N;i++) {
			if(!v[i] && map[vertex][i]==1) dfs(i);
		}
	}

}
