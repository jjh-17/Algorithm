package a0803.camp;

import java.util.*;
import java.io.*;

public class bj_12891_DNA비밀번호_서울_20반_지준호 {

	static int S, P;
	static char[] arr;
	static int[] required = new int[4], current = new int[4];
	static Map<Character, Integer> dna = new HashMap<Character, Integer>() {{
		put('A', 0);
		put('C', 1);
		put('G', 2);
		put('T', 3);
	}};
	
	
	public static boolean check() {
		boolean flag = true;
		
		for(int i=0;i<4;i++) {
			if(required[i] > current[i]) {
				flag = false;
				break;
			}	
		}
		return flag;
	}
	
	public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res\\input_bj_12891_DNA비밀번호.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//기초 값 초기화
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		arr = br.readLine().toCharArray();
		int answer =0;
		
		//dna 최소 조건 초기화
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++)
			required[i] = Integer.parseInt(st.nextToken());
		
		//0~P 구간 내 충족 여부 확인
		for(int i=0;i<P;i++) {
			current[dna.get(arr[i])]+=1;
		}
		answer += check()?1:0;
		
		
		//부분 문자열 순회 - 슬라이딩
		for(int i=1;i<=S-P;i++) {
			current[dna.get(arr[i-1])] -= 1;
			current[dna.get(arr[i+P-1])] += 1;
			
			answer += check()?1:0;
		}
		
		System.out.println(answer);
	}

}
