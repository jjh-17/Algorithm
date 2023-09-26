package a0830.camp;

import java.util.*;
import java.io.*;

public class bj_s2_10971_외판원순회2 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int[] order;  //순서
	static int[][] cost; //비용
	static boolean[] v;  //각 도시 방문 여부
	static int COST_MIN; //최소 비용
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s2_10971_외판원순회2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//입력
		N = Integer.parseInt(br.readLine());
	
		cost = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) cost[i][j] = Integer.parseInt(st.nextToken());
		}
		

		COST_MIN = Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			order = new int[N+1];
			v = new boolean[N];				
			order[0] = i; v[i] = true;
			
			perm(1, i, 0);
		}
		
		sb.append(COST_MIN);
		System.out.println(sb.toString());
		br.close();
	}
	
	//순열
	//현재 방문 횟수, 이전 방문 도시, 지금까지의 비용
	static void perm(int cnt, int preIdx, int sum) {
		//현재 총 비용이 최솟값 보다 크다면 종료
		if(sum>COST_MIN) return;
		
		//현재 방문 횟수가 N회일 경우
		if(cnt==N) {
			if(cost[preIdx][order[0]]!=0) {
				COST_MIN = Integer.min(COST_MIN, sum+cost[preIdx][order[0]]);
			}
			return;
		}
		
		for(int i=0;i<N;i++) {
			//이미 들른 곳이거나, 도달할 수 없는 곳인 경우
			if(v[i] || cost[preIdx][i]==0 || sum+cost[preIdx][i]>COST_MIN) continue;
			v[i] = true; order[cnt] = i;
			perm(cnt+1, i, sum+cost[preIdx][i]);
			v[i] = false;	
		}
	}

}
