package a0801.camp;

import java.util.*;
import java.lang.Math;
import java.io.*;

public class bj_스위치켜고끄기_서울_20반_지준호 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_스위치켜고끄기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		//스위치 개수 입력
		int N = Integer.parseInt(br.readLine());
		
		//스위치 초기화
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		//학생 수
		int cntS = Integer.parseInt(br.readLine());
		
		//학생에게 할당된 수에 따라 스위치 정보 변경
		for(int i=0;i<cntS;i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
			
			if(g==1) { //남학생 ==> n의 배수 스위치 반대
				for(int k=n;k<=N;k+=n)
					arr[k-1] = (arr[k-1]==0 ? 1 : 0);
			} else { //여학생 ==> 번호 중심, 좌우가 대칭/가장 많은 스위치 포함 구간 내 상태 변경
				arr[n-1] = arr[n-1]==0 ? 1 : 0;
				int left = n-2, right = n;
				while(true) {
					//범위 밖 이거나 동일 값이 아닌 경우 탈출
					if(left<0 || right>=N || arr[left]!=arr[right])
						break;
					
					arr[left] = arr[right] = (arr[left]+1)%2;
					--left; ++right;
				}
			}
		}
		
		int cnt=1;
		for(int a : arr) {
			sb.append(a).append(" ");
			if(cnt%20==0) sb.append("\n");
			++cnt;
		}
		System.out.print(sb.toString());
	}
}
