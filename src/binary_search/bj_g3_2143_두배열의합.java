package binary_search;

import java.util.*;
import java.io.*;

public class bj_g3_2143_두배열의합 {

	static long ans;
	static int T, N, M;
	static int[] A, B, sumA, sumB;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g3_2143_두배열의합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		T = Integer.parseInt(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		A = new int[N+1];	sumA = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)	A[i] = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		B = new int[M+1];	sumB = new int[M+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++)	B[i] = Integer.parseInt(st.nextToken());
		
		int idx = 0;
		sumA = new int[N * (N+1) / 2];
		for(int i=0;i<N;i++) {
			int temp = 0;
			for(int j=i;j<N;j++) {
				temp += A[j];
				sumA[idx++] = temp;
			}
		}
		
		idx = 0;
		sumB = new int[M * (M+1) / 2];
		for(int i=0;i<M;i++) {
			int temp = 0;
			for(int j=i;j<M;j++) {
				temp += B[j];
				sumB[idx++] = temp;
			}
		}
		
//		알고리즘
		solution();
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
	static void solution() {
//		1. 부분 합 정렬
		Arrays.sort(sumA);
		Arrays.sort(sumB);
		
//		2. sumA, sumB를 순회
		for(int i=0;i<sumA.length;) {
			int a = sumA[i];
			int aL = lower_bound(sumA, 0, a);
			int bL = lower_bound(sumB, 0, T-a);
			long cntA = upper_bound(sumA, aL, a) - aL;
			long cntB = upper_bound(sumB, bL, T-a) - bL;
			
			ans += cntA * cntB;
			i += cntA;
		}
	}
	
	static int upper_bound(int[] arr, int L, int target) {
		int R = arr.length;
		
		while(L < R) {
			int mid = (L + R) / 2;
			if(arr[mid] <= target)		L = mid + 1;
			else if(arr[mid] > target)	R = mid;
		}
		
		return R;
	}
	
	static int lower_bound(int[] arr, int L, int target) {
		int R = arr.length; 
		
		while(L < R) {
			int mid = (L + R) / 2;
			if(arr[mid] < target)	L = mid + 1;
			else					R = mid;
		}
		
		return R;
	}
}
