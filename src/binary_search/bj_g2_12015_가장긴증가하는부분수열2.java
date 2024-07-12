package binary_search;

import java.util.*;
import java.io.*;

public class bj_g2_12015_가장긴증가하는부분수열2 {

	static int N;
	static int[] arr;
	static final List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g2_12015_가장긴증가하는부분수열2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0;i<N;i++)	{
			arr[i] = Integer.parseInt(st.nextToken());
		}
			
//		알고리즘
		solution();
		
//		출력
		System.out.println(list.size());
		br.close();
	}
	
	static void solution() {
		list.add(arr[0]);
		for(int i=1;i<N;i++) {
			if(list.get(list.size() - 1) < arr[i])	list.add(arr[i]);
			else									list.set(lower_bound(arr[i]), arr[i]);
		}
	}
	
//	infos에서 num이 target 이하인 첫번째 idx 반환
	static int lower_bound(int target) {
		int L = 0;
		int R = list.size();
		
		while(L < R) {
			int mid = (L + R) / 2;
			if(list.get(mid) < target)	L = mid + 1;
			else						R = mid;
		}
		
		return R;
	}
}
