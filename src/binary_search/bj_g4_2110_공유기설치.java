package binary_search;

import java.util.*;
import java.io.*;

public class bj_g4_2110_공유기설치 {

	static int N, C, ans;
	static int[] loc;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_2110_공유기설치.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		loc = new int[N];
		for(int i=0;i<N;i++)
			loc[i] = Integer.parseInt(br.readLine());
		
//		알고리즘
//		이진 탐색을 위해 정렬
		Arrays.sort(loc);
		solution();
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
	static void solution() {
//		최소, 최대 길이
		int minL=1;
		int maxL=loc[N-1]-loc[0]+1;
		
//		반복 개시
		while(minL<maxL) {
			int mid = (minL+maxL)/2;
			
//			설치된 공유기 개수가 C개 미만이면 사이 거리 증가, 이상이면 감소
			if(getCnt(mid)<C)	maxL=mid;
			else				minL=mid+1;
		}
		
		ans = minL-1;
	}

//	공유기 간 길이가 L일 때 설치 가능한 공유기 개수 반환
	static int getCnt(int L) {
//		첫번째 집에는 반드시 공유기가 설치되어 있음
		int cnt=1;
		int lastIdx=0;
		
//		각 집 순회
		for(int i=1;i<N;i++) {			
//			마지막으로 공유기를 설치한 집과의 거리가 L 이상인 경우
			if(loc[i]-loc[lastIdx]>=L) {
				++cnt;
				lastIdx=i;
			}
		}
		
		return cnt;
	}
	
}
