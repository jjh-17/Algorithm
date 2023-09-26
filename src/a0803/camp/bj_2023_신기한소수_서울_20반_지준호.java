package a0803.camp;

import java.io.*;
import java.lang.Math;

public class bj_2023_신기한소수_서울_20반_지준호 {

	//소수 A에 대하여 소수 AB를 찾는 과정 반복
	static int[] narr;
	static int N, limit;
	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_2023_신기한소수.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		narr = new int[N+1]; limit = (int)Math.pow(10, N-1);
		
		perm(0);
		
		br.close();
		System.out.println(sb.toString());
	}
	
	private static boolean checkPrime(int num) {
		if(num==1)
			return false;
		
		for(int i=2;i<=Math.sqrt(num);i++) {
			if(num%i==0)
				return false;
		}		
		return true;
	}
	
	//중복 숫자 포함 가능
	public static void perm(int cnt) {
		if(cnt==N) {
			if((int)narr[N]/limit == 0)
				return;
			sb.append(narr[N]).append("\n");
			return;
		}
		
		for(int i=0;i<10;i++) {
			narr[cnt+1] = 10*narr[cnt] + i;
			if(checkPrime(narr[cnt+1]))
				perm(cnt+1);
		}
	}
}
