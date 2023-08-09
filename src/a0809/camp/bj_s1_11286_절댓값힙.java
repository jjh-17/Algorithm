package a0809.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_s1_11286_절댓값힙 {

	static final StringBuilder sb = new StringBuilder();
	static final List<Integer> list = new ArrayList<>();
	static int N;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_11286_절댓값힙.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			int temp = Integer.parseInt(br.readLine());
			System.out.println(temp);
			
			if(temp!=0) list.add(temp); //0 - 리스트에 값 추가
			else { //0 아님 - 리스트에서 절댓값 가장 작은 값 출력 및 제거
				if(list.isEmpty()) sb.append(0).append("\n"); //리스트가 빈 경우 0 출력
				else {
					int min = Integer.MAX_VALUE;
					int idx=0;
					for(int l=0;l<list.size();l++) {
						if(Math.abs(min) > Math.abs(list.get(l))) {
							min = list.get(l);
							idx = l;
						}
						list.remove(idx);
						sb.append(min).append("\n");
					}
				}
			}
		}
		
		System.out.println();
		br.close();
		System.out.println(sb.toString());
	}

}
