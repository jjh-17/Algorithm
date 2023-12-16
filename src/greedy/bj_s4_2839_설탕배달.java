package greedy;


import java.util.*;
import java.io.*;

public class bj_s4_2839_설탕배달 {

	static int N, MIN = Integer.MAX_VALUE; //설탕 무게, 가지고 가는 최소 개수
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s4_2839_설탕배달.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int cnt5=0;
		while(N-5*cnt5 >= 0) {
			if((N-5*cnt5)%3==0) MIN = cnt5 + (N-5*cnt5)/3;
			cnt5++;
		}
		
		if(MIN == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(MIN);
	}

}
