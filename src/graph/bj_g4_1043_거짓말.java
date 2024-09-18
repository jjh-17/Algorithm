package graph;

import java.util.*;
import java.io.*;

// union - find
public class bj_g4_1043_거짓말 {

	static int ans = 0;
	static int N, M;				// 사람의 수, 파티의 수
	static boolean[] knowTruth;		// 각 사람의 진실 앎 여부
	static List<Integer>[] party;	// 각 파티에 참여하는 사람
	
	static int[] parent;			// 각 사람의 parent
	static int[] rank;				// 각 사람의 rank
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_거짓말.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		knowTruth = new boolean[N+1];
		int cnt = Integer.parseInt(st.nextToken());
		for (int i=0;i<cnt;i++) knowTruth[Integer.parseInt(st.nextToken())] = true;

		party = new ArrayList[M+1];
		for(int i=1;i<=M;i++) {
			party[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int J = Integer.parseInt(st.nextToken());
			for(int j=0;j<J;j++) party[i].add(Integer.parseInt(st.nextToken()));
		}		
		
		// 알고리즘
		solution();
		
		// 출력
		System.out.println(ans);
	}

	static void solution() {
		parent = new int[N+1];
		rank = new int[N+1];
		for(int i=1;i<=N;i++) {
			parent[i] = i;
			rank[i] = 1;
		}
		
		// 파티에 있던 사람 union
		for(int i=1;i<=M;i++) {
			for(int j=0;j<party[i].size()-1;j++) {
				union(party[i].get(j), party[i].get(j+1));
			}
		}
		
		// 진실을 아는 사람의 parent는 진실을 앎
		for(int i=1;i<=N;i++) {
			if(knowTruth[i]) knowTruth[find(i)] = true;
		}
		
		// 각 파티 참석자 순회
		for(int m=1;m<=M;m++) {
			boolean flag = true;
			for(int p : party[m]) {
				if(knowTruth[find(p)]) {
					flag = false;
					break;
				}
			}
			if(flag) ans++;
		}
		
	}
	
	static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if(rootX == rootY)	return;
		
		if(rank[rootX] > rank[rootY])		parent[rootY] = rootX;
		else if(rank[rootX] < rank[rootY])	parent[rootX] = rootY;
		else {
			parent[rootY] = rootX;
			rank[rootX]++;
		}
	}

	static int find(int x) {
		if(parent[x] == x)	return x;
		return parent[x] = find(parent[x]);
	}
	
}
