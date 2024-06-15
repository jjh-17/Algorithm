package bruteForce;

import java.util.*;
import java.io.*;

public class bj_g4_1027_고층건물 {

	static int N, ans;
	static int[] H;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g4_1027_고층건물.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		H = new int[N];
		for(int i=0;i<N;i++)	H[i] = Integer.parseInt(st.nextToken());
		
//		알고리즘
		solution();
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
	static void solution() {
		final int[] cnts = new int[N];
		
		for(int i=0;i<N;i++) {
			for(int j=i+1;j<N;j++) {
				double grad = (H[j] - H[i]) / (double)(j - i);
//				System.out.println(i + " / " + j + " / " + grad);
				if(isVisible(i, j, grad)) {
					++cnts[i];
					++cnts[j];
				}
			}
//			System.out.println(Arrays.toString(cnts));
		}
		
		for(int cnt : cnts)
			ans = Integer.max(ans, cnt);
	}
	
	static boolean isVisible(int start, int target, double grad) {
		for(int i=start+1;i<target;i++) {
			double h = H[start] + grad * (i-start);
			if((double)H[i] >= h)	return false;
		}
		return true;
	}
}
