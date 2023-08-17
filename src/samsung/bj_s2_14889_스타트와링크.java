package samsung;

import java.util.*;
import java.io.*;
import java.lang.Math;

//해결 중
public class bj_s2_14889_스타트와링크 {
	
	static int N, //총 사람 수
			   sumS, sumL, //스타트팀, 링크팀 점수 합
			   LINK[], //링크팀의 선수 목록
	  		   MAP[][], //시너지
			   MIN = Integer.MAX_VALUE; //스타트 팀, 링크 팀 능력치 차이 최솟값
	static boolean V;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_s2_14889_스타트와링크.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		MAP = new int[N][N]; LINK = new int[N/2];
		sumS=0; sumL=0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				MAP[i][j] = Integer.parseInt(st.nextToken());
				sumS += MAP[i][j];
			}
			
		}
		
		dfs(0, 0);
		
		br.close();
		System.out.println(MIN);
	}
	
	
	//순회 시작 idx, 현재 스타트팀 인원 수
	static void dfs(int start, int cnt) {
		if(cnt==N/2) {
			MIN = Integer.min(MIN, Math.abs(sumS-sumL));
			return;
		}
		
		for(int i=start;i<N;i++) {
			int temp = getLinkS(i);
			sumS-=temp; sumL+=temp;
			LINK[cnt] = i;
			dfs(i+1, cnt+1);
			sumS+=temp; sumL-=temp;
		}
	}
	
	//idx번 선수와 LINK내 모든 선수의 시너지 합
	static int getLinkS(int idx) {
		int s = 0;
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<N/2;j++) s+=MAP[LINK[i]][LINK[j]];
		}
		return s;
	}
}
