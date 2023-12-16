package simulation;

import java.util.*;
import java.io.*;

public class swea_1208_Flatten {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_1208_Flatten.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		int diff;
		int[] arr = new int[100];
		
		int T = 10;
		for(int t=1;t<=T;t++) {
			int dump = Integer.parseInt(br.readLine());
			diff = Integer.MAX_VALUE;
			
			//arr 초기화
			String[] sarr = br.readLine().split(" ");
			for(int i=0;i<100;i++)
				arr[i] = Integer.parseInt(sarr[i]);
			
			
			//dump 횟수만큼 순회
			for(int i=0;i<dump;i++) {
				Arrays.sort(arr);
				diff = arr[99] - arr[0];
				if(diff==0 || diff==1)
					break;
				
				arr[99]--;
				arr[0]++;
			}
			
			sb.append("#").append(t).append(" ").append(diff).append("\n");
		}
		System.out.println(sb.toString());
	}

}
