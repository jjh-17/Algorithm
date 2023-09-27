package a0926.camp;

import java.util.*;
import java.io.*;

//발표
public class bj_g5_16987_계란으로계란치기 {

	static final StringBuilder sb = new StringBuilder();
	static int N, ANS;
	static int S[], W[];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_16987_계란으로계란치기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		S = new int[N]; W = new int[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}
		
//		알고리즘
		ANS=0;
		dfs(0, 0);
		
//		출력
		sb.append(ANS);
		System.out.println(sb.toString());
		br.close();
	}

//	dfs(현재 든 계란 번호, 현재 깬 계란의 개수)
	static void dfs(int idx, int broken) {
//		idx번째 계란을 제외한 모든 계란이 깨졌는지 여부 확인 용
		boolean flag=false;
		
//		N개 계란을 모두 사용했다면 종료
		if(idx==N) {
			ANS = Integer.max(ANS, broken);
			return;
		}
		
//		현재 든 계란의 내구도가 0 이하라면 다음 계란으로 넘어간다.
		if(S[idx]<=0) {
			dfs(idx+1, broken);
			return;
		}
		
//		N개 계란 모두에 깨뜨리기를 시도한다.
		for(int i=0;i<N;i++) {
//			자기 자신을 부딪히는 경우나 깨진 계란을 향해 부딪히는 경우는 넘어간다.
			if(i==idx || S[i]<=0) continue;
			
//			idx번째 계란을 제외한 모든 계란이 깨진 상태가 아님
			flag = true;
			
//			부딪힌 두 계란의 내구도를 무게만큼 감소시킨다.
			S[i]-=W[idx]; S[idx]-=W[i];
			
//			두 계란의 내구도에 따라 깨진 계란의 개수를 달리하여 dfs
			if(S[i]<=0 && S[idx]<=0) dfs(idx+1, broken+2);
			else if(S[i]<=0 || S[idx]<=0) dfs(idx+1, broken+1);
			else dfs(idx+1, broken);
			
//			부딪힌 두 계란의 내구도를 복구한다.
			S[i]+=W[idx]; S[idx]+=W[i];
		}
		
//		idx 번째 계란 이외의 모든 계란이 깨진 경우 ANS 최신화 
//			==> 남은 계란이 없으니 더이상의 dfs는 의미가 없음
		if(!flag) ANS = Integer.max(ANS, broken);
	}
}