package a0816;


import java.util.*;
import java.io.*;

public class bj_s2_1182_부분수열의합 {

	static int N, S, CNT;
	static int[] MAP;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s2_1182_부분수열의합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//N, S
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); S = Integer.parseInt(st.nextToken());
		CNT = 0;
		
		//MAP
		st = new StringTokenizer(br.readLine());
		MAP = new int[N];
		for(int i=0;i<N;i++) MAP[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(MAP);

		subS(0, 0, 0);
		
		br.close();
		System.out.println(S==0 ? CNT-1 : CNT);
	}
	
	//MAP은 오름차순 정렬이 되어있으므로, 특정 시점에서 sum이 S이상이면 그 이후 요소들은 고려할 필요가 없어짐
	static void subS(int idx, int considered, int sum) {
		if(considered==N) {
			if(sum==S) ++CNT;
			return;
		}
		
		subS(idx+1, considered+1, sum+MAP[idx]);
		subS(idx+1, considered+1, sum);
	}

}
