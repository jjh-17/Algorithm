package dfs_bfs;

import java.util.*;
import java.io.*;

public class bj_g5_2668_슷자고르기 {

	static final StringBuilder sb = new StringBuilder();
	static final List<Integer> 	list = new ArrayList<>(), 
								temp = new ArrayList<>();
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_2668_슷자고르기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		
//		배열 값을 입력 받으며 첫째, 둘째 줄 값이 동일한 정수를 리스트에 추가
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(i == arr[i])	list.add(i);
		}
		
//		알고리즘
//		각 정수를 순회하며 사이클을 list에 추가한다.
		for(int i=1;i<=N;i++) {
			if(!list.contains(i)) {
				temp.clear();
				dfs(i);				
			}
		}
		
//		출력
		Collections.sort(list);
		sb.append(list.size()).append("\n");
		for(int l : list) sb.append(l).append("\n");
		System.out.print(sb.toString());
		br.close();
	}

//	내 풀이
	static void dfs(int idx) {
//		temp에 들어있지 않은 값은 temp에 추가. 해당 idx의 둘째 줄 값으로 dfs 수행
		if(!temp.contains(idx)) {
			temp.add(idx);
			dfs(arr[idx]);
		} else {
//			dfs를 시작한 지점과 사이클이 시작점이 다를 수 있음
//			temp를 순회하며 사이클 시작점을 찾고, 해당 지점부터 list에 temp 값을 추가
			for(int i=0;i<temp.size();i++) {
				if(idx == temp.get(i)) {
					for(int j=i;j<temp.size();j++)
						list.add(temp.get(j));
				}
				return;
			}
		}
	}
}