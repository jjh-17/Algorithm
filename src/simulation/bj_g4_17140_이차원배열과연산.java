package simulation;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g4_17140_이차원배열과연산 {

	static final StringBuilder sb = new StringBuilder();
	static final int LIMIT = 100;
	static final PriorityQueue<int[]> pq = new PriorityQueue<>(	// [행 번호, 수, 수의 개수]
			(o1, o2) -> o1[0]==o2[0] ? (o1[2]==o2[2] ? o1[1]-o2[1] : o1[2]-o2[2]) : o1[0]-o2[0]);
	static final Map<Integer, Integer> map = new HashMap<>();
	static int r, c, k, AI, AJ, MAX_AI, MAX_AJ;
	static int[][] A;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_17140_이차원배열과연산.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		A = new int[3][3];
		AI = 3; AJ = 3;
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) A[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		알고리즘
		int ans = -1;
		for(int t=0;t<=100;++t) {
			if(r<=AI && c<=AJ && A[r-1][c-1]==k) {
				ans=t;
				break;
			}
			
			if(AI>=AJ)	R();
			else		C();
//			for(int [] a : A) System.out.println(Arrays.toString(a));
//			System.out.println();
		}
		
		
//		출력
		sb.append(ans);
		System.out.println(sb.toString());
		br.close();
	}

//	배열 A 모든 행 정렬
	static void R() {
		pq.clear();
		MAX_AJ = 0;
		
//		각 행을 순회하며 [행 번호, 수, 수의 개수]를 PQ에 저장
		for(int i=0;i<AI;++i) {
			map.clear();
			for(int j=0;j<AJ;++j) {
				if(A[i][j]==0) continue;
				map.put(A[i][j], map.getOrDefault(A[i][j], 0)+1);
			}
			MAX_AJ = Math.max(MAX_AJ, 2*map.keySet().size());
			
			int count=0;
			for(int key : map.keySet()) {
				if((count+=2) > LIMIT) break;
				pq.add(new int[] {i, key, map.get(key)});
			}
		}
		
//		MAX_AJ로 배열 A의 크기 재조정
		AJ = Math.min(MAX_AJ, LIMIT);
		A = new int[AI][AJ];
		
//		PQ 순회
		int ci=0;
		for(int j=0;!pq.isEmpty();j+=2) {
			int[] e = pq.poll();
			if(ci!=e[0]) { 
				ci = e[0]; 
				j=0; 
			}
			A[ci][j]=e[1];	A[ci][j+1]=e[2];
		}
	}
	
//	배열 A 모든 열 정렬
	static void C() {
		pq.clear();
		MAX_AI = 0;
		
//		각 열을 순회하며 [열 번호, 수, 수의 개수]를 PQ에 저장
		for(int j=0;j<AJ;++j) {
			map.clear();
			for(int i=0;i<AI;++i) {
				if(A[i][j]==0) continue;
				map.put(A[i][j], map.getOrDefault(A[i][j], 0)+1);
			}
			MAX_AI = Math.max(MAX_AI, 2*map.keySet().size());
			
			int count=0;
			for(int key : map.keySet()) {
				if((count+=2) > LIMIT) break;
				pq.add(new int[] {j, key, map.get(key)});
			}
		}
		
//		MAX_AI로 배열 A의 크기 재조정
		AI = Math.min(MAX_AI, LIMIT);
		A = new int[AI][AJ];
		
//		PQ 순회
		int cj=0;
		for(int i=0;!pq.isEmpty();i+=2) {
			int[] e = pq.poll();
			if(cj!=e[0]) { 
				cj = e[0]; 
				i=0; 
			}
			A[i][cj]=e[1];	A[i+1][cj]=e[2];
		}
	}
}
