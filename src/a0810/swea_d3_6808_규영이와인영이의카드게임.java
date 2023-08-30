package a0810;

import java.util.*;
import java.io.*;

public class swea_d3_6808_규영이와인영이의카드게임 {

	static final StringBuilder sb = new StringBuilder();
	static final int N = 18;
	static int WIN, LOSE; //규영이가 이긴 횟수, 규영이가 진 횟수, 
	static int[] arr1 = new int[N/2], arr2 = new int[N/2]; //규영이가 뽑은 숫자, 인영이가 뽑은 숫자
	static boolean[] v; //각 숫자의 사용 여부(숫자=인덱스+1)
	
	//추가
	static final int border = 19*9/2;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_6808_규영이와인영이의카드게임.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//테스트 케이스 수 입력
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//출력문 정답 이전까지 append
			sb.append("#").append(t).append(" ");
			
			//초기화
			WIN=0; LOSE=0; //규영이가 이긴 횟수, 진 횟수
			v = new boolean[N]; //각 카드의 사용 여부
			
			
			//첫번째 9개 정수 입력 ==> 숫자를 사용한 것으로 변경
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N/2;i++) {
				arr1[i] = Integer.parseInt(st.nextToken());
				v[arr1[i]-1] = true;
			}
			
			//순열
//			perm(0);
			perm2(0, 0, 0);
			sb.append(WIN).append(" ").append(LOSE).append("\n");
		}
		
		
		br.close();
		System.out.println(sb.toString());
	}
	
	//N/2 길이의 순열
	public static void perm(int cnt) {
		if(cnt==N/2) {
			setWinLose();
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(v[i]) continue;
			v[i] = true;
			arr2[cnt] = i+1;
			perm(cnt+1);
			v[i] = false;
		}
	}
	
	public static void perm2(int cnt, int sum1, int sum2) {
		if(cnt==N/2) {
			if(sum1>sum2) ++WIN;
			else if(sum1<sum2) ++LOSE;
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(v[i]) continue;
			v[i] = true;
			if(arr1[cnt] > i+1) perm2(cnt+1, sum1+arr1[cnt]+(i+1), sum2);
			else if(arr1[cnt] < i+1) perm2(cnt+1, sum1, sum2+arr1[cnt]+(i+1));
			else perm2(cnt+1, sum1, sum2);
			v[i] = false;
		}
	}
	
	//규영이의 이긴 횟수, 진 횟수 추가
	public static void setWinLose() {
		int sum1=0, sum2=0;
		
		for(int i=0;i<N/2;i++) {
			if(arr1[i] > arr2[i]) sum1+=(arr1[i] + arr2[i]);
			else if(arr1[i] < arr2[i]) sum2+=(arr1[i] + arr2[i]);
		}
		
		if(sum1>sum2) ++WIN;
		else if(sum1<sum2) ++LOSE;
	}
}
